package mining;

import java.util.Set;
import java.util.TreeSet;
import data.Data;
import data.EmptyDatasetException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * classe che implementa l'algoritmo di Quality Threshold.
 */
public class QTMiner {

private ClusterSet C;
private double radius;

public QTMiner(final double radius) {
								C = new ClusterSet();
								this.radius = radius;
}

/**
 * carica da file un ClusterSet salvato.
 * @param fileName percorso + nome file
 */
public QTMiner(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
								FileInputStream inFile = new FileInputStream(fileName);
								ObjectInputStream inStream = new ObjectInputStream(inFile);
								C = (ClusterSet) inStream.readObject();
								inStream.close();
								inFile.close();
}

/**
 * restituisce il ClusterSet creato.
 * @return C ClusterSet.
 */
public ClusterSet getC() {
								return C;
}

/**
 * richiama buildCandidateCluster finchè tutte le tuple non sono state inserite in un cluster.
 * restituisce il numero di cluster creati.
 * @param data dataSet contenente i dati da clusterizzare.
 * @return numclusters numero di cluster generati.
 * @throws ClusteringRadiusException
 * @throws EmptyDatasetException
 */
public int compute(final Data data) throws ClusteringRadiusException, EmptyDatasetException {

								if (data.getNumberOfExamples() == 0) {
																throw new EmptyDatasetException("Empty dataset!");
								}
								int numclusters = 0;
								boolean[] isClustered = new boolean[data.getNumberOfExamples()];
								for (int i = 0; i < isClustered.length; i++) {
																isClustered[i] = false;
								}
								int countClustered = 0;
								while (countClustered != data.getNumberOfExamples()) {
																// Ricerca cluster pi� popoloso
																Cluster c = buildCandidateCluster(data, isClustered);
																C.add(c);
																numclusters++;
																// Rimuovo tuple clusterizzate da dataset
																for (Integer i : c) {
																								isClustered[i] = true;
																}
																countClustered += c.getSize();
								}

								if (numclusters == 1) {
																throw new ClusteringRadiusException(data.getNumberOfExamples() + "tuples in one cluster!");
								}
								return numclusters;
}

/**
 * Costruisce un cluster per ciascuna tupla di data non ancora
 * clusterizzata in un cluster di C e restituisce il cluster candidato più
 * popoloso
 * @param data dataSet contenente i dati da clusterizzare.
 * @param isClustered array di booleani che indica quali tuple sono state già clusterizzate
 * @return
 */
Cluster buildCandidateCluster(final Data data, final boolean isClustered[]) {

								Set<Cluster> C = new TreeSet<Cluster>();
								for (int i = 0; i < data.getNumberOfExamples(); i++) {
																if (!isClustered[i]) {
																								Cluster candidato = new Cluster(data.getItemSet(i));
																								for (int j = 0; j < data.getNumberOfExamples(); j++) {
																																if (!isClustered[j]) {
																																								if (data.getItemSet(i).getDistance(data.getItemSet(j)) <= radius) {
																																																candidato.addData(j);
																																								}
																																}
																								}
																								C.add(candidato);
																}
								}
								return ((TreeSet<Cluster>) C).last();
}

/**
 * serializza il ClusterSet su file
 * @param fileName nome del file su cui salvare il ClusterSet.
 */

public void salva(String fileName) throws FileNotFoundException, IOException {
								FileOutputStream outFile = new FileOutputStream(fileName);
								ObjectOutputStream outStream = new ObjectOutputStream(outFile);
								outStream.writeObject(C);
								outFile.close();
}

public String toString() {
								return C.toString();
}
}
