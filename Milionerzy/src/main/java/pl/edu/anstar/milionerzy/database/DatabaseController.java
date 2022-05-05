package pl.edu.anstar.milionerzy.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseController {
	
	private Connection connection = null;
	
	private void connect() {
		try {
			StringBuilder sB = new StringBuilder("jdbc:sqlite:");
			sB.append(System.getProperty("user.dir")); // project location
			sB.append("/resources/assets/Milionerzy.db");
			this.connection = DriverManager.getConnection(sB.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void disconnect() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection = null;
	}
	
	/**
	 * @param prize The prize as integer
	 * @return Question object
	 */
	public Question getRandomQuestion(int prize) {
		if (connection == null) {
			connect();
		}
		try {
			PreparedStatement statement = connection
					.prepareStatement("SELECT question,answer1,answer2,answer3,answer4,correct"
							+ " FROM Quesitons WHERE prize=" + prize + " ORDER BY random() LIMIT 1;");
			ResultSet results = statement.executeQuery();
			return new Question(results, prize);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;
	}
	
}
