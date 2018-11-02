/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package real.estate.management;

import java.awt.Font;
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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KID_UNTAMED
 */
public class client extends JFrame {

    private static void addActionListener(ActionListener actionListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
String gender;
String maritalstatus;
double age;

private JScrollPane scpane;
private JLabel titlelbl;
private JLabel title;
private final JLabel fnamelbl;
private final JLabel lnamelbl;
private final JLabel clientlbl;
private final JLabel houselbl;
private final JLabel childrenlbl;
private final JLabel employeelbl;
private final JLabel telephonelbl;
private final JLabel doblbl;
private final JLabel genderlbl;
private JRadioButton male;
private JRadioButton female;
private final JLabel maritallbl;
private JRadioButton married;
private JRadioButton single;
private ButtonGroup group;
private JTextField fname;
private JTextField client;
private JTextField employee;
private JTextField house;
private JTextField lname;
private JTextField children;
private SimpleDateFormat dob;
com.toedter.calendar.JDateChooser date;
private JTextField telephone;
private final JButton clear;
private final JButton back;
private final JButton save;
private final JButton retrieve;
private Font bf;
private final JTable dataTable;
JPanel tablepanel;
JScrollPane scrollpanel;


public client(){
    super("ADD NEW CLIENT");    
    setLayout(null);
    
clientlbl = new JLabel("Client ID");
clientlbl.setBounds(500,120, 105, 20);
add (clientlbl);
client = new JTextField(21);
client.setBounds(600, 120, 105, 20);
client.setToolTipText("Enter Client ID ");
add(client);

fnamelbl = new JLabel("First Name");
fnamelbl.setBounds(500, 170, 105, 20);
add (fnamelbl);
fname = new JTextField(21);
fname.setBounds(600, 170, 105, 20);
fname.setToolTipText("Enter First name");
add(fname);

lnamelbl = new JLabel("Last Name");
lnamelbl.setBounds(500, 220, 105, 20);
add (lnamelbl);
lname = new JTextField(21);
lname.setBounds(600, 220, 105, 20);
lname.setToolTipText("Enter Last name ");
add(lname);

telephonelbl = new JLabel("Telephone");
telephonelbl.setBounds(500, 270, 105, 20);
add (telephonelbl);
telephone = new JTextField(21);
telephone.setBounds(600, 270, 105, 20);
telephone.setToolTipText("Enter Telephone ");
add(telephone);

doblbl = new JLabel("Date of Birth");
doblbl.setBounds(500, 320, 105, 20);
add (doblbl);
dob=new SimpleDateFormat("d MMM y");
date= new com.toedter.calendar.JDateChooser();
date.setBounds(600,320,105,20);
date.setToolTipText("Enter Date of Birth ");
add(date);

genderlbl = new JLabel("Gender");
genderlbl.setBounds(500,370, 105, 20);
add (genderlbl);
male =new JRadioButton("Male", true);
male.setBounds(600, 370, 105, 20);
female =new JRadioButton("Female",false);
female.setBounds(700, 370, 105, 20);
add(female);
add(male);
group = new ButtonGroup();
group.add(female);
group.add(male);

maritallbl = new JLabel("Marital Status");
maritallbl.setBounds(500, 420, 105, 20);
add (maritallbl);
married =new JRadioButton("Married", true);
married.setBounds(600, 420, 105, 20);
single =new JRadioButton("Single",false);
single.setBounds(700, 420, 105, 20);
add(married);
add(single);
group = new ButtonGroup();
group.add(married);
group.add(single);

childrenlbl = new JLabel("Number of children");
childrenlbl.setBounds(500, 470, 105, 20);
add (childrenlbl);
children = new JTextField(21);
children.setBounds(605, 470, 105, 20);
children.setToolTipText("Enter Client ID ");
add(children);

houselbl = new JLabel("House Number");
houselbl.setBounds(500, 520, 105, 20);
add (houselbl);
house = new JTextField(21);
house.setBounds(600, 520, 105, 20);
house.setToolTipText("Enter Client ID ");
add(house);

employeelbl = new JLabel("Employee ID");
employeelbl.setBounds(500, 570, 105, 20);
add (employeelbl);
employee = new JTextField(21);
employee.setBounds(600, 570, 105, 20);
employee.setToolTipText("Enter Employee ID ");
add(employee);

dataTable= new JTable() ;
dataTable.setBounds(900, 120, 800, 600);
dataTable.setVisible(true);
add(dataTable);

scrollpanel = new JScrollPane();
scrollpanel.setBounds(900, 120, 800, 450);
add(scrollpanel);
scrollpanel.setViewportView(dataTable);

save = new JButton("SAVE");
save.setBounds(900, 620, 105, 20);
add(save);
clear = new JButton("CLEAR");
clear.setBounds(1000, 620, 105, 20);
add (clear);
back = new JButton("BACK");
back.setBounds(1100, 620, 105, 20);
add(back);
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
            Double valu1=Double.parseDouble(client.getText()); 
            String valu2=fname.getText();
            String valu3=lname.getText();
            Double valu4=Double.parseDouble(telephone.getText());
            if(male.isSelected()){
                gender="Male";
            }
            if(female.isSelected()){
                gender="Female";
            }
            if(married.isSelected()){
                maritalstatus="Married";
            }
            if(single.isSelected()){
                maritalstatus="Single";
            }
            age=date.getDate().getYear();
            age=2018-age-1900;
            String valu5=dob.format(date.getDate());
            String valu6=(gender);
            String valu7=(maritalstatus);
            Double valu8=Double.parseDouble(children.getText());
            Double valu9=(age);
            Double valu10=Double.parseDouble(house.getText());
            Double valu11=Double.parseDouble(employee.getText());
            
            String rs="INSERT INTO client VALUES("+"\""+ valu1 +"\""+","+"\""+valu2+"\""+","+"\""+valu3+"\""+","+"\""+valu4+"\""+","+"\""+valu5+"\""+","+"\""+valu6+"\""+","+"\""+valu7+"\""+","+"\""+valu8+"\""+","+"\""+valu9+"\""+","+"\""+valu10+"\""+","+"\""+valu11+"\""+")";
            pst.executeUpdate(rs);
            JOptionPane.showMessageDialog(null, "INSERTED SUCCESSFULLY");
            String s1="";
            String s2="";
            String s3="";
            String s4="";
            String s5="";
            String s6="";
            
            client.setText(s1);
            fname.setText(s2);
            lname.setText(s3);
            telephone.setText(s4);
            house.setText(s5);
            employee.setText(s6);
                    
        }
    }
    catch (HeadlessException | ClassNotFoundException | SQLException e){JOptionPane.showMessageDialog(null, e);}
    })
        ;
}
private void dataTableMouseClicked(java.awt.event.MouseEvent evt){
    
              String clients = dataTable.getValueAt(dataTable.getSelectedRow(), 1).toString();
              String fnames = dataTable.getValueAt(dataTable.getSelectedRow(), 2).toString();
              String lnames = dataTable.getValueAt(dataTable.getSelectedRow(), 3).toString();
              String telephones = dataTable.getValueAt(dataTable.getSelectedRow(), 4).toString();
              String genders = dataTable.getValueAt(dataTable.getSelectedRow(), 5).toString();
              String status = dataTable.getValueAt(dataTable.getSelectedRow(), 6).toString();
              String child = dataTable.getValueAt(dataTable.getSelectedRow(), 7).toString();
              String ages = dataTable.getValueAt(dataTable.getSelectedRow(), 8).toString();
              String houses = dataTable.getValueAt(dataTable.getSelectedRow(), 9).toString();
              String employees = dataTable.getValueAt(dataTable.getSelectedRow(), 10).toString();
              
              client.setText(clients);
              fname.setText(fnames);
              lname.setText(lnames);
              telephone.setText(telephones);
              
              if("Female".equals(genders))
                  group.setSelected(female.getModel(), true);
              else
                  group.setSelected(male.getModel(), true);
              if("married".equals(status))
                  group.setSelected(married.getModel(), true);
              else
                  group.setSelected(single.getModel(), true);
              
              children.setText(child);
              try{
                  date.setDate(dob.parse(ages));
              } catch (ParseException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
              house.setText(houses);
              employee.setText(employees);
        
}

private void retrieve(){
        try {
            DefaultTableModel tblm = new connection().getData();
            dataTable.setModel(tblm);
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
         String s5="";
         String s6="";
         
         client.setText(s1);
         fname.setText(s2);
         lname.setText(s3);
         telephone.setText(s4);
         house.setText(s5);
         employee.setText(s6);
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
