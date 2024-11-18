package shosafoev.simulation.thread.animalLifecycleTask;
import shosafoev.simulation.thread.animalLifecycleTask.task.Eat;
import shosafoev.simulation.thread.animalLifecycleTask.task.HpDecrease;
import shosafoev.simulation.thread.animalLifecycleTask.task.Move;
import shosafoev.simulation.thread.animalLifecycleTask.task.Multiply;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnimalLife implements Runnable{
    private final Eat eat;
    private final Multiply multiply;
    private final HpDecrease hpDecrease;
    private final CountDownLatch latch;

    public AnimalLife() {
        latch = new CountDownLatch(3);
        eat = new Eat(latch);
        multiply = new Multiply(latch);
        hpDecrease = new HpDecrease(latch);
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.submit(eat); // the animal is eating
        executorService.submit(multiply); // the animal reproduces
        executorService.submit(hpDecrease);// decrease in health
        try {
            latch.await(); // waiting until the CountDownLatch reaches zero
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.submit(new Move()); // animals are moving to other locations
    }

    public Multiply getObjectMultiplyTask() {
        return multiply;
    }

    public Eat getAnimalEatTask() {
        return eat;
    }

    public HpDecrease getAnimalHpDecreaseTask() {
        return hpDecrease;
    }
}
