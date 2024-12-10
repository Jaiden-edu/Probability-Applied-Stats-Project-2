import java.io.*;
import java.util.*;

import javax.swing.JFrame;

import java.text.DecimalFormat;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ProgramSalter {
    private String inputFileName;
    private double saltRange; // max range of the salting

    // constructor
    public ProgramSalter(String inputFileName, double saltRange) {
        this.inputFileName = inputFileName;
        this.saltRange = saltRange;
    }

    // method to add the salt to the .csv file
    public void addSalt() {
        List<String> saltedData = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.###"); // format to 3 places

        // creates a new file name based on the old file name
        File inputFile = new File(inputFileName);
        String outputFileName = inputFile.getParent() + File.separator + "MultiSaltedR100_" + inputFile.getName();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            boolean isHeader = true;
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    saltedData.add(line);
                    isHeader = false;
                    continue;
                }
                // parse x and y values
                String[] parts = line.split(",");
                double x = Double.parseDouble(parts[0]);
                double y = Double.parseDouble(parts[1]);
                // adds random noise to the y values only
                double salt = (Math.random() * 2 * saltRange) - saltRange; 
                double saltedY = y + salt;
                saltedData.add(df.format(x) + ", " + df.format(saltedY));
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            return;
        }
        // writes the salted data to the new file
        try (FileWriter writer = new FileWriter(outputFileName)) {
            for (String line : saltedData) {
                writer.write(line + "\n");
            }
            System.out.println("Salted data successfully exported to " + outputFileName + "!");
        } catch (IOException e) {
            System.err.println("Error writing output file: " + e.getMessage());
        }
    }
    
    //creates the display for the chart
    public void displayChart(String filePath) {
        XYSeries series = new XYSeries("Salted Data");
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
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
        JFreeChart chart = ChartFactory.createScatterPlot("Salted Data Chart", "X-Axis", "Y-Axis", dataset);

        JFrame frame = new JFrame("Salted Data Chart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }
}