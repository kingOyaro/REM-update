/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package real.estate.management;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KID_UNTAMED
 */
public class rent extends JFrame{
    
private final JLabel houselbl;
private final JLabel clientlbl;
private final JLabel receiptlbl;
private final JLabel amountlbl;
private final JLabel doblbl;
private final SimpleDateFormat dop;
com.toedter.calendar.JDateChooser date;
private final JTextField house;
private final JTextField client;
private final JTextField receipt;
private final JTextField amount;
private final JButton save;
private final JButton clear;
private final JButton back;
private final JButton retrieve;
private final JButton balance;
private final JTable dataTable;
JPanel tablepanel;
JScrollPane scrollpanel;

    
  public rent() throws SQLException{
    super("RENT");    
    setLayout(null);  
    
houselbl= new JLabel("HOUSE NUMBER");
houselbl.setBounds(500,120, 105, 20);
add (houselbl);
house = new JTextField(21);
house.setBounds(600, 120, 105, 20);
house.setToolTipText("Enter house number");
add(house);

clientlbl= new JLabel("CLIENT ID");
clientlbl.setBounds(500, 170, 105, 20);
add (clientlbl);
client = new JTextField(21);
client.setBounds(600, 170, 105, 20);
client.setToolTipText("Enter client ID");
add(client);

receiptlbl= new JLabel("RECEIPT NO");
receiptlbl.setBounds(500, 220, 105, 20);
add (receiptlbl);
receipt = new JTextField(21);
receipt.setBounds(600, 220, 105, 20);
receipt.setToolTipText("Enter receipt numbr");
add(receipt);

amountlbl= new JLabel("AMOUNT");
amountlbl.setBounds(500, 270, 105, 20);
add (amountlbl);
amount = new JTextField(21);
amount.setBounds(600, 270, 105, 20);
amount.setToolTipText("Enter amount");
add(amount);

doblbl = new JLabel("Date of Pay");
doblbl.setBounds(500, 320, 105, 20);
add (doblbl);
dop=new SimpleDateFormat("d MMM y");
date= new com.toedter.calendar.JDateChooser();
date.setBounds(600, 320, 105, 20);
date.setToolTipText("Enter payment date ");
add(date);

balance= new JButton("CALCULATE");
balance.setBounds(500, 420, 105, 20);
add (balance);

dataTable= new JTable() ;
dataTable.setBounds(1000, 120, 800, 450);
dataTable.setVisible(true);
add(dataTable);

scrollpanel = new JScrollPane();
scrollpanel.setBounds(1000, 120, 800, 450);
add(scrollpanel);
scrollpanel.setViewportView(dataTable);

clear = new JButton("CLEAR");
clear.setBounds(600, 420, 105, 20);
add (clear);
back = new JButton("BACK");
back.setBounds(700, 420, 105, 20);
add(back);
save = new JButton("SAVE");
save.setBounds(800, 420, 105, 20);
add(save);
retrieve = new JButton("RETRIEVE");
retrieve.setBounds(1200, 620, 105, 20);
add(retrieve);

dataTable.setModel(new javax.swing.table.DefaultTableModel(
new Object[][]{},
new String []{}
));

dataTable.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt){
     dataTableMouseClicked(evt);   
    }
});

back.addActionListener(new bc());
clear.addActionListener(new al());
balance.addActionListener(new bl());
retrieve.addActionListener((java.awt.event.ActionEvent evt)->{
    retrieveActionPerfomed(evt);
    });
save.addActionListener((ActionEvent ae) -> {
    try
    {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/remlogin","root","")) {
            Statement pst = connect.createStatement();
            Double valu1=Double.parseDouble(house.getText());
            Double valu2=Double.parseDouble(client.getText());
            Double valu3=Double.parseDouble(receipt.getText());
            String valu4=dop.format(date.getDate());
            Double valu5=Double.parseDouble(amount.getText());
            String rs="INSERT INTO rent VALUES("+"\""+ valu1 +"\""+","+"\""+valu2+"\""+","+"\""+valu3+"\""+","+"\""+valu4+"\""+","+"\""+valu5+"\""+")";
            pst.executeUpdate(rs);
            JOptionPane.showMessageDialog(null, "INSERTED SUCCESSFULLY");
            String s1="";
            String s2="";
            String s3="";
            String s4="";
            
            house.setText(s1);
            client.setText(s2);
            receipt.setText(s3);
            amount.setText(s4);
                        
        }
    }
    catch (HeadlessException | ClassNotFoundException | SQLException e){JOptionPane.showMessageDialog(null, e);}
    })
        ;
}
  
  private void dataTableMouseClicked(java.awt.event.MouseEvent evt){
    
              String houses = dataTable.getValueAt(dataTable.getSelectedRow(), 1).toString();
              String clients = dataTable.getValueAt(dataTable.getSelectedRow(), 2).toString();
              String receipts = dataTable.getValueAt(dataTable.getSelectedRow(), 3).toString();
              String amounts = dataTable.getValueAt(dataTable.getSelectedRow(), 4).toString();
              String ages = dataTable.getValueAt(dataTable.getSelectedRow(), 5).toString();
              
              house.setText(houses);           
              client.setText(clients);
              receipt.setText(receipts);
              amount.setText(amounts);
              try{
                  date.setDate(dop.parse(ages));
              } catch (ParseException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}

private void retrieve(){
        try {
            DefaultTableModel tb = new connection().getData2();
            dataTable.setModel(tb);
        } catch (SQLException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
}

private void retrieveActionPerfomed(java.awt.event.ActionEvent evt){
    retrieve();
}
  
public void close(){
       WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
       Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
   }
    public class al implements ActionListener
    {
     @Override
     public void actionPerformed(ActionEvent e)
     {
         dataTable.setModel(new DefaultTableModel());
         String s1="";
         String s2="";
         String s3="";
         String s4="";
         
         house.setText(s1);
         client.setText(s2);
         amount.setText(s3);
         receipt.setText(s4); 
     }
 }
    
      public class bc implements ActionListener
    {
     @Override
     public void actionPerformed(ActionEvent e)
     {
                close();
                home hm=new home();
                hm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                hm.setSize(1000, 800);
                hm.setVisible(true);
               
                
     }
    }
       public class bl implements ActionListener
    {
     @Override
     public void actionPerformed(ActionEvent e)
     {
         calculations cl=new calculations();
         cl.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         cl.setSize(1000, 800);
         cl.setVisible(true);
     }
    }
}

