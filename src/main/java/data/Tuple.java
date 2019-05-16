package data;

import java.util.Set;

public class Tuple {

	private Item[] tuple; // array di Item

	Tuple(final int size) {
		tuple = new Item[size];
	}

	public int getLength() {
		return tuple.length;
	}

	public Item get(final int i) {
		return tuple[i];
	}

	void add(final Item c, final int i) {
		tuple[i] = c;
	}

	public double getDistance(final Tuple obj) {
		/*
		 * Comportamento: determina la distanza tra la tupla riferita da obj e la tupla
		 * corrente (riferita da this). La distanza è ottenuta come la somma delle
		 * distanze tra gli item in posizioni eguali nelle due tuple. Fare uso di double
		 * distance(Object a) di Item
		 */
		double distance = 0.0;
		for (int i = 0; i < obj.getLength(); i++) {
			distance += get(i).distance(obj.get(i));
		}
		return distance;
	}

	public double avgDistance(final Data data, final Set<Integer> clusteredData) {
		double p = 0.0, sumD = 0.0;
		for (Integer i : clusteredData) {
			double d = getDistance(data.getItemSet(i));
			sumD += d;
		}
		p = sumD / clusteredData.size();
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
