package pl.edu.anstar.milionerzy.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Random;

public class CSVController {
	/**
	 * Amount of rows associated with question in each individual .csv file.
	 */
	private static final int LINES_IN_CSV = 10;
	private static final String SPLIT_DELIM = ";";
	
	/**
	 * @param prize Prize money expected for this question.
	 * @return {@link Question} object
	 */
	public static Question getRandomQuestion(int prize) {
		try {
			String csvNumber = getStageNumber(prize);
			System.out.println("CSV number: " + csvNumber);
//			System.out.println(CSVController.class.getResource("2.csv"));
			URL csvFileLocation = CSVController.class.getResource(csvNumber + ".csv");
			System.out.println("CSV resource location url: " + csvFileLocation);
			File CSVFile = new File(csvFileLocation.getPath());
			BufferedReader br = new BufferedReader(new FileReader(CSVFile));
			int rand = new Random().nextInt(LINES_IN_CSV); // random from 0 to lines-1
			for (int i = 0; i <= rand - 1; i++) {
				br.readLine();
			}
			String line = br.readLine();
			br.close();
			String[] record = line.split(SPLIT_DELIM);
			// question;answer1;answer2;answer3;answer4;correct
			String question = record[0];
			byte correct = Byte.parseByte(record[5]);
			String[] answersArray = new String[4];
			System.arraycopy(record, 1, answersArray, 0, 4);
			return new Question(question, answersArray, correct, prize);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	private static String getStageNumber(int prize) throws Exception {
		byte stageNumber = 0;
		switch (prize) {
		case 1000000:
			stageNumber++;
		case 500000:
			stageNumber++;
		case 250000:
			stageNumber++;
		case 125000:
			stageNumber++;
		case 75000:
			stageNumber++;
		case 40000:
			stageNumber++;
		case 20000:
			stageNumber++;
		case 10000:
			stageNumber++;
		case 5000:
			stageNumber++;
		case 2000:
			stageNumber++;
		case 1000:
			stageNumber++;
		case 500:
			stageNumber++;
			break;
		default:
			throw new Exception("Invalid prize");
		}
		return Byte.toString(stageNumber);
	}
}
