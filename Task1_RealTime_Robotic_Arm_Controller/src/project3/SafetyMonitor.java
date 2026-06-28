package project3;
/**
* High-priority thread representing a safety monitoring system.
* Continuously runs and accesses the shared MotorController resource.
*/
public class SafetyMonitor extends Thread {
	
	private MotorController controller;
	
	/**
	 * Constructs a SafetyMonitor thread with a shared MotorController.
	 *
	 * @param controller shared MotorController instance
	 */
	public SafetyMonitor(MotorController controller) {
		setName("Safety Monitor");
		this.controller = controller;
	}
	
	/**
	 * Continuously executes high-priority monitoring tasks and accesses the shared MotorController resource.
	 */
	public void run() {
		while (true) {
			System.out.println(MotorController.getTimeStamp() + ": [EXECUTION] Safety Monitor (High) running");
			controller.accessController(getName());
		}
		
	}
  
}
