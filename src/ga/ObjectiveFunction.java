package ga;

public class ObjectiveFunction {
	
	public double evaluateExpression(double x) {
		
		double total = 0;
		
		total += (Math.cos(Math.log(x))) * x;
		if (Double.isNaN(total))
			return 0;
		
		return total;
		
	}

}
