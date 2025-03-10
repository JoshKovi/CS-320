package Project/* School: Southern New Hampshire University
 * Course: CS-320
 * Name: Joshua Kovacevich
 * Date: 11/28/2021
 * Description: This is the Appointment Test Class, used to test Appointment Objects with Junit5
 */ 

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class AppointmentTest {
	
	//This date is used for ease 
	Date testDate = new Date();
	
	@BeforeEach
	void futureDate() {
		//Adds 1 minute to testDate before each to ensure testDate is a minute in future
		testDate.setMinutes(testDate.getMinutes() + 1);
	}
	
	
	
	
	@Test
	void testAppointmentValid1() {
		//Creates an Appointment with minimum ID and Description and ensures properly assigned
		Appointment testAppointment = new Appointment("I", "D", testDate);
		assertEquals(testAppointment.getAppointment_ID(),  "I");
		assertEquals(testAppointment.getAppointment_Description(),  "D");
		assertEquals(testAppointment.getAppointment_Date(),  testDate);
	}
	
	@Test
	void testAppointmentValid2() {
		//Creates and Appointment with maximum allowable characters and ensures properly assigned
		Appointment testAppointment = new Appointment("123ABC!@#$", "Max length description with symbols and numbers1!@", testDate);
		assertEquals(testAppointment.getAppointment_ID(),  "123ABC!@#$");
		assertEquals(testAppointment.getAppointment_Description(),  "Max length description with symbols and numbers1!@");
		assertEquals(testAppointment.getAppointment_Date(),  testDate);
	}
	
	@Test
	void testSetDateFail() {
		//Ensures that a date in the past throws and Exception
		testDate = new Date();
		testDate.setMinutes(testDate.getMinutes() - 1);
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			new Appointment("ValidID", "Valid Description", testDate);
		});
		//Sets testDate to null then ensures Null Pointer Exception is thrown with appropriate message
		final Date testDate = null;
		Assertions.assertThrows(NullPointerException.class, ()->{
			new Appointment("ValidID", "Valid Description", testDate);
		}, "Date cannot be blank.");
	}
	
	@Test
	void testSetDescriptionFail() {
		//Ensures empty, blank and longer than allowed descriptions throw an Exception
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			new Appointment("ValidID", "", testDate);
		});
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			new Appointment("ValidID", " ", testDate);
		});
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			new Appointment("ValidID", "This description is 51 chars long which is illegal!", testDate);
		});
	}
	@Test
	void testSetIDFail() {
		//Ensures empty, blank and longer than allowed IDs throw an Exception
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			new Appointment("", "Valid Description", testDate);
		});
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			new Appointment(" ", "Valid Description", testDate);
		});
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			new Appointment("TooLongID12", "Valid Description", testDate);
		});
	}
}
