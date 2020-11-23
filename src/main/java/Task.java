import java.io.Serializable;

public class Task implements Serializable {

    private final int taskNumber;

    private int worstCaseComputationTime;
    private int period;
    private int invocation1;
    private int invocation2;

    public Task(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public void setWorstCaseComputationTime(int worstCaseComputationTime) {
        this.worstCaseComputationTime = worstCaseComputationTime;
    }

    public double getWorstCaseComputationTime() {
        return worstCaseComputationTime;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public double getPeriod() {
        return period;
    }

    public void setInvocation1(int invocation1) {
        this.invocation1 = invocation1;
    }

    public int getInvocation1() {
        return invocation1;
    }

    public void setInvocation2(int invocation2) {
        this.invocation2 = invocation2;
    }

    public int getInvocation2() {
        return invocation2;
    }

    // ToDo: Implement equals and hash code methods if they are required for equality
    //  checks (probably not)
}
