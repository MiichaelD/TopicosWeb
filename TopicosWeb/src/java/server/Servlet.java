/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//http://www.youtube.com/watch?v=pwBNmAhtqk8
package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Scanner;

/**
 *
 * @author Skeleton
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    @EJB SessionBean1 bean;
    private PrintWriter out;
    private Cookie cook=null;
    final int TTL_COOKIE = 60*60*24;
    Cookie[] cooks=null;
    private boolean Debug = false;
    Scanner scan=null;
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private void printHeader()throws IOException{
         out.println("<html>");
         out.println("<head>");
         out.println("<title>Servlet Topicos </title>");  
         out.println("</head>");
         out.println("<body>");
        
    }
    
    private void printBottom()throws IOException{
        out.println("</body>");
        out.println("</html>");
    }
    
    private void showLogIn(HttpServletRequest request){
        out.println("<form method=\"POST\" action=\""+request.getContextPath()+"/Servlet\">");
        out.println("<fieldset>");
	out.println("   <legend>Login</legend>");
        out.println("<p>\n <label> Username: <input type='text' name= 'username' /></label>\n</p>");
        out.println("<p>\n <label> Password: <input type='password' name= 'pass' /></label>\n</p>");
    	out.println("<p>\n<input type=\"submit\" name= \"login\" />\n</p>");
        out.println("</fieldset>\n</form>");
    }
    
    private void showQueryForm(HttpServletRequest request){
        out.println("<fieldset>");
        out.println("   <legend>Query System</legend>");
        out.println("<form method=\"POST\" action=\""+request.getContextPath()+"/Servlet\" id=\"queryForm\">");
        out.println("<pre><label> Day: <select name='day' form=\"queryForm\">"
                +"<option value='0'>Sunday</option>"
                +"<option value='1'>Monday</option>"
                +"<option value='2'>Tuesday</option>"
                +"<option value='3'>Wednesday</option>"
                +"<option value='4'>Thursday</option>"
                +"<option value='5'>Friday</option>"
                +"<option value='6'>Saturday</option>"
                +"</select></label>\n</pre>");
        out.println("<pre><label> Temperature: <input type='checkbox' name= 'temp' value='1' > </label>\n</pre>");
        out.println("<pre><label> Humidity:    <input type='checkbox' name= 'humi' value='1' > </label>\n</pre>");
        out.println("<pre><label> Wind Speed:  <input type='checkbox' name= 'wind' value='1' > </label>\n</pre>");
    	out.println("<p>\n<input type=\"submit\" name= \"Request\" />\n</p>");
        out.println("</form>");
        out.println("\n</fieldset>");
    }
    
    private void showLogOut(HttpServletRequest request){
        out.print("<h2>"+bean.sayHello((String)request.getSession().getAttribute("username"))+"</h2>");
        out.println("<a href='"+request.getContextPath()+"/Servlet?logout=1'>Logout</a>");
    }
    
    private void logout(HttpServletRequest request){
        request.getSession().removeAttribute("sesInit");
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("pass");
    }
    
    private String getCookieValue(Cookie[] cookies, String cookieName) {
        if(cook != null)
            return cook.getValue();
        for(Cookie k : cookies) {
        if (cookieName.equals(k.getName()))
            return(k.getValue());
        }
        return(null);
  }
    
    private void printCookies(HttpServletRequest request){
        cooks = request.getCookies();
        if(cooks.length > 0)
        for(Cookie k : cooks){
            out.println(k.getName() + " - " + k.getValue() );
        }
    }
    
    private void printReqParam(HttpServletRequest request){
         Enumeration<String> arr=request.getParameterNames();
         while (arr.hasMoreElements()){
            String aux=arr.nextElement();
            out.println(aux+" = "+request.getParameter(aux).toString());
         }
            
    }
    
    protected void processRequest(HttpServletRequest request, 
                                  HttpServletResponse response)
                                  throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();
        try {
            printHeader();
            out.println("<h1>Servlet at "+request.getServerName() +" "+ request.getContextPath()+" "+ request.getServletPath() + "</h1>");
            
            
            if(Debug){
               out.println("<font color=red><h3>Session Attributes:</h3>");
                String arr[]=request.getSession().getValueNames();
                for(int i=0;i<arr.length;i++){
                    out.println(arr[i]+" = "+request.getSession().getAttribute(arr[i]).toString());
                }
                out.println("<br />\n<h3>Request Parameters:</h3>");
                printReqParam(request);
                out.println("<br />\n<h3>Cookies:</h3>");
                printCookies(request);
                out.println("<br /></font>");
            }
            
            //String k = getCookieValue(cooks,"ServQueries");
            //Imprimir tabla con queries anteriores
            String k = (String)request.getSession().getAttribute("ServQueries");
            if( k != null ){
                scan=new Scanner(k);
                scan.useDelimiter("[&| ]");
                out.println("<center>");
                out.println("<h3> Previous Queries </h3>");
                out.println("<table border ='1'>"
                            +"<tr><th>User</th><th>Day</th><th>Temp (C)</th>"
                            +"<th>Relative Humidity</th><th>Wind Speed(mph)</th></tr>"  );
                while(scan.hasNext()){
                    out.println("<tr><td>"+scan.next()+"</td><td>"+scan.next()+"</td><td>"+scan.next()+
                            "</td><td>"+scan.next()+"</td><td>"+scan.next()+"</td></tr>");
                }
                out.println("</table>");
                out.println("</center>");
            }
            
            //Checamos session;
            if(request.getSession().getAttribute("sesInit") == null )
                request.getSession().setAttribute("sesInit", 0);
            
            if(((Integer) request.getSession().getAttribute("sesInit")) == 0)
                //showLogIn(request);
                response.sendRedirect(request.getContextPath().toString());
            else{
                showQueryForm(request);
                out.println("<br /><br />");
                showLogOut(request);
                out.println("<br /><br />");
            }
            
            
            
            int count;
            if(request.getSession().getAttribute("count") == null)
                count = 0;
            else
                count = (Integer)request.getSession().getAttribute("count");
            request.getSession().setAttribute("count",++count);
            out.println("You have accessed <b>"+count+"</b> times");
            ///////////////////////////////////////////////////////////////
            printBottom();
        } finally {            
            out.close();
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
        if(request.getParameter("logout") != null && Integer.parseInt(request.getParameter("logout"))==1){
            logout(request);
        }
        processRequest(request, response);
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
            
            //iniciar sesion       
        if(request.getParameter("username") != null && request.getParameter("pass") != null){
            if(request.getSession().getAttribute("sesInit")==null || 
                    0 == ((Integer)request.getSession().getAttribute("sesInit"))){
            request.getSession().setAttribute("sesInit", 1);
            request.getSession().setAttribute("username", request.getParameter("username"));
            request.getSession().setAttribute("pass", request.getParameter("pass"));
            }
        }
                //guardar query String
        else if(request.getParameter("day") != null && request.getSession().getAttribute("username") != null){
            short day;
            /* cooks=request.getCookies();
               getCookieValue(cooks,"ServQueries");
             */
             
            String k=(String)request.getSession().getAttribute("ServQueries");
            day=Short.parseShort(request.getParameter("day"));
            k=(k != null?k+"&":"")+request.getSession().getAttribute("username")+"&"+Query.result[day][0]+"&"+
              ((request.getParameter("temp") != null)? Query.result[day][1]:"-")+"&"+
              ((request.getParameter("humi") != null)? Query.result[day][2]:"-")+"&"+
              ((request.getParameter("wind") != null)? Query.result[day][3]:"-");
            request.getSession().setAttribute("ServQueries",k);
            /* cook=new Cookie("ServQueries", k);
               cook.setMaxAge(TTL_COOKIE);
               response.addCookie(cook);
             */
        }
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
