package assignment3;

/**
 * The Driver class.
 *
 * @author Randolph Bushman
 * @version 12.14.2021
 */
public class Driver
{
    public static void main(String[] args) {
        long start = System.nanoTime();
        AstronomyGetter.downloadAndProcessImages(true);     // True if threaded, false if sequential
        System.out.println("Completed in: " + (System.nanoTime() - start)/1000000 + "ms");
    }
}
