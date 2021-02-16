# AddressBookProject

When menu options are listed, user will be prompted to select one of the options. User must enter an integer (from 1 to 6) that corresponds with the following:
  1. Add a new contact
  2. Update an existing contact
  3. Delete a contact
  4. List all contacts
  5. Search for a given contact
  6. Quit
  
If user enters 1, they will be prompted for further information on the contact's first name, last name, address, and phone number.

If user enters 2, they will be prompted for the name of the contact that they would like to update. They will then enter an integer (from 1 to 4) in order to select which specific information (1. First name, 2. Last name, 3. Address, 4. Phone number) they would like to alter. Finally, they will enter the new information that will replace the contact's old details.

If user enters 3, they will be prompted for the name of the contact that they would like to delete.

If user enters 4, they will be given a list of all contacts that have been added so far. The contacts will be listed in order by their last name, followed by their first name.

If user enters 5, they will enter an integer (from 1 to 3) in order to select what condition (1. First name, 2. Last name, 3. Phone number) they would like to search for contacts by. If a match is found, all details of the matched contact(s) will be printed.

If user enters 6, they will quit the menu.

It should be noted that phone numbers are restricted to numeric values only. Any phone number entered by the user that contains a symbol or an alphabet character will not be registered. If the user is adding a new contact with an non-numeric phone number, then the phone number will be set to "N/A" and can later be updated by selecting 2 from the main menu options.


