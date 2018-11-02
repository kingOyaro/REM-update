/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package real.estate.management;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

/**
 *
 * @author KID_UNTAMED
 */
public class piechart extends JFrame {
    connection con = new connection();
    int married,single;
    
        public piechart(String appTitle, String chartTitle) throws SQLException
        {

            
            PieDataset datasets = createDataset2();
            JFreeChart charts = createCharts(datasets, chartTitle);
            ChartPanel chartPanels = new ChartPanel(charts);
            chartPanels.setPreferredSize(new java.awt.Dimension(800, 500));
            setContentPane(chartPanels);
        }

        private PieDataset createDataset2() throws SQLException
        {
            DefaultPieDataset rst = new DefaultPieDataset();
            try{
                ResultSet rs = con.pieData();
                while(rs.next()){
                    
                    String marital_status = rs.getString(3);
                    if("Married".equals(marital_status)){
                        married=married + 1;
                    }
                    else if("Single".equals(marital_status)){
                        single=single + 1;
                    }
                }
                
            }catch(SQLException e){
             Logger.getLogger(piechart.class.getName()).log(Level.SEVERE,null,e);
                
            }
            
            rst.setValue("Married",married);
            rst.setValue("single",single);
            return rst;
    }

        private JFreeChart createCharts(PieDataset datasets,String titles)
        {
            JFreeChart charts = ChartFactory.createPieChart3D(titles, datasets, rootPaneCheckingEnabled, rootPaneCheckingEnabled, rootPaneCheckingEnabled);
            PiePlot3D plots = (PiePlot3D) charts.getPlot();
            plots.setDirection(Rotation.CLOCKWISE);
            plots.setForegroundAlpha(0.5f);
            return charts;
        }
}
