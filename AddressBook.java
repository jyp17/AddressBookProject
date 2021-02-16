import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddressBook extends AccessDB {
	private Scanner input = new Scanner(System.in).useDelimiter("\n");
	
	public void add() {		
		Contact person = new Contact();
		
		System.out.println("Enter contact's first name: ");
		person.setFirstName(input.next());
		
		System.out.println("Enter contact's last name: ");
		person.setLastName(input.next());
		
		System.out.println("Enter contact's address: ");
		person.setAddress(input.next());
		
		System.out.println("Enter contact's phone number (numbers only, no dashes): ");	
		person.setPhone(input.next());
		
		allContacts.add(person);
		toBeAdded.add(person);
		System.out.println("Successfully added new contact.");
	}
	
	public void update() {
		System.out.println("Enter first name of the contact you wish to update: ");
		String firstName = input.next();
		System.out.println("Enter last name of the contact you wish to update: ");
		String lastName = input.next();
		
		boolean isFound = false;
		String change = "";
		int updateOption = 0;
		
		for(Contact c : allContacts) {
			if(c.getFullName().equalsIgnoreCase(firstName + " " + lastName)) {
				isFound = true;
				System.out.println("Would you like to update 1) first name, 2) last name, 3) address, 4) phone number? ");
				try {
					updateOption = input.nextInt();
				} catch(InputMismatchException e) {
					input.nextLine();
				}
				
				if (updateOption == 1) {
					System.out.println("Enter contact's new first name: ");
					change = input.next();
					c.setFirstName(change);
				}
				else if (updateOption == 2) {
					System.out.println("Enter contact's new last name: ");
					change = input.next();
					c.setLastName(change);
				}
				else if (updateOption == 3) {
					System.out.println("Enter contact's new address: ");
					change = input.next();
					c.setAddress(change);
				}
				else if (updateOption == 4) {
					System.out.println("Enter contact's new phone number: ");
					change = input.next();
					c.setPhone(change);
				}
				else {
					System.out.println("Invalid input. Going back to the main menu.");
				}

				break;
			}
		}
		
		if(!isFound) {
			System.out.println("Sorry, the contact you wished to update does not exist.");
		}
	}
	
	public void delete() {
		System.out.println("Enter first name of the contact you wish to delete: ");
		String firstName = input.next();
		System.out.println("Enter last name of the contact you wish to delete: ");
		String lastName = input.next();
		
		boolean isFound = false;
		
		for (Contact c : allContacts) {
			if (c.getFullName().equalsIgnoreCase(firstName + " " + lastName)) {
				isFound = true;
				allContacts.remove(c);
				System.out.println("Successfully deleted contact.");
				
				if(toBeAdded.contains(c)) { // if c also exists in newContacts array list, delete
					toBeAdded.remove(c);
				}
				else {
					toBeDeleted.add(c); // only add to the TBD list if it exists in the database
				}
				
				break;
			}
		}
		
		if(!isFound) {
			System.out.println("Sorry, this contact does not exist and could not be deleted.");
		}
	}
	
	public void list() {
		if (allContacts.isEmpty()) {
			System.out.println("No contacts are in this address book!");
		}
		else {
			// sorts by last name
			Collections.sort(allContacts);
			for (Contact c : allContacts) {
				System.out.println(c);
			}	
		}
	}
	
	public void search() {		
		System.out.println("Would you like to search by 1) first name, 2) last name, or 3) phone number? ");
		boolean isFound = false;
		int menuSelection = 0;
		try {
			menuSelection = input.nextInt();
		} catch(InputMismatchException e) {
			System.out.println("Invalid input. Going back to the main menu.");
			input.nextLine();
			return;
		}

		if (menuSelection == 1) {
			System.out.println("Enter contact's first name: ");
		}
		else if (menuSelection == 2) {
			System.out.println("Enter contact's last name: ");
		}
		else if (menuSelection == 3) {
			System.out.println("Enter contact's phone number: ");
		}
		else {
			System.out.println("Invalid input. Going back to the main menu.");
			return;
		}
		
		String searchCondition = input.next();
		for(Contact c : allContacts) {
			if ((menuSelection == 1 && c.getFirstName().equalsIgnoreCase(searchCondition)) || 
					(menuSelection == 2 && c.getLastName().equalsIgnoreCase(searchCondition)) || 
					(menuSelection == 3 && c.getPhone().equalsIgnoreCase(searchCondition))) {
				isFound = true;
				System.out.println(c);
			}
		}
		
		if(!isFound) {
			System.out.println("Sorry, the contact you are searching for does not exist.");
		}
	}	
	
}
