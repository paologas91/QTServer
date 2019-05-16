package data;

public class ContinuousItem extends Item {
	
	public ContinuousItem(final Attribute attribute, final double value) {
		super(attribute, value);
	}

	double distance(Object a) {
		// do I need RTTI?
		return (double) Math.abs( ((ContinuousAttribute) this.getAttribute()).getScaledValue((double) this.getValue()) - 
				((ContinuousAttribute) ((Item) a).getAttribute()).getScaledValue((double) ((Item) a).getValue()));
	}

}
