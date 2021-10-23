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
public class SingleThreadedFactorFinder
{
    /**
     * This method locates the factors of a given int n then stores the result in the factor map.
     * @param n the int whose factors we find.
     * @return The List of factors of n.
     */
    public static List<Integer> findFactors(int n) {
        List<Integer> factorlist = new ArrayList<>();
        factorlist.add(1);
        for(int i = 2; i <= n/2; ++i)   // We iterate to n/2 because no larger int cannot divide n besides itself.
            if(n % i == 0)
                factorlist.add(i);
        factorlist.add(n);
        return factorlist;
    }


}
