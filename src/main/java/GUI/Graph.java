package GUI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graph {

    JFreeChart chart;
    XYSeriesCollection dataset;
    XYSeries values;
    ChartPanel chartPanel;
    public Graph() {
        this.dataset = new XYSeriesCollection();
        this.values = new XYSeries("Simulation");
        this.dataset.addSeries(this.values);
    }

    public ChartPanel createGraph() {
        this.chart = ChartFactory.createXYLineChart(
                "Simulation graph",
                "Replications",
                "Value",
                this.dataset
        );
        this.chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(0,100,800,500);
        chartPanel.setVisible(true);
        //this.dataset.addChangeListener(datasetChangeEvent -> chartPanel.updateUI());
        return this.chartPanel;
    }

    public void addData(int iteration, double value) {
        this.values.add(iteration, value);
        this.setYRange(this.values.getMinY(), this.values.getMaxY());
    }

    public void clearData() {
        this.values.clear();
    }

    public void setYRange(double lower, double upper) {
        XYPlot plot = (XYPlot) chart.getPlot();
        NumberAxis y = (NumberAxis) plot.getRangeAxis();
        y.setRange(lower-2, upper+2);
    }

}
