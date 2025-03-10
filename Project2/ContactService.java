package Project2;/* School: Southern New Hampshire University
 * Course: CS-320
 * Name: Joshua Kovacevich
 * Date: 11/14/2021
 * Description: This is the Project2.Contact Service class, this class builds a list (in memory) of contacts
 * and can add, delete, or modify the contacts in that list
 */ 

import java.util.NoSuchElementException;
import java.util.Vector;

public class ContactService {
	//Project2.Contact service has one field that is a vector of contacts that is initiated
	//Empty and then modified as needed.
	Vector<Contact> contacts = new Vector<Contact>();
	
	
	//This method adds a default (valid) contact (for testing use mainly)
	public void addContact() {
		
		//Creates a default contact and adds to contacts vector
		Contact tempContact = new Contact(IDGenerator(), "first", "last",
				"1234567890", "New Address");
		contacts.addElement(tempContact);
	}
	
	//This method creates a contact and auto generates an ID, useful for requirements
	//testing and also enables a realistic way for contact adding, likely the method
	//that would be used in a real world application.
	public void addContact(String firstName, String lastName, 
			String number, String address) {
		
		//Generates a contactID and assigns all other values before adding to contacts vector
		Contact tempContact = new Contact(IDGenerator(), firstName, lastName,
				number, address);
		contacts.addElement(tempContact);
	}
	
	//This method adds a contact with all fields being passed to addContact
	public void addContact(String contactID, String firstName, 
			String lastName, String number, String address) {
		
		//Ensures contactID is not in use, if ID in use throws IllegalArguementException
		//otherwise adds contact to contacts vector
		if(search(contactID) == null) {
			Contact tempContact = new Contact(contactID, firstName, lastName,
					number, address);
			contacts.addElement(tempContact);			
		}
		else {
			throw new IllegalArgumentException("Project2.Contact ID already in use.");
		}
	}
	
	//Method for deleting contact with matching contactID
	public void deleteContact(String contactID) {
		
		//Calls search function for contact ID, throws exception if not found
		//otherwise removes the contact from the list, effectively deleting 
		//contact with java garbage collection
		Contact tempContact = search(contactID);
		if(tempContact == null) {
			throw new NoSuchElementException("Project2.Contact does not exist.");
		}
		else {
			contacts.remove(contacts.indexOf(tempContact));
		}
	}
	
	//Method for searching by contactID
	public Contact search(String contactID) {
		//Returns null contact if contact is not in contacts vector
		Contact returnContact = null;
		
		//Ensures contact vector not empty, then uses manual checks of contactID
		//to find contactID, if found returns that contact
		if(!contacts.isEmpty()) {
			for(int i = 0; i < contacts.size(); i++) {
				if(contacts.elementAt(i).getContactID().equals(contactID)) {
					return contacts.elementAt(i);
				}
			}
		}
		//Only used with a null contact (contact not found)
		return returnContact;
	}
	
	//Method for updating contact first name (by contactID) 
	public void updateFirstName(String contactID, String firstName) {
		//Finds contact in list, if contactID exists updates the first name
		//else throws an exception. Relies on Project2.Contact Class for validation.
		Contact updateContact = search(contactID);
		if(updateContact != null) {
			updateContact.setFirstName(firstName);
		}
		else {
			throw new NoSuchElementException("Project2.Contact does not exist.");
		}
	}
	
	//Method for updating last name
	public void updateLastName(String contactID, String lastName) {
		//Similar to updateFirstName(), finds contact and updates last name
		//otherwise throws exceptions. Dependent on Project2.Contact validation.
		Contact updateContact = search(contactID);
		if(updateContact != null) {
			updateContact.setLastName(lastName);
		}
		else {
			throw new NoSuchElementException("Project2.Contact does not exist.");
		}
	}
	
	//Method for updating phone number
	public void updateNumber(String contactID, String Number) {
		//Similar to other updates, finds contact with search and updates
		//otherwise throws an exception. Dependent on Project2.Contact validation
		Contact updateContact = search(contactID);
		if(updateContact != null) {
			updateContact.setPhone(Number);
		}
		else {
			throw new NoSuchElementException("Project2.Contact does not exist.");
		}
	}
	
	//Method for updating Address
	public void updateAddress(String contactID, String Address) {
		//Similar to other updates, finds contact with search and updates
		//otherwise throws an exception. Dependent on Project2.Contact validation
		Contact updateContact = search(contactID);
		if(updateContact != null) {
			updateContact.setAddress(Address);
		}
		else {
			throw new NoSuchElementException("Project2.Contact does not exist.");
		}
	}
	
	//Used to generate contactIDs pseudo-randomly, very rudimentary
	private String IDGenerator() {
		//selectorString is used as an alphabet for characters, generatorString
		//is used to build string and return.
		String generatorString = "";
		String selectorString = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		//Chooses 10 "randomly" selected characters for ID
		for(int i = 0; i < Contact.CONTACT_ID_MAX_LENGTH; i++) {
			generatorString += selectorString.charAt((int)(Math.random()*61));
		}
		
		//In off chance contactID already exists, recursively calls self to generate new ID
		//have not triggered in testing yet with 10 characters. As there are roughly 4/5s of a
		//Quintillion possible combinations, Unlikely to trigger in normal circumstances.
		if(search(generatorString) != null) {
			generatorString = IDGenerator();
		}
		return generatorString;
	}
}
