package view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class graph {	//instancies le template du graph

	
	
	
   public static XYSeries tempIn = new XYSeries("T° In");
  //  XYSeries tempExt = new XYSeries("T° Ext");
    private ChartPanel panel;


    protected ChartPanel trucDraw() {
      /*  for (double i = -12; i<12.99 ; i+=0.005){
            double vars = i + 5;
            tempIn.add(i, vars);//en supposant que votre fonction s'appelle "f"
        }*/

     /*   for (double i = -12; i<13 ; i++){
            double vars = i * 2;
            tempExt.add(i, vars);//en supposant que votre fonction s'appelle "f"
        }*/

        XYSeriesCollection dataset = new XYSeriesCollection();

       // dataset.addSeries(tempExt);
        dataset.addSeries(tempIn);

        JFreeChart chart = ChartFactory.createXYLineChart("Température", "Temps", "°C", dataset, PlotOrientation.VERTICAL, true, true, true);
        panel = new ChartPanel(chart);

        return panel;
    }
}
