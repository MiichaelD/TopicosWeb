/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.*;
import java.util.*;
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
    private final String id = "id", username = "username", email = "email";

    public DataControl(Connection con) {
        try {
            this.con = con;
            statm = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object[] login(String un, String pass) {
        try {
            statm = con.createStatement();
            Object[] ret = null;
            rs = statm.executeQuery(String.format(
                    " SELECT * FROM user"
                    + " WHERE (username='%s' or email='%s') and password=PASSWORD('%s');", un, un, pass));
            if (rs.next()) {
                ret = new Object[]{rs.getString(id), rs.getString(username)};
                statm.executeUpdate("UPDATE user set lastLogin=now() where id=" + ret[0]);
            }
            return ret;

        } catch (SQLException ex) {
            Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<String[]> searchFriend(String pattern) {
        try {
            statm = con.createStatement();
            rs = statm.executeQuery(String.format(
                    " SELECT id, username, email FROM user"
                    + " WHERE username like '%s' or email like '%s';", pattern, pattern));
            List<String[]> friends = new LinkedList<String[]>();
            while (rs.next()) {
                friends.add(new String[]{rs.getString(id), rs.getString(username), rs.getString(email)});
            }
            if (friends.isEmpty()) {
                return null;
            }

            return friends;
        } catch (SQLException ex) {
            Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<String[]> getMyFriends(String myID) {
        try {
            statm = con.createStatement();
            rs = statm.executeQuery(String.format(
                    "SELECT id, username, email FROM user inner join relationship on ("
                    + "id1=%s and id2=id) or (id2 = %s and id1=id) order by lastLogin desc;", myID, myID));
            List<String[]> friends = new LinkedList<String[]>();
            while (rs.next()) {
                friends.add(new String[]{rs.getString(1), rs.getString(2), rs.getString(3)});
            }
            if (friends.isEmpty()) {
                return null;
            }

            return friends;
        } catch (SQLException ex) {
            Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<String[]> getMyFriendsComments(String myID) {
        try {
            statm = con.createStatement();
            String query =String.format(
        "SELECT c.id, comment, poster_id, u.username, postdate  "
                    + "FROM comment as c "
                    + "INNER JOIN ( user as u "
                        + "INNER JOIN relationship "
                        + "ON (id1=%s and id2=id) or (id1=id and id2=%s) or id =%s) "
                    + "ON poster_id = u.id "
                    + "GROUP BY c.id "
                    + "ORDER BY postdate DESC;", myID, myID, myID);
            System.out.println("[dataControl:] query: "+query);
            rs = statm.executeQuery(query);
            List<String[]> comments = new LinkedList<String[]>();
            while (rs.next()) {
                comments.add(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
            }
            if (comments.isEmpty()) {
                return null;
            }

            return comments;
        } catch (SQLException ex) {
            Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<String[]> getMyComments(String myID) {
        try {
            statm = con.createStatement();
            String query = String.format(
                    "select * from comment where poster_id = %s"
                    + " order by postdate desc limit 35;", myID);

            rs = statm.executeQuery(query);
            List<String[]> comments = new LinkedList<String[]>();
            while (rs.next()) {
                comments.add(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
            }
            if (comments.isEmpty()) {
                return null;
            }

            return comments;
        } catch (SQLException ex) {
            Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String[] getUserData(String ID) {
        //USERDATA
        try {
            statm = con.createStatement();
            rs = statm.executeQuery(String.format("SELECT * FROM  user WHERE id = %s;", ID));
            while (rs.next()) {
                return new String[]{rs.getString(1), rs.getString(2), rs.getString(3)};
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public boolean userIsMyFriend(String myID, String otherID) {
        try {
            statm = con.createStatement();
            rs = statm.executeQuery(String.format(
                    "SELECT * FROM  relationship where (id1 = %s and id2=%s) or (id1=%s and id2=%s);",
                    myID, otherID, otherID, myID));

            while (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void deleteRelation(String myID, String otherID) {
        try {
            statm = con.createStatement();
            statm.executeUpdate(String.format(
                    "DELETE  FROM  relationship where (id1 = %s and id2=%s) or (id1=%s and id2=%s);",
                    myID, otherID, otherID, myID));
        } catch (SQLException ex) {
            Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addRelation(String myID, String otherID) {
        try {
            statm = con.createStatement();
            statm.executeUpdate(String.format(
                    "INSERT INTO relationship values(%s , %s);",
                    myID, otherID));
        } catch (SQLException ex) {
            Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean userExists(String un, String eml) {
        try {
            statm = con.createStatement();
            rs = statm.executeQuery(String.format(
                    " SELECT id FROM user"
                    + " WHERE (username='%s' or email='%s');", un, eml));
            if (rs.next()) {
                return true;
            }
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean userExists(String id) {
        try {
            statm = con.createStatement();
            rs = statm.executeQuery(String.format(
                    " SELECT id FROM user"
                    + " WHERE id=%s;", id));
            if (rs.next()) {
                return true;
            }
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void createUser(String un, String eml, String pass, int sex) {
        try {
            statm = con.createStatement();
            statm.executeUpdate(String.format("INSERT INTO user VALUES(null,'%s','%s',PASSWORD('%s'),1,%d,now())", un, eml, pass, sex));
        } catch (SQLException ex) {
            Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getCommentRow() {
        StringBuilder sb = new StringBuilder();
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
        for (int i = 1; i <= numberOfColumns; i++) {
            String columnName = rsMetaData.getColumnName(i);
            // Get the name of the column's table name
            String tableName = rsMetaData.getTableName(i);
            System.out.println("column name=" + columnName + " table=" + tableName + "");
        }
    }

    public void insertComment(String id, String comment) {
        try {
            statm = con.createStatement();
            statm.executeUpdate("INSERT INTO comment VALUES(null,'" + comment
                    + "'," + id +  ",now());");
        } catch (SQLException ex) {
            Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public void deleteComment(String id, String myID) {
        try {
            statm = con.createStatement();
            statm.executeUpdate("Delete from comment WHERE id=" + id + " and poster_id = "+myID );
            closeStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean siguiente() throws Exception {
        return (rs.next());
    }

    public String getCampo(String nombre) throws Exception {
        return (rs.getString(nombre));
    }

    public String getCampo(int column) throws Exception {
        return (rs.getString(column));
    }

    public void closeStatement() {
        try {
            rs.close();
            statm.close();
        } catch (Exception e) {
        }
    }
}