package assignment1;

import static java.lang.Integer.parseInt;

public class Driver
{
    public static void main(String[] args) {
        NumberClassifier nc = new NumberClassifier();
        for(String n : args) {
            System.out.println("Searching range [2:" + n + "]");
            nc.findPrimes(parseInt(n));
            System.out.println();
        }
    }
}
