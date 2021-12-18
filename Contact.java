/* School: Southern New Hampshire University 
 * Course: CS-320
 * Name: Joshua Kovacevich
 * Date: 11/14/2021
 * Description: This is the Contact Class, used to store and validate Contact Objects
 */ 


public class Contact {
	//These are the 5 fields required of each Contact Object
	private String contactID;
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	
	//This allows changing of min and max lengths of strings
    protected static final int CONTACT_ID_MAX_LENGTH = 10;
    protected static final int MIN_LENGTH = 1;
    protected static final int NAME_MAX_LENGTH = 10;
    protected static final int PHONE_NUMBER_LENGTH = 10;
    protected static final int ADDRESS_MAX_LENGTH = 30;
    
	
	
	//This is a private constructor to prevent accidental instantiation of a null contact with a 
	//default constructor
	private Contact() {}
	
	//This is the overloaded Constructor, it uses each fields setter to assign values to variables.
	public Contact(String contactID_L, String firstName_L, 
			String lastName_L, String phone_L, String address_L) {
		setContactID(contactID_L);
		setFirstName(firstName_L);
		setLastName(lastName_L);
		setPhone(phone_L);
		setAddress(address_L);
		
	}
	
	//This validateString method is used due to the similarity of the code used to check each 
	//fields requirements. It accepts the string for testing, the intended max length and 
	//the intended minimum length.
	private String validateString(String testString_L, int intendedMax, int intendedMin) {
		
		if(testString_L.isBlank()) {
			//If the string is only whitespace or empty throw Illegal argument exception
			throw new IllegalArgumentException("Please add valid entry.");
		}
		else if(testString_L.length() > intendedMax || testString_L.length() < intendedMin) {
			//If the string is below the minimum required or maximum allowed throw illegal
			//argument exception
			throw new IllegalArgumentException("Entry must be between "
					+ intendedMax + " and " + intendedMin + " Characters in length");
		}
		else {
			//If string meets length requirements return string
			return testString_L;
		}
	}
	
	//Method that verifies a string is only letters
	private String onlyLetters(String testString_L) {
		
		//Uses a RegEx to verify string is only letters, else throws an IllegalArguementException
		if(testString_L.matches("^[a-zA-Z]*$")) {
			return testString_L;
		}
		else {
			throw new IllegalArgumentException("Entry must only contain letters.");
		}
	}
	
	//Method that verifies string only contains numbers
	private String onlyNumbers(String testString_L) {
		
		//Uses a RegEx to ensure string has only numbers, otherwise throws Illegal Argument Exception
		if(testString_L.matches("^[0-9]*$")) {
			return testString_L;
		}
		else {
			throw new IllegalArgumentException("Entry must only contain numbers.");
		}	
	}
	
	//Setter for ContactID
	private void setContactID(String contactID_L) {
		//Assigns the return value of validateString, there is no additional
		//checks as a contactID may be letters or numbers.
		this.contactID = validateString(contactID_L, CONTACT_ID_MAX_LENGTH, MIN_LENGTH);
	}
	
	//Setter for firstName
	protected void setFirstName(String firstName_L) {
		//Validates string length first, then verifies name is only letters(not specified but implied
		//as a name only consists of letters)
		firstName_L = validateString(firstName_L, NAME_MAX_LENGTH, MIN_LENGTH);
		this.firstName = onlyLetters(firstName_L);
	}
	
	//Setter for lastName
	protected void setLastName(String lastName_L) {
		//Validates string length, then verifies name is only letters before assignment.
		lastName_L = validateString(lastName_L, NAME_MAX_LENGTH, MIN_LENGTH);
		this.lastName = onlyLetters(lastName_L);
	}
	
	//Setter for phone number
	protected void setPhone(String phone_L) {
		//Validates string length, then validates phone number is only numbers before assignment
		phone_L = validateString(phone_L, PHONE_NUMBER_LENGTH, PHONE_NUMBER_LENGTH);
		this.phone = onlyNumbers(phone_L);
	}
	
	//Setter for Address
	protected void setAddress(String address_L) {
		//Validates string is proper length prior to assignment
		this.address = validateString(address_L, ADDRESS_MAX_LENGTH, MIN_LENGTH);
	}
	
	//Default getters for all fields
	public String getContactID() {
		return this.contactID;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public String getPhone() {
		return this.phone;
	}
	public String getAddress() {
		return this.address;
	}
	
	
}





