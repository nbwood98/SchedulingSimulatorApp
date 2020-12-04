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

    // ToDo: Push error buffer for missed deadlines

    public void simulate(ArrayList<Task> tasks, boolean roundingEnabled) throws TaskNotSchedulableException {
        TaskUtils.setWithRounding(roundingEnabled);
        double currentTime = 0;

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
                for (Task task : tasks) {
                    if ((1 + task.getExecutionCount()) * task.getPeriod() + task.getReleaseTime() <
                            (1 + executingTask.getExecutionCount()) * executingTask.getPeriod() + executingTask.getReleaseTime()) {
                        if (task.getExecutionCount() < 2) {
                            executingTask = task;
                        }
                    }
                }
                executingTask.pushPoint(currentTime, 0);
                double utilization = TaskUtils.calculateUtilization(tasks, executingTask);
                executingTask.pushPoint(currentTime, utilization);

                executingTask.incrementExecutionCount();

                currentTime += executingTask.getExecutionTime(utilization);
                executingTask.pushPoint(currentTime, utilization);
                executingTask.pushPoint(currentTime, 0);
            } else {
                currentTime = TaskUtils.getNextTimeWithExecutableTasks(tasks, currentTime);
            }
        }
        showChart(getChart(tasks));
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

    private JFreeChart getChart(ArrayList<Task> tasks) {

        XYDataset ds = createDataset(tasks);
        return ChartFactory.createXYLineChart("Task Simulation Output Graph", "Time", "Frequency", ds,
                PlotOrientation.VERTICAL, true, true, false);
    }

    private XYDataset createDataset(ArrayList<Task> tasks) {
        DefaultXYDataset ds = new DefaultXYDataset();
        for (Task task : tasks) {
            ds.addSeries("Task " + task.getTaskNumber(), task.getPointBuffer());
        }
        return ds;
    }
}
