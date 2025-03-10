package Project/* School: Southern New Hampshire University
 * Course: CS-320
 * Name: Joshua Kovacevich
 * Date: 11/21/2021
 * Description: This is the Task Test Class, used to test Task Objects with Junit5
 */ 

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
 
class TaskTest {

	@Test
	void testTaskValid1() {
		//This tests the lower edge case of 1 character ID, Name, and Description
		//Then ensures those values are assigned to the task that is created
		Task testTask = new Task("T", "N", "D");
		assertTrue(testTask.getTaskID().equals("T"));
		assertTrue(testTask.getTaskName().equals("N"));
		assertTrue(testTask.getTaskDescription().equals("D"));
	}
	@Test
	void testTaskValid2() {
		//This tests the maximum character edge case of ID, Name, and Description, including special characters and ints
		//Then uses assertTrue to ensure the value was assigned to each variable
		Task testTask = new Task("TAsk1234@!", "Task N@me Is 20 Char", "Description that is exactly 50 characters long!@#$");
		assertTrue(testTask.getTaskID().equals("TAsk1234@!"));
		assertTrue(testTask.getTaskName().equals("Task N@me Is 20 Char"));
		assertTrue(testTask.getTaskDescription().equals("Description that is exactly 50 characters long!@#$"));
	}
	@Test
	void testTaskInvalidID() {
		//This is used to ensure invalid IDs throw and exception, uses empty string, blank string and
		//A 11 character string to test edge cases
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			 new Task("", "Task Name", "Task Description");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			 new Task("     ", "Task Name", "Task Description");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			 new Task("0123456789!", "Task Name", "Task Description");
		});
		
	}
	@Test
	void testTaskInvalidName() {
		//This tests the edge cases of name assignment by creating a empty, blank and 21 character
		//Task name, should throw an exception 
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			 new Task("TaskName1", "", "Task Description");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			 new Task("TaskName1", "     ", "Task Description");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			 new Task("TaskName2", "Task Name Exceeds len", "Task Description");
		});
		
	}
	@Test
	void testTaskInvalidDescription() {
		//Tests the edge cases of description with empty, blank and 51 character description
		//Throws an exception for each
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			 new Task("TaskName1", "Task Name", "");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			 new Task("TaskName2", "Task Name", "      ");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			new Task("TaskName2", "Task Name", "Task Description exceeds the 50 Character limit + 1");
		});
		
	}
	
}
