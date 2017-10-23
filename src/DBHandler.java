import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHandler {
	
	public static final String DDRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String DURL = "jdbc:derby:fileinventory;create=true";

	public static void createDBTable() throws ClassNotFoundException, SQLException {
		Class.forName(DDRIVER);
		Connection connection = DriverManager.getConnection(DURL);
		connection.createStatement().execute(
				"CREATE TABLE FileInventory ( filename varchar(150), filesize int, location varchar(300), checksum varchar(40))");
	}
	
	public static void insertField(String fileName, long fileSize, String location, String checksum) throws SQLException {
		try {
			Connection conn = DriverManager.getConnection(DURL);
			String query = "INSERT INTO FileInventory (filename, filesize, location, checksum) VALUES (?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, fileName);
			statement.setLong(2, fileSize);
			statement.setString(3, location);
			statement.setString(4, checksum);
			
			int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println(fileName + " added to DB");
            }
            conn.close();
		} catch (SQLException ex) {
            ex.printStackTrace();
        }
	}
	
	public static void queryDB(String sqlStatement) throws SQLException {
		Connection connection = DriverManager.getConnection(DURL);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sqlStatement);
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		int columnCount = resultSetMetaData.getColumnCount();
		for (int x = 1; x <= columnCount; x++)
			System.out.format("%100s", resultSetMetaData.getColumnName(x));
		while (resultSet.next()) {
			System.out.println();
			for (int x = 1; x <= columnCount; x++)
				System.out.format("%100s", resultSet.getString(x));
				System.out.println();
		}
		statement.close();
		connection.close();
	}
}