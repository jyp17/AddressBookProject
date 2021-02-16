import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	private AddressBook myContacts = new AddressBook();
	private int userSelection;
	private boolean isActive = true;
	private Scanner input = new Scanner(System.in);

	public Menu() {		
		System.out.println("-----------ADDRESS BOOK-----------");
		
		while (isActive) {
			System.out.println("Select from the following options:" +
					"\n1. Add a new contact" +
					"\n2. Update an existing contact" +
					"\n3. Delete a contact" +
					"\n4. List all contacts" +
					"\n5. Search for contact" +
					"\n6. Quit");
			
			try {
				userSelection = input.nextInt();
			} catch(InputMismatchException e) {
				input.nextLine();
			}
			
			switch(userSelection) {
				case 1:
					myContacts.add();
					break;
				case 2:
					myContacts.update();
					break;
				case 3:
					myContacts.delete();
					break;
				case 4:
					myContacts.list();
					break;
				case 5:
					myContacts.search();
					break;
				case 6:
					isActive = false;
					System.out.println("Goodbye!");
					break;
				default:
					System.out.println("Invalid input. You must select from the available menu options (1 - 6).");
			}
		}
	}
	
	public AddressBook getAddressBook() {
		return myContacts;
	}

}
