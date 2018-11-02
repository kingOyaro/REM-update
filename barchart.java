/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package real.estate.management;

import java.awt.Color;
import java.awt.GradientPaint;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author KID_UNTAMED
 */
public class barchart extends JFrame{
    connection con = new connection();
    int age,ageg,agegrpa,agegrpb,agegrpc,agegrpd,agegrpe,agegrpf;
    
    
    public barchart(String appTitle, String chartTitle) throws SQLException{
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createbarchart(dataset,"age group");
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
        setContentPane(chartPanel);
       
    }
    
    private CategoryDataset createDataset()
        {
            DefaultCategoryDataset rsl = new DefaultCategoryDataset();
            try{
                ResultSet rs = con.pieData();
                while(rs.next()){
                 String agegr = rs.getString(1);
                    age=Integer.valueOf(agegr);
                    if(18<age && 30>=age){
                         agegrpa=agegrpa + 1;
                    }
                    else if(31<age && 40>=age){
                         agegrpb=agegrpb +1;
                    }
                    else if(41<age && 50>=age){
                        agegrpc=agegrpc + 1;
                    }
                    else if(51 >= age && 60<=age){
                        agegrpd = agegrpd + 1;
                    }
                    else if(61 >= age && 70<=age){
                        agegrpe = agegrpe + 1;
                    }
                    else if(70<age){
                        agegrpf = agegrpf + 1;
                    }
                }
            }
            catch(SQLException e){
             Logger.getLogger(barchart.class.getName()).log(Level.SEVERE,null,e);}
            
            DefaultCategoryDataset ds = new DefaultCategoryDataset();
            ds.addValue(agegrpa, "18-30", "");
            ds.addValue(agegrpb, "31-40", "");
            ds.addValue(agegrpc, "41-50", "");
            ds.addValue(agegrpd, "51-60", "");
            ds.addValue(agegrpe, "61-70", "");
            ds.addValue(agegrpf, "over 70", "");
            return ds;
            }
    
   
    
    private JFreeChart createbarchart(CategoryDataset data,String title){
        JFreeChart charts = ChartFactory.createBarChart(title, "age group", "number", data);
        charts.setBackgroundPaint(Color.LIGHT_GRAY);
        CategoryPlot plots = charts.getCategoryPlot();
        plots.setRangeGridlinePaint(Color.BLUE);
        plots.setDomainGridlinePaint(Color.BLUE);
        plots.setBackgroundPaint(Color.WHITE);
        NumberAxis rangeAxis =(NumberAxis) plots.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        BarRenderer rens = (BarRenderer) plots.getRenderer();
        GradientPaint groupa = new GradientPaint(0.0f,0.0f,Color.CYAN,0.0f,0.0f,Color.WHITE);
        GradientPaint groupb = new GradientPaint(0.0f,0.0f,Color.ORANGE,0.0f,0.0f,Color.WHITE);
        GradientPaint groupc = new GradientPaint(0.0f,0.0f,Color.MAGENTA,0.0f,0.0f,Color.WHITE);
        GradientPaint groupd = new GradientPaint(0.0f,0.0f,Color.PINK,0.0f,0.0f,Color.WHITE);

        rens.setSeriesPaint(1, groupa);
        rens.setSeriesPaint(2, groupb);
        rens.setSeriesPaint(3, groupc);
        rens.setSeriesPaint(4, groupd);

        
        org.jfree.chart.axis.CategoryAxis domainAxis = plots.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 1.0));
        return charts;
    }
    
}
