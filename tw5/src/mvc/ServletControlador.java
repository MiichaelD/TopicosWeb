package mvc; 
import java.io.*; 
import java.util.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import mvc.init.*;
import mvc.event.*;
 
@SuppressWarnings("serial")
public class ServletControlador extends HttpServlet { 
    // Variables obtenidas del Descriptor de Despliegue (DD) 
    private static final String INICIALIZADOR = "inicializador"; 
    private static final String PREFIJO_EVENTO = "evento.";      
    // Otras variables 
    private static final String NOM_TABLA_EVENTOS = "tablaEventos"; 
    // Inicializaci�n del servlet 
    public void init(ServletConfig config) throws ServletException { 
    	// almacena el objeto ServlerConfig y el log de inicializacion 
        super.init(config); 
        // Util para no perderse en los logs 
        mostrar(""); 
        mostrar("********* Comienza practica **********");          
        try { 
            Inicializador ini = new InicializadorDatos();
            ini.init(config); 
            // Recuperar las clases de los eventos 
            Map<String, Evento> eventos = new HashMap<String, Evento>(); 
            
            //creacion de Instancia, de la Clase que maneja el evento     
            Evento evento = new EventoConsulta();
            eventos.put(((EventoConsulta)evento).NOMBRE, evento); 
                    
            evento = new EventoVoto();
            eventos.put(((EventoVoto)evento).NOMBRE, evento);  
            //  Guardar la tabla de eventos en el contexto 
            config.getServletContext().setAttribute(NOM_TABLA_EVENTOS, eventos); 
        } catch (Exception ex) { 
            mostrar(ex.getMessage()); 
            ex.printStackTrace(); 
            throw new UnavailableException(ex.getMessage()); 
        } 
    } 
 
    public void doPost(HttpServletRequest req, HttpServletResponse res) 
                        throws ServletException, IOException {  
        // Busca en la estructura HTTP par�metro y valor lanzados desde el formulario 
        String nomEvento = req.getParameter(Evento.NOM_EVENTO);          
        // Obtiene la colecci�n de eventos definidos 
        Map eventos = (Map)getServletContext().getAttribute(NOM_TABLA_EVENTOS); 
        if (!eventos.containsKey(nomEvento)) {   
            //Si no se encuentra el evento se lanza excepci�n 
            String msg = "Evento no encontrado " + nomEvento; 
            mostrar(msg); 
            throw new ServletException(msg); 
        } else {    
            // Si se encuentra el evento se procesa 
            mostrar("Procesando evento " + nomEvento);             
            // Se recupera la clase controladora 
            Evento evento = (Evento)eventos.get(nomEvento);                        
            // Se delega el evento del proceso 
            String path = evento.procesar(getServletContext(), req);              
            // Se redirige la petici�n 
            mostrar("Evento procesado, redirigiendo a " + path);
            req.getRequestDispatcher(path).forward(req, res);             
            mostrar("Evento " + nomEvento + " procesado"); 
        } 
    } 
 
    private void mostrar(String msg) { 
        System.out.println(msg); 
        //log(msg); 
    } 
} 