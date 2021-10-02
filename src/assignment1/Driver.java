package assignment1;

import static java.lang.Integer.parseInt;

/**
 * The Driver class.
 * @author Randolph Bushman
 * @version 9.30.2021
 */
public class Driver
{
    public static void main(String[] args) {
        long startTime;
        for(String s : args) {
            try {
                int number = parseInt(s);
                if(number > 2) {
                    System.out.println("Searching range [2:" + number + "]");
                    startTime = System.currentTimeMillis();
                    NumberClassifier.findPrimes(number);
                    System.out.println("Time: " + (System.currentTimeMillis() - startTime) + " Millisecond(s)");
                }
                else
                    System.out.println("Number must be larger than 2");
                System.out.println();
            } catch (NumberFormatException e) {
                // If the user passes an argument that is not an int.
                System.out.println("Not Properly Formatted: " + s);
                System.out.println();
            }
        }
    }
}
