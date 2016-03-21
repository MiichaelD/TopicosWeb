<%-- ErrorVotar.jsp --%> 
 
<%@ page contentType="text/html;charset=ISO-8859-1" 
  import="mvc.modelo.*, mvc.event.*, mvc.init.*"%> 
 
<html> 
  <head><title>Estadísticas de Futbol</title></head> 
 
  <body> 
    <h2>Error en la votación</h2> 
 
    Puede intentar votar otra vez.<br> 
    <a href="<%= request.getContextPath() %>"> Inicio</a> 
  </body> 
 
</html> 