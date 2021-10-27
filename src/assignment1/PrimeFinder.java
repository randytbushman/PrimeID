package assignment1;

import java.util.List;

/**
 *
 */
public class PrimeFinder
{

    /**
     * This method confirms if a given int n is prime. This algorithm completes in O(sqrt(N)) time. This method assumes
     * that n is neither 2 nor 3 as these are the trivial primes. This method also assumes that n is odd and greater
     * than 1!
     * @param n The number we are classifying.
     * @return true if n is prime and false if n is composite.
     */
    public static boolean isPrime(int n, List<Integer> primeList) {

        if(n % 3 == 0)
            return false;

        for(int i = 5; i*i <= n; i += 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;

        // All primes greater than 3 are the form of 6k-+1
        // We can also check -> 6k-1 <= sqrt(n)
        // Only need to check up to sqrt(n) for primes
        // Here, two theorems are used
        primeList.add(n);
        return true;
    }

}
