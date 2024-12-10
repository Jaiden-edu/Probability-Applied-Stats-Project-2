
public class PoissonProbability {
	
	private double lambda; // mean number of events. aka Half life symbol
    private int y;         // number of occurrences

    // constructor
    public PoissonProbability(double lambda, int y) {
        this.lambda = lambda;
        this.y = y;
    }

    // factorial calculator
    private long factorial(int num) {
        long result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    // calculation of p(y)
    public double calculateProbability() {
        return (Math.pow(lambda, y) * Math.exp(-lambda)) / factorial(y);
    }

    // expected value
    public double calculateExpectedValue() {
        return lambda;
    }

    // variance
    public double calculateVariance() {
        return lambda;
    }

    // Method to display all results
    public void displayResults() {
        System.out.println("Poisson Probability Distribution Results:");
        System.out.println("P(y): " + calculateProbability());
        System.out.println("Expected Value (E[Y]): " + calculateExpectedValue());
        System.out.println("Variance (Var[Y]): " + calculateVariance());
    }

}
