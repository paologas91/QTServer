package data;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * modella una colonna di attributi discreti, con dei valori possibili.
 */

class DiscreteAttribute extends Attribute implements Iterable<String> {

// attributi d'istanza

	private TreeSet<String> values = new TreeSet<String>();

// metodi

	DiscreteAttribute(final String name, final int index, final String[] values) {
		super(name, index);
		for (final String s : values) {
			this.values.add(s);
		}
	}

	@Override
	public Iterator<String> iterator() {
		return values.iterator();
	}

	int getNumberOfDistinctValues() {
		return values.size();
	}
}
