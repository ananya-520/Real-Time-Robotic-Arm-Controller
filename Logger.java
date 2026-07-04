public class Logger extends Thread {

    private MotorController motor;

    public Logger(MotorController motor) {
        this.motor = motor;
    }

    @Override
    public void run() {

        while (true) {

            System.out.println("Logger recording activity...");

            motor.useMotor(getName());

            try {
                Thread.sleep(1200);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
