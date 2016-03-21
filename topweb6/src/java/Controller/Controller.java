/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import Events.*;
import Model.DataControl;

/**
 *
 * @author Skeleton
 */
public class Controller extends HttpServlet {

    Connection conn = null;
    ServletContext context = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("********* Controller Servlet Initialization **********");
        context = config.getServletContext();
        
        try {
            Class.forName(context.getInitParameter(Cons.DRIVER)).newInstance();
            conn = DriverManager.getConnection(
                    context.getInitParameter(Cons.BASEDATOS),
                    context.getInitParameter(Cons.USERDB),
                    context.getInitParameter(Cons.PASSDB));
            context.setAttribute(Cons.DATA, new DataControl(conn));
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Map<String, Event> events = new HashMap<String, Event>();

        //creacion de Instancia, de la Clase que maneja el evento     
        Event event = new InsertCommentEvent();
        events.put(((InsertCommentEvent) event).NAME, event);
        
        event = new DeleteCommentEvent();
        events.put(((DeleteCommentEvent) event).NAME, event);
        
        context.setAttribute(Cons.EVENT_NAME, events);
        context.setAttribute(Cons.INIT, true);

    }

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        //get the event requested
        String nomEvento = request.getParameter(Cons.EVENT_NAME);          
        
        //map it to the events registered
        Map events = (Map)getServletContext().getAttribute(Cons.EVENT_NAME); 
        if (events.containsKey(nomEvento)) {   
            // Si se encuentra el evento se procesa 
            System.out.println("Procesando evento " + nomEvento);             
            // Se recupera la clase controladora 
            Event evento = (Event)events.get(nomEvento);                        
            // Se delega el evento del proceso 
            String path = evento.handleRequest(getServletContext(), request);              
            // Se redirige la petici�n 
            System.out.println("Evento procesado, redirigiendo a " + path);
            request.getRequestDispatcher(path).forward(request, response);             
            System.out.println("Evento " + nomEvento + " procesado"); 
        } else {    
            //Si no se encuentra el evento se lanza excepci�n 
            String msg = "Evento no encontrado " + nomEvento; 
            System.out.println(msg); 
            throw new ServletException(msg); 
            //response.sendRedirect(request.getContextPath().toString()+"/Error.jsp");
        } 
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Parameters:");
        Cons.printParams(request);
        //
        if(request.getParameter(Cons.EVENT_NAME)!= null){
            processRequest(request, response);
        }    
        else if(request.getParameter("rtn") == null)
            response.sendRedirect(request.getContextPath().toString()+"/form.jsp");
        else
            response.sendRedirect(request.getContextPath().toString()+request.getParameter("rtn"));
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
