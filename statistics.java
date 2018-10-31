/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package real.estate.management;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
private final  JButton bchart;

    public statistics(){
    super("STATISTICS");    
    setLayout(new FlowLayout());
    pcharts = new JButton("PIE CHARTS");
    add(pcharts);
    pcharts.addActionListener(new ctp());

    bchart = new JButton("BAR CHART");
    add(bchart);
    bchart.addActionListener(new btp());
       
}
    public class ctp implements ActionListener
{
   @Override
   public void actionPerformed(ActionEvent ce) 
   {
       try {
           piechart pie = new piechart("age distribution", "gender chart ");
           pie.pack();
           pie.setVisible(true);
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
}
