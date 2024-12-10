
public class HypergeometricProbability {
	
	private int N; // the population size
    private int r; // number of needed successes
    private int n; // the sample size
    private int y; // the number of successes in the sample

    // constructor
    public HypergeometricProbability(int N, int r, int n, int y) {
        this.N = N;
        this.r = r;
        this.n = n;
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

    // calculate binomial coefficient 
    private long binomialCoefficient(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    // calculate p(y)
    public double calculateProbability() {
        long numerator = binomialCoefficient(r, y) * binomialCoefficient(N - r, n - y);
        long denominator = binomialCoefficient(N, n);
        return (double) numerator / denominator;
    }

    // expected vale calculator
    public double calculateExpectedValue() {
        return n * ((double) r / N);
    }

    // variance calculator
    public double calculateVariance() {
        double term1 = n * ((double) r / N);
        double term2 = (N - r) / (double) N;
        double term3 = (N - n) / (double) (N - 1);
        return term1 * term2 * term3;
    }

    // display results
    public void displayResults() {
        System.out.println("Hypergeometric Probability Distribution Results:");
        System.out.println("P(y): " + calculateProbability());
        System.out.println("Expected Value (E[Y]): " + calculateExpectedValue());
        System.out.println("Variance (Var[Y]): " + calculateVariance());
    }

}
