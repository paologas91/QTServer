import java.io.FileNotFoundException;
import java.io.IOException;
import keyboardinput.Keyboard;
import mining.ClusteringRadiusException;
import mining.QTMiner;
import data.Data;
import data.EmptyDatasetException;

public class MainTest {
	/**
	 * @param args
	 */
	private int menu() {
		int answer;
		do {
			System.out.println("(1) Load Clusters from File");
			System.out.println("(2) Load Data from Database");
			System.out.print("(1/2):");
			answer = Keyboard.readInt();
		}
		while (answer <= 0 || answer > 2);
		return answer;
		
	}

	private QTMiner learningFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		String fileName = "";
		System.out.print("File name:");
		fileName = Keyboard.readString();
		return new QTMiner(fileName + ".dmp");
		
	}
	public static void main(String[] args) {
		/*
		try {
			System.setErr(new PrintStream(new File("log.txt")));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		*/
		MainTest main = new MainTest();
		do {
			int menuAnswer = main.menu();
			switch(menuAnswer)
			{
				case 1:
					try {
						QTMiner qt = main.learningFromFile();
						System.out.println(qt);
					} catch (FileNotFoundException e1) {
						System.out.println(e1.getMessage());
					} catch (IOException e1) {
						System.out.println(e1.getMessage());
					} catch (ClassNotFoundException e1) {
						System.out.println(e1.getMessage());
					}
					break;
				case 2:
					System.out.print("Insert table name: ");
					Data data = new Data(Keyboard.readString());
					System.out.println(data);
					char answer = 'y';
					do{
						double radius = 1.0;
						do{
							System.out.print("Insert radius (>0):");
							radius = Keyboard.readDouble();
						} while (radius <= 0);
						QTMiner qt = new QTMiner(radius);
						try
						{
							int numC = qt.compute(data);
							System.out.println("Number of clusters:" + numC);
							System.out.println(qt.getC().toString(data));
							System.out.print("Backup file name:");
							String fileName = Keyboard.readString() + ".dmp";
							System.out.println("Saving clusters in " + fileName);
							try {
								qt.salva(fileName);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
							System.out.println("Saving transaction ended!");
							
							
						}
						catch(EmptyDatasetException | ClusteringRadiusException e)
						{
							System.out.println(e.getMessage());
						}
						System.out.print("New execution?(y/n)");
						answer = Keyboard.readChar();
					}
					while (Character.toUpperCase(answer) == 'Y');
					break;
				default:
					System.out.println("Invalid option!");
		
			}
			
			System.out.print("Would you choose another option from the menu?(y/n)");
			if (Character.toUpperCase(Keyboard.readChar()) != 'Y')
				break;
			}
		while(true);
	}
}
