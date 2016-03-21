/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import Controller.*;
import Model.DataControl;
import java.util.List;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Skeleton
 */
public class SearchFriendEvent implements Event{

    
    public String NAME=Cons.SEARCHFRIENDEVENT_NAME;
    @Override
    public String handleRequest(ServletContext ctx, HttpServletRequest request) throws ServletException {
        
        if(request.getSession().getAttribute(Cons.USERID) == null )
            return "login.jsp";
        
        String name     = request.getParameter(Cons.SEARCHQUERY);
        HttpSession ses = request.getSession();
        
        Integer id;
        
        System.out.println("Received: querySearch='"+name+"'");
        if(name == null || name.length()==0){
            ses.setAttribute(Cons.ERROR,"Please provide a non-empty query");
            return ("index.jsp?"+Cons.ERROR+"=1"); 
        }
        
        DataControl dc = (DataControl)ctx.getAttribute(Cons.DATA);
        List<String[]> fl = dc.searchFriend("%"+name+"%");
        if(fl == null){
            ses.setAttribute(Cons.ERROR,"no friends found :C");
            return "people.jsp?"+Cons.ERROR+"=1";
        }
        else{
            ses.setAttribute(Cons.SEARCHQUERY,fl);
            return "people.jsp";
        }
    }
    
}
