package assignment2;

import java.util.concurrent.atomic.AtomicInteger;

public class PrimeFinder {


    /**
     * This method confirms if a given int n is prime. This algorithm completes in O(sqrt(N)) time. This method assumes
     * that n is neither 2 nor 3 as these represent the trivial primes. This method also assumes that n is odd and greater
     * than 1!
     * @param n The number we are classifying.
     * @param tally
     * @return true if n is prime and false if n is composite.
     */
    public static boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;

        if(n % 2 == 0 || n % 3 == 0)
            return false;
        /*
        [1] All primes number must either be written as 6n - 1 or 6n + 1
        [2] We need only check until we reach sqrt(n). Since sqrt functions are expensive, we instead square the
        other side. If we cannot find a number before sqrt(n), that implies there exist no factor pairs (prime).
        */
        for(int i = 5; i*i <= n; i += 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;

        return true;
    }


}
