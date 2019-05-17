package data;

import java.io.Serializable;

public class ContinuousItem extends Item implements Serializable {
	
	public ContinuousItem(final Attribute attribute, final double value) {
		super(attribute, value);
	}

	double distance(Object a) {
		// do I need RTTI?
		return (double) Math.abs( ((ContinuousAttribute) this.getAttribute()).getScaledValue((double) this.getValue()) - 
				((ContinuousAttribute) ((Item) a).getAttribute()).getScaledValue((double) ((Item) a).getValue()));
		// se metto il casta ContinuousItem funziona lo stesso ma qual � meglio dei due?
	}
}
