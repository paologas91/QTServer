package data;

/**
 * modella una colonna di attributi continui, con minimo e massimo che un
 * singolo attributo può assumere.
 */

class ContinuousAttribute extends Attribute {

	private double max;
	private double min;

	ContinuousAttribute(final String name, final int index, final double min, final double max) {
		super(name, index); // chiama il costruttore della classe madre
		this.max = max;
		this.min = min;
	}

	double getScaledValue(final double v) {
		final double v1 = (v - min) / (max - min);
		return v1;
	}
}
