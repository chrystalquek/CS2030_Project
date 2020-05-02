import cs2030.simulator.Simulator;
import java.util.Scanner;
import java.util.PriorityQueue;


/**
 * Main is the client who provides parameters of the simulation.
 */

public class Main {

    /**
     * Main is the client whose sole job is to take in parameters of the simulation.
     * After that Simulator should handle the simulation.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int seed = sc.nextInt();
        int noServers = sc.nextInt();

        int self = sc.nextInt();

        int max = sc.nextInt();
        int customers = sc.nextInt();
        double arrivalRate = sc.nextDouble();
        double serviceRate = sc.nextDouble();
        double restingRate = sc.nextDouble();
        double probRest = sc.nextDouble();

        double probGreedy = sc.nextDouble();

        Simulator.simulate(seed, noServers, self, max, customers, 
            arrivalRate, serviceRate, restingRate, probRest, probGreedy);

        sc.close();
       
    }
}
