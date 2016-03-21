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
import java.util.*;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Skeleton
 */
public class OpenFriendEvent implements Event{

    
    public String NAME=Cons.OPENFRIENDEVENT_NAME;
    @Override
    public String handleRequest(ServletContext ctx, HttpServletRequest request) throws ServletException {
        
        String id       = request.getParameter(Cons.USERID);
        if(request.getSession().getAttribute(Cons.USERID) == null )
            return "login.jsp";
	String usID = request.getSession().getAttribute(Cons.USERID).toString();
        DataControl dc = (DataControl) ctx.getAttribute(Cons.DATA);
        
        HttpSession ses = request.getSession();
        
        System.out.println("Received: id='"+id+"', myid='"+usID+"'");
        
        if(id != null && dc.userExists(id)){
            boolean friend = dc.userIsMyFriend(usID, id);
            ses.setAttribute(Cons.ISFRIEND, friend);

            //1 =id, 2 =name, 3=email
            String[] usdata = dc.getUserData(id);
            ses.setAttribute(Cons.USERDATA,usdata);
            
            List<String[]> cmms = dc.getMyComments(id);
            if(cmms == null){
                ses.setAttribute(Cons.ERROR,"The user hasn't share anything");
                return "me.jsp?"+Cons.ERROR+"=1";
            }
            ses.setAttribute(Cons.COMMENTS,cmms);
            
            

            //session stores user ID
            System.out.println("[openFriend:] isfriend?"+friend);
            
            return "me.jsp?"+Cons.USERID+"="+id;
        }
        ses.setAttribute(Cons.ERROR,"The user doesn't exist");
        return "me.jsp?"+Cons.ERROR+"=1";
    }
    
}
