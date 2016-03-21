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
        System.out.println("Delete Comment Event Parameters:");
        Cons.printParams(request);
        
	String id	= request.getParameter("f_id");
	
        ((DataControl)ctx.getAttribute(Cons.DATA)).deleteComment(id);
        
	return ("show.jsp"); 
    }
    
}
