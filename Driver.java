
public class Driver {

	public static void main(String[] args) throws Exception {
		Menu m = new Menu(); // launch address book menu
		AccessDB addressDB = new AccessDB();
		addressDB.readDB(m.getAddressBook());
	}

}
