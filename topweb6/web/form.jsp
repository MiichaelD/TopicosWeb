<%@page import="Controller.Cons"%>
<%

    if(application.getAttribute(Cons.INIT) == null){
        response.sendRedirect("ctrl?rtn=/form.jsp");
        return;
    }
    
    String id = request.getParameter("f_id");
    String a = "I";
    String mensaje = "";
    boolean update = false;
    Model.DataControl dc = (Model.DataControl) application.getAttribute("DATACONTROL");
    
    if (id != null) {
            dc.getCommentById(id);
            if (dc.siguiente()) {
                update = true;
                mensaje = dc.getCampo("mensaje");
                a = "U";
            }
        
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Formulario</title>
    </head>
    <body>
        <h3>Formulario </h3>
        <form method="post" action="ctrl">
            <input type="hidden" name="<%= Controller.Cons.EVENT_NAME%>" 
                   value="<%= Controller.Cons.INSERTCOMMENTEVENT_NAME%>" />
            <input type="hidden" name="accion" value="<%= a%>" />
            <%if (update) {%>
            <input type="hidden" name="f_id" value="<%= id%>" />
            <%}%>
            <table width="400" border="0" cellspacing="2" cellpadding="0">
                <tr>
                    <td width="64">Nombres:</td>
                    <td width="330"><input type="text" name="f_nombre" value="<% if (update) {
                    out.print(dc.getCampo("nombre"));
                }%>" /></td>
                </tr>
                <tr>
                    <td>Correo:</td>
                    <td><input type="text" name="f_correo" value="<% if (update) {
                    out.print(dc.getCampo("correo"));
                }%>" /></td>
                </tr>
                <tr>
                    <td>Asunto:</td>
                    <td><select name="f_tipos_id" id="f_tipos_id">
                            <%
                               out.print(dc.getTiposId(id));
                            %>
                        </select>	 
                    </td>
                </tr>
                <tr>
                    <td>Mensaje:</td>
                    <td><textarea name="f_mensaje" id="f_mensaje" cols="45" rows="5"><%= mensaje%></textarea>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><input type="submit" name="button" id="button" value="Enviar" />
                        <input type="reset" name="button2" id="button2" value="Restablecer" /></td>
                </tr>
            </table>
        </form>
        <br></br>
        <h3><a href="show.jsp">Mostrar datos</a></h3>
    </body>
</html>