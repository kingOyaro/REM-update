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
public class barcharts extends JFrame{
    
    connection con = new connection();
   int chi,chig,chi1,chi2,chi3,chi4; 
   public barcharts(String appTitle, String chartTitle) throws SQLException{
        
        CategoryDataset datasets = createDataset();
        JFreeChart charts = createbarcharts(datasets,"number of children");
        ChartPanel chartPanels = new ChartPanel(charts);
        chartPanels.setPreferredSize(new java.awt.Dimension(800, 500));
        setContentPane(chartPanels);
    }
   
    private CategoryDataset createDataset()
        {
            DefaultCategoryDataset rsl = new DefaultCategoryDataset();
            try{
                ResultSet rs = con.pieData();
                while(rs.next()){
                 String chigs = rs.getString(1);
                    chi=Integer.valueOf(chigs);
                    if(0==chi && 3>chi){
                         chi1=chi1 + 1;
                    }
                    else if(3<=chi && 6>chi){
                         chi2=chi2 +1;
                    }
                    else if(6<=chi && 8>chi){
                        chi3=chi3 + 1;
                    }
                    else if(9<=chi){
                        chi4 = chi4 + 1;
                    }
                }
            }
            catch(SQLException e){
             Logger.getLogger(barchart.class.getName()).log(Level.SEVERE,null,e);}
            
            DefaultCategoryDataset ds = new DefaultCategoryDataset();
            ds.addValue(chi1, "0-2", "");
            ds.addValue(chi2, "3-5", "");
            ds.addValue(chi3, "6-8", "");
            ds.addValue(chi4, "9 & over ", "");
            return ds;
            }
    private JFreeChart createbarcharts (CategoryDataset data,String title){
        JFreeChart chart = ChartFactory.createBarChart(title, "children", "number", data);
        chart.setBackgroundPaint(Color.LIGHT_GRAY);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.BLUE);
        plot.setDomainGridlinePaint(Color.BLUE);
        plot.setBackgroundPaint(Color.WHITE);
        NumberAxis rangeAxis =(NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        BarRenderer ren = (BarRenderer) plot.getRenderer();
        GradientPaint groupa = new GradientPaint(0.0f,0.0f,Color.CYAN,0.0f,0.0f,Color.WHITE);
        GradientPaint groupb = new GradientPaint(0.0f,0.0f,Color.ORANGE,0.0f,0.0f,Color.WHITE);
        GradientPaint groupc = new GradientPaint(0.0f,0.0f,Color.MAGENTA,0.0f,0.0f,Color.WHITE);
        GradientPaint groupd = new GradientPaint(0.0f,0.0f,Color.PINK,0.0f,0.0f,Color.WHITE);
        GradientPaint groupe = new GradientPaint(0.0f,0.0f,Color.GREEN,0.0f,0.0f,Color.WHITE);
        GradientPaint groupf = new GradientPaint(0.0f,0.0f,Color.RED,0.0f,0.0f,Color.WHITE);
        ren.setSeriesPaint(1, groupa);
        ren.setSeriesPaint(2, groupb);
        ren.setSeriesPaint(3, groupc);
        ren.setSeriesPaint(4, groupd);
        ren.setSeriesPaint(5, groupe);
        ren.setSeriesPaint(6, groupf);
        
        org.jfree.chart.axis.CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 1.0));
        return chart;
    }
}
