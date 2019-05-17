package data;

import java.util.TreeSet;
import java.util.Iterator;

class DiscreteAttribute extends Attribute implements Iterable<String> {

	// attributi d'istanza

	private TreeSet<String> values = new TreeSet<String>();

	// metodi

	DiscreteAttribute(final String name, final int index, final String[] values) {

		super(name, index);
		for (String s : values) {
			this.values.add(s);
		}
	}

	public Iterator<String> iterator() {

		return values.iterator();
	}

	int getNumberOfDistinctValues() {

		return values.size();
	}
}
