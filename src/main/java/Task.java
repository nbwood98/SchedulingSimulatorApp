import java.io.Serializable;

public class Task implements Serializable {

    private final int taskNumber;

    private double worstCaseComputationTime;
    private double period;

    public Task(int taskNumber) {
        this.taskNumber = taskNumber;
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

    // ToDo: Implement equals and hash code methods if they are required for equality
    //  checks (probably not)
}
