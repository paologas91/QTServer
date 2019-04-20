
public class ContinuousAttribute extends Attribute {

	// attributi d'istanza
	
	private double max;
	private double min;
	
	// metodi
	
	public ContinuousAttribute(String name, int index, double min, double max) {
		
		super(name, index); // chiama il costruttore della classe madre
		this.max = max;
		this.min = min;
	}
	
	public double getScaledValue(double v) {
		
		double v1 = (v-min)/(max-min);
				
		return v1;
	}
}
