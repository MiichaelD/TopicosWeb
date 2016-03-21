<%-- 
    Document   : me
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
        System.out.println("[me.jsp]Session not created redirecting to login.jsp");
        response.sendRedirect("login.jsp");
        return;
    }

    HttpSession ses = request.getSession();
    String personID = request.getParameter(Cons.USERID);
    String usnm = (String) ses.getAttribute(Cons.USERNAME);
    String usID = ses.getAttribute(Cons.USERID).toString();


    if (personID == null) {
        System.out.println("[me.jsp] no id");
        response.sendRedirect("ctrl?" + Cons.USERID + "=" + usID + "&" + Cons.EVENT_NAME + "=" + Cons.OPENFRIENDEVENT_NAME);
        return;
    }

    String[] usdata = (String[]) ses.getAttribute(Cons.USERDATA);
    Iterator<String[]> lcIter = null;
    boolean isFriend = (Boolean) ses.getAttribute(Cons.ISFRIEND);

    String er = request.getParameter(Cons.ERROR);
    String error = null;
    if (er == null) {
        lcIter = ((LinkedList<String[]>) ses.getAttribute(Cons.COMMENTS)).iterator();
        if (lcIter == null) {
            response.sendRedirect("ctrl?" + Cons.USERID + "=" + personID + "&" + Cons.EVENT_NAME + "=" + Cons.OPENFRIENDEVENT_NAME);
            return;
        }
    } else {
        error = (String) ses.getAttribute(Cons.ERROR);
    }

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css" media="screen"/>
        <title>Looking for friends ...</title>
        <script> 
            
            function unfriend(id){
                if(window.confirm('Unfriend user?')){ 
                    window.location='ctrl?<%=Cons.EVENT_NAME%>=<%=Cons.UNFRIENDEVENT_NAME%>';
                    return true; 
                }
                return false;
            } 
            
            function deleteComment(id){
                if(confirm('Delete this comment?')){
                    window.location='ctrl?<%=Cons.EVENT_NAME%>=<%=Cons.DELETECOMMENTEVENT_NAME%>&<%=Cons.COMMENTS%>='+id;
                    return true; 
                }
                return false;
            }
            function logout(){
                if(confirm('Logout?')){ 
			window.location='ctrl?<%=Cons.EVENT_NAME%>=<%=Cons.LOGOUTEVENT_NAME%>';
                        return true; 
		} 
		return false; 
            } 
            function friend(id){
                window.location='ctrl?<%=Cons.EVENT_NAME%>=<%=Cons.ADDFRIENDEVENT_NAME%>';
                return true; 
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
        <div id="about">
            <fieldset>
                <%if(personID.equals(usID)){
                            out.println("<legend>About Me:</legend><ul>");
                        }
                        else{
                            out.println("<legend>About your friend:</legend><ul>");
                        }
                
                %>
                <h2> JoinME:<font color ="green"> <%=usdata[1]%></font><br />email: <font color="blue"><%=usdata[2]%></font></h2>
            </fieldset>
        </div>
        <br />
        <%

            if (!personID.equals(usID)) {
        %>
        <form action="ctrl" method="POST">
            <input type="hidden" name="rtn" 
                   value="<%= request.getRequestURI()%>" />
            <%

                if (isFriend) {
            %>

            <input type="hidden" name="<%= Cons.EVENT_NAME%>" 
                   value="<%= Cons.UNFRIENDEVENT_NAME%>" />
            <p><input type="submit" name="search" value="UNFRIEND THIS PERSON"/></p>
                <%
                } else {
                %>
            <input type="hidden" name="<%= Cons.EVENT_NAME%>" 
                   value="<%= Cons.ADDFRIENDEVENT_NAME%>" />
            <p><input type="submit" name="search" value="ADD TO FRIEND"/></p>
                <%
                    }
                %>
            <input type="hidden" name="<%= Cons.USERID%>" 
                   value="<%= personID%>" />       
        </form>
        <%
            }
            if (error == null) {
                if (isFriend || personID.equals(usID)) {
                    if (lcIter != null) {
                        String ar[];
                        if(personID.equals(usID)){
                            out.println("<div id='commentsdiv'><fieldset>"
                                + "<legend>Your shares:</legend><ul>");
                        }
                        else{
                            out.println("<div id='commentsdiv'><fieldset>"
                                + "<legend>Your friend's shares:</legend><ul>");
                        }
                        while (lcIter.hasNext()) {
                            ar = lcIter.next();
                            if (ar[2].equals(usID)) {
                                 out.print("<font class='delete'><a href='#' onclick='deleteComment(" + ar[0] + ")' >"
                                    + "Delete<img src='http://bbsimg.ngfiles.com/1/18030000/ngbbs4931727a92a83.jpg' width=20 height=20></a></font>");
                            }
                            //c.id, comment, poster_id, u.username, postdate
                            out.println(" <br /><font class='cmmnt'> " + ar[1] + "</font>");
                            
                            out.println("<br /><label><font class='date'>" + ar[3] + "</font></label>");

                            out.println("</li><br /><hr align='center'>");

                        }
                        out.println("</ul></fieldset></div>");
                        ses.removeAttribute(Cons.COMMENTS);
                    }


                }
            } else {
                out.println("<h1> " + error + "</h1>");
                ses.removeAttribute(Cons.ERROR);
                out.println("<h2> <a href='ctrl?" + Cons.EVENT_NAME + "=" + Cons.LOADINDEXEVENT_NAME + "' >HOME</a>");
            }
            ses.removeAttribute(Cons.ISFRIEND);
        %>
    </center>
    <div id="logout">
        <h3><a  href="#" onclick="logout()" >Logout</a></h3>
    </div>
</body>
</html>
