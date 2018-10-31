/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package real.estate.management;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author KID_UNTAMED
 */
public class employee extends JFrame
{
Connection conn = new connection().connect();
    
private final JLabel employeelbl;
private final JTextField employeeid;
private final JLabel usernamelbl;
private final JTextField usernames;
private final JLabel passwordlbl;
private final JPasswordField passwords;
private final JButton save;
private final JButton back;
private final JButton reset;
  
public void close(){
       WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
       Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
   }

public employee(){
    super("EMPLOYEE");    
    setLayout(new FlowLayout());
    
employeelbl = new JLabel("Employee ID");
add (employeelbl);
employeeid = new JTextField(21);
employeeid.setToolTipText("Enter Employee ID ");
add(employeeid);   
    
usernamelbl = new JLabel("Username");
add (usernamelbl);
usernames = new JTextField(21);
usernames.setToolTipText("Enter Username");
add(usernames);

passwordlbl = new JLabel("PASSWORD");
add (passwordlbl);
passwords = new JPasswordField(21);
passwords.setToolTipText("Enter Password");
add(passwords);

save = new JButton("SAVE");
add(save);
reset = new JButton("RESET");
add(reset);
back = new JButton("BACK");
add(back);

back.addActionListener(new bc());
reset.addActionListener(new al());
save.addActionListener((ActionEvent ae) -> {
    try
    {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/remlogin","root","")) {
            Statement pst = connect.createStatement();
            
            String valu1=employeeid.getText();
            String valu2=usernames.getText();
            String valu3=passwords.getText();
            
            String rs="INSERT INTO userlogin VALUES("+"\""+ valu1 +"\""+","+"\""+valu2+"\""+","+"\""+valu3+"\""+")";
            pst.executeUpdate(rs);
            JOptionPane.showMessageDialog(null, "INSERTED SUCCESSFULLY");
            String s1="";
            String s2="";
            String s3="";
            
            usernames.setText(s1);
            employeeid.setText(s2);
            passwords.setText(s3);
        }}
    catch (HeadlessException | ClassNotFoundException | SQLException e){JOptionPane.showMessageDialog(null, e);}
    });
}
        
 public class al implements ActionListener
 {
     @Override
     public void actionPerformed(ActionEvent e)
     {
         String s1="";
         String s2="";
         String s3="";
         
         usernames.setText(s1);
         passwords.setText(s2);
         employeeid.setText(s3);
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

