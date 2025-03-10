package Project/* School: Southern New Hampshire University
 * Course: CS-320
 * Name: Joshua Kovacevich
 * Date: 11/28/2021
 * Description: This is the Appointment Service test class, used to test Appointment Service class with Junit5
 */ 

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppointmentServiceTest {

	//Creates a new instance of AppointmentService and a date to be used throughout tests
	AppointmentService appointmentService = new AppointmentService();
	Date today = new Date();
	
	@BeforeEach
	void setupDate() {
		//Sets date to 1 day in the future before each test
		today.setDate(today.getDate()+1);
	}
	
	
	@Test
	void testAppointmentServiceValid1() {
		//Uses default Appointment creation and ensures properly assigned
		appointmentService.addAppointment();
		Appointment testAppointment = appointmentService.appointments.elementAt(appointmentService.appointments.size() -1);
		assertEquals(testAppointment.getAppointment_Description(), "This is a default Appointment.");
		//Tests against null as the Date is difficult to catch within the millisecond
		assertTrue(testAppointment.getAppointment_Date() != null);
	}
	
	@Test
	void testAppointmentServiceValid2() {
		//Uses the ID generator with a date and description to create an Appointment then validates
		//that variables in object are correct
		appointmentService.addAppointment("Valid Test Description", today);
		Appointment testAppointment = appointmentService.appointments.elementAt(appointmentService.appointments.size() -1);
		assertEquals(testAppointment.getAppointment_Description(), "Valid Test Description");
		assertEquals(testAppointment.getAppointment_Date(), today);
	}
	
	@Test
	void testAppointmentServiceValid3() {
		//Tests to ensure manually setting an ID works when creating an Appointment
		appointmentService.addAppointment("Valid ID","Valid Test Description", today);
		Appointment testAppointment = appointmentService.appointments.elementAt(appointmentService.appointments.size() -1);
		assertEquals(testAppointment.getAppointment_ID(), "Valid ID");
		assertEquals(testAppointment.getAppointment_Description(), "Valid Test Description");
		assertEquals(testAppointment.getAppointment_Date(), today);
	}
	
	@Test
	void testAppointmentServiceDeleteValid() {
		//Creates then deletes an Appointment with AppointmentService, ensures
		//and exception is throw when attempting to delete the same ID a second time.
		appointmentService.addAppointment("Valid ID","Valid Test Description", today);
		Assertions.assertDoesNotThrow(()->{appointmentService.deleteAppointment("Valid ID");});
		Assertions.assertThrows(NoSuchElementException.class, ()->{
			appointmentService.deleteAppointment("Valid ID");
		});
	}
	
	@Test
	void testUpdateAppointmentServiceDateValid() {
		//Updates the date with valid date, updates with an invalid ID(expecting exception),
		//tests to ensure a date in the past throws an exception, finally ensures proper update
		appointmentService.addAppointment("Valid ID","Valid Test Description", today);
		Date todayLater = today;
		todayLater.setDate(today.getDate()+10);
		Assertions.assertDoesNotThrow(()->{appointmentService.updateAppointmentDate("Valid ID", todayLater);});
		Assertions.assertThrows(NoSuchElementException.class, ()->{
			todayLater.setDate(today.getDate()-12);
			appointmentService.updateAppointmentDate("Not Valid", todayLater);
		});
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			todayLater.setDate(today.getDate()-12);
			appointmentService.updateAppointmentDate("Valid ID", todayLater);
		});
		todayLater.setDate(today.getDate()+10);
		assertEquals(appointmentService.search("Valid ID").getAppointment_Date(), todayLater);
	}
	@Test
	void testUpdateAppointmentServiceDescriptionValid(){
		//Creates a valid Appointment then attempts to update with a valid description(no exception)
		//an invalid ID(exception thrown), and invalid description (exception thrown) and finally
		//validates correct update made.
		appointmentService.addAppointment("Valid ID","Valid Test Description", today);
		Assertions.assertDoesNotThrow(()->{appointmentService.updateAppointmentDescription("Valid ID", 
				"This is another valid description.");
		});
		Assertions.assertThrows(NoSuchElementException.class, ()->{appointmentService.updateAppointmentDescription("Not Real", 
				"This is another valid description.2");
		});
		Assertions.assertThrows(IllegalArgumentException.class, ()->{appointmentService.updateAppointmentDescription("Valid ID", 
				"This is an ivalid description. Given its length just to ensure no bypass");
		});
		assertEquals(appointmentService.search("Valid ID").getAppointment_Description(), "This is another valid description.");
	}
	
	@Test
	void testAddAppointmentFail() {
		//Ensures Assertion thrown when attempting to add appointment with duplicate ID
		appointmentService.addAppointment("Valid ID","Valid Test Description", today);
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			appointmentService.addAppointment("Valid ID","Valid Test Description", today);
		}, "Appointment ID already exist.");
	}
	
	@Test
	void testDeleteAppointmentFail() {
		//Ensures assertion thrown when trying to delete an Appoint that does not exist
		Assertions.assertThrows(NoSuchElementException.class, ()->{
			appointmentService.deleteAppointment("Not Valid");
		});
	}
	
	@Test
	void testUpdateDescriptionFail() {
		//Tests to ensure updates to description are still checked for validity with failing edge cases
		//Not strictly required as should always go through Appointment setDescription but nice to check
		//in off chance a direct assignment was unintentionally done
		appointmentService.addAppointment("Valid ID","Valid Test Description", today);
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			appointmentService.updateAppointmentDescription("Valid ID", "");
		});
		Assertions.assertThrows(IllegalArgumentException.class, ()->{appointmentService.updateAppointmentDescription("Valid ID", 
				" ");
		});
		Assertions.assertThrows(IllegalArgumentException.class, ()->{appointmentService.updateAppointmentDescription("Valid ID", 
				"This is an ivalid description. Given its length just to ensure no bypass");
		});
		assertEquals(appointmentService.search("Valid ID").getAppointment_Description(), "Valid Test Description");
	}
	
	@Test
	void testUpdateDateFail() {
		//Tests to ensure updates to date are still checked for validity with null
		//and in the past dates. Not strictly required but can catch unintentional direct assignment
		appointmentService.addAppointment("Valid ID","Valid Test Description", today);
		Date badDate = today;
		badDate.setDate(today.getDate() - 20);
		Assertions.assertThrows(NullPointerException.class, ()->{
			appointmentService.updateAppointmentDate("Valid ID", null);
		});
		Assertions.assertThrows(IllegalArgumentException.class, ()->{appointmentService.updateAppointmentDate("Valid ID", 
				badDate);
		});
		assertEquals(appointmentService.search("Valid ID").getAppointment_Date(), today);
	}
	
	@Test
	void testSearchUnderLoad() {
		//Tests search under load to ensure it works even with 1000 contacts
		//grabs valid id and then ensures exception not throw in valid search
		for(int i = 0; i < 1000; i++) {
			appointmentService.addAppointment();
		}
		String Appointment_ID = appointmentService.appointments.elementAt(877).getAppointment_ID();
		
		//Ensures Exception not thrown for any reason(should not be able to throw exception),
		//Finds contact (not null), deletes said task, then ensures task is no longer in list
		//Then once again ensures exception is not thrown when searching for deleted Task
		Assertions.assertDoesNotThrow(()->appointmentService.search(Appointment_ID)); 
		assertFalse(appointmentService.search(Appointment_ID) == null);
		Assertions.assertDoesNotThrow(()->appointmentService.deleteAppointment(Appointment_ID));
		assertTrue(appointmentService.search(Appointment_ID) == null);
		Assertions.assertDoesNotThrow(()->appointmentService.search(Appointment_ID)); 
	}

}
