package mvc.event; 
 
import javax.servlet.*; 
import javax.servlet.http.*; 
import mvc.*; 
 
public class EventoConsulta implements Evento { 
    // Variables obtenidas del Descriptor de Despliegue (DD) 
    public static final String NOMBRE = "evento.consulta"; 
    private final static String JSP_DESTINO = "TablaVotos.jsp"; 
 
    public EventoConsulta() {} //Constructor vac�o para instanciaci�n din�mica. 
 
    public String procesar(ServletContext ctx, HttpServletRequest req) { 
        mostrar( "En " + this.toString() + " procesando evento"); 
        return JSP_DESTINO; 
    } 
 
    private void mostrar(String msg){ 
        System.out.println(msg); 
    } 
} 