<%-- 
    Document   : index
    Created on : Mar 29, 2013, 8:03:28 PM
    Author     : Skeleton
    Tutorial   : //http://www.youtube.com/watch?v=pwBNmAhtqk8
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= request.getRequestURI()%></title>
    </head>
    <body>
    <center>
        <h1>Login!</h1>
        <br /> <br />
        <h1><%= request.getRequestURI()%></h1>
        <form method="POST" action="Servlet" >
        <fieldset>
	   <legend>Login</legend>
        <p> <label> Username: <input type='text' name= 'username' /></label></p>
        <p> <label> Password: <input type='password' name= 'pass' /></label></p>
    	<p><input type="submit" name="login" /></p>
        </fieldset>
        </form>
        <%--response.sendRedirect("Servlet"); >
        <h2><a href="Servlet">Go To SERVLET</a></h2--%>
    </center>
    </body>
</html>
