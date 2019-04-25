
class DiscreteItem extends Item {

	DiscreteItem(DiscreteAttribute attribute, String value) {
		/*
		 * constructor
		 */
		super(attribute, value);
	}
	
	double distance(Object a) {
		if (getValue().equals(a)) // se non metto il toString() fallisce sempre il confronto
			return 0.0;	
		else 		
			return 1.0;
	}
}
