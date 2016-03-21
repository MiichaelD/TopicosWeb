/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Skeleton
 */
public class SessionBean1Test {
    
    public SessionBean1Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of sayHello method, of class SessionBean1.
     */
    @Test
    public void testSayHello() throws Exception {
        System.out.println("sayHello");
        String name = "Michael";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        SessionBean1 instance = (SessionBean1)container.getContext().lookup("java:global/classes/SessionBean1");
        String expResult = "Hello Michael";
        String result = instance.sayHello(name);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
