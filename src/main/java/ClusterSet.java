
class ClusterSet {

	private Cluster C[] = new Cluster[0];
	
	ClusterSet() {}
	
	void add(Cluster c) {
		Cluster tempC[] = new Cluster[C.length+1];
		for (int i = 0; i < C.length; i++)
			tempC[i] = C[i];
		tempC[C.length] = c;
		C = tempC;
	}
	
	Cluster get(int i) {	
		return C[i];
	}
	
	public String toString() {
		String str = "";
		for (int i = 0; i < C.length; i++) {
			str += C[i].getCentroid();
		}
		return str;
	}
	
	public String toString(Data data) {
		String str = "";
		for (int i = 0; i < C.length; i++) {	
			if (C[i] != null) 			
				str += i + ":" + C[i].toString(data) + "\n";		
		}
		return str;
	}
}
