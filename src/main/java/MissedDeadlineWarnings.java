import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MissedDeadlineWarnings {

    private static final Map<Integer, Double> missedDeadlines = new HashMap<>();

    private MissedDeadlineWarnings() {
    }

    public static void clearWarnings() {
        missedDeadlines.clear();
    }

    public static void showWarnings(Component parentComponent) {
        StringBuilder output = new StringBuilder("Warning: The following tasks missed their deadlines");
        for (Map.Entry<Integer, Double> entry : missedDeadlines.entrySet()) {
            output.append("\nTask ");
            output.append(entry.getKey());
            output.append(" failed to meet a deadline at ");
            output.append(entry.getValue());
            output.append(" ms.");
        }
        if (!missedDeadlines.isEmpty()) {
            JOptionPane.showMessageDialog(parentComponent, output, "Warning - Missed Deadlines", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void addWarning(Integer taskNumber, Double expectedDeadline) {
        missedDeadlines.put(taskNumber, expectedDeadline);
    }

}
