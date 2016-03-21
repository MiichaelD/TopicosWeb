package mvc.modelo; 
 
import java.sql.*; 
 
public class Jugadores { 
    private Connection con; 
    private Statement set; 
    private ResultSet rs; 
 
    public Jugadores( Connection con){
    	this.con = con; 
    	} 
     
    public boolean existeJugador(String nombre) throws Exception { 
        boolean existe = false; 
        String cad; 
        set = con.createStatement(); 
        rs = set.executeQuery("SELECT * FROM Jugadores"); 
        while (rs.next()) { 
            cad = rs.getString("Nombre"); 
            cad = cad.trim(); 
            if (cad.compareTo(nombre.trim())==0) existe = true; 
        }      
        rs.close(); 
        set.close(); 
        return(existe); 
    } 
    
    public boolean existeVisitante(String nombre) throws Exception { 
        boolean existe = false; 
        String cad; 
        set = con.createStatement(); 
        rs = set.executeQuery("SELECT * FROM registro where Nombre like '%"+nombre+"%'"); 
        while (rs.next()) { 
            cad = rs.getString("Nombre"); 
            cad = cad.trim(); 
            existe = true; 
        }      
        rs.close(); 
        set.close(); 
        return(existe); 
    } 
     
    public void actualizarJugador(String nombre) throws Exception { 
        set = con.createStatement(); 
        set.executeUpdate( "UPDATE Jugadores SET votos=votos+1 WHERE nombre " 
                            + " LIKE '%" + nombre + "%'"); 
        rs.close(); 
        set.close(); 
    } 
    
    public void actualizarVisitante(String nombre) throws Exception { 
        set = con.createStatement(); 
        set.executeUpdate( "UPDATE registro SET visitas=visitas+1 WHERE Nombre " 
                            + " LIKE '%" + nombre + "%'"); 
        rs.close(); 
        set.close(); 
    } 
     
    public void insertarJugador(String nombre) throws Exception { 
        set = con.createStatement(); 
        set.executeUpdate("INSERT INTO Jugadores " 
                            + " (nombre,votos) VALUES ('" + nombre + "',1)"); 
        rs.close(); 
        set.close(); 
    } 
    
    public void insertarVisitante(String nombre, String email) throws Exception { 
        set = con.createStatement(); 
        set.executeUpdate("INSERT INTO registro(nombre,correo,visitas) VALUES ('" + nombre + "','" + email + "',1)"); 
        rs.close(); 
        set.close(); 
    } 
     
    public void consultarJugadores() throws Exception { 
        set = con.createStatement(); 
        rs = set.executeQuery("SELECT * FROM Jugadores"); 
    } 
     
    public int consultarVisitas(String nombre )throws Exception{
        set = con.createStatement(); 
        rs = set.executeQuery("SELECT visitas FROM registro where nombre like '%"+nombre+"%'"); 
        if(!rs.next()){
            return 1;
        }
        return rs.getInt(1);
    }
    
    public boolean siguiente() throws Exception { 
        return (rs.next()); 
    } 
     
    public String getCampo(String nombre) throws Exception { 
        return (rs.getString(nombre)); 
    } 
     
    public void cerrarConsulta() { 
        try { 
            rs.close(); 
            set.close(); 
        } catch(Exception e) { } 
    }
}