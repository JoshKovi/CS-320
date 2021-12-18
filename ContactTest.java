/* School: Southern New Hampshire University 
 * Course: CS-320
 * Name: Joshua Kovacevich
 * Date: 11/14/2021
 * Description: This is the Contact Test Class, used to test Contact Objects with Junit5
 */ 

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ContactTest {
	//Class contains all tests that directly test Contact Class

	@Test
	void testContactValid1() {
		//Test valid case of a typical contact by creating new contact with valid parameters
		//Then using assertTrue method to ensure values are appropriately assigned.
		//This tests the lower edge
		Contact testContact = new Contact("1", "T", "B", "0000000000", "1");
		assertTrue(testContact.getContactID() == "1");
		assertTrue(testContact.getFirstName() == "T");
		assertTrue(testContact.getLastName() == "B");
		assertTrue(testContact.getPhone() == "0000000000");
		assertTrue(testContact.getAddress() == "1");
		
	}
	
	@Test
	void testContactValid2() {
		//Test valid case similar to above test though at the upper edge with characters that may otherwise cause issues
		Contact testContact = new Contact("9999AB!ty7", "Thomasthan", "Braddyison", "9999999999", "123 Apple Ave, Nowhere, NE !@#");
		assertTrue(testContact.getContactID() == "9999AB!ty7");
		assertTrue(testContact.getFirstName() == "Thomasthan");
		assertTrue(testContact.getLastName() == "Braddyison");
		assertTrue(testContact.getPhone() == "9999999999");
		assertTrue(testContact.getAddress() == "123 Apple Ave, Nowhere, NE !@#");
	}
	
	@Test
	void testContactInvalidID() {
		//This tests the two major failures of contact ID, blank ID or 11 character ID
		//test fails if exception is not thrown
		Assertions.assertThrows(IllegalArgumentException.class, () -> { 
			new Contact("", "Thomasthan", "Braddyison", "0000000000", "123 Apple Ave, Nowhere, NE !@#");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> { 
			new Contact("12345678901", "Thomasthan", "Braddyison", "0000000000", "123 Apple Ave, Nowhere, NE !@#");
		});
	}
	
	@Test
	void testContactInvalidFirstName() {
		//This tests the out of bounds areas for first name, if the string is empty, 11 characters, contains 
		//a letter or contains a number, test fails if exception not thrown
		Assertions.assertThrows(IllegalArgumentException.class, () -> { 
			new Contact("1234567890", "", "Braddyison", "0000000000", "123 Apple Ave, Nowhere, NE !@#");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> { 
			new Contact("1234567890", "Thomasthans", "Braddyison", "0000000000", "123 Apple Ave, Nowhere, NE !@#");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> { 
			new Contact("1234567890", "Thomas1", "Braddyison", "0000000000", "123 Apple Ave, Nowhere, NE !@#");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> { 
			new Contact("1234567890", "Thomas!", "Braddyison", "0000000000", "123 Apple Ave, Nowhere, NE !@#");
		});
	}
	
	@Test
	void testContactInvalidLastName() {
		//Similar to first name invalid test. Tests out of bounds (length at both ends, number and special char)
		//to ensure each throws a proper exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> { 
			new Contact("1234567890", "Thomas", "", "0000000000", "123 Apple Ave, Nowhere, NE !@#");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> { 
			new Contact("1234567890", "Thomas", "Braddyisons", "0000000000", "123 Apple Ave, Nowhere, NE !@#");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> { 
			new Contact("1234567890", "Thomas", "Braddy1", "0000000000", "123 Apple Ave, Nowhere, NE !@#");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> { 
			new Contact("1234567890", "Thomas", "Braddy!", "0000000000", "123 Apple Ave, Nowhere, NE !@#");
		});
	}
	
	@Test
	void testContactInvalidPhone() {
		//This tests invalid cases for the phone number ensuring each invalid case(Blank, too short, too long, letters,
		// or special characters) throws an exception.
		Assertions.assertThrows(IllegalArgumentException.class, () -> { 
			new Contact("1234567890", "Thomas", "Brady", "", "123 Apple Ave, Nowhere, NE !@#");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> { 
			new Contact("1234567890", "Thomas", "Brady", "0", "123 Apple Ave, Nowhere, NE !@#");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> { 
			new Contact("1234567890", "Thomas", "Brady", "00000000000", "123 Apple Ave, Nowhere, NE !@#");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> { 
			new Contact("1234567890", "Thomas", "Brady", "Abcdefghij", "123 Apple Ave, Nowhere, NE !@#");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> { 
			new Contact("1234567890", "Thomas", "Brady", "!@#$%^&*()", "123 Apple Ave, Nowhere, NE !@#");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> { 
			new Contact("1234567890", "Thomas", "Brady", "123456789A", "123 Apple Ave, Nowhere, NE !@#");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> { 
			new Contact("1234567890", "Thomas", "Brady", "123456789!", "123 Apple Ave, Nowhere, NE !@#");
		});
	}
	
	@Test
	void testContactInvalidAddress() {
		//Tests the two invalid cases (blank or 31+ characters) and ensures exceptions are thrown.
		Assertions.assertThrows(IllegalArgumentException.class, () -> { 
			new Contact("1234567890", "Thomas", "Brady", "1234567890", "");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> { 
			new Contact("1234567890", "Thomas", "Brady", "1234567890", "123 Apple Ave, Nowhere, NE !@#1");
		});

	}

}
