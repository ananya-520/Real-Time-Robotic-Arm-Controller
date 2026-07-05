package project3;
/**
* High-priority thread representing a safety monitoring system.
* Continuously runs and accesses the shared MotorController resource.
*/
public class SafetyMonitor extends Thread {
	
	private MotorController controller;
	public long waitTime;
	
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
//		while (true) {
		for (int i=0; i<5; i++) {
			try {
				Thread.sleep(100);
			}
			catch (InterruptedException e){
				
			}
			
			System.out.println(MotorController.getTimeStamp() + ": [EXECUTION] Safety Monitor requesting resource");
			
			long start = System.nanoTime();
			controller.lockResource(getName());
			waitTime = System.nanoTime() - start;
			
			System.out.println(MotorController.getTimeStamp() + ": [METRIC] Safety Monitor Waiting Time = " + (waitTime/1000000.0) + " ms");
		
			try {
				Thread.sleep(100);
			}
			catch (InterruptedException e){
				
			}
			
			controller.unlockResource(getName());
		}
		
	}
  
}
