import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccessDB {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;
	protected static ArrayList<Contact> allContacts = new ArrayList<>();
	protected static ArrayList<Contact> toBeAdded = new ArrayList<>();
	protected static ArrayList<Contact> toBeDeleted = new ArrayList<>();
	
	public void readDB() throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBook", "root", "I7105trg");
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from AddressBook.contacts");
			
			populateList(resultSet);
			
			Menu addressMenu = new Menu();
			
			deleteContacts();
			addNewContacts();
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
	
	private void populateList(ResultSet resultSet) throws SQLException {
		while(resultSet.next()) {
			Contact c = new Contact();
			c.setFirstName(resultSet.getString("first_name"));
			c.setLastName(resultSet.getString("last_name"));
			c.setAddress(resultSet.getString("address"));
			c.setPhone(resultSet.getString("phone"));
			allContacts.add(c);
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
	
	public void addNewContacts() throws Exception {
		for (Contact c : toBeAdded) {
			preparedStatement = connect.prepareStatement("insert into AddressBook.contacts (first_name, last_name, address, phone) values(?, ?, ?, ?)");
			
			preparedStatement.setString(1, c.getFirstName());
			preparedStatement.setString(2, c.getLastName());
			preparedStatement.setString(3, c.getAddress());
			preparedStatement.setString(4, c.getPhone());
			
			preparedStatement.executeUpdate();
		}
	}
	
	public void deleteContacts() throws Exception {
		for (Contact c : toBeDeleted) {
			preparedStatement = connect.prepareStatement("delete from AddressBook.contacts where first_name = ? and last_name = ?;");
			preparedStatement.setString(1, c.getFirstName());
			preparedStatement.setString(2, c.getLastName());
			preparedStatement.executeUpdate();
		}
	}
}
