
public class Data {

	private Object[][] data;
	private int numberOfExamples;
	private Attribute[] attributeSet;

	public Data() {

		//data

		data = new Object[14][5];

		data[0] = new String[]{"sunny", "hot", "high", "weak", "no"};
		data[1] = new String[]{"sunny", "hot", "high", "strong", "no"};
		data[2] = new String[]{"overcast", "hot", "high", "weak", "yes"};
		data[3] = new String[]{"rain", "mild", "high", "weak", "yes"};
		data[4] = new String[]{"rain", "cool", "normal", "weak", "yes"};
		data[5] = new String[]{"rain", "cool", "normal", "strong", "no"};
		data[6] = new String[]{"overcast", "cool", "normal", "strong", "yes"};
		data[7] = new String[]{"sunny", "mild", "high", "weak", "no"};
		data[8] = new String[]{"sunny", "cool", "normal", "weak", "yes"};
		data[9] = new String[]{"rain", "mild", "normal", "weak", "yes"};
		data[10] = new String[]{"sunny", "mild", "normal", "strong", "yes"};
		data[11] = new String[]{"overcast", "mild", "high", "strong", "yes"};
		data[12] = new String[]{"overcast", "hot", "normal", "weak", "yes"};
		data[13] = new String[]{"rain", "mild", "high", "strong", "no"};

		// numberOfExamples

		numberOfExamples = 14;

		//explanatory Set

		// siccome è un vettore e non un oggetto posso istanziare la classe astratta
		attributeSet = new Attribute[5];

		String[] outLookValues = new String[3];
		outLookValues[0] = "overcast";
		outLookValues[1] = "rain";
		outLookValues[2] = "sunny";
		attributeSet[0] = new DiscreteAttribute("Outlook", 0, outLookValues);

		String[] temperatureValues = new String[3];
		temperatureValues[0] = "hot";
		temperatureValues[1] = "mild";
		temperatureValues[2] = "cool";
		attributeSet[1] = new DiscreteAttribute("Temperature", 1, temperatureValues);

		String[] humidityValues = new String[2];
		humidityValues[0] = "high";
		humidityValues[1] = "normal";
		attributeSet[2] = new DiscreteAttribute("Humidity", 2, humidityValues);

		String[] windValues = new String[2];
		windValues[0] = "weak";
		windValues[1] = "strong";
		attributeSet[3] = new DiscreteAttribute("Wind", 3, windValues);

		String[] playTennisValues = new String[2];
		playTennisValues[0] = "yes";
		playTennisValues[1] = "no";
		attributeSet[4] = new DiscreteAttribute("PlayTennis", 4, playTennisValues);
	}

	public int getNumberOfExamples() {
		return numberOfExamples;
	}

	public int getNumberOfAttributes() {
		return attributeSet.length;
	}

	public Object getAttributeValue(final int exampleIndex, final int attributeIndex) {
		return data[exampleIndex][attributeIndex];
	}

	public Attribute getAttribute(final int index) {
		return attributeSet[index];
	}

	public Attribute[] getAttributeSchema() {
		return attributeSet;
	}

	Tuple getItemSet(final int index) {
		Tuple tuple = new Tuple(attributeSet.length);
		for (int i = 0; i < attributeSet.length; i++) {
			tuple.add(new DiscreteItem((DiscreteAttribute) attributeSet[i], (String) data[index][i]), i);
		}
		return tuple;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < getNumberOfAttributes(); i++) {
			// se non avessi ridefinito il toString in Attribute, avrei la stampa di default di Object
			s += attributeSet[i];
			if (i != getNumberOfAttributes() - 1) {
				s += ", ";
			}
		}
		s += '\n';
		for (int i = 0; i < getNumberOfExamples(); i++) {
			s += (i + 1) + ":";
			for (int j = 0; j < getNumberOfAttributes(); j++) {
				s += data[i][j];
				if (j != getNumberOfAttributes() - 1) {
					s += ", ";
				}
			}
			s += "\n";
		}
		return s;
	}

	public static void main(final String[] args) {

		Data trainingSet = new Data();
		System.out.println(trainingSet);

		/*DiscreteAttribute attribute1 = (DiscreteAttribute)trainingSet.getAttribute(0);
		for (int i = 0; i < attribute1.getNumberOfDistinctValues(); i++) {
			System.out.println(attribute1.getValue(i));
		}*/

		//System.out.println(trainingSet.getItemSet(0));
		//System.out.println(trainingSet.getItemSet(1));
		//System.out.println(trainingSet.getItemSet(0).getDistance(trainingSet.getItemSet(1)));
		//System.out.println(trainingSet.getNumberOfExamples());

		for (int i = 0; i < trainingSet.getNumberOfExamples(); i++) {
			if (trainingSet.getItemSet(1).getDistance(trainingSet.getItemSet(i)) <= 2.0) {
				System.out.println(trainingSet.getItemSet(0).getDistance(trainingSet.getItemSet(i)));
				System.out.println(trainingSet.getItemSet(i));
				System.out.println(i);
			}

		}

		/*for (int i = 0; i < trainingSet.getAttributeSchema().length; i++) {
		*
		*	for (int j = 0; j < trainingSet.getAttributeSchema()[i].getNumberOfDistinctValues(); j++) {
		*
		*	}
		*	System.out.println();
		}*/

	}
}
