package assignment1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for classifying numbers as either prime or composite. The methods within this class
 * are single threaded.
 * @author Randolph Bushman
 * @version 10.22.2021
 */
public class SingleThreadedFactorizer
{
    /**
     * Given an int, n, this method locates the prime numbers between 2 and n. The numbers that are not prime
     * will be stored in a map whose keys represent the composite numbers and whose values represent a list of
     * that number's factors. The time it takes to complete shall then be printed.
     * @param n calculate and store the prime numbers between 2 and n inclusively. n > 2.
     */
    public static void factorize(int n) {
        long startTime = System.nanoTime();
        List<Integer> primeList = new ArrayList<>();
        Map<Integer, List<Integer>> factorMap = new HashMap<>();
        primeList.add(2);

        if (n >= 3)
            primeList.add(3);

        // All even number greater than 2 are composite
        for (int i = 4; i <= n; i+=2)
            FactorFinder.findFactors(i, factorMap);

        // All odd numbers greater than 3 may be prime
        for (int i = 5; i <= n; i+=2)
            if (!PrimeFinder.isPrime(i, primeList))
                FactorFinder.findFactors(i, factorMap);

        System.out.println("Finished in " + (System.nanoTime() - startTime) + "ns\n\n");
        //System.out.println(primeList);
        //System.out.println(factorMap+"\n\n");
    }


    /**
     * Given an int, start, and int, end this method locates the prime numbers between start and end inclusively. The
     * numbers that are not prime will be stored in a map whose keys represent the composite numbers and whose values
     * represent a list of that number's factors. The time it takes to complete shall then be printed.
     * @param start calculate and store the prime numbers between start and end inclusively. start < end.
     * @param end calculate and store the prime numbers between start and end inclusively. end > 2.
     */
    public static void factorize(int start, int end) {
        long startTime = System.nanoTime();
        List<Integer> primeList = new ArrayList<>();
        Map<Integer, List<Integer>> factorMap = new HashMap<>();

        if (start == 2)
            primeList.add(2);
        if (start <= 3)
            primeList.add(3);

        int factorStart;
        int primeStart;
        if (start % 2 == 0) {
            factorStart = start;
            primeStart = start + 1;
        }
        else {
            factorStart = start + 1;
            primeStart = start;
        }


        // All even number greater than 2 are composite
        for (int i = factorStart; i <= end; i+=2)
            FactorFinder.findFactors(i, factorMap);

        // All odd numbers greater than 3 may be prime
        for (int i = primeStart; i <= end; i+=2)
            if (!PrimeFinder.isPrime(i, primeList))
                FactorFinder.findFactors(i, factorMap);

        System.out.println("Finished in " + (System.nanoTime() - startTime) + "ns\n\n");
        //System.out.println(primeList);
        //System.out.println(factorMap+"\n\n");
    }


}
