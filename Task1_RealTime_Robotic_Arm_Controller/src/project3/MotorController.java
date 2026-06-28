package project3;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
* Represents a shared motor controller resource accessed by multiple threads.
* Simulates critical section entry and exit with timestamp logging.
*/
public class MotorController {
	
	/**
    * Generates a formatted timestamp representing the current system time.
    *
    * @return current time in HH:mm:ss.SSS format
    */
	public static String getTimeStamp() {
		String timestamp = new SimpleDateFormat("HH:mm:ss.SSS").format(new Date());
		
		return timestamp;
	}
	
	
	/**
	 * Simulates access to the motor controller as a shared resource.
	 * Prints entry and exit logs and holds the thread for a short duration to simulate processing time.
	 *
	 * @param threadName name of the thread accessing the controller
	 */
  
	public void accessController(String threadName) {
		
		System.out.println(getTimeStamp() + ": [RESOURCE ENTRY] " + threadName + " has entered the MotorController");
		
		try {
			Thread.sleep(100);
		}
		catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
		System.out.println(getTimeStamp() + ": [RESOURCE EXIT] " + threadName + " has exited the MotorController");
	}
	
}
