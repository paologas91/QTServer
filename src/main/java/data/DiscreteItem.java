package data;

/**
 * modella una cella della tabella contenente un valore discreto.
 */

class DiscreteItem extends Item {

DiscreteItem(final DiscreteAttribute attribute, final String value) {
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
