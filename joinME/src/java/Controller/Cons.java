/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.Enumeration;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Skeleton
 */
public class Cons {
    
    public final static String 
        INIT="SERVLET_INIT", 

        //Events Names:
        EVENT_NAME = "EVENT", 
        LOADINDEXEVENT_NAME = "EVENT_LOADINDEX",
        INSERTCOMMENTEVENT_NAME = "EVENT_INSERTCOMMENT",
        LOGOUTEVENT_NAME = "EVENT_LOGOUT",
        LOGINEVENT_NAME = "EVENT_LOGIN",
        SIGNUPEVENT_NAME = "EVENT_SIGNUP",
        DELETECOMMENTEVENT_NAME = "EVENT_DELETECOMMENT",
        SEARCHFRIENDEVENT_NAME = "EVENT_SEARCHFRIEND",
        OPENFRIENDEVENT_NAME = "EVENT_OPENFRIEND",
        ADDFRIENDEVENT_NAME = "EVENT_ADDFRIEND",
        UNFRIENDEVENT_NAME = "EVENT_UNFRIEND",
        

        //DataBase Parameters:
        DATA="DATACONTROL", BASEDATOS="baseDatos", DRIVER="driver",
        USERDB="usuarioBD", PASSDB="passBD",

        //Session IDs
        USERID="userID",    ERROR="error",

    
        //Query String Parameter IDs
        USERNAME = "usnm",  EMAIL="eml",    SEX="sex",  PASS="pass",
        USERDATA = "usrdata",
            
            
        //INDEX
        SEARCHFRIEND = "srfr", SEARCHQUERY = "searchQuery", ISFRIEND = "isFriend",
        COMMENTS = "comments", FRIENDLIST = "friendList"
            
            
        ;
    public final static void printParams(HttpServletRequest request){
         Enumeration<String> arr=request.getParameterNames();
         while (arr.hasMoreElements()){
            String aux=arr.nextElement();
            System.out.println("\t"+aux+" = "+request.getParameter(aux).toString());
         }
        
    }
    
    public final static void printCookies(HttpServletRequest request){
        Cookie[] cooks = request.getCookies();
        if(cooks.length > 0)
        for(Cookie k : cooks){
            System.out.println(k.getName() + " - " + k.getValue() );
        }
    }
}
