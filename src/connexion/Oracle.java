package connexion;

import java.sql.*;

public class Oracle {
    private static Connection c; 
    public static Connection getConnection(String username, String password){ 
        if (c == null) 
            Oracle.c = new Oracle(username, password).c; 
        return Oracle.c;
    }; 
    private Oracle(String username,String password) {
        try{
        Class.forName("oracle.jdbc.driver.OracleDriver").newInstance(); 
        c = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fil.univ-lille1.fr:1521:filora",username,password);
        c.setAutoCommit(false);}
        catch(Exception e){
            System.out.println(e);  
        };
    }
}
