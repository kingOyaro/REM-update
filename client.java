/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package real.estate.management;

import java.awt.FlowLayout;
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
import java.text.SimpleDateFormat;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

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
private Font bf;


public client(){
    super("ADD NEW CLIENT");    
    setLayout(new FlowLayout());
    
clientlbl = new JLabel("Client ID");
add (clientlbl);
client = new JTextField(21);
client.setToolTipText("Enter Client ID ");
add(client);

fnamelbl = new JLabel("First Name");
add (fnamelbl);
fname = new JTextField(21);
fname.setToolTipText("Enter First name");
add(fname);

lnamelbl = new JLabel("Last Name");
add (lnamelbl);
lname = new JTextField(21);
lname.setToolTipText("Enter Last name ");
add(lname);

telephonelbl = new JLabel("Telephone");
add (telephonelbl);
telephone = new JTextField(21);
telephone.setToolTipText("Enter Telephone ");
add(telephone);

doblbl = new JLabel("Date of Birth");
add (doblbl);
dob=new SimpleDateFormat("d MMM y");
date= new com.toedter.calendar.JDateChooser();
date.setToolTipText("Enter Date of Birth ");
add(date);

genderlbl = new JLabel("Gender");
add (genderlbl);
male =new JRadioButton("Male", true);
female =new JRadioButton("Female",false);
add(female);
add(male);
group = new ButtonGroup();
group.add(female);
group.add(male);

maritallbl = new JLabel("Marital Status");
add (maritallbl);
married =new JRadioButton("Married", true);
single =new JRadioButton("Single",false);
add(married);
add(single);
group = new ButtonGroup();
group.add(married);
group.add(single);

childrenlbl = new JLabel("Number of children");
add (childrenlbl);
children = new JTextField(21);
children.setToolTipText("Enter Client ID ");
add(children);

houselbl = new JLabel("House Number");
add (houselbl);
house = new JTextField(21);
house.setToolTipText("Enter Client ID ");
add(house);

employeelbl = new JLabel("Employee ID");
add (employeelbl);
employee = new JTextField(21);
employee.setToolTipText("Enter Employee ID ");
add(employee);


save = new JButton("SAVE");
add(save);
clear = new JButton("CLEAR");
add (clear);
back = new JButton("BACK");
add(back);

back.addActionListener(new bc());
clear.addActionListener(new al());
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

public void close(){
       WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
       Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
   }

    public class al implements ActionListener
    {
     @Override
     public void actionPerformed(ActionEvent e)
     {
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
