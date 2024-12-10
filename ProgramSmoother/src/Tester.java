
public class Tester {
	public static void main(String[] args) {
		String inputFileName = "C:/Users/Jaiden Nunez/Desktop/Eclipse Projects/ProgramPlotter/MultiSaltedR100_data.csv"; 
        int windowValue = 3; // window size
        int smoothPasses = 10; // number of times the process gets smoothed

        ProgramSmoother smoother = new ProgramSmoother(inputFileName, windowValue, smoothPasses);
        smoother.smoothData();

    }

}
