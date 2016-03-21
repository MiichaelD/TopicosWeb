/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Controller.Cons;
import Model.DataControl;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Skeleton
 */
public class DeleteCommentEvent implements Event{
    
    public String NAME=Cons.DELETECOMMENTEVENT_NAME;

    @Override
    public String handleRequest(ServletContext ctx, HttpServletRequest request)throws ServletException {
        
        if(request.getSession().getAttribute(Cons.USERID) == null )
            return "login.jsp";
        
        String usID     = request.getSession().getAttribute(Cons.USERID).toString();
	String id       = request.getParameter(Cons.COMMENTS);
        DataControl dc  = (DataControl) ctx.getAttribute(Cons.DATA);
        
        System.out.println("[DELETECOMMENTEVENT]: received: id:"+id+", usID:"+usID);
        
        dc.deleteComment(id,usID);
        
        
        return "ctrl?"+Cons.EVENT_NAME+"="+Cons.LOADINDEXEVENT_NAME;
    }
    
}
