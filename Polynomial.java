import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


public class Polynomial{
	double [] coeff;
	int [] expo;
	
	public Polynomial(){
		coeff=new double[]{1};
		expo=new int[]{-9999};
	}
	
	public Polynomial(double [] c_arr, int [] e_arr){
		coeff=new double [c_arr.length];
		expo=new int [e_arr.length];
		for (int i=0; i<c_arr.length; i++){
			coeff[i]=c_arr[i];
		}
		for (int i=0; i<e_arr.length; i++){
			expo[i]=e_arr[i];
		}

	}



	public Polynomial(File f) throws IOException{
		BufferedReader reader= new BufferedReader(new FileReader(f));
		String line = reader.readLine();
		String[] terms=line.split("(?=[+-])");
		double [] coeff=new double[terms.length];
		int [] expo=new int[terms.length];
		for (int i=0; i<terms.length; i++){
			if (!terms[i].contains("x")){
				coeff[i]=Double.parseDouble(terms[i]);
				expo[i]=0;
				continue;
			}else{
				String [] nums=terms[i].split("x", -1);
				if (nums[0].equals("")){coeff[i]=1;}else{
					coeff[i]=Double.parseDouble(nums[0]);
				}
				if(nums.length>1){
					if (nums[1].equals("")){
						expo[i]=1;
					}else{
						expo[i]=Integer.parseInt(nums[1]);
					}
				}
			}
		}
		this.coeff=coeff;
		this.expo=expo;

	}



	public Polynomial add(Polynomial x){
		ArrayList<Integer> new_e = new ArrayList<> ();
		ArrayList<Double> new_c = new ArrayList<> ();
		for (int i=0; i<coeff.length; i++){                      //insert this info first
			new_e.add(i, expo[i]);
			new_c.add(i, coeff[i]);
		}

		for (int i=0; i<x.coeff.length; i++){                      //insert x info 
			if (new_e.indexOf(x.expo[i])==-1){
				new_e.add(x.expo[i]);
				new_c.add(x.coeff[i]);
			}else{
				new_c.set(new_e.indexOf(x.expo[i]), new_c.get(new_e.indexOf(x.expo[i]))+x.coeff[i]);
			}
		}
		int [] nw_e=new int [new_e.size()];
		double [] nw_c=new double [new_c.size()];
		for (int i=0; i<nw_e.length; i++){
			nw_e[i]=new_e.get(i);
			nw_c[i]=new_c.get(i);
		}
		Polynomial poly=new Polynomial(nw_c, nw_e);
		return poly;
	}

	public Polynomial multiply(Polynomial x){
		Polynomial result=new Polynomial();
		for (int i =0; i<coeff.length; i++){                  //evry term in the calling object
			Polynomial poly=new Polynomial(x.coeff, x.expo);
			for (int j =0; j<poly.coeff.length; j++){
				poly.coeff[j]*=coeff[i];
				poly.expo[j]+=expo[i];
			}
			result=result.add(poly);
		}
		return result;
	}


	public double evaluate(double x){
		double result=0;
		for (int i = 0; i<coeff.length; i++){
			 result+=coeff[i]*Math.pow(x, expo[i]); 
			
		}
		return result;
	}

	public boolean hasRoot(double x){
		double poly=this.evaluate(x);
		return poly==0;
	}

	
	public void saveToFile(String name) throws IOException{
		PrintWriter writer=new PrintWriter(name);
		for (int j =0; j<coeff.length-1; j++){
			if (coeff[j]!=1){writer.print(coeff[j]);}
			if (expo[j]!=0){writer.print("x");}
			if (expo[j]!=1&&expo[j]!=0){writer.print(expo[j]);}
			if (coeff[j+1]>0){
				writer.print("+");
			}
		}
		if (coeff[coeff.length-1]!=1){writer.print(coeff[coeff.length-1]);}
		if (expo[coeff.length-1]!=0){writer.print("x");}
		if (expo[coeff.length-1]!=1){writer.print(expo[coeff.length-1]);}
		writer.close();
	}

	public void print(){
		System.out.println(Arrays.toString(coeff)+Arrays.toString(expo));

	}

}