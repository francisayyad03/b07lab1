public class Polynomial {
	 	double [] coefs;
	
	public Polynomial() {
		coefs = new double []{0};
	}
	
	public Polynomial(double [] p) {
		if (p.length >= 1)
			coefs = new double [p.length];
		for (int i = 0; i < p.length; i++) {
			coefs[i] = p[i];
		}
	}
	
	public Polynomial add (Polynomial p) {
		int len1 = coefs.length;
		int len2 = p.coefs.length;
		if (len1 > len2) {
			double [] result = new double[coefs.length];
			for (int i = 0; i < len2; i++) {
				result[i] = coefs[i] + p.coefs[i];
			}
			for (int i = len2; i < len1; i++) {
				result[i] = coefs[i];
			}
			Polynomial r = new Polynomial(result);
			return r;
		}
		else{
			double [] result = new double[p.coefs.length];
			for (int i = 0; i < len1; i++) {
				result[i] = coefs[i] + p.coefs[i];
			}
			for (int i = len1; i < len2; i++) {
				result[i] = p.coefs[i];
			}
			Polynomial r = new Polynomial(result);
			return r;
		}
	}
	
	
	public double evaluate (double x) {
		double sum = 0;
		for (int i = 0; i < coefs.length; i++) {
			sum += coefs[i] * Math.pow(x, i);
		}
		return sum;
	}
	
	public boolean hasRoot (double x) {
		boolean c = false;
		if(evaluate(x) == 0)
			c = true;
		return c;
	}
}
