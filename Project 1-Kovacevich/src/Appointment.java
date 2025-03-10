package Project /* School: Southern New Hampshire University
 * Course: CS-320
 * Name: Joshua Kovacevich
 * Date: 11/28/2021
 * Description: This is the Appointment Class, used to store and validate Appointment Objects
 */  

import java.util.Date;

public class Appointment {

	//Required Object Variables
	private String Appointment_ID;
	private String Appointment_Description;
	private Date Appointment_Date;
	
	//Constants to enable easier changes to minimum and max lengths of variables
	protected static final int ID_MIN_LENGTH = 1;
	protected static final int ID_MAX_LENGTH = 10;
	protected static final int DESCRIPTION_MIN_LENGTH = 1;
	protected static final int DESCRIPTION_MAX_LENGTH = 50;
	
	//Constructors
	private Appointment() {/*Used to prevent empty instantiation of Appointment*/}
	
	protected Appointment(String Appointment_ID_L, String Appointment_Description_L, Date Appointment_Date_L) {
		setAppointment_ID(Appointment_ID_L);
		setAppointment_Description(Appointment_Description_L);
		setAppointment_Date(Appointment_Date_L);
	}
	
	//Copied from Contact, used to validate strings and ensure proper length
	private String stringValidation(String inputString_L, int minLength_L, int maxLength_L) {
		
		//If string is blank, empty, or outside proper length throws IllegalArgumentException
		//otherwise returns inputString_L
		if(inputString_L.isEmpty() || inputString_L.isBlank()) {
			throw new IllegalArgumentException("Please input required information.");
		}
		else if(inputString_L.length() < minLength_L || inputString_L.length() > maxLength_L) {
			throw new IllegalArgumentException("Your input must be between " + minLength_L + 
					" characters and " + maxLength_L + ".");
		}
		else {
			return inputString_L;
		}
	}
	
	//Setters
	protected void setAppointment_ID(String Appointment_ID_L) {
		//Uses string validation to ensure ID meets requirements
		this.Appointment_ID = stringValidation(Appointment_ID_L, ID_MIN_LENGTH, ID_MAX_LENGTH);
	}
	
	protected void setAppointment_Description(String Appointment_Description_L) {
		//Uses string validation to ensure Description meets requirements
		this.Appointment_Description = stringValidation(Appointment_Description_L, DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH);
	}
	
	protected void setAppointment_Date(Date Appointment_Date_L) {
		//If the appointment is null throws NullPointerException, if date is 
		//before current date, throws IllegalArgumentException. Else, assigns date.
		if(Appointment_Date_L == null) {
			throw new NullPointerException("Date cannot be blank.");
		}
		else if(Appointment_Date_L.before(new Date())) {
			throw new IllegalArgumentException("Date must be in the future.");
		}
		else {
			this.Appointment_Date = Appointment_Date_L;			
		}
	}
	
	
	//Default Getters for variables
	protected String getAppointment_ID() {
		return Appointment_ID;
	}

	protected String getAppointment_Description() {
		return Appointment_Description;
	}

	protected Date getAppointment_Date() {
		return Appointment_Date;
	}

}
