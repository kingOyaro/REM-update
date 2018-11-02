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
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author KID_UNTAMED
 */
public class statistics extends JFrame{
    
private final  JButton pcharts;
private final  JButton pchart;
private final  JButton bchart;
private final  JButton bcharts;
private final JButton back;

    public statistics()
    {
    super("STATISTICS");    
    setLayout(null);
    pcharts = new JButton("PIE CHARTS(marital status)");
    pcharts.setBounds(500,120, 105, 20);
    add(pcharts);
    pcharts.addActionListener(new ctps());
    
    pchart = new JButton("PIE CHARTS(gender)");
    pchart.setBounds(600,120, 105, 20);
    add(pchart);
    pchart.addActionListener(new ctp());

    bchart = new JButton("BAR CHART (age)");
    bchart.setBounds(500, 170, 105, 20);
    add(bchart);
    bchart.addActionListener(new btp());
    
    bcharts = new JButton("BAR CHART (children)");
    bcharts.setBounds(600, 170, 105, 20);
    add(bcharts);
    bcharts.addActionListener(new btps());
       
    back = new JButton("BACK");
    back.setBounds(500, 220, 105, 20);
    add(back);
    back.addActionListener(new bc());
}
    public class ctp implements ActionListener
{
   @Override
   public void actionPerformed(ActionEvent ce) 
   {  
       try {
           piechart pies = new piechart("distribution", "Marital status chart ");
           pies.pack();
           
           pies.setVisible(true);
       } catch (SQLException ex) {
           Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
}
    
        public class ctps implements ActionListener
{
   @Override
   public void actionPerformed(ActionEvent ce) 
   {  
       try {
           pecharts pies = new pecharts("distribution", "Gender chart ");
           pies.pack();
           
           pies.setVisible(true);
       } catch (SQLException ex) {
           Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
}
    
    public class btp implements ActionListener
{
   @Override
   public void actionPerformed(ActionEvent cet) 
   {
       try {
           barchart bc = new barchart("AGES REGISTERED", "age group ");
           bc.pack();
           RefineryUtilities.centerFrameOnScreen(bc);
           bc.setVisible(true);
       } catch (SQLException ex) {
           Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
       }
   } 
    }
    
        public class btps implements ActionListener
{
   @Override
   public void actionPerformed(ActionEvent cet) 
   {
       try {
           barcharts bc = new barcharts("NUMBER OF CHILDREN", "number of children ");
           bc.pack();
           RefineryUtilities.centerFrameOnScreen(bc);
           bc.setVisible(true);
       } catch (SQLException ex) {
           Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
       }
   } 
    }
    
    
    public void close(){
       WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
       Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
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
