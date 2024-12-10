
public class UniformDistribution {
	
	private double a; // lower bound
    private double b; // upper bound
    private double c; // starting point of interval
    private double d; // ending point of interval

    // constructor 
    public UniformDistribution(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    // calculate p(y)
    public double calculateProbability() {
        if (c >= a && d <= b && a < b) {
            return (d - c) / (b - a);
        } else {
            throw new IllegalArgumentException("Invalid bounds or intervals!");
        }
    }

    // calculate the expected value
    public double calculateExpectedValue() {
        return (a + b) / 2;
    }

    // calculate Variance
    public double calculateVariance() {
        return Math.pow(b - a, 2) / 12;
    }

    // display results
    public void displayResults() {
        System.out.println("Uniform Distribution Results:");
        System.out.println("P(y): " + calculateProbability());
        System.out.println("Expected Value (E[Y]): " + calculateExpectedValue());
        System.out.println("Variance (Var[Y]): " + calculateVariance());
    }

}
