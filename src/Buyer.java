
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buyer extends Thread {

    private final MachineManufacturer machineManufacturer;
    private static final Integer TIME_UNTIL_THE_NEXT_PURCHASE = 1000;
    private final ReentrantLock lock;
    protected Condition condition;


    public Buyer(MachineManufacturer machineManufacturer, ReentrantLock lock, Condition condition) {
        this.machineManufacturer = machineManufacturer;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {

            try {
                Thread.sleep(TIME_UNTIL_THE_NEXT_PURCHASE);

                if (machineManufacturer.cars.isEmpty()) {
                    System.out.println(Thread.currentThread().getName() + " пришел в автосалон");
                    System.out.println("Машин нет");
                }
                lock.lock();
                while (machineManufacturer.cars.isEmpty()) {
                    condition.await();
                }
                    System.out.println(Thread.currentThread().getName() + " уехал домой на новенькой " + machineManufacturer.cars.remove(0));

            } catch (InterruptedException e) {
                break;
            } finally {
                lock.unlock();
            }
        }
    }
}





