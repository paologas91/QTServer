package data;

class DiscreteAttribute extends Attribute {

	// attributi d'istanza

	private String[] values;

	// metodi

	DiscreteAttribute(final String name, final int index, final String[] values) {

		super(name, index);
		this.values = values;
	}

	int getNumberOfDistinctValues() {

		return values.length;
	}

	String getValue(final int i) {

		return values[i];	// oppure this.values[i]
	}
}
