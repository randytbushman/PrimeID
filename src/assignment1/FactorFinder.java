package assignment1;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;


/**
 * This class is responsible for calculating and storing the factors of a given number.
 * @author Randolph Bushman
 * @version 10.22.2021
 */
public class FactorFinder
{
    /**
     * This method locates the factors of a given int n then stores the result in the factor map.
     * @param n the int whose factors we find.
     * @return The List of factors of n.
     */
    public static List<Integer> findFactors(int n, Map<Integer, List<Integer>> factorMap) {
        List<Integer> factorlist = new ArrayList<>();
        factorlist.add(1);

        // [2] We only need to iterate to sqrt(n) as we add factor pairs. sqrt operations are expensive so we square i
        for (int i = 2; i*i <= n; ++i)      // We only need to iterate to sqrt(n)
            if (n % i == 0) {
                factorlist.add(i);
                if (n/i != i)               // In case we are dealing with a square number
                    factorlist.add(n/i);
            }
        factorlist.add(n);
        factorMap.put(n, factorlist);
        return factorlist;
    }


}
