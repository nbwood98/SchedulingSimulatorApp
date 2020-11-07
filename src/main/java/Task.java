import java.io.Serializable;

public class Task implements Serializable {

    private double executionTime;
    private double releaseTime;
    private double period;
    private double deadline;

    public void setExecutionTime(double executionTime) {
        this.executionTime = executionTime;
    }

    public double getExecutionTime() {
        return executionTime;
    }

    public void setReleaseTime(double releaseTime) {
        this.releaseTime = releaseTime;
    }

    public double getReleaseTime() {
        return releaseTime;
    }

    public void setPeriod(double period) {
        this.period = period;
    }

    public double getPeriod() {
        return period;
    }

    public void setDeadline(double deadline) {
        this.deadline = deadline;
    }

    public double getDeadline() {
        return deadline;
    }

    // ToDo: Implement equals and hash code methods if they are required for equality
    //  checks (probably not)
}
