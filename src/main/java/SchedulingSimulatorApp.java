import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class SchedulingSimulatorApp extends JFrame {

    public static final int APPLICATION_HEIGHT = 700;
    public static final int APPLICATION_WIDTH = 1000;

    private static final String APPLICATION_TITLE = "Scheduling Simulator App";

    private JPanel mainPanel;
    private JButton btnSimulate;
    private JButton btnCheckSchedulability;
    private JLabel label1;

    private JRadioButton task1Enable;
    private JTextField task1Period;
    private JTextField task1WorstCase;
    private JTextField task1Invocation1;
    private JTextField task1Invocation2;

    private JRadioButton task2Enable;
    private JTextField task2Period;
    private JTextField task2WorstCase;
    private JTextField task2Invocation1;
    private JTextField task2Invocation2;

    private JRadioButton task3Enable;
    private JTextField task3WorstCase;
    private JTextField task3Period;
    private JTextField task3Invocation1;
    private JTextField task3Invocation2;
    private JTextField task1ReleaseTime;
    private JTextField task2ReleaseTime;
    private JTextField task3ReleaseTime;


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
        // Set default state
        task1Enable.setSelected(true);
        task2Enable.setSelected(true);
        task3Enable.setSelected(true);

        populateTaskZeroes();

        //Intended for action listener and other related initializations
        task1Enable.addActionListener(this::taskEnable);
        task2Enable.addActionListener(this::taskEnable);
        task3Enable.addActionListener(this::taskEnable);
        btnSimulate.addActionListener(this::btnSimulateActionPerformed);
        btnCheckSchedulability.addActionListener(this::btnCheckSchedulabilityActionPerformed);
    }

    private void populateTaskZeroes() {
        final String ZERO_STRING = "0";
        task1ReleaseTime.setText(ZERO_STRING);
        task1WorstCase.setText(ZERO_STRING);
        task1Period.setText((ZERO_STRING));
        task1Invocation1.setText(ZERO_STRING);
        task1Invocation2.setText(ZERO_STRING);
        task2ReleaseTime.setText(ZERO_STRING);
        task2WorstCase.setText(ZERO_STRING);
        task2Period.setText((ZERO_STRING));
        task2Invocation1.setText(ZERO_STRING);
        task2Invocation2.setText(ZERO_STRING);
        task3ReleaseTime.setText(ZERO_STRING);
        task3WorstCase.setText(ZERO_STRING);
        task3Period.setText((ZERO_STRING));
        task3Invocation1.setText(ZERO_STRING);
        task3Invocation2.setText(ZERO_STRING);
    }

    private void taskEnable(ActionEvent e) {
        // Implement generic task enabling functionality
        // make call to the check tasks method or move the logic here
    }

    private void btnSimulateActionPerformed(ActionEvent e) {
        try {
            controller.simulate(setupAndGetEnabledTasks());
        } catch (InvalidInputException | TaskNotSchedulableException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void btnCheckSchedulabilityActionPerformed(ActionEvent e) {
        // ToDo: Implement functionality for validating schedulability
    }

    // ToDo: Address why this logic isn't working properly, however, this is low-priority.
    private void checkEnabledTasks(int task) {
        boolean enabled;
        switch (task) {
            case 1:
                enabled = task1Enable.isEnabled();
                task1Period.setEditable(enabled);
                task1WorstCase.setEditable(enabled);
                break;
            case 2:
                enabled = task2Enable.isEnabled();
                task2Period.setEditable(enabled);
                task2WorstCase.setEditable(enabled);
                break;
            default:
                // do nothing for now
        }
    }

    private ArrayList<Task> setupAndGetEnabledTasks() throws InvalidInputException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            task1Enable.setSelected(true);
            if (task1Enable.isSelected()) {
                Task task = new Task(1);

                // ToDo: Implement these setters with restrictions on the fields set properly

                task.setWorstCaseComputationTime(Integer.parseInt(task1WorstCase.getText()));
                task.setPeriod(Integer.parseInt(task1Period.getText()));
                task.setInvocation1(Integer.parseInt(task1Invocation1.getText()));
                task.setInvocation2(Integer.parseInt(task1Invocation2.getText()));

                // For Testing Purposes
                task.setWorstCaseComputationTime(3);
                task.setPeriod(8);
                task.setInvocation1(2);
                task.setInvocation2(1);
                task.setReleaseTime(0);
                tasks.add(task);
            }

            task2Enable.setSelected(true);
            if (task2Enable.isSelected()) {
                Task task = new Task(2);

                // ToDo: Implement these setters with restrictions on the fields set properly

            /*task.setWorstCaseComputationTime(Integer.parseInt(task2WorstCase.getText()));
            task.setPeriod(Integer.parseInt(task2Period.getText()));
            task.setInvocation1(Integer.parseInt(task2Invocation1.getText()));
            task.setInvocation2(Integer.parseInt(task2Invocation2.getText()));*/

                // For Testing Purposes
                task.setWorstCaseComputationTime(3);
                task.setPeriod(10);
                task.setInvocation1(1);
                task.setInvocation2(1);
                task.setReleaseTime(0);
                tasks.add(task);
            }

            task3Enable.setSelected(true);
            if (task3Enable.isSelected()) {
                Task task = new Task(3);

                // ToDo: Implement these setters with restrictions on the fields set properly

            /*task.setWorstCaseComputationTime(Integer.parseInt(task3WorstCase.getText()));
            task.setPeriod(Integer.parseInt(task3Period.getText()));
            task.setInvocation1(Integer.parseInt(task3Invocation1.getText()));
            task.setInvocation2(Integer.parseInt(task3Invocation2.getText()));*/

                // For Testing Purposes
                task.setWorstCaseComputationTime(1);
                task.setPeriod(14);
                task.setInvocation1(1);
                task.setInvocation2(1);
                task.setReleaseTime(0);
                tasks.add(task);
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("One or more enabled tasks contains an invalid integer or might not have been initialized.", e);
        }

        return tasks;
    }
}
