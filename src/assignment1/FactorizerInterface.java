package assignment1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FactorizerInterface
{
    private Scanner sc;
    private int lastSelectedFactorizeMode;
    private boolean running;

    public FactorizerInterface() {
        sc = new Scanner(System.in);
        running = true;
        System.out.println("Welcome to Randy's Factorizer!");
        int numThreads = 0;



        while(running) {
            System.out.println("Please select a mode:\n" +
                    "1) Single Threaded\n" +
                    "2) Unbounded Thread (generate a new thread for each unit of work)\n" +
                    "3) Bounded Threadpool using the Executor Framework\n" +
                    "4) Bounded Threadpool using a Callable rather than a Runnable\n" +
                    "5) Quit Factorizer");
            try {
                lastSelectedFactorizeMode = sc.nextInt();
                switch (lastSelectedFactorizeMode) {
                    case 1: System.out.println(1); break;
                    case 2: System.out.println(2); break;
                    case 3:
                        System.out.println("Please specify Threadpool size:");
                        numThreads = sc.nextInt();
                        if(numThreads > 0)
                            System.out.println("Size of threadpool: " + numThreads);
                        else
                            System.err.println("Thread count must be positive!");
                        break;
                    case 4:
                        System.out.print("Please specify Threadpool size: ");
                        numThreads = sc.nextInt();
                        if(numThreads > 0)
                            System.err.println("Size of threadpool: " + numThreads);
                        else
                            System.err.println("Thread count must be positive!");
                        break;

                    case 5: System.out.println("Goodbye!"); running=false; break;
                    default: System.err.println("Please pick a valid selection\n");
                }
            }

            catch (InputMismatchException e) {
                System.err.println("Please provide a valid integer input.");
                sc.next();
            }

        }
    }
}
