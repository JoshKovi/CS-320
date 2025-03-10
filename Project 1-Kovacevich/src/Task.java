package Project /* School: Southern New Hampshire University
 * Course: CS-320
 * Name: Joshua Kovacevich
 * Date: 11/21/2021
 * Description: This is the Task Class, used to store and validate Task Objects
 */ 

public class Task {
	//These are the three fields required for the task class. ID, Name, and Description
	private String taskID;
	private String taskName;
	private String taskDescription;
	
	//I realized I could make my design slightly more maintainable with protected
	//constant ints so that is what these are, have implemented some in the contact
	//class as well though I have not completed that yet.
	protected static final int TASK_ID_MAX_LENGTH = 10;
	protected static final int TASK_NAME_LENGTH = 20;
	protected static final int TASK_DESCRIPTION_LENGTH = 50;
	protected static final int MIN_LENGTH = 1;
	
	private Task() {/*Empty Default constructor prevents empty class initialization*/}
	
	//The main constructor for the task class
	public Task(String taskID_L, String taskName_L, String taskDescription_L) {
		//Uses the setters to assign values to variables and validate input
		setTaskID(taskID_L);
		setTaskName(taskName_L);
		setTaskDescription(taskDescription_L);
	}
	
	//Copied from Contact, used to validate strings and ensure proper length
	private String stringValidation(String inputString_L, int minLength_L, int maxLength_L) {
		
		//If string is blank, empty, or outside proper length throws IllegalArgumentException
		//otherwise returns inputString_L
		if(inputString_L.isBlank() || inputString_L.isEmpty()) {
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
	
	//Default Getters
	protected String getTaskID() {
		return taskID;
	}

	protected String getTaskName() {
		return taskName;
	}
	
	protected String getTaskDescription() {
		return taskDescription;
	}
	
	//Setters that use stringValidation method to assign value
	protected void setTaskID(String taskID_L) {
		//TASK_ID_LENGTH is the maximum length allowed
		this.taskID = stringValidation(taskID_L, MIN_LENGTH, TASK_ID_MAX_LENGTH);
	}

	protected void setTaskName(String taskName_L) {
		//TASK_NAME_LENGTH is the maximum length allowed
		this.taskName = stringValidation(taskName_L, MIN_LENGTH, TASK_NAME_LENGTH);
	}

	protected void setTaskDescription(String taskDescription_L) {
		//TASK_DESCRIPTION_LENGTH is the maximum length allowed
		this.taskDescription = stringValidation(taskDescription_L, MIN_LENGTH, TASK_DESCRIPTION_LENGTH);
	}

}
