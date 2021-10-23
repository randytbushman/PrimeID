package assignment1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for classifying numbers as either prime or composite. The time it
 * @author Randolph Bushman
 * @version 10.22.2021
 */
public class SingleThreadedFactorizer implements Factorizer
{
    /**
     * Given an int, n, this method locates the prime numbers between 2 and n. The numbers that are not prime
     * will be stored in a map whose keys represent the composite numbers and whose values represent a list of
     * that number's factors. The time it takes to complete shall then be printed.
     * @param n calculate and store the prime numbers between 2 and n inclusively. n > 2
     */
    public void factorize(int n) {
        long startTime = System.nanoTime();
        List<Integer> primeList = new ArrayList<>();
        Map<Integer, List<Integer>> factorMap = new HashMap<>();
        for (int i = 2; i <= n; ++i)
            if (SingleThreadedPrimeFinder.isPrime(i))
                primeList.add(i);
            else
                factorMap.put(i, SingleThreadedFactorFinder.findFactors(i));
        System.out.println("Finished in " + (System.nanoTime() - startTime) + "ns");
    }
}
