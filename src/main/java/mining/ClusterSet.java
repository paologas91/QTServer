package mining;

import data.Data;
import java.util.Set;
import java.util.TreeSet;
import java.io.Serializable;
import java.util.Iterator;

/**
 * memorizza tutti i cluster creati.
 */

public class ClusterSet implements Iterable<Cluster>, Serializable {

private Set<Cluster> C = new TreeSet<Cluster>();

/**
 * aggiunge un cluster al set.
 * @param c Cluster da aggiungere.
 */
void add(final Cluster c) {
								C.add(c);
}

public Iterator<Cluster> iterator() {
								return C.iterator();
}

@Override
public String toString() {
								String str = "";
								int i = 1;
								for (Cluster c : C) {
																str += i + ":Centroid=(" + c.getCentroid() + ")\n";
																i++;
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
