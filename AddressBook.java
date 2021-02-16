import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddressBook {
	private ArrayList <Contact> contactList;
	private Scanner input = new Scanner(System.in).useDelimiter("\n");
	
	public AddressBook() {
		contactList = new ArrayList <Contact>();		
	}
	
	public ArrayList<Contact> getContactList() {
		return contactList;
	}
	
	public void add() {		
		System.out.println("Enter contact's first name: ");
		String firstName = input.next();
		
		System.out.println("Enter contact's last name: ");
		String lastName = input.next();
		
		System.out.println("Enter contact's address: ");
		String address = input.next();
		
		System.out.println("Enter contact's phone number (numbers only, no dashes): ");	
		String phone = input.next();
		
		contactList.add(new Contact(firstName, lastName, address, phone));
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
		
		for(Contact c : contactList) {
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
		
		for (Contact c : contactList) {
			if (c.getFullName().equalsIgnoreCase(firstName + " " + lastName)) {
				isFound = true;
				contactList.remove(c);
				System.out.println("Successfully deleted contact.");
				break;
			}
		}
		
		if(!isFound) {
			System.out.println("Sorry, this contact does not exist and could not be deleted.");
		}
	}
	
	public void list() {
		if (contactList.isEmpty()) {
			System.out.println("No contacts are in this address book!");
		}
		else {
			// sorts by last name
			Collections.sort(contactList);
			for (Contact c : contactList) {
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
		for(Contact c : contactList) {
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
