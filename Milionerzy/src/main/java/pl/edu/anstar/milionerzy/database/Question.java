package pl.edu.anstar.milionerzy.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Question {
	
	private String[] answersArray = new String[4];
	private byte correct;
	private int prize;
	private String questionString;
	
	public String getQuestionString() {
		return questionString;
	}
	
	private enum Answer {
		FIRST, SECOND, THIRD, FOURTH
	}
	
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
	
	public String[] getAnswersArray() {
		return answersArray;
	}
	
	public byte getCorrect() {
		return correct;
	}
	
	public int getPrize() {
		return prize;
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
	
}
