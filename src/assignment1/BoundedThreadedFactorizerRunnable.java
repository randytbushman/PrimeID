package assignment1;

import java.util.*;
import java.util.concurrent.*;


/**
 * This class is responsible for classifying numbers as either prime or composite. The methods within this class
 * are multithreaded and use bounded threadpools with Runnables.
 * @author Randolph Bushman
 * @version 10.27.2021
 */
public class BoundedThreadedFactorizerRunnable {

    /**
     * Given an int, n, this method locates the prime numbers between 2 and n. The numbers that are not prime
     * will be stored in a map whose keys represent the composite numbers and whose values represent a list of
     * that number's factors. The time it takes to complete shall then be printed. This method is threaded and
     * will use [#cores on system + 1] threads.
     * @param n calculate and store the prime numbers between 2 and n inclusively. n > 2
     */
    public static void factorize(int n) {
        factorize(n, Runtime.getRuntime().availableProcessors() + 1);
    }

    /**
     * Given an int, n, this method locates the prime numbers between 2 and n. The numbers that are not prime
     * will be stored in a map whose keys represent the composite numbers and whose values represent a list of
     * that number's factors. The time it takes to complete shall then be printed. This method is threaded and will
     * use the number of threads specified.
     * @param n calculate and store the prime numbers between 2 and n inclusively. n > 2
     * @param threadpoolSize the number of threads that threadpool uses.
     */
    public static void factorize(int n, int threadpoolSize) {
        System.out.println("Threadpool size: " + threadpoolSize);

        long startTime = System.nanoTime();
        ExecutorService exec = Executors.newFixedThreadPool(threadpoolSize);
        List<Integer> primeList = Collections.synchronizedList(new ArrayList<>());
        Map<Integer, List<Integer>> factorMap = new ConcurrentHashMap<>();
        primeList.add(2);

        if (n >= 3)
            primeList.add(3);

        // All even number greater than 2 are composite
        for (int i = 4; i <= n; i+=2) {
            int finalI = i;
            Runnable task = () -> FactorFinder.findFactors(finalI, factorMap);
            exec.execute(task);
        }

        // All odd numbers greater than 3 may be prime
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
