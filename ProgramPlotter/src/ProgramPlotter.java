import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import java.io.FileReader;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ProgramPlotter {
    private double start; // range start
    private double end; // range end
    private double step; //increment sizes
    private String fileName; // output of the file

    // constructor
    public ProgramPlotter(double start, double end, double step, String fileName) {
        this.start = start;
        this.end = end;
        this.step = step;
        this.fileName = fileName;
    }

    // calculate the function and export it to the .csv file
    public void generateAndExport() {
        List<String> data = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.###"); // format the output

        // calculate the function
        for (double x = start; x <= end; x += step) {
        	double y = (x * x) - (2 * x) + 1; // y = x^2 - 2x + 1
            // format the output
            data.add(df.format(x) + "," + df.format(y));
        }

        // export to .csv
        exportToCSV(data, fileName);
        System.out.println("Data successfully exported to " + fileName + "!");
    }

    // helper method to export data to a CSV file
    private void exportToCSV(List<String> data, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("x,y\n"); //creates the header in the .csv file
            for (String line : data) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    // given code from JFreeChart to display the data in a window
    public void displayChart() {
        XYSeries series = new XYSeries("Function: y = x^2 - 2x + 1");

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isHeader = true;
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; // Skip the header
                    continue;
                }
                String[] parts = line.split(",");
                double x = Double.parseDouble(parts[0].trim());
                double y = Double.parseDouble(parts[1].trim());
                series.add(x, y);
            }
        } catch (Exception e) {
            System.err.println("Error reading data for chart: " + e.getMessage());
            return;
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createScatterPlot("Function Plot","X-Axis","Y-Axis",dataset);

        JFrame frame = new JFrame("Function Chart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }
}
