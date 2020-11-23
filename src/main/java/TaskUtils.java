import java.util.ArrayList;

public class TaskUtils {

    public static double calculateUtilization(ArrayList<Task> tasks, int iteration) {
        double sum = 0;

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


        return sum;
    }

}
