package mvc; 
 
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
 
public interface Evento 
{ 
    // Variables obtenidas del Descriptor de Despliegue (DD) 
    public String NOM_EVENTO = "evento"; 
    public String procesar(ServletContext ctx, HttpServletRequest req) 
                              throws IOException, ServletException; 
} 