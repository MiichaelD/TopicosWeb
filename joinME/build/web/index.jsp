<%-- 
    Document   : index
    Created on : Mar 29, 2013, 8:03:28 PM
    Author     : Skeleton
    Tutorial   : //http://www.youtube.com/watch?v=pwBNmAhtqk8
--%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.LinkedList"%>
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
    String usID = (String) ses.getAttribute(Cons.USERID);

    String search = request.getParameter(Cons.SEARCHFRIEND);

    String[] usdata = (String[]) ses.getAttribute(Cons.USERDATA);
    Iterator<String[]> myFriendIT = null, commentIT = null;

    if (usdata == null) {
        response.sendRedirect("ctrl?" + Cons.EVENT_NAME + "=" + Cons.LOADINDEXEVENT_NAME);
        return;
    }

    String[] ar = null;

    if (ses.getAttribute(Cons.FRIENDLIST) != null) {
        myFriendIT = ((LinkedList<String[]>) ses.getAttribute(Cons.FRIENDLIST)).iterator();
    }
    if (ses.getAttribute(Cons.COMMENTS) != null) {
        commentIT = ((LinkedList<String[]>) ses.getAttribute(Cons.COMMENTS)).iterator();
    }

    Enumeration<String> atribs = request.getSession().getAttributeNames();
    while (atribs.hasMoreElements()) {
        String st = atribs.nextElement();
        //out.println(st+": "+request.getSession().getAttribute(st));
    }

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>joinME - HOME</title>
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
                function deleteComment(id){
                    if(confirm('Delete this comment?')){
                        window.location='ctrl?<%=Cons.EVENT_NAME%>=<%=Cons.DELETECOMMENTEVENT_NAME%>&<%=Cons.COMMENTS%>='+id;
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
            <div id="commentPoster">
                <form action="ctrl" method="POST">
                    <input type="hidden" name="<%= Cons.EVENT_NAME%>" 
                           value="<%= Cons.INSERTCOMMENTEVENT_NAME%>" />
                    <p> <label> What's on your mind: <input type='text' name= '<%=Cons.COMMENTS%>'/></label>
                        <input type="submit" name="POST" value="share!"/></p>         
                </form>
            </div>
            <br />
            <%
                if (myFriendIT != null) {

                    out.println("<div id='friendsdiv'><fieldset>"
                            + "<legend>My Friends:</legend><ul>");
                    while (myFriendIT.hasNext()) {
                        ar = myFriendIT.next();

                        out.print("<li><h4><a href='#' onclick='friend(" + ar[0]
                                + ")' >" + ar[1] + "</a></h4></li>");
                        out.println();

                    }
                    out.println("</ul></fieldset></div>");
                    ses.removeAttribute(Cons.FRIENDLIST);
                }
            %>
            <br/>
            <%
                if (commentIT != null) {
                    out.println("<div id='commentsdiv'><fieldset>"
                            + "<legend>What's going on:</legend><ul>");
                    while (commentIT.hasNext()) {
                        ar = commentIT.next();
                        //c.id, comment, poster_id, u.username, postdate
                        out.print("<li ><br /><label><font class='usr'>" + ar[3] + ": </font> </label>");
                        if (ar[2].equals(usID)) {
                            out.print("<font class='delete'><a href='#' onclick='deleteComment(" + ar[0] + ")' >"
                                    + "Delete<img src='http://bbsimg.ngfiles.com/1/18030000/ngbbs4931727a92a83.jpg' width=20 height=20></a></font>");
                        }
                        out.println(" <br /><font class='cmmnt'> " + ar[1] + "</font>"
                                + "<br /><label><font class='date'>" + ar[4] + "</font></label>");

                        out.println("</li><br /><hr align='center'>");

                    }
                    out.println("</ul></fieldset></div>");
                    ses.removeAttribute(Cons.COMMENTS);
                }

                ses.removeAttribute(Cons.USERDATA);
            %>
        </center>
        <div class="logout">
            <h3><a  href="#" onclick="logout()" >Logout</a></h3>
        </div>
    </body>
</html>
