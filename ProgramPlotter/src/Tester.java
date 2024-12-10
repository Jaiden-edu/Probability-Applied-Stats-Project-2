
public class Tester { 
	
    public static void main(String[] args) {
        
        ProgramPlotter plotter = new ProgramPlotter(-10.0, 10.0, 0.1, "data.csv");
        plotter.generateAndExport();
        plotter.displayChart();
    }
}
