package singlethreadedfactorizer;
import java.util.Map;
import java.util.HashMap;
import java.util.List;


public class FactorFinder
{
    private Map<Integer, List<Integer>> factorMap;

    public FactorFinder() {
        factorMap = new HashMap<>();
    }

    /**
     * This method locates the factors of a given int n then stores the result in the factor map.
     * @param n the int whose factors we find.
     * @return
     */
    public void findFactors(int n) {

    }

    /**
     * This method prints the current state of the factor map.
     */
    public void printMap() {
        System.out.println(factorMap);
    }

}
