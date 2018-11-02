/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package real.estate.management;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author KID_UNTAMED
 */
public class home extends JFrame{
private final JButton addhouse;
private final JButton addclients;
private final JButton addemployee;
private final JButton stats;
private final JButton addrent;
private final JButton logout;

public home(){
    super("HOME");    
    setLayout(null);
    
addclients = new JButton("ADD CLIENT");
addclients.setBounds(800,200, 140, 40);
add(addclients);
addclients.addActionListener(new ac());

addemployee = new JButton("ADD EMPLOYEE");
addemployee.setBounds(800,300, 140, 40);
addemployee.addActionListener(new ae());
add (addemployee);

addhouse = new JButton("HOUSE");
addhouse.setBounds(800,400, 140, 40);
addhouse.addActionListener(new ah());
add (addhouse);

stats = new JButton("STATISTICS");
stats.setBounds(800,500, 140, 40);
stats.addActionListener(new st());
add (stats);

addrent = new JButton("RENT");
addrent.setBounds(800,600, 140, 40);
addrent.addActionListener(new ar());
add (addrent);

logout = new JButton("LOG OUT");
logout.setBounds(1400,800, 140, 40);
logout.addActionListener(new lo());
add (logout);
}

public void close(){
       WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
       Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
   }

public class ac implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        close();
        client fm = new client();
        fm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fm.setSize(1000, 800);
        fm.setVisible(true); 
        
    }
}
public class ae implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
    close();
    employee emp =new employee();
    emp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    emp.setSize(1000, 800);
    emp.setVisible(true); 
    }
}
public class ah implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        try {
            close();
            house hs = new house();
            hs.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            hs.setSize(1000, 800);
            hs.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
public class st implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        close();
        statistics stat = new statistics();
        stat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        stat.setSize(1000, 800);
        stat.setVisible(true);
    }
}

public class ar implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        try {
            close();
            rent rn = new rent();
            rn.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            rn.setSize(1000, 800);
            rn.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

public class lo implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        login vlog = null;
        try {
            close();
            vlog = new login();
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        vlog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vlog.setSize(1000, 800);
        vlog.setVisible(true);
    }
}
}
