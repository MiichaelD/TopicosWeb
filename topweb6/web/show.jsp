<%@page import="Controller.*, Model.DataControl"%>
<% 
    
    if(application.getAttribute(Cons.INIT) == null){
        response.sendRedirect("ctrl?rtn=/show.jsp");
        return;
    }

    DataControl dc = ((DataControl) application.getAttribute(Cons.DATA));
    dc.getCommentById(null);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Mostrar</title>
	<script> 
	function borrar(id){
		if(confirm('¿Desea realmente eliminar este registro?')){ 
			window.location='ctrl?f_id='+id+'&<%=Cons.EVENT_NAME %>=<%=Cons.DELETECOMMENTEVENT_NAME %>';
                        return true; 
		} 
		return false; 
	} 
	</script>
</head>
<body>
	<h3><a href="form.jsp">Agregar </a></h3>
	<h3>Comentarios </h3>
	<table width="650" border="1" cellspacing="2" cellpadding="0">
	  <tr>
	    <th width="38">Nº</th>
	    <th width="159">Usuario</th>
	    <th width="78">Fecha</th>
	    <th width="291">Mensaje</th>
	    <th width="72">Acciones</th>
	  </tr>
	<%
        while(dc.siguiente()){
	%>
	  <tr>
	     <%=dc.getCommentRow()%>
	    <td>
	    	<a href="form.jsp?f_id=<%=dc.getCampo(1) %>">Modificar</a>
	    	<a href="#" onclick="borrar('<%= dc.getCampo(1) %>')">Eliminar</a>
	    </td>
	  </tr>
	<%}
            dc.closeStatement();
	%>
	</table>	
</body>
</html>