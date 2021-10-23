package assignment1;

public interface Factorizer
{
    /**
     * This function is responsible for finding and storing all the prime and composite numbers from 2 to n
     * inclusively. The primes are stored in a List and the composite numbers are stored in a Map whose keys represent
     * the number itself and whose values represent the factors of that number.
     * @param n
     */
    public void factorize(int n);
}
