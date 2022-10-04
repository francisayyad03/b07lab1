
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Scanner;

public class Polynomial {
	double[] coefs;
	double[] expo;

	public Polynomial() {
		coefs = new double[] { 0 };
		expo = new double[] { 0 };
	}

	public Polynomial(File file) throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		String s = "";
		while (scan.hasNextLine()) {
			s += (scan.nextLine());
		}
		String arrS[] = s.split("x");
		double []c = new double[arrS.length];
		double []e = new double[arrS.length];
		int cj = 0;
		int ej = 0;
		for (int i = 0; i < arrS.length; i++) {
			if (arrS[i].contains("^")) {
				e[cj] = Integer.valueOf(arrS[i].substring(arrS[i].indexOf("^") + 1, arrS[i].indexOf("^") + 2));
				cj++;
			}
			if (arrS[i].contains("+")) {
				c[ej] = Integer.valueOf(arrS[i].substring(arrS[i].indexOf("+") + 1, arrS[i].indexOf("+") + 2));
				ej++;
			}
			if (arrS[i].contains("-")) {
				c[ej] = Integer.valueOf(arrS[i].substring(arrS[i].indexOf("-"), arrS[i].indexOf("-") + 2));
				ej++;
			}
			if (arrS[i].indexOf("+") == -1 && arrS[i].indexOf("-") == -1 && arrS[i].indexOf("^") == -1) {
				c[ej] = Integer.valueOf(arrS[i]);
				ej++;
			}
		}
		
		int count = 0;
		for (int i = 1; i < c.length; i++) {
			if (c[i] != 0) {
				count++;
			}
		}
		coefs = new double[count + 1];
		expo = new double[count + 1];

		int j = 0;
		for (int i = 0; i < c.length; i++) {
			if (c[i] != 0) {
				coefs[j] = c[i];
				expo[j] = e[i];
				j++;
			}
		}
	}

	public Polynomial(double[] pcoefs, double[] pexpo) {
		coefs = new double[pcoefs.length];
		expo = new double[pexpo.length];
		for (int i = 0; i < pcoefs.length; i++) {
			coefs[i] = pcoefs[i];
			expo[i] = pexpo[i];
		}
	}

	public Polynomial add(Polynomial p) {
		double[] c = new double[(int) (Math.max(p.expo[p.expo.length - 1], expo[expo.length - 1])) + 100];
		double[] e = new double[(int) (Math.max(p.expo[p.expo.length - 1], expo[expo.length - 1])) + 100];
		for (int i = 0; i < c.length; i++) {
			e[i] = i;
		}
		for (int i = 0; i < coefs.length; i++) {
			c[(int) (expo[i])] += coefs[i];
		}
		for (int i = 0; i < p.coefs.length; i++) {
			c[(int) (p.expo[i])] += p.coefs[i];
		}

		int count = 0;
		for (int i = 1; i < c.length; i++) {
			if (c[i] != 0) {
				count++;
			}
		}
		double[] c2 = new double[count + 1];
		double[] e2 = new double[count + 1];

		int j = 0;
		for (int i = 0; i < c.length; i++) {
			if (c[i] != 0) {
				c2[j] = c[i];
				e2[j] = e[i];
				j++;
			}
		}

		Polynomial r = new Polynomial(c2, e2);
		return r;

	}

	public double evaluate(double x) {
		double sum = 0;
		for (int i = 0; i < coefs.length; i++) {
			sum += coefs[i] * Math.pow(x, expo[i]);
		}
		return sum;
	}

	public boolean hasRoot(double x) {
		return evaluate(x) == 0;
	}

	public Polynomial multiply(Polynomial p) {
		int len1 = coefs.length;
		int len2 = p.coefs.length;
		double[] tc = new double[Math.max(len1, len2)];
		double[] te = new double[Math.max(len1, len2)];
		double[] rc = new double[Math.max(len1, len2)];
		double[] re = new double[Math.max(len1, len2)];
		Polynomial r = new Polynomial(rc, re);
		Polynomial n = new Polynomial(tc, te);
		for (int i = 0; i < coefs.length; i++) {
			for (int j = 0; j < p.coefs.length; j++) {
				tc[j] = coefs[i] * p.coefs[j];
				te[j] = expo[i] + p.expo[j];
			}
			n = new Polynomial(tc, te);
			r = r.add(n);
		}

		int count = 0;
		for (int i = 1; i < r.coefs.length; i++) {
			if (r.coefs[i] != 0) {
				count++;
			}
		}

		double[] c2 = new double[count + 1];
		double[] e2 = new double[count + 1];

		int j = 0;
		for (int i = 0; i < r.coefs.length; i++) {
			if (r.coefs[i] != 0) {
				c2[j] = r.coefs[i];
				e2[j] = r.expo[i];
				j++;
			}
		}
		r = new Polynomial(c2, e2);
		return r;
	}
	
	public void saveToFIle(String name){
		
		try {
			File file = new File(name);
			
			if(!file.exists()) {
				file.createNewFile();
			}
			
			PrintWriter pw = new PrintWriter(file);
			String str = "";
			for (int i = 0; i < coefs.length-1; i++) {
				str += (int)coefs[i];
				str += "x^";
				str += (int)expo[i];
				if (coefs[i+1]>0) {
					str += "+";
				}
			}
				str += (int)coefs[coefs.length-1];
				str += "x^";
				str += (int)expo[expo.length-1];
			pw.print(str);
			pw.close();
			System.out.println("File is created");
   
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

}

