
public class Tester {

	public static void main(String[] args) {

		new GeometricProbability(0.5, 2).displayResults(); // prints Geometric Probability 
		new NegativeBinomialProbability(0.4, 3, 6).displayResults(); //prints Negative Binomial Probability
		new HypergeometricProbability(50, 20, 10, 5).displayResults(); // prints HyperHeometric Probability
		new PoissonProbability(3.5, 4).displayResults(); //prints Poisson Probability
		new UniformDistribution(0, 10, 3, 7).displayResults(); //prints Uniform Distribution
	}

}
