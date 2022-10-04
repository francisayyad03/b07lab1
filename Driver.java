import java.io.File; 
import java.io.IOException;
import java.util.Scanner;

public class driver {
	public static void main(String [] args) throws IOException   { 
		Polynomial p = new Polynomial(); 
		//System.out.println(p.evaluate(3)); 
		double [] c1 = {4,2}; 
		double [] e1 = {0,1};
		Polynomial p1 = new Polynomial(c1,e1); 
		double [] c2 = {-2,-9}; 
		double [] e2 = {1,4};
		Polynomial p2 = new Polynomial(c2,e2); 
		Polynomial s = p1.multiply(p2); 
		p2.saveToFIle("lool2");
		for (int i = 0; i<s.coefs.length; i++) {
			System.out.println(s.coefs[i]);
			System.out.println(s.expo[i]);
		}
		System.out.println("s(0.1) = " + s.evaluate(0.1)); 
		if(s.hasRoot(1))
			System.out.println("1 is a root of s"); 
		else 
			System.out.println("1 is not a root of s"); 
		 
	
	 	File file = new File("C:\\Users\\User\\Desktop\\ploy.txt");
	 	Polynomial P = new Polynomial(file);
		for (int i = 0; i<P.coefs.length; i++) {
			System.out.println(P.coefs[i]);
			System.out.println(P.expo[i]);
		}
		
		

	}

}
