package data;

import java.util.List;
import java.util.LinkedList;

public class Data {

	private Object[][] data;
	private int numberOfExamples;
	private List<Attribute> attributeSet = new LinkedList<Attribute>();

	public Data() {

		//data

		data = new Object[14][5];

		data[0][0] = new String("sunny");
		data[1][0] = new String("sunny");
		data[2][0] = new String("overcast");
		data[3][0] = new String("rain");
		data[4][0] = new String("rain");
		data[5][0] = new String("rain");
		data[6][0] = new String("overcast");
		data[7][0] = new String("sunny");
		data[8][0] = new String("sunny");
		data[9][0] = new String("rain");
		data[10][0] = new String("sunny");
		data[11][0] = new String("overcast");
		data[12][0] = new String("overcast");
		data[13][0] = new String("rain");

		data[0][1] = new Double (30.3);
		data[1][1] = new Double (30.3);
		data[2][1] = new Double (30);
		data[3][1] = new Double (13);
		data[4][1] = new Double (0);
		data[5][1] = new Double (0);
		data[6][1] = new Double (0.1);
		data[7][1] = new Double (13);
		data[8][1] = new Double (0.1);
		data[9][1] = new Double (12);
		data[10][1] = new Double (12.5);
		data[11][1] = new Double (12.5);
		data[12][1] = new Double(29.21);
		data[13][1] = new Double (12.5);

		data[0][2] = new String("high");
		data[1][2] = new String("high");
		data[2][2] = new String("high");
		data[3][2] = new String("high");
		data[4][2] = new String("normal");
		data[5][2] = new String("normal");
		data[6][2] = new String("normal");
		data[7][2] = new String("high");
		data[8][2] = new String("normal");
		data[9][2] = new String("normal");
		data[10][2] = new String("normal");
		data[11][2] = new String("high");
		data[12][2] = new String("normal");
		data[13][2] = new String("high");

		data[0][3] = new String("weak");
		data[1][3] = new String("strong");
		data[2][3] = new String("weak");
		data[3][3] = new String("weak");
		data[4][3] = new String("weak");
		data[5][3] = new String("strong");
		data[6][3] = new String("strong");
		data[7][3] = new String("weak");
		data[8][3] = new String("weak");
		data[9][3] = new String("weak");
		data[10][3] = new String("strong");
		data[11][3] = new String("strong");
		data[12][3] = new String("weak");
		data[13][3] = new String("strong");

		data[0][4] = new String("no");
		data[1][4] = new String("no");
		data[2][4] = new String("yes");
		data[3][4] = new String("yes");
		data[4][4] = new String("yes");
		data[5][4] = new String("no");
		data[6][4] = new String("yes");
		data[7][4] = new String("no");
		data[8][4] = new String("yes");
		data[9][4] = new String("yes");
		data[10][4] = new String("yes");
		data[11][4] = new String("yes");
		data[12][4] = new String("yes");
		data[13][4] = new String("no");

		// numberOfExamples

		numberOfExamples = 14;

		//explanatory Set

		// siccome è un vettore e non un oggetto posso istanziare la classe astratta
		// attributeSet = new Attribute[5];

		String[] outLookValues = new String[3];
		outLookValues[0] = "overcast";
		outLookValues[1] = "rain";
		outLookValues[2] = "sunny";
		attributeSet.add(new DiscreteAttribute("Outlook", 0, outLookValues));

		attributeSet.add(new ContinuousAttribute("Temperature", 1, 3.2, 38.7));

		String[] humidityValues = new String[2];
		humidityValues[0] = "high";
		humidityValues[1] = "normal";
		attributeSet.add(new DiscreteAttribute("Humidity", 2, humidityValues));

		String[] windValues = new String[2];
		windValues[0] = "weak";
		windValues[1] = "strong";
		attributeSet.add(new DiscreteAttribute("Wind", 3, windValues));

		String[] playTennisValues = new String[2];
		playTennisValues[0] = "yes";
		playTennisValues[1] = "no";
		attributeSet.add(new DiscreteAttribute("PlayTennis", 4, playTennisValues));
	}

	public int getNumberOfExamples() {
		return numberOfExamples;
	}

	public int getNumberOfAttributes() {
		return attributeSet.size();
	}

	public Object getAttributeValue(final int exampleIndex, final int attributeIndex) {
		return data[exampleIndex][attributeIndex];
	}

	public Attribute getAttribute(final int index) {
		return attributeSet.get(index);
	}

	public List<Attribute> getAttributeSchema() {
		return attributeSet;
	}

	public Tuple getItemSet(final int index) {
		Tuple tuple = new Tuple(attributeSet.size());
		for (int i = 0; i < attributeSet.size(); i++) {
			if (attributeSet.get(i) instanceof DiscreteAttribute) {
				tuple.add(new DiscreteItem((DiscreteAttribute) attributeSet.get(i), (String) data[index][i]), i);
			} else {
				tuple.add(new ContinuousItem((ContinuousAttribute) attributeSet.get(i), (double) data[index][i]), i); // dubbio
			}
		}
		return tuple;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < getNumberOfAttributes(); i++) {
			// se non avessi ridefinito il toString in Attribute, avrei la stampa di default di Object
			s += attributeSet.get(i);
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
