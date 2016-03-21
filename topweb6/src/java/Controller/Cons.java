/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Skeleton
 */
public class Cons {
    
    public final static String 
            EVENT_NAME = "EVENT", INIT="SERVLET_INIT", 
            INSERTCOMMENTEVENT_NAME = "EVENT_INSERTCOMMENT",
            DELETECOMMENTEVENT_NAME="EVENT_DELETECOMMENT",
            DATA="DATACONTROL", BASEDATOS="baseDatos", DRIVER="driver",
            USERDB="usuarioBD", PASSDB="passBD";
    
    public final static void printParams(HttpServletRequest request){
         Enumeration<String> arr=request.getParameterNames();
         while (arr.hasMoreElements()){
            String aux=arr.nextElement();
            System.out.println("\t"+aux+" = "+request.getParameter(aux).toString());
         }
        
    }
}
