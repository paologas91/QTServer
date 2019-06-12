package mining;

import data.Data;
import data.Tuple;
import java.util.Set;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;


/**
 * memorizza il centroide e gli indici delle tuple che fanno parte del cluster.
 */

class Cluster implements Iterable<Integer>, Comparable<Cluster>, Serializable {

private Tuple centroid;
private Set<Integer> clusteredData = new HashSet<Integer>();

Cluster(final Tuple centroid) {
								this.centroid = centroid;
}

Tuple getCentroid() {
								return centroid;
}

/**
 * aggiunge una tupla.
 * @param id da inserire in clusteredData.
 * @return true se la tupla è stata inserita.
 */
boolean addData(final int id) {
								return clusteredData.add(id);
}

/**
 * verifica se una transazione è clusterizzata nell'array corrente.
 * @param id della tupla di cui si vuole effettuare la verifica.
 * @return true se è contenuta.
 */
boolean contain(final int id) {
								return clusteredData.contains(id); // oppure (Integer(id)) ????
}
/**
 * rimuove la tupla che ha cambiato il cluster.
 * @param id della tupla da rimuovere.
 */
void removeTuple(final int id) {
								clusteredData.remove(id);
}

int getSize() {
								return clusteredData.size();
}

public Iterator<Integer> iterator() {
								return clusteredData.iterator();
}

@Override
public String toString() {
								String str = "Centroid=(";
								for (int i = 0; i < centroid.getLength(); i++) {
																str += centroid.get(i) + " ";
								}
								str += ")";
								return str;
}

public int compareTo(Cluster c) {
								if (this.getSize() > c.getSize()) {
																return 1;
								}
								else {
																return -1;
								}
}

public String toString(final Data data) {
								String str = "Centroid=(";
								for (int i = 0; i < centroid.getLength(); i++) {
																str += centroid.get(i) + " ";
								}
								str += ")\nExamples:\n";
								for (Integer i : clusteredData) {
																str += "[";
																for (int j = 0; j < data.getNumberOfAttributes(); j++) {
																								str += data.getAttributeValue(i, j) + " ";
																}
																str += "] dist=" + getCentroid().getDistance(data.getItemSet(i)) + "\n";
								}
								str += "\nAvgDistance=" + getCentroid().avgDistance(data, clusteredData);
								return str;
}
}
