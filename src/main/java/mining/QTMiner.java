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

public class QTMiner {

	/**
	 * @param args
	 */
	private ClusterSet C;
	private double radius;

	public QTMiner(final double radius) {
		C = new ClusterSet();
		this.radius = radius;
	}

	/**
	 * @param fileName percorso + nome file
	 */
	public QTMiner(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
		FileInputStream inFile = new FileInputStream(fileName);
		ObjectInputStream inStream = new ObjectInputStream(inFile);
		C = (ClusterSet) inStream.readObject();
		inStream.close();
	}

	public ClusterSet getC() {
		return C;
	}

	public int compute(final Data data) throws ClusteringRadiusException, EmptyDatasetException {// throws EmptyDatasetException
		
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
			// Ricerca cluster più popoloso
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

	Cluster buildCandidateCluster(final Data data, final boolean isClustered[]) {// throws EmptyDatasetException
		/*
		 * Comportamento: costruisce un cluster per ciascuna tupla di data non ancora
		 * clusterizzata in un cluster di C e restituisce il cluster candidato più
		 * popoloso
		 */
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

		// ricerco il cluster più popoloso

		return ((TreeSet<Cluster>) C).last();
	}
	
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
