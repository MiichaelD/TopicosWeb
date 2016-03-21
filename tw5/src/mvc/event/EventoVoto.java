package mvc.event; 
 
import javax.servlet.*; 
import javax.servlet.http.*; 
import mvc.*; 
import mvc.init.*; 
import mvc.modelo.*; 
 
public class EventoVoto implements Evento { 
    // Variables obtenidas del Descriptor de Despliegue (DD) 
    public static final String NOMBRE = "evento.voto"; 
    private static final String JSP_DESTINO = "Votacion.jsp";//"TablaVotos.jsp"; 
    private static final String JSP_ERROR = "ErrorVotar.jsp"; 
 
    public EventoVoto() {} //Constructor vac�o para instanciaci�n din�mica.  
    // M�todo sincronzado para evitar problemas de concurrencia al comprobar el stock 
    @SuppressWarnings("deprecation")
	public synchronized String procesar(ServletContext ctx, HttpServletRequest req) { 
        
        mostrar( "En " + this.toString() + " procesando evento");         
        // Obtener el componente de estado del modelo 
        Jugadores jug =(Jugadores)ctx.getAttribute(InicializadorDatos.NOM_JUGADORES);       
        // Obtener la sesion 
        HttpSession s = req.getSession(true);
        // Guardar nombre del cliente en sesi�n para poderlo utilizar en el siguiente servlet 
        String nombreP = (String)req.getParameter("txtNombre"); 
        String email = (String)req.getParameter("txtMail"); 
        s.putValue("nombreCliente", nombreP);  
        s.putValue("mailCliente", email);  
        String nombre=(String)req.getParameter("R1");         
        try { 
            if (nombre.equals("Otros")) 
                nombre=(String)req.getParameter("txtOtros");             
            if(nombre.equals("")) throw new Exception(); 
            
            
            if(jug.existeJugador(nombre)) jug.actualizarJugador(nombre); 
            else jug.insertarJugador(nombre);  
            mostrar("Se actualiz� o insert� jugador " + nombre); 
            
            if(jug.existeVisitante(nombreP)) jug.actualizarVisitante(nombreP);
            else jug.insertarVisitante(nombreP,email);
            mostrar("Se actualiz� o insert� visitante " + nombreP);
            
            
            return JSP_DESTINO; 
        } 
        catch( Exception e) { 
            mostrar("Error al actualizar o insertar jugador " + nombre); 
            jug.cerrarConsulta(); 
            return JSP_ERROR; 
        } 
    } 
 
    private void mostrar(String msg){ 
        System.out.println(msg); 
    } 
}