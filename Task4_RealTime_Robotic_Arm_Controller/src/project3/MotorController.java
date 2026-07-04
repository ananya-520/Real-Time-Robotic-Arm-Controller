package project3;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
* Represents a shared motor controller resource accessed by multiple threads.
* Simulates critical section entry and exit with timestamp logging.
*/
public class MotorController {
	
	private Thread currentOwner = null;
	private int originalOwnerPriority;
	private final Object structureLock = new Object();
	
	/**
    * Generates a formatted timestamp representing the current system time.
    *
    * @return current time in HH:mm:ss.SSS format
    */
	public static String getTimeStamp() {
		String timestamp = new SimpleDateFormat("HH:mm:ss.SSS").format(new Date());
		
		return timestamp;
	}
	
	
	public void lockResource (String threadName, int callingPriority) {
		Thread callingThread = Thread.currentThread();
		Thread threadToIncrease = null;
		
		synchronized (structureLock) {
			if (currentOwner != null && currentOwner != callingThread) {
				if (callingPriority > currentOwner.getPriority()) {
					threadToIncrease = currentOwner;
					System.out.println(getTimeStamp() + ": [PRIORITY INHERITANCE] " + threadName + " (Priority: " + callingPriority + ") blocked by " + threadToIncrease.getName());
					
					System.out.println(getTimeStamp() + ": [PRIORITY INHERITANCE] Changing priority of " + threadToIncrease.getName() + " to " + callingPriority);
					
					threadToIncrease.setPriority(callingPriority);
				}
			}
		}
		
		synchronized (this) {
			while (currentOwner != null) {
				try {
					this.wait();
				}
				catch (InterruptedException e) {
					
				}
			}
			
			currentOwner = callingThread;
			originalOwnerPriority = callingPriority;
			
			System.out.println(getTimeStamp() + ": [RESOURCE] " + threadName + " successfully acquired MotorController");
		}
		
	}
	
	
	public synchronized void unlockResource (String threadName) {
		if (currentOwner == Thread.currentThread()) {
			System.out.println(getTimeStamp() + ": [RESOURCE] " + threadName + " is releasing MotorController");
			
			if (Thread.currentThread().getPriority() > originalOwnerPriority) {
				System.out.println(getTimeStamp() + ": [PRIORITY INHERITANCE] Inheritance Restored; Resetting " + threadName + " priority to " + originalOwnerPriority);
				
				Thread.currentThread().setPriority(originalOwnerPriority);
			}
			
			currentOwner = null;
			this.notifyAll();
		}
	}
}
