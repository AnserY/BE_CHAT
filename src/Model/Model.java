/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author anser
 */
public class Model {
    
    Connection con ;
    
   
    
   public Model() throws SQLException, ClassNotFoundException {
    // Load the JDBC driver
    Class.forName("org.mariadb.jdbc.Driver");
    System.out.println("Driver loaded");

    // Try to connect
    this.con = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "Beats+ipod09");
    
   }

    
   
   private Path dirPath(String adr) throws SQLException{
       
        String query=null;
        Path result =null;
        
        query = "select FILE_PATH from Conversation where IP_ADRESSE LIKE \""+adr+"\"";
        Statement stmt = this.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        
        while(rs.next()){
            result=Paths.get(rs.getString("FILE_PATH"));
        }
        
            return result;
        }
    
   
  
    public void saveMessage(String remoteIp, String message, String who) throws SQLException, IOException{

       FileWriter f;
       Path path = this.dirPath(remoteIp) ;
       if (path==null) {
                path = Paths.get(System.getProperty("user.dir")+File.separator+"src"+File.separator+"Historique"+File.separator+remoteIp+".txt");
                f = new FileWriter(path.toString(),true);
                String request = "INSERT INTO Conversation(IP_ADRESSE,FILE_PATH) VALUES (?,?)";
                try (PreparedStatement ps =con.prepareStatement(request)){
                  ps.setString(1,remoteIp) ;
                  ps.setString(2,path.toString()) ;
                  ps.executeUpdate() ;
                }
               catch (SQLException e) {
                  System.out.println(e.getMessage()) ;
                }

                f.write(" [ " + who + " : " + message + " ] " );
                f.append("\n") ;
                f.flush();
            }
        else {
            f = new FileWriter(path.toString(),true);
            f.write(" [ " + who + " : " + message + " ] \n " );
            f.append("\n") ;
            f.flush();

            }
    }
   
   
   
      public String getStringConv(String remoteIp) throws SQLException, IOException {
        Path file = this.dirPath(remoteIp) ;
        InputStream in = null;
        StringBuilder cBuf = new StringBuilder();
        if (file !=null){
        try {
            in = Files.newInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;

            while ((line = reader.readLine()) != null) {
                cBuf.append("\n");
                cBuf.append(line);
            }
        } catch (IOException x) {
            System.err.println(x);
        } finally {
            if (in != null) in.close();
        }
        return cBuf.toString() ;
        }
        return "";
         // cBuf.toString() will contain the entire file contents
    }
    
      
    public void deco() throws SQLException{
        this.con.close();
    }
    
    

}