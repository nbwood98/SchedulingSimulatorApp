import java.util.ArrayList;

public class TaskUtils {

    private static double[] periods;

    public static double calculateUtilization(double time1, double time2, double time3, boolean withRounding) {
        double sum = 0;
        sum += (time1 / periods[0]) + (time2 / periods[1]) + (time3 / periods[2]);

        if (sum > 1) {
            // throw new UtilizationExceeds1Exception('Message Error);
            System.out.println("Utilization exceeds 1");
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

    public static void setPeriodsForTasks(ArrayList<Task> tasks) {
        periods = new double[3];
        for (int i = 0; i < 3; i++) {
            periods[i] = tasks.get(i).getPeriod();
        }
    }

}
