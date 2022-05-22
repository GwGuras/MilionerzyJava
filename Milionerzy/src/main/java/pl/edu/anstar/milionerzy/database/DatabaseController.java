package pl.edu.anstar.milionerzy.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseController {
	
	/**
	 * 
	 * @param prize Prize money expected for this question as in database
	 * @return {@link Question} object
	 */
	public static Question getRandomQuestion(int prize) {
		
		Connection connection = null;
		try {
			StringBuilder sB = new StringBuilder("jdbc:sqlite:");
			sB.append(DatabaseController.class.getResource("Milionerzy.db").getPath());
			System.out.println("Attempting to connect to:");
			System.out.println(sB.toString());
			connection = DriverManager.getConnection(sB.toString());
			PreparedStatement statement = connection
					.prepareStatement("SELECT question,answer1,answer2,answer3,answer4,correct"
							+ " FROM Quesitons WHERE prize=" + prize + " ORDER BY random() LIMIT 1;");
			ResultSet results = statement.executeQuery();
			return new Question(results, prize);
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
