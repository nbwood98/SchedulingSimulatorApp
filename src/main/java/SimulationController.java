import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SimulationController {

    private static final String CHART_TITLE = "Simulation Output";

    public SimulationController() {

    }

    public void simulate(ArrayList<Task> tasks) {
        for (Task enabledTask : tasks) {
            System.out.println(enabledTask.getWorstCaseComputationTime());
        }
        showChart(getChart());
    }

    private void showChart(JFreeChart chart) {
        JFrame chartFrame = new JFrame(CHART_TITLE);
        JPanel chartPanel = new ChartPanel(chart);
        chartFrame.setContentPane(chartPanel);
        chartFrame.setPreferredSize(
                new Dimension(SchedulingSimulatorApp.APPLICATION_WIDTH, SchedulingSimulatorApp.APPLICATION_HEIGHT));
        chartFrame.pack();
        chartFrame.setVisible(true);
    }

    private JFreeChart getChart() {

        // ToDo: Need a way to set bounds on the chart, dependent on max time and start at 0

        XYDataset ds = createDataset();
        return ChartFactory.createXYLineChart("Test Simulation Chart", "Time", "Frequency", ds,
                PlotOrientation.VERTICAL, true, true, false);
    }

    private XYDataset createDataset() {
        DefaultXYDataset ds = new DefaultXYDataset();
        double[][] data = {{0.1, 0.2, 0.3}, {1, 2, 3}};
        double[][] data2 = {{0.2, 0.3, 0.4}, {1, 2, 3}};
        double[][] data3 = {{0.3, 0.4, 0.5}, {1, 2, 3}};
        ds.addSeries("Task 1", data);
        ds.addSeries("Task 2", data2);
        ds.addSeries("Task 3", data3);
        return ds;
    }
}
