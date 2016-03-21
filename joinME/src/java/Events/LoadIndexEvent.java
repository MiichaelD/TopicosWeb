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
public class LoadIndexEvent implements Event{

    
    public String NAME=Cons.LOADINDEXEVENT_NAME;
    @Override
    public String handleRequest(ServletContext ctx, HttpServletRequest request) throws ServletException {
        
        HttpSession ses = request.getSession();
        
        if(ses.getAttribute(Cons.USERID) == null)
            return "login.jsp";
        String usID    =  ses.getAttribute(Cons.USERID).toString();
        DataControl dc = (DataControl) ctx.getAttribute(Cons.DATA);
        
        List<String[]> auxList;
        
        {
            auxList = dc.getMyFriends(usID);
            if(auxList != null)
                ses.setAttribute(Cons.FRIENDLIST, auxList);

            //1 =id, 2 =name, 3=email
            String[] usdata = dc.getUserData(usID);
            if (usdata != null)
                ses.setAttribute(Cons.USERDATA,usdata);
            
            //c.id, comment, poster_id, u.username, postdate
            auxList = dc.getMyFriendsComments(usID);
            if(auxList != null){
                ses.setAttribute(Cons.COMMENTS,auxList);
            }
            
            return "index.jsp";
        }
    }
    
}
