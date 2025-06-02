import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Driver {
	public static void main(String [] args) {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3)+" should be 0.0");
		double [] c1 = {6,-2, 3, 5};
		int [] e1={0, 1, 4, 5};
		Polynomial p1 = new Polynomial(c1, e1);
		System.out.println(p1.evaluate(3)+" should be 1458.0");
		try{
			p1.saveToFile("p1.txt");
			Polynomial p1c=new Polynomial(new File("p1.txt"));
			System.out.println(p1c.evaluate(3)+" should be 1458.0");
			p1c.print();
		}catch (IOException e){
			e.printStackTrace();
		}

		double [] c2 = {-2,1, -9};
		int [] e2={1, 2, 3};
		Polynomial p2 = new Polynomial(c2, e2);

		System.out.println(p2.evaluate(3)+" should be -240.0");

		Polynomial p3=p1.add(p2);
		System.out.println(p3.evaluate(3)+" should be 1218.0");

		Polynomial p4=p1.multiply(p2);
		System.out.println(p4.evaluate(3)+" should be -34992.0");
		
		
	}
}