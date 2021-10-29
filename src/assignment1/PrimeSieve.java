package assignment1;

import java.util.*;
import java.util.concurrent.*;

public class PrimeSieve
{
    public static void sieveSingleThreaded(int n) {
        long startTime = System.nanoTime();
        boolean[] sieve = new boolean[n+1];
        List<Integer> primeList = new ArrayList<>();
        Map<Integer, List<Integer>> factorMap = new HashMap<>();

        for (int i = 2; i<=n; i++)
            if (!sieve[i]) {
                primeList.add(i);
                for(int j = 2; j*i <= n; ++j) {
                    if(!sieve[i*j]) {
                        FactorFinder.findFactors(j * i, factorMap);
                        sieve[i * j] = true;
                    }
                }
            }


        System.out.println("Finished in " + (System.nanoTime() - startTime) + "ns");
        //System.out.println(primeList);
        //System.out.println(factorMap);
    }



    public static void sieveThreadedUnbounded(int n) {
        long startTime = System.nanoTime();
        boolean[] sieve = new boolean[n+1];
        List<Thread> threads = new ArrayList<>();
        List<Integer> primeList = new ArrayList<>();
        Map<Integer, List<Integer>> factorMap = new ConcurrentHashMap<>();


        for (int i = 2; i<=n; ++i)
            if (!sieve[i]) {
                primeList.add(i);
                for (int j = 2; j * i <= n; ++j) {
                    if(!sieve[i*j]) {
                        int finalIJ = i * j;
                        Runnable task = () -> FactorFinder.findFactors(finalIJ, factorMap);
                        Thread t = new Thread(task);
                        t.start();
                        threads.add(t);
                        sieve[i * j] = true;
                    }
                }
            }



        try {
            for (Thread t : threads)
                t.join();
        } catch (InterruptedException e) {
            System.err.println("InterruptException while joining on main thread.");
        }
        System.out.println("Finished in " + (System.nanoTime() - startTime) + "ns");
        //System.out.println(primeList);
        //System.out.println(factorMap);
    }

    public static void sieveThreadedBoundedRunnable(int n) {
        sieveThreadedBoundedRunnable(n, Runtime.getRuntime().availableProcessors() + 1);
    }

    public static void sieveThreadedBoundedRunnable(int n, int threadpoolSize) {
        long startTime = System.nanoTime();
        boolean[] sieve = new boolean[n+1];

        List<Integer> primeList = new ArrayList<>();
        Map<Integer, List<Integer>> factorMap = new ConcurrentHashMap<>();
        ExecutorService exec = Executors.newFixedThreadPool(threadpoolSize);


        for (int i = 2; i<=n; ++i)
            if (!sieve[i]) {
                primeList.add(i);
                for (int j = 2; j * i <= n; ++j) {
                    if(!sieve[i*j]) {
                        int finalIJ = i * j;
                        Runnable task = () -> FactorFinder.findFactors(finalIJ, factorMap);
                        exec.execute(task);
                        sieve[i * j] = true;
                    }
                }
        }

        exec.shutdown();
        try {
            exec.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.err.println("InterruptedException while awaiting termination.");
        }
        System.out.println("Finished in " + (System.nanoTime() - startTime) + "ns");
        //System.out.println(primeList);
        //System.out.println(factorMap);

    }

    public static void sieveThreadedBoundedCallable(int n) {
        sieveThreadedBoundedCallable(n, Runtime.getRuntime().availableProcessors() + 1);
    }

    public static void sieveThreadedBoundedCallable(int n, int threadpoolSize) {
        long startTime = System.nanoTime();
        boolean[] sieve = new boolean[n+1];

        List<Integer> primeList = new ArrayList<>();
        Map<Integer, List<Integer>> factorMap = new ConcurrentHashMap<>();
        ExecutorService exec = Executors.newFixedThreadPool(threadpoolSize);


        for (int i = 2; i<=n; ++i)
            if (!sieve[i]) {
                primeList.add(i);
                for (int j = 2; j * i <= n; ++j) {
                    if (!sieve[i * j]) {
                        int finalIJ = i * j;
                        Callable<Boolean> task = () -> {
                            FactorFinder.findFactors(finalIJ, factorMap);
                            return true;
                        };
                        exec.submit(task);
                        sieve[i * j] = true;
                    }
                }
            }

        exec.shutdown();
        try {
            exec.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.err.println("InterruptedException while awaiting termination.");
        }

        System.out.println("Finished in " + (System.nanoTime() - startTime) + "ns");
        //System.out.println(primeList);
        //System.out.println(factorMap);
    }
}
