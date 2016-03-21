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
public class LoginEvent implements Event{

    
    public String NAME=Cons.LOGINEVENT_NAME;
    @Override
    public String handleRequest(ServletContext ctx, HttpServletRequest request) throws ServletException {
        
        String un       = request.getParameter(Cons.USERNAME);
	String pass	= request.getParameter(Cons.PASS);
        Object[] token;
        
        HttpSession ses = request.getSession();
        
        System.out.println("Received: usn='"+un+"', pass='"+pass+"'");
        if(un == null || un.length()==0){
            ses.setAttribute(Cons.ERROR,"Please provide username");
            return ("login.jsp?"+Cons.ERROR+"=1"); 
        }
        
        if(pass == null || pass.length()==0){
            ses.setAttribute(Cons.ERROR,"Password can't be blank");
            return ("login.jsp?"+Cons.ERROR+"=1&"+Cons.USERNAME+"="+un); 
        }
        
        token = ((DataControl)ctx.getAttribute(Cons.DATA)).login(un, pass+"_8");
        if(token == null){
            ses.setAttribute(Cons.ERROR,"Username and Password are incorrect");
            return ("login.jsp?"+Cons.ERROR+"=1&"+Cons.USERNAME+"="+un); 
        }
        //session stores user ID
        ses.setAttribute(Cons.USERID, token[0]);
        ses.setAttribute(Cons.USERNAME, token[1]);
        System.out.println("LogedIN: "+request.getSession().getAttribute(Cons.USERID)+" - "+token[1]);
        return "index.jsp";
    }
    
}
