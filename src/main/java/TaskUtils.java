import java.util.ArrayList;

public class TaskUtils {

    private static boolean withRounding = false;

    public static double calculateUtilization(ArrayList<Task> tasks, Task executingTask) throws TaskNotSchedulableException {
        double sum = 0;

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

    // ToDo: Something seems off here. CurrentTime should probably factor into this somehow.

    public static double getNextTimeWithExecutableTasks(ArrayList<Task> tasks, double currentTime) {
        double lowestPeriod = Double.MAX_VALUE;
        for (Task task : tasks) {
            if (task.getPeriod() < lowestPeriod) {
                lowestPeriod = task.getPeriod() * task.getExecutionCount();
            }
        }
        return lowestPeriod;
    }

}
