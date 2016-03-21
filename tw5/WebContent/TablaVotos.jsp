<%-- TablaVotos.jsp --%> 
<%@ page import="mvc.*, mvc.event.*, mvc.modelo.*, mvc.init.*"%> 
<html> 
  <head><title>Estadísticas de Futbol</title></head> 
 
  <body>
    <h3>Total de votaciones</h3> 
    
    <a href="<%= request.getContextPath() %>">Inicio</a> <br> <br> <br>
    <table border=1> 
      <tr><td><b>Jugador</b></td><td><b>Votos</b></td></tr> 
      <% 
        Jugadores jug=(Jugadores)application.getAttribute(InicializadorDatos.NOM_JUGADORES); 
        jug.consultarJugadores(); 
        while (jug.siguiente()){ 
          String nombre = jug.getCampo("Nombre"); 
          String votos = jug.getCampo("Votos"); 
          out.println("<tr><td>"+ nombre + "</td><td>" + votos + "</td></tr>"); 
        } 
         
        jug.cerrarConsulta(); 
      %> 
    </table> 
    </font> 
    <br> 
 
    
    
  </body> 
</html> 