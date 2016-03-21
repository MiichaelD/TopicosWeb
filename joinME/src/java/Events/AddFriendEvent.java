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
public class AddFriendEvent implements Event{

    
    public String NAME=Cons.ADDFRIENDEVENT_NAME;
    @Override
    public String handleRequest(ServletContext ctx, HttpServletRequest request) throws ServletException {
        
        String id       = request.getParameter(Cons.USERID);
        if(request.getSession().getAttribute(Cons.USERID) == null )
            return "login.jsp";
	String usID     = request.getSession().getAttribute(Cons.USERID).toString();
        DataControl dc = (DataControl) ctx.getAttribute(Cons.DATA);
        
        HttpSession ses = request.getSession();
        System.out.println("Received: id='"+id+"', myid='"+usID+"'");
         
        if(id.equals(usID))
            return "me.jsp?"+Cons.USERID+"="+usID;
        
        if(dc.userExists(id)){
            boolean friend = dc.userIsMyFriend(usID, id);
            if(!friend)
                dc.addRelation(usID, id);
            
            ses.setAttribute(Cons.ISFRIEND, true);

            List<String[]> cmms = dc.getMyComments(id);
            if(cmms == null){
                ses.setAttribute(Cons.ERROR,"The user hasn't shared anything");
                return "me.jsp?"+Cons.ERROR+"=1";
            }
            ses.setAttribute(Cons.COMMENTS,cmms);
            
            return "me.jsp?"+Cons.USERID+"="+id;
        }
        ses.setAttribute(Cons.ERROR,"The user doesn't exist");
        return "me.jsp?"+Cons.ERROR+"=1";
    }
    
}
