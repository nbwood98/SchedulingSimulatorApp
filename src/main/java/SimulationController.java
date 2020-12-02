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

    public void simulate(ArrayList<Task> tasks) throws TaskNotSchedulableException {
        TaskUtils.setWithRounding(false);
        double currentTime = 0;
        ArrayList<Double> utilizations = new ArrayList<>();

        while (tasks.get(0).getExecutionCount() < 2
                || tasks.get(1).getExecutionCount() < 2
                || tasks.get(2).getExecutionCount() < 2) {
            boolean anyRunnable = false;
            for (Task task : tasks) {
                if (task.isRunnable(currentTime)) {
                    anyRunnable = true;
                }
            }
            if (anyRunnable) {
                Task executingTask = Task.getMaxTask();
                // Current time
                // Next deadline of all tasks in the queue (initial release + n * period)
                // Execute the minimum of next deadline - current time
                for (Task task : tasks) {
                    if ((1 + task.getExecutionCount()) * task.getPeriod() + task.getReleaseTime() <
                            (1 + executingTask.getExecutionCount()) * executingTask.getPeriod() + executingTask.getReleaseTime()) {
                        if (task.getExecutionCount() < 2) {
                            executingTask = task;
                        }
                    }
                }

                double utilization = TaskUtils.calculateUtilization(tasks, executingTask);
                executingTask.incrementExecutionCount();

                utilizations.add(utilization);

                currentTime += executingTask.getExecutionTime(utilization);
                System.out.println("Executing Task: " + executingTask.getTaskNumber());
                System.out.println("Execution Count For Task: " + executingTask.getExecutionCount());
                System.out.println("Utilization: " + utilization);
            } else {
                currentTime = TaskUtils.getNextTimeWithExecutableTasks(tasks, currentTime);
                System.out.println("System Idle, Finished at: " + currentTime);
            }
            System.out.println("Current Time: " + currentTime);
            System.out.println("----------------------------------------------------");
            // Now say we know the next task to be executed.
            // Whether the other tasks have been run in this invocation

            // Task has run
        }
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
        double[][] data2 = {{0.0, 2.68, 2.68, 4.29, 4.29, 10, 10, 12.016, 12.015}, {0.0, 0.0, 0.621, 0.621, 0, 0, 0.496, 0.496, 0}};
        double[][] data3 = {{0.0, 4.29, 4.29, 6.66, 6.66, 14, 14, 17.37, 17.37},
                {0.0, 0.0, 0.421, 0.421, 0.0, 0.0, 0.296, 0.296, 0.0}};

        ds.addSeries("Task 1", data);
        ds.addSeries("Task 2", data2);
        ds.addSeries("Task 3", data3);
        return ds;
    }
}
