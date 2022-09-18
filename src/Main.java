import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        MachineManufacturer machineManufacturer = new MachineManufacturer(lock, condition);
        ThreadGroup threadGroup = new ThreadGroup("Группа покупателей");
        Runnable buy = new Buyer(machineManufacturer, lock, condition);
        new Thread(threadGroup, buy, "Покупатель 1").start();
        new Thread(threadGroup, buy, "Покупатель 2").start();
        new Thread(threadGroup, buy, "Покупатель 3").start();
        new Thread(threadGroup, buy, "Покупатель 4").start();

        machineManufacturer.start();

        if (!machineManufacturer.isAlive()) {
            threadGroup.interrupt();
            System.out.println();


        }
    }

}



