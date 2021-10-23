package assignment1;

public interface Factorizer
{
    /**
     * Given an int, n, this method locates the prime numbers between 2 and n. The numbers that are not prime
     * will be stored in a map whose keys represent the composite numbers and whose values represent a list of
     * that number's factors. The time it takes to complete shall then be printed.
     * @param n calculate and store the prime numbers between 2 and n inclusively. n > 2
     */
    public void factorize(int n);
}