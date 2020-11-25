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

        double utilization = TaskUtils.calculateUtilization(tasks, 0);
        double task1TimeAtCompletion = tasks.get(0).getInvocation1() / utilization;
        double task2TimeAtCompletion = (tasks.get(1).getInvocation1() / utilization) + task1TimeAtCompletion;
        double task3TimeAtCompletion = (tasks.get(2).getInvocation1() / utilization) + task2TimeAtCompletion;

        System.out.println("Utilization: " + utilization);
        System.out.println("Task 1 Time at Completion: " + task1TimeAtCompletion);
        System.out.println("Task 2 Time at Completion: " + task2TimeAtCompletion);
        System.out.println("Task 3 Time at Completion: " + task3TimeAtCompletion);

        showChart(getChart());
    }

    private void showChart(JFreeChart chart) {
        // to do: if missed dead line, we need to show a warning
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
        double[][] data = {{0.0, 2.68, 2.68, 8.0, 8.0, 9.83, 9.83}, {.746, .746, 0.0, 0.0, .546, .546, 0.0}};
        double[][] data2 = {{0.0, 2.68, 2.68, 4.29, 4.29, 10, 10, 12.016, 12.015}, {0.0, 0.0, 0.621, 0.621, 0, 0, 0.496, 0, 0}};
        double[][] data3 = {{0.0, 0.0, 4.29, 4.29, 6.66, 6.66, 14, 14, 17.37, 17.37}, {0.0, 0.0, 0.421, 0.0, 0.0, 0.296, 0.296, 0.0, 0.0, 0.0}};

        double[][] data4 = new double[100][100];

        ds.addSeries("Task 1", data);
        ds.addSeries("Task 2", data2);
        ds.addSeries("Task 3", data3);
        return ds;
    }
}
