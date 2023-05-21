package java8.multithread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class EvenOddPrinter {
   // private  static Object object = new Object();

    static ReentrantLock rLock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.runAsync(()->EvenOddPrinter.task(i->i%2!=0));
        CompletableFuture.runAsync(()->EvenOddPrinter.task(i->i%2==0));


        Thread.sleep(1000);
    }

    public static void task(IntPredicate condition){
        IntStream.range(1,10).filter(condition).forEach(EvenOddPrinter::execute);
    }


    public static void execute(int n){

        rLock.lock();

        try {
            System.out.println(Thread.currentThread().getName()+" - "+n);

            rLock.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        rLock.unlock();

    }
}
