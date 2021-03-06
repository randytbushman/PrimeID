package assignment1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class is responsible for classifying numbers as either prime or composite. The methods in this class are
 * threaded. The number of threads created is unbounded.
 * @author Randolph Bushman
 * @version 10.22.2021
 */
public class UnboundedTheadedFactorizer {

    /**
     * Given an int, n, this method locates the prime numbers between 2 and n. The numbers that are not prime
     * will be stored in a map whose keys represent the composite numbers and whose values represent a list of
     * that number's factors. The time it takes to complete shall then be printed.
     * @param n calculate and store the prime numbers between 2 and n inclusively. n > 2
     */
    public static void factorize(int n) {
        long startTime = System.nanoTime();
        List<Integer> primeList = Collections.synchronizedList(new ArrayList<>());
        Map<Integer, List<Integer>> factorMap = new ConcurrentHashMap<>();
        List<Thread> threadList = new ArrayList<>();
        primeList.add(2);

        if (n >= 3)
            primeList.add(3);


        // All even number greater than 2 are composite
        for (int i = 4; i <= n; i+=2) {
            int finalI = i;
            Runnable task = () -> FactorFinder.findFactors(finalI, factorMap);
            Thread t = new Thread(task);
            t.start();
            threadList.add(t);
        }

        // All odd numbers greater than 3 may be prime
        for (int i = 5; i <= n; i+=2) {
            int finalI = i;
            Runnable task = () -> {
                if (!PrimeFinder.isPrime(finalI, primeList))
                    FactorFinder.findFactors(finalI, factorMap);
            };
            Thread t = new Thread(task);
            t.start();
            threadList.add(t);
        }

        try {
            for (Thread t : threadList)
                t.join();
        } catch (InterruptedException e) {
            System.err.println("InterruptException while joining on main thread.");
        }

        System.out.println("Finished in " + (System.nanoTime() - startTime) + "ns\n\n");
        //System.out.println(primeList);
        //System.out.println(factorMap+"\n\n");
    }
}
