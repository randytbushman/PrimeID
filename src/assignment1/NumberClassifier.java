package assignment1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for classifying numbers as either prime or composite.
 * @author Randolph Bushman
 * @version 9.30.2021
 */
public class NumberClassifier
{
    /**
     * Given an int, n, this method locates all the prime numbers between 2 and n. All numbers that are not prime
     * will be stored in a map whose keys represent the composite numbers and whose values represent a list of all
     * that number's factors. All the primes are then printed along with the factor map.
     * @param n search for all prime numbers between 2 and n inclusively. n > 2
     */
    public static void findPrimes(int n) {
        Map<Integer, List<Integer>> factorMap = new HashMap<>();    // Keys represent the number, values represent factors
        List<Integer> primeList = new ArrayList<>();
        List<Integer> factors;

        for(int i = 2; i <= n; ++i) {
            factors = FactorFinder.findFactors(i);

            if(factors.size() == 2)     // This implies the number is prime.
                primeList.add(i);
            else
                factorMap.put(i, factors);
        }

        // System.out.println("Prime List:" + primeList);
        // System.out.println("Factor Map:" + factorMap);
    }
}
