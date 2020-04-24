import cs2030.simulator.Simulator;
import java.util.Scanner;
import java.util.PriorityQueue;


/**
 * Main is the client who provides customers.
 */

public class Main {

    /**
     * Main is the client whose sole job is to accept and create a priority queue of events.
     * After that Simulator should handle the priority queue and servers
     * @param args command line arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // an int value denoting the base seed for the RandomGenerator object;
        int seed = sc.nextInt();
        // an int value representing the number of servers
        int noServers = sc.nextInt();
        // an int value for the maximum queue length, Qmax
        int Qmax = sc.nextInt();
        // an int representing the number of customers (or the number of arrival events) to simulate
        int customers = sc.nextInt();
        // a positive double parameter for the arrival rate, λ
        double arrivalRate = sc.nextDouble();
        // a positive double parameter for the service rate, μ
        double serviceRate = sc.nextDouble();

        // a positive double parameter for the resting rate, ρ
        double restingRate = sc.nextDouble();
        // a double parameter for the probability of resting, Pr
        double probRest = sc.nextDouble();

        Simulator.simulate(seed, noServers, Qmax, customers, arrivalRate, serviceRate, restingRate, probRest);
        sc.close();
       
    }
}
