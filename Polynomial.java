public class Polynomial{
	double [] coeff;
	
	public Polynomial(){
		coeff=new double[1];
	}
	
	public Polynomial(double [] arr){
		coeff=new double [arr.length];
		for (int i=0; i<arr.length; i++){
			coeff[i]=arr[i];
		}
	}

	public Polynomial add(Polynomial x){
		double [] result;
		if (x.coeff.length<=coeff.length){
			result= new double [coeff.length] ;
			for (int i=0; i<x.coeff.length; i++){
				result[i]=x.coeff[i]+coeff[i];
			}
			for (int i=x.coeff.length; i<coeff.length; i++){
				result[i]=coeff[i];
			}

		}
		else{
			result= new double [x.coeff.length] ;
			for (int i=0; i<coeff.length; i++){
				result[i]=x.coeff[i]+coeff[i];
			}
			for (int i=coeff.length; i<x.coeff.length; i++){
				result[i]=x.coeff[i];
			}
		}
		Polynomial poly= new Polynomial(result);
		return poly;
		
	}

	public double evaluate(double x){
		double result=0;
		for (int i = 0; i<coeff.length; i++){
			 result+=coeff[i]*Math.pow(x, i); 
			
		}
		return result;
	}

	public boolean hasRoot(double x){
		double poly=this.evaluate(x);
		return poly==0;
	}

}