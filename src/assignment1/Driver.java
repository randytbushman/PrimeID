package assignment1;

/**
 * The Driver class.
 * @author Randolph Bushman
 * @version 9.30.2021
 */
public class Driver
{
    public static void main(String[] args) {
        int n = 1000000;
        PrimeSieve.sieveSingleThreaded(n);
        //PrimeSieve.sieveThreadedUnbounded(n);
        //PrimeSieve.sieveThreadedBoundedRunnable(n);
        //PrimeSieve.sieveThreadedBoundedCallable(n);
        new FactorizerUserInterface();
    }
}
