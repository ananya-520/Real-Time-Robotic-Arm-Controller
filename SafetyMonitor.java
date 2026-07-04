public class SafetyMonitor extends Thread {

    private MotorController motor;

    public SafetyMonitor(MotorController motor) {
        this.motor = motor;
    }

    @Override
    public void run() {

        while (true) {

            System.out.println("SafetyMonitor checking system...");

            motor.useMotor(getName());

            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
