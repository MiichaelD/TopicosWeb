<%-- 
    Document   : index
    Created on : Mar 29, 2013, 8:03:28 PM
    Author     : Skeleton
    Tutorial   : //http://www.youtube.com/watch?v=pwBNmAhtqk8
--%>
<%@page import="java.util.Enumeration,Controller.Cons"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% 
    if(application.getAttribute(Controller.Cons.INIT) == null){
        response.sendRedirect("ctrl?rtn=/login.jsp");
        return;
     }
    
    if(request.getSession().getAttribute(Controller.Cons.USERID) != null){
        System.out.println("[login.jsp]Session already created redirecting to index.jsp");
        response.sendRedirect("ctrl?"+Cons.EVENT_NAME+"="+Cons.LOADINDEXEVENT_NAME);
        return;
    }
    
    int er = 0;
    String un = null, em = null, error = null;
    
    if(request.getParameter(Controller.Cons.ERROR) != null){
        er = Integer.parseInt(request.getParameter(Controller.Cons.ERROR));
        un = request.getParameter(Controller.Cons.USERNAME);
        em = request.getParameter(Controller.Cons.EMAIL);
        error = (String)request.getSession().getAttribute(Controller.Cons.ERROR);
        request.getSession().removeAttribute(Controller.Cons.ERROR);
    }
       
    Enumeration<String> atribs = request.getSession().getAttributeNames();
    while(atribs.hasMoreElements()){
        String st = atribs.nextElement();
        out.println("ATRIBS: name: "+st+", val: "+request.getSession().getAttribute(st));
    }
    
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css" media="screen"/>
        <title>Welcome to joinME</title>
    </head>
    <body>
    <center>
        <h1>Welcome to joinME</h1>
        <br /> 
        <h1>Login!</h1>
        <br />
        <div id='logindiv'>
        <table>
            <tr><td>
                    <form method="POST" action="ctrl" >
                        <fieldset>
                            <legend>Login</legend>
                            <input type="hidden" name="<%= Controller.Cons.EVENT_NAME%>" 
                                    value="<%= Controller.Cons.LOGINEVENT_NAME%>" />
                            <% if(er == 1 && error != null) {
                                    out.println("<p id='error'>"+error+"</p>");
                                }
                            %>
                            <p> <label> Username: <input type='text' name='<%=Controller.Cons.USERNAME%>' <%if(un != null && er==1) out.print("value='"+un+"'");%> /></label></p>
                            <p> <label> Password: <input type='password' name='<%=Controller.Cons.PASS%>' /></label></p>
                            <center>
                                <p><input type="submit" name="login" value="Login" /></p>
                            </center>
                        </fieldset>
                    </form>

                </td>
                <td>
                    <form method="POST" action="ctrl" >
                        <fieldset>
                            <legend>New Account</legend>
                            <input type="hidden" name="<%= Controller.Cons.EVENT_NAME%>" 
                                    value="<%= Controller.Cons.SIGNUPEVENT_NAME%>" />
                            <% if(er == 2 && error != null) {
                                    out.println("<p id='error'>"+error+"</p>");
                                }
                            %>
                            <p> <label> Username: <input type='text' name= '<%=Controller.Cons.USERNAME%>'  <%if(un != null && er == 2) out.print("value='"+un+"'");%>/></label></p>
                            <p> <label> E-Mail: <input type='text' name='<%=Controller.Cons.EMAIL%>'  <%if(em != null && er == 2) out.print("value='"+em+"'");%>/></label></p>
                            <p> <label> Password: <input type='password' name='<%=Controller.Cons.PASS%>' /></label></p>
                            <p> <label> Sex:    </label><input type="radio" name='<%=Controller.Cons.SEX%>' value="1" checked/>Male <input type="radio" name='<%=Controller.Cons.SEX%>' value="0"/>Female</p>
                            <center>
                                <p><input type="submit" name="signUp" value="Sign up"/></p>
                            </center>

                        </fieldset>
                    </form>

                </td>
            </tr>
        </table>
        </div>
    </center>
</body>
</html>
