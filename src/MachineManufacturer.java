import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MachineManufacturer extends Thread {

    protected static final int PRODUCTION_PROCESS_TIME = 2000;
    protected List<String> cars;
    protected static final int MAX_COUNT = 10;
    private final ReentrantLock lock;
    protected Condition condition;


    public MachineManufacturer(ReentrantLock lock, Condition condition) {
        this.cars = new LinkedList<>();
        this.lock = lock;
        this.condition = condition;
    }


    @Override
    public void start() {
        for (int i = 0; i < MAX_COUNT; i++) {
            try {
                Thread.sleep(PRODUCTION_PROCESS_TIME);
                lock.lock();
                cars.add("Лада гранта");
                System.out.println("Автомобиль выпущен");
                condition.signalAll();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
              lock.unlock();
            }

        }
    }
}

