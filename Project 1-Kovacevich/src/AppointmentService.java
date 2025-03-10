package Project/* School: Southern New Hampshire University
 * Course: CS-320
 * Name: Joshua Kovacevich
 * Date: 11/28/2021
* Description: This is the Appointment Service class, this class builds a list (in memory) of Appoints
 * and can add, delete, or modify the Appointments in that list
 */  

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Vector;

public class AppointmentService {
	
	//This vector is used to hold appointments
	Vector<Appointment> appointments = new Vector<Appointment>();
	
	public void addAppointment() {
		//Creating a new date then adding 1 day makes creation of default Appointments
		//slightly easier and in this case is desirable as dependent on timing a regular
		//new Date() would likely be registered as in the past.
		Date tomorrow = new Date();
		tomorrow.setDate(tomorrow.getDate()+1);
		appointments.addElement(new Appointment(IDGenerator(), "This is a default Appointment.", tomorrow));
	}
	
	public void addAppointment(String Appointment_Description_L, Date Appointment_Date_L) {
		//This assigns a date and description while generating an ID as needed
		appointments.addElement(new Appointment(IDGenerator(), Appointment_Description_L, Appointment_Date_L));
	}
	public void addAppointment(String Appointment_ID_L, String Appointment_Description_L, Date Appointment_Date_L) {
		//Checks the ID against all other IDs then if ID not found creates the Appointment, else
		//throws an IllegalArgumentException
		Appointment newAppointment = search(Appointment_ID_L);
		if(newAppointment == null) {
			appointments.addElement(new Appointment(Appointment_ID_L, Appointment_Description_L, Appointment_Date_L));
		}
		else {
			throw new IllegalArgumentException("Appointment ID already exist.");
		}
		
	}

	//Used to delete Tasks from tasks vector and effectively delete all reference to said task
	public void deleteAppointment(String Appointment_ID_L) {
		
		//Searches for task in tasks vector 
		Appointment tempAppointment = search(Appointment_ID_L);
		
		//If the tempTask Task is null throw exception, else remove task from vector
		if(tempAppointment == null) {
			throw new NoSuchElementException("Appointment does not exist.");
		}
		else {
			appointments.remove(appointments.indexOf(tempAppointment));
		}
	}
	
	//While strictly not required, it seems useful to enable the modification of a description
	//if an error was made or new information about the appointment is learned
	public void updateAppointmentDescription(String Appointment_ID_L, String New_Appointment_Description_L) {
		//Ensures ID exists then updates description, else throws NoSuchElementException
		Appointment appointmentToModify = search(Appointment_ID_L);
		if(appointmentToModify != null) {
			appointmentToModify.setAppointment_Description(New_Appointment_Description_L);
		}
		else {
			throw new NoSuchElementException("Appointment does not exist.");
		}
	}
	
	//While strictly not required, it seems useful to enable the modification of a date
	//if an error was made or an appointment date needs to be changed 
	public void updateAppointmentDate(String Appointment_ID_L, Date New_Appointment_Date_L) {
		//Ensures ID exists then updates Date, else throws NoSuchElementException
		Appointment appointmentToModify = search(Appointment_ID_L);
		if(appointmentToModify != null) {
			appointmentToModify.setAppointment_Date(New_Appointment_Date_L);
		}
		else {
			throw new NoSuchElementException("Appointment does not exist.");
		}	
	}
	
	//Method for searching by AppointmentID (Reused from ContactService.java)
	public Appointment search(String Appointment_ID_L) {
		//Returns null Appointment if Appointment is not in appointments vector
		Appointment returnAppointment = null;
		
		//Ensures appointments vector not empty, then uses manual checks of AppointmentIDs
		//to find AppointmentID, if found returns that AppointmentID
		if(!appointments.isEmpty()) {
			for(int i = 0; i < appointments.size(); i++) {
				if(appointments.elementAt(i).getAppointment_ID().equals(Appointment_ID_L)) {
					return appointments.elementAt(i);
				}
			}
		}
		//Only used with a null Appointment (Appointment not found)
		return returnAppointment;
	}

	//Used to generate AppointmentIDs pseudo-randomly, very rudimentary (Reused from ContactService.java)
		private String IDGenerator() {
			//selectorString is used as an alphabet for characters, generatorString
			//is used to build string and return.
			String generatorString = "";
			String selectorString = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			
			//chooses TASK_ID_LENGTH to "randomly" select characters for ID
			for(int i = 0; i < Appointment.ID_MAX_LENGTH; i++) {
				generatorString += selectorString.charAt((int)(Math.random()*61));
			}
			
			//In off chance AppointmentID already exists, recursively calls self to generate new ID
			//have not triggered in testing yet with 10 characters. As there are roughly 4/5s of a
			//Quintillion possible combinations, Unlikely to trigger in normal circumstances.
			if(search(generatorString) != null) {
				generatorString = IDGenerator();
			}
			return generatorString;
		}
}
