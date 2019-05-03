import data.Data;
import exceptions.ClusteringRadiusException;
import keyboardinput.Keyboard;
import mining.QTMiner;

public class MainTest {

	/**
	 * @param args an array of strings
	 */
	public static void main(final String[] args) {

		Data data = new Data();
		System.out.println(data);
		double radius = 2.0;

		while (true) {
			do {
				System.out.println("Insert radius (>0)");
				radius = Keyboard.readDouble();
			} while (radius <= 0);

			QTMiner qt = new QTMiner(radius);
			int numIter = 0;
			try {
				numIter = qt.compute(data);
				System.out.println("Number of clusters:" + numIter);
				System.out.println(qt.getC().toString(data));
			} catch (ClusteringRadiusException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}

			System.out.println("New execution?(y/n) ");
			if (Keyboard.readChar() == 'n') {
				break;
			}
		}
	}
}
