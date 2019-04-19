
public abstract class Attribute {
	
	private String name;
	private int index;
	
	/**
	 * Inizializza i valori dei membri name, index
	 * @param name nome dell'attributo
	 * @param index identificativo numerico dell'attributo
	 */
	
	protected Attribute(String name, int index) {   // la classe è astratta pertanto non può essere public
		
		this.name = name;
		this.index = index;
	}
	
	/**
	 * 
	 * @return nome dell'attributo
	 */
	
	public String getName() {
		
		return name;   // oppure this.name
	}
	
	public int getIndex() {
		
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
