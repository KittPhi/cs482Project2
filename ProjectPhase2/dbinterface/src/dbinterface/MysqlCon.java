package dbinterface;

import java.sql.*;

class MysqlCon{  
   public static void main(String args[]){  
      try{  
         //When you are using JDBC outside of an application server, the 
         //DriverManager class manages the establishment of connections.
         //Specify to the DriverManager which JDBC drivers to try to make 
         //Connections with. The easiest way to do this is to use Class.forName()
         //on the class that implements the java.sql.Driver interface.
         //Class.forName("com.mysql.jdbc.Driver");  
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         
         Connection con = DriverManager.getConnection(  
            //here sonoo is database name, root is username and password  
            //"jdbc:mysql://localhost:3306/sonoo","root","root"); 
            
            //"jdbc:mysql://127.0.0.1:3306/nmsu_basketball","root","6100");  
            
            "jdbc:mysql://127.0.0.1:3306/nmsu_basketball; user=root; password=6100;");
            //jdbc:mysql://127.0.0.1:3306/?user=root
            
             
            
            
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from emp");  
            while(rs.next())  
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
            con.close();  
      }catch(Exception e){ System.out.println(e);}  
   }  
}

/*
//reference: many ways to connect
https://www.codejava.net/java-se/jdbc/connect-to-microsoft-sql-server-via-jdbc

https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-usagenotes-connect-drivermanager.html

*/
