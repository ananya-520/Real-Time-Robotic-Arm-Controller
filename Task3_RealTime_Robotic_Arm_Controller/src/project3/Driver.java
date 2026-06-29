/**
*
*/
package project3;
/**
* Main class of the system
* Creates multiple priority-based threads that share a MotorController resource.
* Demonstrates thread scheduling and concurrent access behavior.
*/
public class Driver {
	/**
	 * Starts the simulation by creating threads with different priorities and launching them concurrently.
	 * @param args command-line arguments
	 * @throws InterruptedException if thread execution is interrupted
	 */
   public static void main(String[] args) throws InterruptedException {
      
   	System.out.println();
   	
   	MotorController controller = new MotorController();
   	
   	SafetyMonitor high = new SafetyMonitor(controller);
   	MotionPlanner med = new MotionPlanner(controller);
   	Logger low = new Logger(controller);
   	
   	high.setPriority(Thread.MAX_PRIORITY);
   	med.setPriority(Thread.NORM_PRIORITY);
   	low.setPriority(Thread.MIN_PRIORITY);
   	
   	low.start();
   	med.start();
   	high.start();
   }
 
}
