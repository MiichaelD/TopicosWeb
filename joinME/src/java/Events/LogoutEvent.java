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
/**
 *
 * @author Skeleton
 */
public class LogoutEvent implements Event{

    
    public String NAME=Cons.LOGOUTEVENT_NAME;
    @Override
    public String handleRequest(ServletContext ctx, HttpServletRequest request) throws ServletException {
        HttpSession ses = request.getSession();
       
        ses.removeAttribute(Cons.USERID);
        ses.removeAttribute(Cons.USERNAME);
        ses.invalidate();

        return "login.jsp";
    }
    
}
