/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import javax.ejb.Stateless;

/**
 *
 * @author Skeleton
 */
@Stateless
public class SessionBean1 {

    public String sayHello(String name) {
        return "Hello "+name+"!!";
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
