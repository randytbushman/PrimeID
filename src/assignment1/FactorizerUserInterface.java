package assignment1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class provides an interface for the user to select one of four methods of factorizing.
 * @author Randolph Bushman
 * @version 10.21.2021
 */
public class FactorizerUserInterface
{
    public FactorizerUserInterface() {
        boolean running = true;
        int numThreads;
        int lastSelectedFactorizeMode;
        int numToFactorize;
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Randy's Factorizer!");

        while(running) {
            System.out.println("""
                    Please select a mode:
                    1) Single Threaded
                    2) Unbounded Thread (generate a new thread for each unit of work)
                    3) Bounded Threadpool using the Executor Framework
                    4) Bounded Threadpool using a Callable rather than a Runnable
                    5) Quit Factorizer""");

            try {
                lastSelectedFactorizeMode = sc.nextInt();

                if(lastSelectedFactorizeMode == 5) {
                    System.out.println("Goodbye!");
                    running = false;
                }
                else {
                    System.out.println("What number would you like to factorize?");
                    numToFactorize = sc.nextInt();

                    switch (lastSelectedFactorizeMode) {
                        case 1 -> SingleThreadedFactorizer.factorize(numToFactorize);
                        case 2 -> UnboundedTheadedFactorizer.factorize(numToFactorize);
                        case 3 -> {
                            System.out.println("Specify Threadpool size (enter 0 for default settings): ");
                            numThreads = sc.nextInt();
                            if (numThreads == 0)
                                BoundedThreadedFactorizerRunnable.factorize(numToFactorize);
                            else if (numThreads > 0)
                                BoundedThreadedFactorizerRunnable.factorize(numToFactorize, numThreads);
                            else
                                System.err.println("Thread count must be positive!");
                        }
                        case 4 -> {
                            System.out.println("Specify Threadpool size (enter 0 for default settings): ");
                            numThreads = sc.nextInt();
                            if (numThreads == 0)
                                BoundedThreadedFactorizerCallable.factorize(numToFactorize);
                            else if (numThreads > 0)
                                BoundedThreadedFactorizerCallable.factorize(numToFactorize, numThreads);
                            else
                                System.err.println("Thread count must be positive!");
                        }
                        default -> System.err.println("Please pick a valid selection\n");
                    }
                }
            }

            catch (InputMismatchException e) {
                System.err.println("Please provide a valid integer input.");
                sc.next();
            }

        }
    }
}
