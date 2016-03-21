package mvc; 
import javax.servlet.*; 
 
public interface Inicializador 
{ 
    public void init(ServletConfig cfg) throws ServletException; 
} 