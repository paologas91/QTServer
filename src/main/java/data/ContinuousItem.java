package data;

/**
 * modella una cella della tabella contenente un valore continuo.
 */

public class ContinuousItem extends Item {

	public ContinuousItem(final ContinuousAttribute attribute, final double value) {
		super(attribute, value);
	}

	@Override
	double distance(final Object a) {
		return (double) Math.abs(((ContinuousAttribute) getAttribute()).getScaledValue((double) getValue())
				- ((ContinuousAttribute) ((Item) a).getAttribute())
						.getScaledValue((double) ((Item) a).getValue()));

	}
}
