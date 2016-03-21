/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.*; 
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Skeleton
 */
public class DataControl {
    

    private Connection con; 
    private Statement statm; 
    private ResultSet rs; 
 
    public DataControl( Connection con){
        try {
            this.con = con; 
        statm=con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    	} 
     
    
    public void getCommentById(String id){
        try {
            statm = con.createStatement(); 
            String query=null;
            if( id != null)
                query="SELECT * FROM comentarios where id="+id;
            else
                query= "SELECT c.id,c.nombre,c.correo,c.fecha,t.detalle,c.mensaje "+
                        "FROM comentarios as c INNER JOIN( tipos as t) "+
                        "ON (c.tipos_id = t.id) "+//"WHERE c.tipos_id = t.id "+
                        "ORDER BY c.fecha desc;";
            System.out.println("Query: "+query);
            rs=statm.executeQuery(query);
            //getColumnNames(rs);
        } catch (SQLException ex) {
            Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getCommentRow(){
        StringBuilder sb=new StringBuilder();
        try {
            sb.append("<td>").append(getCampo("id")).append("</td>");
            sb.append("<td>").append(getCampo("nombre")).append("<br /> ");
            sb.append(getCampo("correo")).append("</td>");
            sb.append("<td>").append(getCampo("fecha")).append("</td>");
            sb.append("<td><b>").append(getCampo("t.detalle")).append("</b><br />");
            sb.append(getCampo("mensaje")).append("</td>");
        } catch (Exception ex) {
            Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }
    
    public static void getColumnNames(ResultSet rs) throws SQLException {
    if (rs == null) {
      return;
    }
    ResultSetMetaData rsMetaData = rs.getMetaData();
    int numberOfColumns = rsMetaData.getColumnCount();

    // get the column names; column indexes start from 1
    for (int i = 1; i <= numberOfColumns ; i++) {
      String columnName = rsMetaData.getColumnName(i);
      // Get the name of the column's table name
      String tableName = rsMetaData.getTableName(i);
      System.out.println("column name=" + columnName + " table=" + tableName + "");
    }
  }
    
    public void insertComment(String tipos_id,String nombre, String correo, String mensaje){
        try {
            statm = con.createStatement(); 
            statm.executeUpdate("INSERT INTO comentarios VALUES(null,"+tipos_id+
                                    ",now(),'"+nombre+"','"+correo+"','"+mensaje+"')");
        } catch (SQLException ex) {
            Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateComment(String id,String tipos_id,String nombre, String correo, String mensaje){
        try {
            statm = con.createStatement(); 
            statm.executeUpdate("UPDATE comentarios SET nombre='"+nombre+
                    "', correo='"+correo+"', mensaje='"+mensaje+"', tipos_id="+tipos_id+" WHERE id="+id );
            closeStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteComment(String id){
        try {
            statm = con.createStatement(); 
            statm.executeUpdate("Delete from comentarios WHERE id="+id );
            closeStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getTiposId(String id){
        try {
            statm = con.createStatement(); 
            rs = statm.executeQuery("SELECT * FROM tipos");
            StringBuilder sb=new StringBuilder();
            while(rs.next()){
                    sb.append("<option value=\"").append(rs.getString("id")).append("\" ");
                    if(id != null && id.equals(rs.getString("id")))
                            sb.append("selected"); 
                    sb.append("> ").append(rs.getString("detalle")).append("</option>");
            }
            closeStatement();
            return sb.toString();
        } catch (SQLException ex) {
            Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
 
    public boolean siguiente() throws Exception { 
        return (rs.next()); 
    } 
     
    public String getCampo(String nombre) throws Exception { 
        return (rs.getString(nombre)); 
    } 
    
    public String getCampo(int column)throws Exception{
        return (rs.getString(column)); 
    }
     
    public void closeStatement() { 
        try { 
            rs.close(); 
            statm.close(); 
        } catch(Exception e) { } 
    }
}