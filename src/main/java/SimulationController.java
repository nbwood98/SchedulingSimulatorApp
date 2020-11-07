import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

public class SimulationController {

    public SimulationController() {

    }

    public void simulate(String testField) {
        System.out.println(testField);
    }

    public JFreeChart getChart() {
        XYDataset ds = createDataset();
        return ChartFactory.createXYLineChart("Test Simulation Chart", "x", "y", ds,
                PlotOrientation.VERTICAL, true, true, false);
    }

    private XYDataset createDataset() {
        DefaultXYDataset ds = new DefaultXYDataset();
        double[][] data = {{0.1, 0.2, 0.3}, {1, 2, 3}};
        ds.addSeries("series1", data);
        return ds;
    }
}
