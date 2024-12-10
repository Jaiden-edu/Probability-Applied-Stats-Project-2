
public class GeometricProbability {
	
	private double p; // the probability of success 
    private int y;    // number of trials 

    // constructor
    public GeometricProbability(double p, int y) {
        this.p = p;
        this.y = y;
    }

    // the method to calculate p(y)
    public double calculateProbability() {
        double q = 1 - p; // probability of failure
        return Math.pow(q, y - 1) * p;
    }

    // calculate expected value
    public double calculateExpectedValue() {
        return 1 / p;
    }

    // calclate Variance
    public double calculateVariance() {
        return (1 - p) / (p * p);
    }

    // display calculations
    public void displayResults() {
        System.out.println("Geometric Probability Distribution Results:");
        System.out.println("P(y): " + calculateProbability());
        System.out.println("Expected Value: " + calculateExpectedValue());
        System.out.println("Variance: " + calculateVariance());
    }

}
