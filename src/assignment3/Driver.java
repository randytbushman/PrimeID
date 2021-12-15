package assignment3;

public class Driver
{
    public static void main(String[] args) {
        long start = System.nanoTime();
        AstronomyGetter.downloadAndProcessImages(true);
        System.out.println((System.nanoTime() - start)/1000000 + "ms");
    }
}
