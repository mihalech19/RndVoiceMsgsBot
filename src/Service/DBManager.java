package Service;
import java.sql.*;
import java.util.logging.Logger;

public class DBManager {

	// private static String url = "jdbc:mysql://localhost/voicemsgs?serverTimezone=Europe/Moscow&useSSL=false";
	//private static String username = "bot";
	// private static String password = "BOTbot123";
	
	private static String url = "jdbc:" + System.getenv("CLEARDB_DATABASE_URL");
	
	
	private static final Logger log = Logger.getLogger(DBManager.class.getName());

	public static int Count() {
		int count = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		//	try (Connection conn = DriverManager.getConnection(url, username, password)) {
			
			try (Connection conn = DriverManager.getConnection(url)) {

				String sql = "select count(*) from messages";
				try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
					ResultSet resultSet = preparedStatement.executeQuery();
					if(resultSet.next())
						count = resultSet.getInt(1);
				}
			}
		} catch (Exception ex) {
			log.severe(ex.getMessage());
		}
		return count;

	}
	
	public static int Insert(VoiceMsg msg) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			try (Connection conn = DriverManager.getConnection(url)) {

				String sql = "INSERT INTO messages (fileUniqueId, fileId, username, sendtime) Values (?, ?, ?, ?)";
				try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
					preparedStatement.setString(1, msg.getFileUniqueId());
					preparedStatement.setString(2, msg.getFileId());
					preparedStatement.setString(3, msg.getUsername());
					preparedStatement.setTimestamp(4, msg.getSendtime());
					return preparedStatement.executeUpdate();
				}
			}
		} catch (Exception ex) {
			log.severe(ex.getMessage());
		}
		return 0;
	}
	
	public static VoiceMsg GetRandom() {
		VoiceMsg voiceMsg = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			try (Connection conn = DriverManager.getConnection(url)) {

				String sql = "SELECT * FROM messages ORDER BY rand() LIMIT 1";
				try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
					ResultSet resultSet = preparedStatement.executeQuery();
					if(resultSet.next()){
						 
                        String fileUniqueId = resultSet.getString(1);
                        String fileId = resultSet.getString(2);
                        String username = resultSet.getString(3);
                        Timestamp timestamp = resultSet.getTimestamp(4);
                        voiceMsg = new VoiceMsg(fileUniqueId, fileId, username, timestamp);
                    }
				}
			}
		} catch (Exception ex) {
			log.severe(ex.getMessage());
		}
		return voiceMsg;
	}
}
