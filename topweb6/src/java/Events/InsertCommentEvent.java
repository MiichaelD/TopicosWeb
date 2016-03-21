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
/**
 *
 * @author Skeleton
 */
public class InsertCommentEvent implements Event{

    
    public String NAME=Cons.INSERTCOMMENTEVENT_NAME;
    @Override
    public String handleRequest(ServletContext ctx, HttpServletRequest request) throws ServletException {
        
        char a          = request.getParameter("accion").charAt(0);
	String tipos_id = request.getParameter("f_tipos_id");
	String nombre	= request.getParameter("f_nombre");
	String correo	= request.getParameter("f_correo");
	String mensaje	= request.getParameter("f_mensaje");
	String id	= request.getParameter("f_id");
	
        DataControl dc = (DataControl)ctx.getAttribute(Cons.DATA);
        switch(a){
            case 'I':
                dc.insertComment(tipos_id, nombre, correo, mensaje);
                break;
            case 'U':
                dc.updateComment(id, tipos_id, nombre, correo, mensaje);
                break;
        }
	return ("show.jsp"); 
    }
    
}
