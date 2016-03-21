<%-- Futbol.jsp --%> 
<%@ page contentType="text/html;charset=ISO-8859-1" %> 
<%@ page import="mvc.*, mvc.event.*"%> 
<html> 
  <head> 
  <title>Estadísticas de Futbol</title> 
  </head> 
  <body>
  <table> 
  <tr><td>
  <img src="medioglobo.gif" border=0 ></td><td>
  <center><H1>Estadísticas de Jugadores de Futbol</H1>
  <p ><font color="#002424" size="4"> 
    <u>VOTE POR EL MEJOR JUGADOR DE FUTBOL DE 2008</u></font></p> 
  
  <form action="futbol" method="POST" left>  
	    <p align ="right" >Nombre del Visitante: <input type="text" size="20" name="txtNombre"><br>
	    			   eMail: <input type="text" size="20" name="txtMail"><br><br>
	    	<input type="radio" name="R1" value="Garcia">Garcia
	    	<input type="radio" name="R1" value="Ramirez">Ramirez
	        <input type="radio" name="R1" value="Perez">Perez <br><br><br>
	   		<input type="radio" name="R1" value="Otros">
	  Otros <input type="text" size="20" name="txtOtros"><br>
	    <p ><input type="submit" name="B1" value="Votar"> 
	    	<input type="reset" name="B2" value="Reset"></p> 
	    	<input type="hidden" name="<%= Evento.NOM_EVENTO %>" value="<%= EventoVoto.NOMBRE %>" /> 
  </form> </td></tr>
  </body> 
</html> 