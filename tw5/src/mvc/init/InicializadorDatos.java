package mvc.init; 
import java.sql.*; 

import javax.servlet.*; 
import mvc.*; 
import mvc.modelo.*; 
 
public class InicializadorDatos implements Inicializador { 
    // Nombre para el objeto Jugadores que se guardara en el contexto 
    public static final String NOM_JUGADORES = "jugadores"; 
    // ... Aniadir para mis objetos      
    // Variables obtenidas del Descriptor de Despliegue (DD) 
    private static final String URL = "url"; 
    private static final String DRIVER = "driver"; 
    private static final String USER = "user"; 
    private static final String PASSWORD = "password";  
    //Constructor vacio para instanciacion dinamica. 
    public InicializadorDatos() {}  
    public void init(ServletConfig cfg) throws ServletException { 
        ServletContext ctx = cfg.getServletContext(); 
        String url = ctx.getInitParameter(URL); 
        String driver = ctx.getInitParameter(DRIVER); 
        String user = ctx.getInitParameter(USER); 
        String password = ctx.getInitParameter(PASSWORD); 
        mostrar( "InicializadorDatos lee basedatos " + url); 
        mostrar( "InicializadorDatos lee driver " + driver);         
        try { 
            // Conectarse a la base de datos 
            Class.forName(driver).newInstance(); 
            Connection con = DriverManager.getConnection(url, user, password); 
            mostrar("Se ha conectado a " + url);              
            // Crear el objeto Jugadores del modelo 
            Jugadores jug = new Jugadores(con); 
            ctx.setAttribute(NOM_JUGADORES, jug); 
            // Crear otros objetos ...             
        } catch(Exception e) { 
            mostrar( "No se ha conectado a " + url); 
            throw new ServletException(e.getMessage()); 
        } 
    } 
     
    private void mostrar(String msg) { 
        System.out.println(msg); 
    } 
} 