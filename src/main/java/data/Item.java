package data;

abstract class Item {

	private Attribute attribute;
	private Object value;

	Item(final Attribute attribute, final Object value) {

		this.attribute = attribute;
		this.value = value;
	}

	Attribute getAttribute() {

		return attribute;
	}

	Object getValue() {

		return value;
	}

	public String toString() {

		return value.toString();
	}

	abstract double distance(Object a);
}
