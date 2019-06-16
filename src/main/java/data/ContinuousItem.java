package data;

/**
 * modella una cella della tabella contenente un valore continuo.
 */

public class ContinuousItem extends Item {

	/**
	 * Inizializza i valori dei membri attribute e value.
	 * 
	 * @param attribute
	 * @param value
	 */
	public ContinuousItem(final ContinuousAttribute attribute, final double value) {
		super(attribute, value);
	}

	@Override
	double distance(final Object a) {
		return Math.abs(((ContinuousAttribute) getAttribute()).getScaledValue((double) getValue())
				- ((ContinuousAttribute) ((Item) a).getAttribute())
						.getScaledValue((double) ((Item) a).getValue()));

	}
}
