
public class DiscreteAttribute extends Attribute {
	
	// attributi d'istanza
	
	private String values[];
	
	// metodi
	
	public DiscreteAttribute(String name, int index, String values[]) {
	
		super(name, index);
		this.values = values;
	}
	
	public int getNumberOfDistinctValues() {
		
		return values.length;
	}
	
	public String getValue(int i) {
		
		return values[i];       // oppure this.values[i]
	}
}
