import java.util.ArrayList;

public class TaskUtils {
    // calc ulti should output 6 different u, if task completed then use the actual time, if not use the worst time
    // maybe we can use the variable name to do the calc
    public static double calculateUtilization(ArrayList<Task> tasks, int iteration) {
        double sum = 0;
        double currntTime = 0;

        switch (iteration) {
            case 1:
                for (Task task : tasks) {
                    if (task != null) {
                        sum += (task.getWorstCaseComputationTime() / task.getPeriod());
                    }
                }

            case 2:

            case 3:

            default:

        }
    public     

        return sum;
    }

}
