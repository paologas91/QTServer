
abstract class Attribute {

	private String name;
	private int index;

	/**
	 * Inizializza i valori dei membri name, index.
	 * @param name nome dell'attributo.
	 * @param index identificativo numerico dell'attributo.
	 */

	Attribute(final String name, final int index) {		// la classe è astratta pertanto non può essere public

		this.name = name;
		this.index = index;
	}

	/**
	 * @return nome dell'attributo
	 */

	String getName() {

		return name;	// oppure this.name
	}

	int getIndex() {

		return index;
	}

	/*
	 * evitando di scrivere il toString(), verrà utilizzato quello di default della classe Object
	 */

	@Override
	public String toString() {

		return name;
	}
}
