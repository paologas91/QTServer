package data;

import java.io.Serializable;

class DiscreteItem extends Item implements Serializable {

	DiscreteItem(final DiscreteAttribute attribute, final String value) {
		/*
		 * constructor
		 */
		super(attribute, value);
	}

	double distance(final Object a) {
		// se non metto il toString() fallisce sempre il confronto
		if (getValue().equals(((Item) a).getValue())) {
			return 0.0;
		} else {
			return 1.0;
		}
	}
}
