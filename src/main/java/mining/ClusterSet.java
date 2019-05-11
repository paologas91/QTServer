package mining;

import data.Data;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

public class ClusterSet implements Iterable<Cluster> {

	private Set<Cluster> C = new TreeSet<Cluster>();

	ClusterSet() { }

	void add(final Cluster c) {
		C.add(c);
	}
	
	public Iterator<Cluster> iterator() {
		return C.iterator();
	}
	
	@Override
	public String toString() {
		String str = "";
		for (Cluster c : C) {
			str += c.getCentroid();
		}
		return str;
	}

	public String toString(final Data data) {
		String str = "";
		int i = 0;
		for (Cluster c : C) {
			str += (i + 1) + ":";
			str += c.toString(data) + "\n";
			i++;
		}
		return str;
	}
}
