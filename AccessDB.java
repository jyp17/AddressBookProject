import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccessDB {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;
	
	public void readDB(AddressBook adBk) throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBook", "root", "I7105trg");
			statement = connect.createStatement();
			
			// insert contacts into address book database
			for (Contact c : adBk.getContactList()) {
				preparedStatement = connect.prepareStatement("insert into AddressBook.contacts (first_name, last_name, address, phone) values(?, ?, ?, ?)");
				
				preparedStatement.setString(1, c.getFirstName());
				preparedStatement.setString(2, c.getLastName());
				preparedStatement.setString(3, c.getAddress());
				preparedStatement.setString(4, c.getPhone());
				
				preparedStatement.executeUpdate();
			}
			
			preparedStatement = connect.prepareStatement("select * from AddressBook.contacts");
			resultSet = preparedStatement.executeQuery();
			writeResultSet(resultSet);
			
		} catch(Exception e) {
			throw e;
		} finally {
			close();
		}
	}
	
	private void writeResultSet(ResultSet resultSet) throws SQLException {
		while(resultSet.next()) {
			String first_name = resultSet.getString("first_name");
			String last_name = resultSet.getString("last_name");
			String address = resultSet.getString("address");
			String phone = resultSet.getString("phone");
			
			System.out.println("Name: " + first_name + " " + last_name +
					"\nAddress: " + address + 
					"\nPhone Number: " + phone);
		}
	}
	
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
