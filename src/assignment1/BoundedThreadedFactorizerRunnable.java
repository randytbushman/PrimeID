package assignment1;

import java.util.*;
import java.util.concurrent.*;

public class BoundedThreadedFactorizerRunnable {

    public static void factorize(int n) {
        factorize(n, Runtime.getRuntime().availableProcessors() + 1);
    }

    public static void factorize(int n, int threadpoolSize) {
        System.out.println("Threadpool size: " + threadpoolSize);

        long startTime = System.nanoTime();
        ExecutorService exec = Executors.newFixedThreadPool(threadpoolSize);
        List<Integer> primeList = Collections.synchronizedList(new ArrayList<>());
        Map<Integer, List<Integer>> factorMap = new ConcurrentHashMap<>();
        primeList.add(2);

        if (n >= 3)
            primeList.add(3);


        for (int i = 4; i <= n; i+=2) {
            int finalI = i;
            Runnable task = () -> FactorFinder.findFactors(finalI, factorMap);
            exec.execute(task);
        }

        for (int i = 5; i <= n; i+=2) {
            int finalI = i;
            Runnable task = () -> {
                if (!PrimeFinder.isPrime(finalI, primeList))
                    FactorFinder.findFactors(finalI, factorMap);
            };
            exec.execute(task);
        }

        exec.shutdown();
        try {
            exec.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.err.println("I am lame");
        }


        System.out.println("Finished in " + (System.nanoTime() - startTime) + "ns\n\n");
        System.out.println(primeList);
        //System.out.println(factorMap+"\n\n");
    }





}
