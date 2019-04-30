
class Tuple {

	private Item[] tuple; // array di Item

	Tuple(final int size) {
		tuple = new Item[size];
	}

	int getLength() {
		return tuple.length;
	}

	Item get(final int i) {
		return tuple[i];
	}

	void add(final Item c, final int i) {
		tuple[i] = c;
	}

	double getDistance(final Tuple obj) {
		/*
		 * Comportamento: determina la distanza tra la tupla riferita da obj e la tupla
		 * corrente (riferita da this). La distanza � ottenuta come la somma delle
		 * distanze tra gli item in posizioni eguali nelle due tuple. Fare uso di double
		 * distance(Object a) di Item
		 */
		double distance = 0.0;
		for (int i = 0; i < obj.getLength(); i++) {
			distance += get(i).distance(obj.get(i).getValue());
		}
		return distance;
	}

	double avgDistance(final Data data, final int[] clusteredData) {
		double p = 0.0, sumD = 0.0;
		for (int i = 0; i < clusteredData.length; i++) {
			double d = getDistance(data.getItemSet(clusteredData[i]));
			sumD += d;
		}
		p = sumD / clusteredData.length;
		return p;
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < tuple.length; i++) {
			str += tuple[i].getAttribute() + "=" + tuple[i].getValue() + ",";
		}
		return str;
	}
}
