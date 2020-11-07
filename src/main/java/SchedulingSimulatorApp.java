import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class SchedulingSimulatorApp extends JFrame {

    public static final int APPLICATION_HEIGHT = 700;
    public static final int APPLICATION_WIDTH = 1000;

    private static final String APPLICATION_TITLE = "Scheduling Simulator App";

    private JRadioButton task1Enable;
    private JTextField task1ExecutionTime;
    private JTextField task1Period;
    private JTextField task1Deadline;
    private JTextField task1ReleaseTime;
    private JPanel mainPanel;
    private JButton btnSimulate;
    private JLabel label1;
    private JRadioButton task2Enable;
    private JTextField task2Period;
    private JTextField task2Deadline;
    private JTextField task2ReleaseTime;
    private JTextField task2ExecutionTime;

    private final SimulationController controller;

    public SchedulingSimulatorApp() {
        super(APPLICATION_TITLE);
        init();
        initComponents();
        controller = new SimulationController();
    }

    private void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT));
        this.setResizable(false);
        this.pack();
    }

    private void initComponents() {
        //Intended for action listener and other related initializations
        task1Enable.addActionListener(this::taskEnable);
        task2Enable.addActionListener(this::taskEnable);
        btnSimulate.addActionListener(this::btnSimulateActionPerformed);
    }

    private void taskEnable(ActionEvent e) {
        // Implement generic task enabling functionality
        // make call to the check tasks method or move the logic here
    }

    private void btnSimulateActionPerformed(ActionEvent e) {
        controller.simulate(setupAndGetEnabledTasks());
    }

    // ToDo: Address why this logic isn't working properly, however, this is low-priority.

    private void checkEnabledTasks(int task) {
        boolean enabled;
        switch (task) {
            case 1:
                enabled = task1Enable.isEnabled();
                task1Period.setEditable(enabled);
                task1Deadline.setEditable(enabled);
                task1ExecutionTime.setEditable(enabled);
                task1ReleaseTime.setEditable(enabled);
                break;
            case 2:
                enabled = task2Enable.isEnabled();
                task2Period.setEditable(enabled);
                task2Deadline.setEditable(enabled);
                task2ExecutionTime.setEditable(enabled);
                task2ReleaseTime.setEditable(enabled);
                break;
            default:
                // do nothing for now
        }
    }

    private ArrayList<Task> setupAndGetEnabledTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        if (task1Enable.isSelected()) {
            Task task = new Task();

            // ToDo: Implement these setters with restrictions on the fields set properly

            task.setDeadline(1D);
            task.setExecutionTime(1D);
            task.setPeriod(1D);
            task.setReleaseTime(1D);
            tasks.add(task);
        }

        if (task2Enable.isSelected()) {
            Task task = new Task();

            // ToDo: Implement these setters with restrictions on the fields set properly

            task.setDeadline(2D);
            task.setExecutionTime(2D);
            task.setPeriod(2D);
            task.setReleaseTime(2D);
            tasks.add(task);
        }

        // ToDo: Add logic for a 3rd task (or more depending on final decisions)

        return tasks;
    }
}
