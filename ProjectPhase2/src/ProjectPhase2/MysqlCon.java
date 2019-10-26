package ProjectPhase2;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.*;
import java.util.*;         
import java.nio.*;      
import java.sql.ResultSet;
import java.sql.Statement;


class MysqlCon{  
   public static void main(String args[]){  
      try{   
            
            //Prompt for Single, Multi-Row or Bulk Insert
            Scanner insertType = new Scanner(System.in);
            System.out.println("Enter 1 for Single Insert");
            System.out.println("Enter 2 for Multii Row Insert");
            System.out.println("Enter 3 for Bulk Insert");
            System.out.println("Enter 4 to clear all tables data");
            
            int number = insertType.nextInt();
            
            // get the input file
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the path and file name with extension");
            String filePath = input.nextLine();
            System.out.println("You Entered: " + filePath);
            
            // get fileName
            //https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
            File fileName = new File(filePath);
            System.out.println( fileName.getName() + "  is the file name");
            
            // get the tableName from fileName
            //https://www.quora.com/How-do-I-delete-substring-from-original-string-in-Java
            String str = "" + fileName.getName() +"";
            int indexOfLast = str.lastIndexOf(".");
            String justName = str;
            if (indexOfLast >= 0) 
                justName = str.substring(0, indexOfLast);
            System.out.println(justName + "  is the table name form file");
            
            Class.forName("com.mysql.jdbc.Driver");  

            Connection conn = DriverManager.getConnection(  
            "jdbc:mysql://127.0.0.1:3306/nmsu_basketball","root","6100");  
            System.out.println("connection successful!");
            
            PreparedStatement prepStmt = null;
            
            System.out.println("Lets read from the file");
            //storing line by line using BufferedReader
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            
            //split Strings by catching "," and save it to String array
            String line = reader.readLine();  //testing
            int lineCounter = 0;
            String string ="";
            String [] column = string.split(",");   //String.split() method
            
            Scanner scan = new Scanner(filePath);
            
            
            switch (number) {
                case 1:
                System.out.println("Case 1 Single Row was Selected");
                
                //Read and Output line by line
                //https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
                while ((line = reader.readLine()) != null) {
                    //System.out.println("Reading Line " + lineCounter + " " + line);
                    //lineCounter++;
                    
                    System.out.println("reader line is not null");
                    //line = reader.readLine();
                        
                            if (scan.hasNext()) {
                                System.out.println("newline detected in line");
                                // EXECUTE SINGLE QUERY
                                System.out.println(justName + "  is the table name");
                                if (justName.equals("games")) {
                                    String [] arrOfStr = line.split(", ");
                                    String [] temp = new String[1000];
                                    for ( int i = 0; i < 6; i++ ) {
                                        temp[i] = arrOfStr[i];
                                        temp[i] = "'" + temp[i] + "'";
                                    }
                                    String sql = "INSERT INTO " + justName + " values" + " (" 
                                            + temp[0] + "," + temp[1] + "," + temp[2] + ","
                                            + temp[3] + "," + temp[4] + "," + temp[5] + ")";
                                    System.out.println(sql + " is the query");
                                    prepStmt = conn.prepareStatement(sql);
                                    prepStmt.executeUpdate();
                                
                            }    
                        }
                    // C:\Users\kittp\Desktop\Kitt_Phi_Phase2\testFiles\games.txt 

                    //check if string contains \n or New Line
                    //If reader has newLine, execute INSERT
                    //https://stackoverflow.com/questions/5518451/check-if-string-contains-n-java
                    //if (line.contains("\n") == true) {
                    //https://stackoverflow.com/questions/43145232/how-to-read-nextline-of-textfile-in-java

                } // end while
                    //https://stackoverflow.com/questions/30170141/read-data-from-txt-file-and-insert-it-into-database-using-java
                    reader.close();
                    conn.close();
                    prepStmt.close();
                    break;
                case 2:
                    System.out.println("Case 2 Multi-Row was selected");
                    while ((line = reader.readLine()) != null) {
                    //System.out.println("Reading Line " + lineCounter + " " + line);
                    //lineCounter++;
                    
                    System.out.println("reader line is not null");
                    //line = reader.readLine();
                        
                            if (scan.hasNext()) {
                                System.out.println("newline detected in line");
                                // EXECUTE SINGLE QUERY
                                System.out.println(justName + "  is the table name");
                                if (justName.equals("players")) {
                                    String [] arrOfStr = line.split(", ");
                                    String [] temp = new String[100000000];
                                    String [] temp2 = new String[100000000];
                                    String [] row = new String[60000];
                                    String all = "";
                                    for (int j = 0; j < 60000; j++) {   //run 60000 times
                                        for (int k = 0; k < 1; k++) {     //run once
                                            
                                            for ( int i = 0; i < 7; i++ ) {   //run 7  for ( int i = 0; i < 7; i++ ) {
                                                
                                                    temp[i] = arrOfStr[i];
                                                    temp[i] = "'" + temp[i] + "'";  //temp[i] = "'" + temp[i] + "'";
                                                    temp2[i] += temp2[i];
                                                   
                                            }
                                        }
                                        row[j] = " (" + temp[0] + "," + temp[1] + "," + temp[2] + ","
                                               + temp[3] + "," + temp[4] + "," + temp[5] + ","
                                               + temp[6] + "),";
                                    System.out.println(row[j]);
                                    all += row[j] + "\n";
                                    }    
                               // C:\Users\kittp\Desktop\Kitt_Phi_Phase2\testFiles\players.txt
                                  
                                    String sql_intro = "INSERT INTO " + justName + " values (\n Name,\n PlayerID,\n TeamName,\n Position,\n Touchdowns,\n TotalYards,\n Salary\n) VALUES\n (" + all;              
                                    
                                    System.out.println(sql_intro + all +" is the query");
                                    prepStmt = conn.prepareStatement(sql_intro + all);
                                    prepStmt.executeUpdate();
                                
                                }    
                            }
                    

                    } // end while
                    reader.close();
                    conn.close();
                    prepStmt.close();
                break;
                
                case 3:
                    System.out.println("Case 3 Bulk Insert was selected");

                    String sql_intro = "BULK INSERT " + justName + " FROM " + filePath;
                    System.out.println("Entered: " + sql_intro);
                    prepStmt = conn.prepareStatement(sql_intro);
                    prepStmt.executeUpdate();
                    // C:\Users\kittp\Desktop\Kitt_Phi_Phase2\src\testFiles\players.txt
                    // C:\ProgramData\MySQL\MySQL Server 8.0\Uploads\players.txt

           
                    reader.close();
                    conn.close();
                    prepStmt.close();
                break;
                
                case 4:
                    //block
                break;
                
                default:
                    System.out.println("1 - 4 was not entered");
            } // end switch

         
        } catch(Exception e){ 
          System.out.println(e);
        }
    }
}

//  C:\Users\kittp\Desktop\Kitt_Phi_Phase2\testFiles\games.txt
