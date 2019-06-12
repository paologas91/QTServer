package data;

/**
 * modella una cella della tabella contenente un valore continuo.
 */

public class ContinuousItem extends Item {

public ContinuousItem(final ContinuousAttribute attribute, final double value) {
								super(attribute, value);
}

double distance(Object a) {
								return (double) Math.abs(((ContinuousAttribute) this.getAttribute()).getScaledValue((double) this.getValue()) -
																																	((ContinuousAttribute) ((Item) a).getAttribute()).getScaledValue((double) ((Item) a).getValue()));

}
}
