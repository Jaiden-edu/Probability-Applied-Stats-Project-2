
public class NegativeBinomialProbability {
	private double p; // probability of success
    private int r;    // number of successes wanted
    private int y;    // number of trials

    // constructor
    public NegativeBinomialProbability(double p, int r, int y) {
        this.p = p;
        this.r = r;
        this.y = y;
    }

    // method to calculate factorial
    private long factorial(int num) {
        long result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    // calculate binomial coefficient 
    private long binomialCoefficient(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    // method used to calculate p(y) 
    public double calculateProbability() {
        double q = 1 - p; // Probability of failure
        long coefficient = binomialCoefficient(y - 1, r - 1); 
        return coefficient * Math.pow(p, r) * Math.pow(q, y - r);
    }

    // calculate expected value
    public double calculateExpectedValue() {
        return (double) r / p;
    }

    // calculate Variance
    public double calculateVariance() {
        return r * (1 - p) / (p * p);
    }

    // display results
    public void displayResults() {
        System.out.println("Negative Binomial Probability Distribution Results:");
        System.out.println("P(y): " + calculateProbability());
        System.out.println("Expected Value (E[Y]): " + calculateExpectedValue());
        System.out.println("Variance (Var[Y]): " + calculateVariance());
    }

}
