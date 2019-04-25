
class QTMiner {
	
	/** 
	 * @param args
	 */
	private ClusterSet C;
	private double radius;
	
	QTMiner(double radius) {
		C = new ClusterSet();
		this.radius = radius;
	}
	
	ClusterSet getC() {
		return C;
	}
	
	int compute(Data data) {
		int numclusters = 0;
		boolean isClustered[] = new boolean[data.getNumberOfExamples()];
		for (int i = 0; i < isClustered.length; i++)
			isClustered[i] = false;
		int countClustered = 0;
		while (countClustered != data.getNumberOfExamples()) {
			//Ricerca cluster più popoloso
			Cluster c = buildCandidateCluster(data, isClustered);
			C.add(c);
			numclusters++;
			//Rimuovo tuple clusterizzate da dataset
			int clusteredTupleId[] = c.iterator();
			for (int i = 0; i < clusteredTupleId.length; i++){
				isClustered[clusteredTupleId[i]] = true;
			}
			countClustered += c.getSize();
		}
		return numclusters;
	}
	
	Cluster buildCandidateCluster(Data data, boolean isClustered[]) {
		/*
		 * Comportamento: costruisce un cluster per ciascuna tupla di data non ancora clusterizzata 
		 * in un cluster di C e restituisce il cluster candidato più popoloso
		 */
		ClusterSet C = new ClusterSet();
		int n = 0;
		for (int i = 0; i < data.getNumberOfExamples(); i++) {
			if (!isClustered[i]) {
				Cluster candidato = new Cluster(data.getItemSet(i));
				for (int j = 0; j < data.getNumberOfExamples(); j++) {
					if (!isClustered[j]) {
						if (data.getItemSet(i).getDistance(data.getItemSet(j)) <= radius)
							candidato.addData(j);
					}
				}
				C.add(candidato);
				n++;
			}
		}
		// ricerco il cluster più popoloso
		int max = C.get(0).getSize();
		Cluster popoloso = C.get(0);
		for (int i = 0; i < n; i++) {
			if (C.get(i).getSize() > max){
				popoloso = C.get(i);
				max = C.get(i).getSize();
			}	
		}
		return popoloso;
	}
}
