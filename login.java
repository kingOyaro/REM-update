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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class login extends JFrame {

    private static void addActionListener(ActionListener actionListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Connection conn = new connection().connect();
    
private final JLabel usernamelbl;
private final JLabel passwordlbl;
private final JTextField usernames;
private final JPasswordField passwords;
private final JButton enter;
private final JButton reset;

public login() throws SQLException{
    super("LOGIN");    
    setLayout(new FlowLayout());

usernamelbl = new JLabel("USERNAME");
add (usernamelbl);

usernames = new JTextField(21);
usernames.setToolTipText("Enter username");
add(usernames);

passwordlbl = new JLabel("PASSWORD");
add (passwordlbl);

passwords = new JPasswordField(21);
passwords.setToolTipText("Enter Password");
add(passwords);

enter = new JButton("ENTER");
add(enter);

reset = new JButton("RESET");
add(reset);

enter.addActionListener(new ac());
reset.addActionListener(new al());
}

public void close(){
       WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
       Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
   }
    public class ac implements ActionListener
    {
      @Override
      public void actionPerformed(ActionEvent ae)
      {
          try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/remlogin","root","");
            String rs = "select * from userlogin where usernames=? && passwords=?";    
            
            PreparedStatement state = connect.prepareStatement(rs);
            
            state.setString(1,usernames.getText());
            state.setString(2,passwords.getText());
            
            ResultSet rst = state.executeQuery();
            
            if(rst.next())
            {
                JOptionPane.showMessageDialog(null, "ACCESS GRANTED :)");
               
                home hm = new home();
                close();
                hm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                hm.setSize(1000, 800);
                hm.setVisible(true);
                connect.close();
                
            }
            else
            {
              JOptionPane.showMessageDialog(null,"Invalid username or Password");  
            }
          }
          catch(HeadlessException | ClassNotFoundException | SQLException e)
          {
              System.out.println(e);
          }
          }
    }
 public class al implements ActionListener
 {
     @Override
     public void actionPerformed(ActionEvent e)
     {
         String s1="";
         String s2="";
         
         usernames.setText(s1);
         passwords.setText(s2);
     }
 }
}
