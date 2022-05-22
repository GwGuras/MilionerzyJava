package pl.edu.anstar.milionerzy.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Question {
	
	private String[] answersArray = new String[4];
	private byte correct;
	private int prize;
	private String questionString;
	
	/**
	 * 
	 * @return {@link String} literal of question. Ready to be inserted into
	 *         question text field.
	 */
	public String getQuestionString() {
		return questionString;
	}
	
	private enum Answer {
		FIRST, SECOND, THIRD, FOURTH
	}
	
	/**
	 * 
	 * @param which {@link Answer} enum poining to which question from array do we
	 *              want to get (FIRST,SECOND,THIRD,FOURTH)
	 * @return {@link String} literal of answer. Ready to be inserted into button
	 */
	public String getAnswer(Answer which) {
		switch (which) {
		case FIRST:
			return answersArray[0];
		case SECOND:
			return answersArray[1];
		case THIRD:
			return answersArray[2];
		case FOURTH:
			return answersArray[3];
		default:
			return null;
		}
	}
	
	/**
	 * 
	 * @return Correct answer number from {@code 1} to {@code 4} of this
	 *         {@link Question} object
	 */
	public byte getCorrect() {
		return correct;
	}
	
	/**
	 * 
	 * @return which prize was this question associated with
	 */
	public int getPrize() {
		return prize;
	}
	
	public Question(String questionString, String[] answersArray, byte correct, int prize) {
		this.answersArray = answersArray;
		this.correct = correct;
		this.questionString = questionString;
		this.prize = prize;
	}
	
	public Question(ResultSet resultSet, int prize) {
		try {
			resultSet.first();
			for (int i = 0; i < 3; i++) {
				this.answersArray[i] = resultSet.getString(i + 1);
			}
			this.correct = resultSet.getByte("correct");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sB = new StringBuilder(this.questionString);
		sB.append(System.lineSeparator());
		for (String s : answersArray) {
			sB.append(s);
			sB.append(System.lineSeparator());
		}
		sB.append("Correct: " + this.correct);
		return sB.toString();
	}
	
}
