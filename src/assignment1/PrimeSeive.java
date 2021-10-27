package assignment1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrimeSeive
{
    public static void seiveSingleThreaded(int n) {
        boolean[] seive = new boolean[n+1];

        List<Integer> primeList = new ArrayList<>();
        Map<Integer, List<Integer>> factorMap = new HashMap<>();

        for (int i = 2; i<=n; ++i)
            if (!seive[i]) {
                primeList.add(i);
                for(int j = 2; j*i <= n; ++j) {
                    FactorFinder.findFactors(j*i, factorMap);
                    seive[i * j] = true;
                }
            }
    }



    public static void seiveThreadedUnbounded(int n) {
        List<Thread> threads = new ArrayList<>();
        boolean[] seive = new boolean[n+1];

        List<Integer> primeList = new ArrayList<>();
        Map<Integer, List<Integer>> factorMap = new HashMap<>();

        for (int i = 2; i<=n; ++i) {
            if (!seive[i]) {
                primeList.add(i);
                for (int j = 2; j * i <= n; ++j) {
                    int finalI = i;
                    int finalJ = j;
                    Runnable task = () -> {
                        FactorFinder.findFactors(finalJ * finalI, factorMap);
                    };
                    Thread t = new Thread(task);
                    t.start();
                    threads.add(t);
                    seive[i * j] = true;
                }
            }
        }

        try {
            for (Thread t : threads)
                t.join();
        } catch (InterruptedException e) {
            System.err.println("InterruptException while joining on main thread.");
        }
    }

    public static void seiveThreadedBoundedRunnable(int n, List<Integer> primeList, Map<Integer, List<Integer>> factorMap) {
        List<Thread> threads = new ArrayList<>();
        boolean[] seive = new boolean[n+1];

        for (int i = 2; i<=n; ++i) {
            if (!seive[i]) {
                primeList.add(i);
                for (int j = 2; j * i <= n; ++j) {
                    int finalI = i;
                    int finalJ = j;
                    Runnable task = () -> {
                        FactorFinder.findFactors(finalJ * finalI, factorMap);
                    };
                    Thread t = new Thread(task);
                    t.start();
                    threads.add(t);
                    seive[i * j] = true;
                }
            }
        }

        try {
            for (Thread t : threads)
                t.join();
        } catch (InterruptedException e) {
            System.err.println("InterruptException while joining on main thread.");
        }
    }



}
