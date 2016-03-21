<%-- 
    Document   : index
    Created on : Mar 29, 2013, 8:03:28 PM
    Author     : Skeleton
    Tutorial   : //http://www.youtube.com/watch?v=pwBNmAhtqk8
--%>
<%@page import="java.util.*"%>
<%@page import="javax.servlet.http.HttpSession, Controller.Cons"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (application.getAttribute(Cons.INIT) == null) {
        response.sendRedirect("ctrl?rtn=/login.jsp");
        return;
    }

    if (request.getSession().getAttribute(Cons.USERID) == null) {
        System.out.println("[index.jsp]Session not created redirecting to login.jsp");
        response.sendRedirect("login.jsp");
        return;
    }

    HttpSession ses = request.getSession();

    String usnm = (String) ses.getAttribute(Cons.USERNAME);
    String usID = ses.getAttribute(Cons.USERID).toString();
    Iterator<String[]> lfIter = null;

    String er = request.getParameter(Cons.ERROR);
    String error = null;
    if (er == null) {
        lfIter = ((LinkedList<String[]>) ses.getAttribute(Cons.SEARCHQUERY)).iterator();
    } else {
        error = (String) ses.getAttribute(Cons.ERROR);
    }

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>joinME - SEARCH</title>
        <link rel="stylesheet" href="styles.css" media="screen"/>
        <script> 
            function friend(id){
                window.location='ctrl?<%=Cons.USERID%>='+id+'&<%=Cons.EVENT_NAME%>=<%=Cons.OPENFRIENDEVENT_NAME%>';
                return true; 
            } 
            function logout(){
                if(confirm('Logout?')){ 
			window.location='ctrl?<%=Cons.EVENT_NAME%>=<%=Cons.LOGOUTEVENT_NAME%>';
                        return true; 
		} 
		return false; 
            }   
        </script>
    </head>
    <body>
        <h2 id="home">Welcome <a href="ctrl?<%=Cons.EVENT_NAME%>=<%=Cons.LOADINDEXEVENT_NAME%>" ><%=usnm%></a>!</h2>  
    <center>
        <div id="friendbardiv">
            <fieldset>
                <legend>Add a friend:</legend>
                <form action="ctrl" method="POST">
                    <input type="hidden" name="<%= Cons.EVENT_NAME%>" 
                           value="<%= Cons.SEARCHFRIENDEVENT_NAME%>" />
                    <p> <label> Friend: <input type='text' name= '<%=Cons.SEARCHQUERY%>'/></label>
                        <input type="submit" name="SEARCH" value="search"/></p>         
                </form>
            </fieldset>     
        </div>
        <br />
        <div id="result">
            <fieldset>
                <legend>Results:</legend>
                <%
                    if (er == null) {
                        out.println("<ul>");
                        String ar[];
                        while (lfIter.hasNext()) {
                            ar = lfIter.next();
                            out.println("<li><a href='#' onclick='friend(" + ar[0] + ")' >");
                            out.println("<font color=green>"+ar[1] + "</font> - <font color=blue>" + ar[2] + "</blue></a></li><hr align='center'>");
                        }
                        ses.removeAttribute(Cons.SEARCHQUERY);
                        out.println("</ul>");
                    } else {
                        out.println("<h3>" + error + "</h3>");
                        ses.removeAttribute(Cons.ERROR);
                    }
                    ses.removeAttribute(Cons.SEARCHQUERY);
                %>
            </fieldset>   
        </div>
    </center>
    <div id="logout">
        <h3><a  href="#" onclick="logout()" >Logout</a></h3>
    </div>
</body>
</html>
