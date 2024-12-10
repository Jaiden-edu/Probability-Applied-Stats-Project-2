import java.io.*;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.text.DecimalFormat;

public class ProgramSmoother {
	
	private String inputFileName;
    private int windowValue; 
    private int smoothPasses; 

    public ProgramSmoother(String inputFileName, int windowValue, int smoothPasses) {
        this.inputFileName = inputFileName;
        this.windowValue = windowValue;
        this.smoothPasses = smoothPasses;
    }
    
    public void smoothData() {
        List<Double> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();

        File inputFile = new File(inputFileName);
        String outputFileName = inputFile.getParent() + File.separator + "MultiSaltedR20_data_smoothed_" + inputFile.getName();

        // read input file
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; // skip the first line
                    continue;
                }

                String[] parts = line.split(",");
                
                xValues.add(Double.parseDouble(parts[0].trim()));
                
                yValues.add(Double.parseDouble(parts[1].trim()));
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            return;
        }

        // smoothes the data the amount of times the double value in the tester class is valued at
        for (int pass = 0; pass < smoothPasses; pass++) {
            yValues = smoothOnce(yValues);
        }

        // writes smoothed data to its .csv file
        try (FileWriter writer = new FileWriter(outputFileName)) {
            writer.write("x,y\n");
            DecimalFormat df = new DecimalFormat("#.###");

            for (int i = 0; i < xValues.size(); i++) {
                writer.write(df.format(xValues.get(i)) + ", " + df.format(yValues.get(i)) + "\n");
            }
            System.out.println("Smoothed data successfully exported to " + outputFileName + "!");
        } catch (IOException e) {
            System.err.println("Error writing output file: " + e.getMessage());
        }
        displayChart(xValues, yValues);
    }

    // smoothes the data one time
    private List<Double> smoothOnce(List<Double> yValues) {
        List<Double> smoothed = new ArrayList<>();
        int n = yValues.size();

        for (int i = 0; i < n; i++) {
            double sum = 0;
            int count = 0;
            for (int j = i - windowValue; j <= i + windowValue; j++) {
                if (j >= 0 && j < n) { 
                    sum += yValues.get(j);
                    count++;
                }
            }
            // get average and adds it to smoothed list
            smoothed.add(sum / count);
        }

        return smoothed;
    }
    
    private void displayChart(List<Double> xValues, List<Double> yValues) {
        XYSeries series = new XYSeries("Smoothed Data");

        for (int i = 0; i < xValues.size(); i++) {
            series.add(xValues.get(i), yValues.get(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);

        // Create the chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Smoothed Data Chart of 100x Salt Smoothed 10 Times", // Chart title
                "X-Axis",             // X-Axis Label
                "Y-Axis",             // Y-Axis Label
                dataset               // Dataset
        );

        // Display the chart in a ChartPanel
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Smoothed Data Chart");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new ChartPanel(chart));
            frame.pack();
            frame.setVisible(true);
        });
    }

}


