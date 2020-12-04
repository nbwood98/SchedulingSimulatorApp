import java.io.Serializable;

public class Task implements Serializable {

    private final int taskNumber;

    private double worstCaseComputationTime;
    private double period;
    private double invocation1;
    private double invocation2;
    private int releaseTime;
    private int executionCount;

    private final double[][] pointBuffer;
    private int pointCount = 0;

    public Task(int taskNumber) {
        this.taskNumber = taskNumber;
        executionCount = 0;
        pointBuffer = new double[2][9];
        this.pushPoint(0, 0);
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public void setWorstCaseComputationTime(double worstCaseComputationTime) {
        this.worstCaseComputationTime = worstCaseComputationTime;
    }

    public double getWorstCaseComputationTime() {
        return worstCaseComputationTime;
    }

    public void setPeriod(double period) {
        this.period = period;
    }

    public double getPeriod() {
        return period;
    }

    public void setInvocation1(double invocation1) {
        this.invocation1 = invocation1;
    }

    public double getInvocation1() {
        return invocation1;
    }

    public void setInvocation2(double invocation2) {
        this.invocation2 = invocation2;
    }

    public double getInvocation2() {
        return invocation2;
    }

    public void setReleaseTime(int releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getReleaseTime() {
        return releaseTime;
    }

    public int getExecutionCount() {
        return executionCount;
    }

    public void setExecutionCount(int executionCount) {
        this.executionCount = executionCount;
    }

    public void incrementExecutionCount() {
        executionCount++;
    }

    public double getExecutionTime(double utilization) {
        if (executionCount == 0) {
            System.out.println("Error, task executionCount should not be zero here.");
            return this.getWorstCaseComputationTime() / utilization;
        } else if (executionCount == 1) {
            return this.getInvocation1() / utilization;
        } else if (executionCount == 2) {
            return this.getInvocation2() / utilization;
        } else {
            System.out.println("Error, task executionCount should never be higher than 2");
            return 0;
        }
    }

    public boolean isRunnable(double currentTime) {
        if (executionCount >= 2) {
            return false;
        }
        return currentTime >= this.getPeriod() * executionCount;
    }

    public void pushPoint(double x, double y) {
        pointBuffer[0][pointCount] = x;
        pointBuffer[1][pointCount] = y;
        pointCount++;
    }

    public double[][] getPointBuffer() {
        return pointBuffer;
    }

    public static Task getMaxTask() {
        Task task = new Task(100);
        task.setExecutionCount(10000000);
        task.setPeriod(1000000);
        task.setReleaseTime(100000000);
        return task;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
