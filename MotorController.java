public class MotorController {

    public synchronized void useMotor(String threadName) {

        long time = System.currentTimeMillis();

        System.out.println("[" + time + "] " + threadName + " entered MotorController.");

        try {
            // Simulate using the motor
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[" + System.currentTimeMillis() + "] "
                + threadName + " released MotorController.");
    }
}
