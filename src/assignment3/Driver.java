package assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Driver
{
    public static void main(String[] args) {


        long start = System.nanoTime();
        AstronomyGetter.downloadAndProcessImages(true);
        System.out.println((System.nanoTime() - start)/1000000 + "ms");
    }
}
