package project3;
/**
* Low-priority thread representing system logging operations.
* Continuously runs and accesses shared MotorController resource.
*/
public class Logger extends Thread {
	
	private MotorController controller;
	
	/**
	 * Constructs a Logger thread with a shared MotorController.
	 *
	 * @param controller shared MotorController instance
	 */
	public Logger (MotorController controller) {
		setName("Logger");
		this.controller = controller;
	}
	
	
	/**
	 * Continuously executes logging tasks and accesses the MotorController resource.
	 */
	public void run() {
		while (true) {
			System.out.println(MotorController.getTimeStamp() + ": [EXECUTION] Logger (Low) requesting lock");
			controller.accessController(getName());
		}
	}
	
}

