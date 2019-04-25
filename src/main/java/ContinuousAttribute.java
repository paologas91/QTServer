
class ContinuousAttribute extends Attribute {

	// attributi d'istanza

	private double max;
	private double min;

	// metodi

	ContinuousAttribute(final String name, final int index, final double min, final double max) {

		super(name, index);	// chiama il costruttore della classe madre
		this.max = max;
		this.min = min;
	}

	double getScaledValue(final double v) {

		double v1 = (v-min)/(max-min);

		return v1;
	}
}
