<%-- Votacion.jsp --%> 
 
<%@ page contentType="text/html;charset=ISO-8859-1" %> 
<%@ page import="mvc.*,mvc.init.*, mvc.event.*, mvc.modelo.*"%> 
 
<html> 
  <head><title>Estadísticas de Futbol</title></head> 
 
  <body> 
    <h2>Voto completado</h2> 
 
    <form action="futbol" method="POST"> 
      <input type="submit" value="Ver estadísticas" /> 
      <input type="hidden" name="<%= Evento.NOM_EVENTO %>"  
                 value="<%= EventoConsulta.NOMBRE %>" /> 
    </form> 
 
    <h3>Muchas gracias <%= session.getValue("nombreCliente") %> por su  visita #<% 
    Jugadores jug=(Jugadores)application.getAttribute(InicializadorDatos.NOM_JUGADORES); 
        out.println(jug.consultarVisitas((String)session.getValue("nombreCliente"))); 
        jug.cerrarConsulta(); 
                 
                 %></h3> 
 
    <a href="<%= request.getContextPath() %>">Inicio</a> 
   </body> 
 
</html> 