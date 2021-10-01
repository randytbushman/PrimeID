package assignment1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FactorFinder
{
    /**
     * This method returns a list of all the factors of the given int, n.
     * @param n the int we are calculating the factors for.
     * @return A list of all the factors of n.
     */
    public static List<Integer> findFactors(int n) {
        List<Integer> factorlist = new ArrayList<>();
        factorlist.add(1);
        for(int i = 2; i <= n/2; ++i)
            if(n % i == 0)
                factorlist.add(i);
        factorlist.add(n);
        return factorlist;
    }
}
