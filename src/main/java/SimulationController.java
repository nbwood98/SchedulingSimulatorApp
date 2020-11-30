import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class SimulationController {

    private static final String CHART_TITLE = "Simulation Output";

    public SimulationController() {

    }

    public void simulate(ArrayList<Task> tasks) throws TaskNotSchedulableException {
        // ToDo: Need logic to setup ordering of tasks based on EDF
        // ToDo: Refactoring to accommodate release times (will need to be evaluated at every task)

        ArrayList<Task> EDFSortedTasks = TaskUtils.getEDFOrderedTasks(tasks);
        TaskUtils.setPeriodsForTasks(EDFSortedTasks);
        Map<Integer, Integer> taskNumbertoOrderMap = TaskUtils.getEDFToInputTaskMapping(EDFSortedTasks, tasks);

        Task task1 = EDFSortedTasks.get(0);
        Task task2 = EDFSortedTasks.get(1);
        Task task3 = EDFSortedTasks.get(2);

        /*
         * Invocation 1
         */
        double currentTime = 0;
        ArrayList<Double> utilizations = new ArrayList<>();

        // Task 1
        double utilization = TaskUtils.calculateUtilization(task1.getWorstCaseComputationTime(),
                task2.getWorstCaseComputationTime(),
                task3.getWorstCaseComputationTime(),
                false);
        currentTime += task1.getInvocation1() / utilization;
        utilizations.add(utilization);
        System.out.println("Utilization for Task 1: " + utilization);
        System.out.println("Current Time After Task 1: " + currentTime);

        // Task 2
        utilization = TaskUtils.calculateUtilization(task1.getInvocation1(),
                task2.getWorstCaseComputationTime(),
                task3.getWorstCaseComputationTime(),
                false);
        currentTime += task2.getInvocation1() / utilization;
        utilizations.add(utilization);
        System.out.println("Utilization for Task 2: " + utilization);
        System.out.println("Current Time After Task 2: " + currentTime);

        // Task 3
        utilization = TaskUtils.calculateUtilization(task1.getInvocation1(),
                task2.getInvocation1(),
                task3.getWorstCaseComputationTime(),
                false);
        currentTime += task3.getInvocation1() / utilization;
        utilizations.add(utilization);
        System.out.println("Utilization for Task 3: " + utilization);
        System.out.println("Current Time After Task 3: " + currentTime);

        if (currentTime < (task1.getPeriod() + task1.getReleaseTime())) {
            currentTime = task1.getPeriod() + task1.getReleaseTime();
        }

        /*
         * Invocation 2
         */

        // ToDo: Finish implementing these numbers properly

        // Task 1
        utilization = TaskUtils.calculateUtilization(task1.getInvocation1(),
                task2.getWorstCaseComputationTime(),
                task3.getWorstCaseComputationTime(),
                false);
        currentTime += task2.getInvocation1() / utilization;
        utilizations.add(utilization);
        System.out.println("Utilization for Task 1: " + utilization);
        System.out.println("Current Time After Task 1: " + currentTime);

        // Task 2
        utilization = TaskUtils.calculateUtilization(task1.getInvocation1(),
                task2.getInvocation1(),
                task3.getWorstCaseComputationTime(),
                false);
        currentTime += task3.getInvocation1() / utilization;
        utilizations.add(utilization);
        System.out.println("Utilization for Task 2: " + utilization);
        System.out.println("Current Time After Task 2: " + currentTime);

        // Task 3
        utilization = TaskUtils.calculateUtilization(task1.getInvocation1(),
                task2.getInvocation1(),
                task3.getWorstCaseComputationTime(),
                false);
        currentTime += task3.getInvocation1() / utilization;
        utilizations.add(utilization);
        System.out.println("Utilization for Task 3: " + utilization);
        System.out.println("Current Time After Task 3: " + currentTime);

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
        return ChartFactory.createXYLineChart("Task Simulation Output Graph", "Time", "Frequency", ds,
                PlotOrientation.VERTICAL, true, true, false);
    }

    private XYDataset createDataset() {
        DefaultXYDataset ds = new DefaultXYDataset();
        double[][] data = {{0.0, 2.68, 2.68, 8.0, 8.0, 9.83, 9.83}, {.746, .746, 0.0, 0.0, .546, .546, 0.0}};
        double[][] data2 = {{0.0, 2.68, 2.68, 4.29, 4.29, 10, 10, 12.016, 12.015}, {0.0, 0.0, 0.621, 0.621, 0, 0, 0.496,  0.496, 0}};
        double[][] data3 = {{0.0, 4.29, 4.29, 6.66, 6.66, 14, 14, 17.37, 17.37},
                            {0.0, 0.0, 0.421, 0.421, 0.0, 0.0, 0.296, 0.296, 0.0}};

        ds.addSeries("Task 1", data);
        ds.addSeries("Task 2", data2);
        ds.addSeries("Task 3", data3);
        return ds;
    }
}
