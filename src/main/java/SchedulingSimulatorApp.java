import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SchedulingSimulatorApp extends JFrame {
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

    private SimulationController controller;

    public SchedulingSimulatorApp() {
        super(APPLICATION_TITLE);
        init();
        initComponents();
        controller = new SimulationController();
    }

    private void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(1000, 700));
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
        controller.simulate(task1ReleaseTime.getText());
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
}
