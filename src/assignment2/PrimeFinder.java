package assignment2;

public class PrimeFinder {

    public static int findPrimes(int start, int end) {
        long startTime = System.nanoTime();
        int count = 0;

        if (start == 2)
            ++count;
        if (start <= 3)
            ++count;

        if (start % 2 == 0)
            ++start;


        // All odd numbers greater than 3 may be prime
        for (; start <= end; start+=2)
            if (isPrime(start))
                ++count;

        System.out.println("Finished in " + (System.nanoTime() - startTime) + "ns\n\n");
        System.out.println("Final count: " + count);
        return count;
    }

    /**
     * This method confirms if a given int n is prime. This algorithm completes in O(sqrt(N)) time. This method assumes
     * that n is neither 2 nor 3 as these represent the trivial primes. This method also assumes that n is odd and greater
     * than 1!
     * @param n The number we are classifying.
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
