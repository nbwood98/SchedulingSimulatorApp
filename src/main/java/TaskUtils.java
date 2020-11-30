import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaskUtils {

    private static double[] periods;
    private static boolean withRounding = false;

    public static double calculateUtilization(double time1, double time2, double time3, boolean withRounding) throws TaskNotSchedulableException {
        double sum = 0;
        sum += (time1 / periods[0]) + (time2 / periods[1]) + (time3 / periods[2]);

        if (sum > 1) {
            throw new TaskNotSchedulableException("Task not schedulable. Utilization exceeds 1.");
        }
        if (withRounding) {
            if (sum <= 0.5) {
                return 0.5;
            } else if (sum <= 0.75) {
                return 0.75;
            }
            return 1;
        }
        return sum;
    }

    public static double calculateUtilization(ArrayList<Task> tasks, Task executingTask) throws TaskNotSchedulableException {
        double sum = 0;
        // ToDo: Implement a list or map mapping (if time permitting) to make this scalable.
        for (Task task : tasks) {
            if (task == executingTask || task.getExecutionCount() == 0) {
                sum += task.getWorstCaseComputationTime() / task.getPeriod();
            } else if (task.getExecutionCount() == 1) {
                sum += task.getInvocation1() / task.getPeriod();
            } else if (task.getExecutionCount() == 2) {
                sum += task.getInvocation2() / task.getPeriod();
            }
        }
        if (sum > 1) {
            throw new TaskNotSchedulableException("Task not schedulable. Utilization exceeds 1.");
        }
        if (withRounding) {
            if (sum <= 0.5) {
                return 0.5;
            } else if (sum <= 0.75) {
                return 0.75;
            }
            return 1;
        }
        return sum;
    }

    public static void setWithRounding(boolean rounding) {
        withRounding = rounding;
    }

    public static void setPeriodsForTasks(ArrayList<Task> tasks) {
        periods = new double[3];
        for (int i = 0; i < 3; i++) {
            periods[i] = tasks.get(i).getPeriod();
        }
    }

    public static ArrayList<Task> getEDFOrderedTasks(ArrayList<Task> unorderedTasks) {
        ArrayList<Task> orderedTasks = new ArrayList<>();
        ArrayList<Task> remainingTasks = new ArrayList<>(unorderedTasks);

        // ToDo: Verify what the consequences will be if somehow there are no enabled tasks
        while (!remainingTasks.isEmpty()) {
            Task lowestTask = remainingTasks.stream().findFirst().get();
            for (Task task : remainingTasks) {
                if (task.getPeriod() < lowestTask.getPeriod()) {
                    lowestTask = task;
                }
            }
            orderedTasks.add(lowestTask);
            remainingTasks.remove(lowestTask);
        }

        return orderedTasks;
    }

    public static Map<Integer, Integer> getEDFToInputTaskMapping(ArrayList<Task> sortedTasks, ArrayList<Task> unsortedTasks) {
        Map<Integer, Integer> map = new HashMap<>();
        int sortedTaskCount = 0;
        int unsortedTaskCount = 0;
        for (Task sortedTask : sortedTasks) {
            sortedTaskCount++;
            for (Task unsortedTask : unsortedTasks) {
                unsortedTaskCount++;
                if (sortedTask.getTaskNumber() == unsortedTask.getTaskNumber()) {
                    map.put(sortedTaskCount, unsortedTaskCount);
                    unsortedTaskCount = 0;
                    break;
                }
            }
        }
        return map;
    }

}
