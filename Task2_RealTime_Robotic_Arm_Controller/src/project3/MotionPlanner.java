package project3;
/**
* Medium-priority thread representing motion planning operations.
* Repeatedly executes planning logic and accesses shared resource.
*/
public class MotionPlanner extends Thread {
	private MotorController controller;
	
	/**
	 * Constructs a MotionPlanner thread with a shared MotorController.
	 *
	 * @param controller shared MotorController instance
	 */
	public MotionPlanner(MotorController controller) {
		setName("Motion Planner");
		this.controller = controller;
	}
	
	/**
	 * * Continuously executes motion planning tasks and accesses the MotorController resource.
	 */
	public void run() {
		while (true) {
			System.out.println(MotorController.getTimeStamp() + ": [EXECUTION] Motion Planner (Medium) requesting lock");
			controller.accessController(getName());
		}
	}
		
}
