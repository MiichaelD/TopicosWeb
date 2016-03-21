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
import javax.servlet.http.HttpSession;
/**
 *
 * @author Skeleton
 */
public class InsertCommentEvent implements Event{

    
    public String NAME=Cons.INSERTCOMMENTEVENT_NAME;
    @Override
    public String handleRequest(ServletContext ctx, HttpServletRequest request) throws ServletException {
        
        String comment   =   request.getParameter(Cons.COMMENTS);
        if(request.getSession().getAttribute(Cons.USERID) == null )
            return "login.jsp";
	String usID      =   request.getSession().getAttribute(Cons.USERID).toString();
        
        
        if(comment == null || comment.isEmpty())
            return "ctrl?"+Cons.EVENT_NAME+"="+Cons.LOADINDEXEVENT_NAME;
        
        DataControl dc = (DataControl) ctx.getAttribute(Cons.DATA);
        dc.insertComment(usID, comment);
        
        
        return "ctrl?"+Cons.EVENT_NAME+"="+Cons.LOADINDEXEVENT_NAME;
    }
    
}
