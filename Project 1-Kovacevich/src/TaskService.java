package Project/* School: Southern New Hampshire University
 * Course: CS-320
 * Name: Joshua Kovacevich
 * Date: 11/21/2021
* Description: This is the Task Service class, this class builds a list (in memory) of Tasks
 * and can add, delete, or modify the Tasks in that list
 */ 

import java.util.NoSuchElementException;
import java.util.Vector; 

public class TaskService {
	//This vector is used to hold created Tasks
	Vector<Task> tasks = new Vector<Task>();
	
	
	
	//These are the addTask methods, depending upon final implementation the first would likely 
	//be unnecessary in final design but makes testing easier.
	
	//This creates a default task with a randomly generated IDthen adds that Task to the tasks vector
	public void addTask(){
		Task tempTask = new Task(IDGenerator(), "Task Name", "A placeholder task description.");
		tasks.addElement(tempTask);
	}
	
	//This is the most likely to be used in real implementation as it generates a taskID
	//Then takes input Name and description to create task
	public void addTask(String taskName_L, String taskDescription_L) {
		Task tempTask = new Task(IDGenerator(), taskName_L, taskDescription_L);
		tasks.addElement(tempTask);
	}
	
	//This is uses to input a task ID, mostly used to validate only unique IDs are created, otherwise
	//functions as the above add task, throws exception if ID is already in use
	public void addTask(String taskID_L, String taskName_L, String taskDescription_L) {
		if(search(taskID_L) == null) {
			Task tempTask = new Task(taskID_L, taskName_L, taskDescription_L);
			tasks.addElement(tempTask);
		}
		else {
			throw new IllegalArgumentException("Task ID already in use.");
		}
	} 
	
	//Used to delete Tasks from tasks vector and effectively delete all reference to said task
	public void deleteTask(String taskID_L) {
		
		//Searches for task in tasks vector 
		Task tempTask = search(taskID_L);
		
		//If the tempTask Task is null throw exception, else remove task from vector
		if(tempTask == null) {
			throw new NoSuchElementException("Task does not exist.");
		}
		else {
			tasks.remove(tasks.indexOf(tempTask));
		}
	}
	
	//Used to update task name
	public void updateTaskName(String taskID_L, String taskName_L) {
		//Searches for task ID then updates task if it exist, else throws exception
		Task updateTask = search(taskID_L);
		if(updateTask != null) {
			updateTask.setTaskName(taskName_L);
		}
		else {
			throw new NoSuchElementException("Task does not exist");
		}
	}
	
	//Used to update Task Description 
	public void updateTaskDescription(String taskID_L, String taskDescription_L) {
		//Searches for task ID then updates task if it exist, else throws exception
		Task updateTask = search(taskID_L);
		if(updateTask != null) {
			updateTask.setTaskDescription(taskDescription_L);
		}
		else {
			throw new NoSuchElementException("Task does not exist");
		}
	}

	
	//Method for searching by taskID (Reused from ContactService.java)
	public Task search(String taskID_L) {
		//Returns null task if task is not in tasks vector
		Task returnTask = null;
		
		//Ensures task vector not empty, then uses manual checks of taskID
		//to find taskID, if found returns that contact
		if(!tasks.isEmpty()) {
			for(int i = 0; i < tasks.size(); i++) {
				if(tasks.elementAt(i).getTaskID().equals(taskID_L)) {
					return tasks.elementAt(i);
				}
			}
		}
		//Only used with a null task (task not found)
		return returnTask;
	}

	//Used to generate taskIDs psuedo-randomly, very rudimentary (Reused from ContactService.java)
	private String IDGenerator() {
		//selectorString is used as an alphabet for characters, generatorString
		//is used to build string and return.
		String generatorString = "";
		String selectorString = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		//chooses TASK_ID_LENGTH to "randomly" select characters for ID
		for(int i = 0; i < Task.TASK_ID_MAX_LENGTH; i++) {
			generatorString += selectorString.charAt((int)(Math.random()*61));
		}
		
		//In off chance taskID already exists, recursively calls self to generate new ID
		//have not triggered in testing yet with 10 characters. As there are roughly 4/5s of a
		//Quintillion possible combinations, Unlikely to trigger in normal circumstances.
		if(search(generatorString) != null) {
			generatorString = IDGenerator();
		}
		return generatorString;
	}
}
