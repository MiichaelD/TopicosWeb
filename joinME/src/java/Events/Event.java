/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Skeleton
 */
public interface Event {
    
    public String NAME=Controller.Cons.EVENT_NAME;
    public String handleRequest(ServletContext ctx, HttpServletRequest req) 
                              throws IOException, ServletException; 
}
