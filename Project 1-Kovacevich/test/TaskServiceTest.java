package Project/* School: Southern New Hampshire University
 * Course: CS-320
 * Name: Joshua Kovacevich
 * Date: 11/21/2021
 * Description: This is the Task Service test class, used to test Task Service class with Junit5
 */ 

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
 
class TaskServiceTest {
	
	//This is created to allow each test to use this TaskService instance
	//rather than create a new taskService each time
	TaskService testService = new TaskService();

	@Test
	void testTaskServiceValid1() {
		//Tests to ensure that default addTask method creates a task and
		//then test to ensure the expected variables are correct with assertTrue
		testService.addTask();
		Task testTask = testService.tasks.elementAt(testService.tasks.size() - 1);
		assertTrue(testTask.getTaskName().equals("Task Name"));
		assertTrue(testTask.getTaskDescription().equals("A placeholder task description."));
	}
	@Test
	void testTaskServiceValid2() {
		//Uses the addTask method with Name and Description input to ensure valid 
		//Input can be used to create a task, tests using assertTrue to ensure values
		//are assigned
		testService.addTask("Hello Task", "Task Description");
		Task testTask = testService.tasks.elementAt(testService.tasks.size() - 1);
		assertTrue(testTask.getTaskName().equals("Hello Task"));
		assertTrue(testTask.getTaskDescription().equals("Task Description"));
	}
	
	@Test
	void testTaskServiceValid3() {
		//Tests to ensure a Valid, Unique Task ID can be used to create a new Task
		//with the addTask method that takes a Task ID, tests with assert true
		testService.addTask("ValidID", "Hello Task", "Task Description");
		Task testTask = testService.search("ValidID");
		assertTrue(testTask.getTaskName().equals("Hello Task"));
		assertTrue(testTask.getTaskDescription().equals("Task Description"));
	}
	@Test
	void updateTaskValid() {
		//Adds a default task then updates both name and Description with valid input
		//To ensure assertions are not thrown and proper variable is updated
		testService.addTask();
		//Grabs generatedID for updating Task
		String generatedID = testService.tasks.elementAt(testService.tasks.size() - 1).getTaskID();
		//Checks for thrown Assertion and validates field was updated
		Assertions.assertDoesNotThrow(()->testService.updateTaskName(generatedID, "New Task Name"));
		assertTrue(testService.search(generatedID).getTaskName().equals("New Task Name"));
		Assertions.assertDoesNotThrow(()->testService.updateTaskDescription(generatedID, "New Task Description"));
		assertTrue(testService.search(generatedID).getTaskDescription().equals("New Task Description"));	
	}
	@Test
	void deleteTaskValid() {
		//Uses valid ID to delete default task, then checks to ensure Exception is thrown 
		//on second attempt to delete(already deleted) element
		testService.addTask();
		String generatedID = testService.tasks.elementAt(testService.tasks.size() - 1).getTaskID();
		Assertions.assertDoesNotThrow(() -> testService.deleteTask(generatedID));
		Assertions.assertThrows(NoSuchElementException.class, () -> testService.deleteTask(generatedID));
	}
	@Test
	void addTaskFail() {
		//Uses same Task ID to add second Task and ensures that Exception is thrown 
		testService.addTask("FirstID", "Passing Task Add", "Passing Task Add Description");
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			testService.addTask("FirstID", "Failing Task Add", "Failing Task Add Description");
		});
	}
	@Test
	void updateTaskFail1() {
		//Updates task Name and Description with empty input to ensure Exception is thrown 
		testService.addTask("NewTaskID", "Passing Task Add", "Passing Task Add Description");
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			testService.updateTaskName("NewTaskID", "");
		});
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			testService.updateTaskDescription("NewTaskID", "");
		});
	}
	@Test
	void updateTaskFail2() {
		//Updates task Name and Description with Blank input to ensure Exception is thrown
		testService.addTask("NewTaskID", "Passing Task Add", "Passing Task Add Description");
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			testService.updateTaskName("NewTaskID", "         ");
		});
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			testService.updateTaskDescription("NewTaskID", "        ");
		});
	}
	@Test
	void updateTaskFail3() {
		//Updates task Name and Description with Longer than allowed input to ensure Exception is thrown
		testService.addTask("NewTaskID", "Passing Task Add", "Passing Task Add Description");
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			testService.updateTaskName("NewTaskID", "Task Name is 21 Chars");
		});
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			testService.updateTaskDescription("NewTaskID", "Task Description is 51 characters in length 1 above");
		});
	}
	@Test
	void updateTaskFail4() {
		//Attempts to update task that does not exist in tasks to ensure Exception is thrown
		testService.addTask("NewTaskID", "Passing Task Add", "Passing Task Add Description");
		Assertions.assertThrows(NoSuchElementException.class,()->{
			testService.updateTaskName("OldTaskID", "Valid Task Name");
		});
		Assertions.assertThrows(NoSuchElementException.class,()->{
			testService.updateTaskDescription("OldTaskID", "Valid Task Description");
		});
	}
	@Test
	void deleteTaskFail() {
		//Used to ensure Exception is thrown when attempting to delete a Task
		//that does not exist (redundant but tests empty list)
		Assertions.assertThrows(NoSuchElementException.class, ()->
			testService.deleteTask("InvalidID"));
	}
	@Test
	void searchUnderLoad() {//Taken from Contact Service Test
		//Tests search under load to ensure it works even with 1000 tasks
		//grabs valid id and then ensures exception not throw in valid search
		for(int i = 0; i < 1000; i++) {
			testService.addTask();
		}
		String contactID = testService.tasks.elementAt(877).getTaskID();
		
		//Ensures Exception not thrown for any reason(should not be able to throw exception),
		//Finds contact (not null), deletes said task, then ensures task is no longer in list
		//Then once again ensures exception is not thrown when searching for deleted Task
		Assertions.assertDoesNotThrow(()->testService.search(contactID)); 
		assertFalse(testService.search(contactID) == null);
		Assertions.assertDoesNotThrow(()->testService.deleteTask(contactID));
		assertTrue(testService.search(contactID) == null);
		Assertions.assertDoesNotThrow(()->testService.search(contactID)); 
	}
}
