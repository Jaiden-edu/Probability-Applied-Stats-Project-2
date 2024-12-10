
public class Tester {
    public static void main(String[] args) {
    	String inputFileName = "C:/Users/Jaiden Nunez/Desktop/Eclipse Projects/ProgramPlotter/data.csv";
        double saltRange = 100.0;

        // create and salt data
        ProgramSalter salter = new ProgramSalter(inputFileName, saltRange);
        salter.addSalt();

        // display the chart for the salted data
        String saltedFileName = "C:/Users/Jaiden Nunez/Desktop/Eclipse Projects/ProgramPlotter/MultiSaltedR100_data.csv";
        salter.displayChart(saltedFileName);
    }
}
