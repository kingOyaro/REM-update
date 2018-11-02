/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package real.estate.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KID_UNTAMED
 */
public class connection {
    private Connection connection;
    public Connection connect(){
       try {
           Class.forName("com.mysql.jdbc.Driver");
           System.out.println("Connection Success");
       }
       catch(ClassNotFoundException cnfe){
            System.out.println("Connection failed"+cnfe);
       }
       String url ="jdbc:mysql://localhost:3306/remlogin?zeroDateTimeBehavior=convertToNull";
       try{
           connection = (Connection) DriverManager.getConnection(url, "root", "");
           System.out.println("Database Connected");
       }
       catch(SQLException se){
         System.out.println("No Database Connected");
       }
       return connection;
       
    }
   public DefaultTableModel getData() throws SQLException{
       DefaultTableModel tblm = new DefaultTableModel();
       tblm.addColumn("CLIENT ID");
       tblm.addColumn("FIRST NAME");
       tblm.addColumn("LAST NAME");
       tblm.addColumn("TELEPHONE");
       tblm.addColumn("DATE OF BIRTH");
       tblm.addColumn("GENDER");
       tblm.addColumn("MARITAL STATUS");
       tblm.addColumn("NUMBER OF CHILDREN");
       tblm.addColumn("AGE");
       tblm.addColumn("HOUSE NUMBER");
       tblm.addColumn("REGISTERED BY");
       
       
       String datab = "SELECT * FROM client";
       
       try{
           Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/remlogin","root","");
           Statement state = connect.prepareStatement(datab);
           ResultSet rs = state.executeQuery(datab);
           while (rs.next()){
              String client = rs.getString(1);
              String fname = rs.getString(2);
              String lname = rs.getString(3);
              String telephone = rs.getString(4);
              String dob = rs.getString(5);
              String gender = rs.getString(6);
              String status = rs.getString(7);
              String children = rs.getString(8);
              String age = rs.getString(9);
              String house = rs.getString(10);
              String employee = rs.getString(11);
              
              tblm.addRow(new String[]{client,fname,lname,telephone,dob,gender,status,children,age,house,employee});
           }
           return tblm;
       }catch (SQLException e){
   }
       return null;
}
   
      public DefaultTableModel getData1() throws SQLException{
       DefaultTableModel tbl = new DefaultTableModel();
       tbl.addColumn("HOUSE NUMBER");
       tbl.addColumn("STATUS");
       tbl.addColumn("LOCATION");
       tbl.addColumn("DESCRIPTION");
     String datab = "SELECT * FROM house";
       
       try{
           Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/remlogin","root","");
           Statement state = connect.prepareStatement(datab);
           ResultSet rs = state.executeQuery(datab);
           while (rs.next()){
              String house = rs.getString(1);
              String status = rs.getString(2);
              String location = rs.getString(3);
              String desc = rs.getString(4);
              
              tbl.addRow(new String[]{house,status,location,desc});
           }
           return tbl;
       }catch (SQLException e){
   }
       return null;
      }
       
          
      public DefaultTableModel getData2() throws SQLException{
       DefaultTableModel tb = new DefaultTableModel();
       tb.addColumn("HOUSE NUMBER");
       tb.addColumn("CLIENT ID");
       tb.addColumn("RECEIPT NO.");
       tb.addColumn("AMOUNT");
       tb.addColumn("DATE OF PAY");
       
     String datab = "SELECT * FROM rent";
       
       try{
           Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/remlogin","root","");
           Statement state = connect.prepareStatement(datab);
           ResultSet rs = state.executeQuery(datab);
           while (rs.next()){
              String house = rs.getString(1);
              String client = rs.getString(2);
              String receipt = rs.getString(3);
              String amount = rs.getString(4);
              String dop = rs.getString(5);

              
              tb.addRow(new String[]{house,client,receipt,amount,dop});
           }
           return tb;
       }catch (SQLException e){
   }
       return null;
}
   
   public ResultSet pieData() throws SQLException{
       ResultSet rs = null;
       String datab = "SELECT age,gender,marital_status,children FROM client";
       try{           
       Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/remlogin","root","");
       Statement state = connect.prepareStatement(datab);
       rs = state.executeQuery(datab);
   }catch(SQLException e){
}
        return rs;
   
   }
}

