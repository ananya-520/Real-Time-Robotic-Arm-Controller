package project3;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
* Represents a shared motor controller resource accessed by multiple threads.
* Simulates critical section entry and exit with timestamp logging.
*/
public class MotorController {
	
	private final int ceilingPriority;
	private Thread currentOwner = null;
	private int originalPriority;
	
	/**
    * Generates a formatted timestamp representing the current system time.
    *
    * @return current time in HH:mm:ss.SSS format
    */
	public static String getTimeStamp() {
		String timestamp = new SimpleDateFormat("HH:mm:ss.SSS").format(new Date());
		
		return timestamp;
	}
	
	
	public MotorController(int ceilingPriority) {
		this.ceilingPriority = ceilingPriority;
	}
	
	public synchronized void lockResource (String threadName) {
		Thread callingThread = Thread.currentThread();
		
		while (currentOwner != null) {
			try {
				this.wait();
			}
			catch (InterruptedException e) {
				
			}
		}
		
		currentOwner = callingThread;
		originalPriority = callingThread.getPriority();
		
		System.out.println(getTimeStamp() + ": [PRIORITY CEILING] Raising " + threadName + " priority to Ceiling: " + ceilingPriority);
		callingThread.setPriority(ceilingPriority);
		
		System.out.println(getTimeStamp() + ": [RESOURCE] " + threadName + " acquired MotorController");
		
	}
	
	
	public synchronized void unlockResource (String threadName) {
		if (currentOwner == Thread.currentThread()) {
			System.out.println(getTimeStamp() + ": [RESOURCE] " + threadName + " is releasing MotorController");
			currentOwner = null;
			
			System.out.println(getTimeStamp() + ": [PRIORITY CEILING] Ceiling Restored; Resetting " + threadName + " priority to " + originalPriority);
				
			Thread.currentThread().setPriority(originalPriority);
			
			this.notifyAll();
		}
	}
}
