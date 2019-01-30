/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

    
   
   public String findPath(String adr) throws SQLException{
       
        String query=null;
        String result =null;
        
        query = "select FILE_PATH from Conversation where IP_ADRESSE LIKE \""+adr+"\"";
        Statement stmt = this.con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while(rs.next()){
            result=rs.getObject("FILE_PATH").toString();
        }
        
            return result;
        
    }
    
   
   public void createRow(String adr) throws SQLException{
        String query=null;
        int out;
        
        query="insert into Conversation values(0,"+adr+",/home/anser/NetBeansProjects/BE_CHAT"+adr+".txt)";
        Statement st = this.con.createStatement();
        out = st.executeUpdate(query);
        
   }
    
   
    public void saveMessage(String mess, String path) throws IOException{
        
         FileWriter f = new FileWriter(path,true);
         f.write(mess+"\n");
         f.flush();
         f.close();
    }    
        
    
    public void deco() throws SQLException{
        this.con.close();
    }
    
    
    

public static void main(String [] args) throws SQLException, ClassNotFoundException, IOException{
    Model mo = new Model();
    String result;
    
    String query="SELECT * from Conversation";
    Statement st = mo.con.createStatement();
    ResultSet rs = st.executeQuery(query);
      while(rs.next()){
           System.out.println(rs.getObject("FILE_PATH").toString());
        }
    if((result = mo.findPath("192.168.1.2"))!=null){
        mo.saveMessage("ouii", result);
    }
    
    
    
}


}