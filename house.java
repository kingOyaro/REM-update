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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KID_UNTAMED
 */
public class house extends JFrame{
    
String status;
private final JRadioButton rent;
private final JRadioButton sale;
private final ButtonGroup group;
private final JLabel houselbl;
private final JTextField house;
private final JLabel statuslbl;
private final JLabel locationlbl;
private final JTextField location;
private final JLabel desclbl;
private final JTextArea desc;
private final JButton save;
private final JButton clear;
private final JButton back;
private final JButton retrieve;
private final JTable dataTable;
JPanel tablepanel;
JScrollPane scrollpanel;

@SuppressWarnings("empty-statement")
public house() throws SQLException{
    super("LOGIN");    
    setLayout(null);
   
houselbl= new JLabel("HOUSE NUMBER");
houselbl.setBounds(500,120, 105, 20);
add (houselbl);
house = new JTextField(21);
house.setBounds(600, 120, 105, 20);
house.setToolTipText("");
add(house);   
  
statuslbl = new JLabel("STATUS");
statuslbl.setBounds(500, 170, 105, 20);
add (statuslbl);
sale =new JRadioButton("Sale", true);
sale.setBounds(600, 170, 105, 20);
rent =new JRadioButton("Rent",false);
rent.setBounds(700, 170, 105, 20);
add(rent);
add(sale);
group = new ButtonGroup();
group.add(rent);
group.add(sale);

locationlbl= new JLabel("LOCATION");
locationlbl.setBounds(500, 220, 105, 20);
add (locationlbl);
location = new JTextField(21);
location.setBounds(600, 220, 105, 20);
location.setToolTipText("");
add(location);   
  
desclbl= new JLabel("DESCRIPTION");
desclbl.setBounds(500, 270, 105, 20);
add (desclbl);
desc = new JTextArea();
desc.setColumns(40);
desc.setBounds(600, 270, 200, 100);
desc.setToolTipText("description of house");
add(desc); 

dataTable= new JTable() ;
dataTable.setBounds(900, 120, 800, 600);
dataTable.setVisible(true);
add(dataTable);

scrollpanel = new JScrollPane();
scrollpanel.setBounds(900, 120, 800, 450);
add(scrollpanel);
scrollpanel.setViewportView(dataTable);

clear = new JButton("CLEAR");
clear.setBounds(500, 420, 105, 20);
add (clear);
back = new JButton("BACK");
back.setBounds(600, 420, 105, 20);
add(back);
save = new JButton("SAVE");
save.setBounds(700, 420, 105, 20);
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
            if(sale.isSelected()){
                status="Sale";
            }
            if(rent.isSelected()){           
                status="Rent";
            }
            String valu2=(status);;
            String valu3=location.getText();
            String valu4=desc.getText();
            String rs="INSERT INTO house VALUES("+"\""+ valu1 +"\""+","+"\""+valu2+"\""+","+"\""+valu3+"\""+","+"\""+valu4+"\""+")";
            pst.executeUpdate(rs);
            JOptionPane.showMessageDialog(null, "INSERTED SUCCESSFULLY");
            String s1="";
            String s2="";
            String s3="";
            String s4="";
            
            house.setText(s1);
            desc.setText(s2);
            location.setText(s3);
        }
    }
    catch (HeadlessException | ClassNotFoundException | SQLException e){JOptionPane.showMessageDialog(null, e);}
    })
        ;
}
private void dataTableMouseClicked(java.awt.event.MouseEvent evt){
    
              String houses = dataTable.getValueAt(dataTable.getSelectedRow(), 1).toString();
              String statuse = dataTable.getValueAt(dataTable.getSelectedRow(), 2).toString();
              String locate = dataTable.getValueAt(dataTable.getSelectedRow(), 3).toString();
              String describe = dataTable.getValueAt(dataTable.getSelectedRow(), 4).toString();

              
              house.setText(houses);           
              if("rent".equals(statuse))
                  group.setSelected(rent.getModel(), true);
              else
                  group.setSelected(sale.getModel(), true);
              location.setText(locate);
              desc.setText(describe);
        
}

private void retrieve(){
        try {
            DefaultTableModel tbl = new connection().getData1();
            dataTable.setModel(tbl);
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
         desc.setText(s2);
         location.setText(s3);
   
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
}
