package Project/* School: Southern New Hampshire University
 * Course: CS-320
 * Name: Joshua Kovacevich
 * Date: 11/14/2021
 * Description: This is the Contact Service test class, used to test Contact Service class with Junit5
 */ 


import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ContactServiceTest {
	
	//This is built outside of tests as it is used repeatedly and creation and deletion for each test
	//is slow and inefficient.
	ContactService testService = new ContactService();

	

	@Test
	void testContactServiceValid1() {
		//Tests a valid blank addContact call, if this fails it could cause errors in later
		//testing so it is useful to ensure it works first. Uses assertTrue to validate
		//contact is properly assigned
		testService.addContact();
		Contact testContact = testService.contacts.elementAt(testService.contacts.size() - 1);
		assertTrue(testContact.getFirstName().equals("first"));
		assertTrue(testContact.getLastName().equals("last"));
		assertTrue(testContact.getPhone().equals("1234567890"));
		assertTrue(testContact.getAddress().equals("New Address"));
	}
	
	@Test
	void testContactServiceValid2() {
		//Similar to above, creates a valid contact without a contact ID to ensure method
		//works prior to more extensive testing through use of assertTrue statements.
		testService.addContact("Tam", "Brody", "0123456789", "1 Street, Town, State");
		Contact testContact = testService.contacts.elementAt(testService.contacts.size() - 1);
		assertTrue(testContact.getFirstName().equals("Tam"));
		assertTrue(testContact.getLastName().equals("Brody"));
		assertTrue(testContact.getPhone().equals("0123456789"));
		assertTrue(testContact.getAddress().equals("1 Street, Town, State"));
		
	}
	@Test
	void testContactServiceValid3() {
		//Ensures final addContact method works with assertTrue
		testService.addContact("edmda123","Mat", "Ydorb", "9876543210", "2 Street, Town, State");
		Contact testContact = testService.contacts.elementAt(testService.contacts.size() - 1);
		assertTrue(testContact.getFirstName().equals("Mat"));
		assertTrue(testContact.getLastName().equals("Ydorb"));
		assertTrue(testContact.getPhone().equals("9876543210"));
		assertTrue(testContact.getAddress().equals("2 Street, Town, State"));
		
	}
	
	@Test
	void updateContactValid() {
		
		//Ensures that updating a contact(with valid input) works for each required field with assert true
		testService.addContact("update123","Mat", "Ydorb", "9876543210", "2 Street, Town, State");
		testService.updateFirstName("update123", "John");
		assertTrue(testService.search("update123").getFirstName().equals("John"));
		testService.updateLastName("update123", "McCain");
		assertTrue(testService.search("update123").getLastName().equals("McCain"));
		testService.updateNumber("update123", "1112223333");
		assertTrue(testService.search("update123").getPhone().equals("1112223333"));
		testService.updateAddress("update123", "New Address Changed");
		assertTrue(testService.search("update123").getAddress().equals("New Address Changed"));
	}
	
	@Test
	void deleteContactValid() {
		//Ensures that delete contact(with valid input) works as expected
		//Creates (valid) contact, deletes contact and ensures nothing is thrown
		//Finally, does invalid delete and ensures an exception is thrown.
		testService.addContact("DeleteIt","Mat", "NewLastN", "9876543210", "2 Street, Town, State");
		Assertions.assertDoesNotThrow( ()-> testService.deleteContact("DeleteIt"));
		Assertions.assertThrows(NoSuchElementException.class, ()-> testService.deleteContact("DeleteIt"));
	}
	
	@Test
	void addContactFail() {
		//Ensures that adding invalid contact(same contactID) throws an exception
		testService.addContact("update123","Mat", "Ydorb", "9876543210", "2 Street, Town, State");
		Assertions.assertThrows(IllegalArgumentException.class, ()-> testService.addContact("update123",
				"Mat", "Ydorb", "9876543210", "2 Street, Town, State"));
		
	}
	
	@Test
	void updateContactFail1() {
		//Ensures that invalid updates to contacts throw exceptions, if exception is thrown the 
		//field has not been updated
		testService.addContact("update123","Mat", "Ydorb", "9876543210", "2 Street, Town, State");
		Assertions.assertThrows(IllegalArgumentException.class, ()->  
			testService.updateFirstName("update123", "John1"));
		Assertions.assertThrows(IllegalArgumentException.class, ()->  
			testService.updateLastName("update123", "Ydorb2"));
		Assertions.assertThrows(IllegalArgumentException.class, ()->  
			testService.updateNumber("update123", "fjqeqcvswp"));
		Assertions.assertThrows(IllegalArgumentException.class, ()->  
			testService.updateAddress("update123", ""));
		
	}
	
	@Test
	void updateContactFail2() {
		//Tests update contact methods with empty inputs ensuring exceptions are thrown, if exception
		//is thrown the field has not been updated.
		testService.addContact("update123","Mat", "Ydorb", "9876543210", "2 Street, Town, State");
		Assertions.assertThrows(IllegalArgumentException.class, ()->  
			testService.updateFirstName("update123", ""));
		Assertions.assertThrows(IllegalArgumentException.class, ()->  
			testService.updateLastName("update123", ""));
		Assertions.assertThrows(IllegalArgumentException.class, ()->  
			testService.updateNumber("update123", ""));
		Assertions.assertThrows(IllegalArgumentException.class, ()->  
			testService.updateAddress("update123", ""));

	}
	
	@Test
	void updateContactFail3() {
		//Attempts to update a contact that does not exist, ensures exception is thrown.
		Assertions.assertThrows(NoSuchElementException.class, ()->  
			testService.updateFirstName("update123", ""));
		Assertions.assertThrows(NoSuchElementException.class, ()->  
			testService.updateLastName("update123", ""));
		Assertions.assertThrows(NoSuchElementException.class, ()->  
			testService.updateNumber("update123", ""));
		Assertions.assertThrows(NoSuchElementException.class, ()->  
			testService.updateAddress("update123", ""));

	}
	
	@Test
	void deleteContactFail() {
		//Tests delete on invalid element to ensure exception is thrown
		Assertions.assertThrows(NoSuchElementException.class, ()->
				testService.deleteContact("12da"));
	}
	
	@Test
	void searchUnderLoad() {
		//Tests search under load to ensure it works even with 1000 contacts
		//grabs valid id and then ensures exception not throw in valid search
		for(int i = 0; i < 1000; i++) {
			testService.addContact();
		}
		String contactID = testService.contacts.elementAt(877).getContactID();
		
		Assertions.assertDoesNotThrow(()->testService.search(contactID)); 
	}
	
	
	
	
	
	
	
	
	

}
