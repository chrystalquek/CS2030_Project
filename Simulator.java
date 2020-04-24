package cs2030.simulator;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Simulator handles a priority queue of events and updates the servers
 * and queue based on the server's response.
 */
public class Simulator {

    /**
     * Sends each event in priority queue to be executed and adds new events back to queue.
     * @param serverNo number of servers
     * @param events priority queue of events that the server has to manage
     */
    public static void simulate(int seed, int noServers, int Qmax, int customers, double arrivalRate, double serviceRate) {

        RandomGenerator rng = new RandomGenerator(seed, arrivalRate, serviceRate, 0);
        
        PriorityQueue<Event> events = 
            new PriorityQueue<>(new EventComparator());
        int i = 1;
        double now = 0;
        while (i <= customers) {
            events.add(new ArriveEvent(new Customer(i++), now));
            now += rng.genInterArrivalTime();
        }


        ServerCluster serverCluster = generateServers(noServers, Qmax);

        Stats stats = new Stats();

        while (events.peek() != null) {
            Event e = events.poll();
            System.out.println(e);
            e = e.execute(serverCluster, stats);

            if (e != null) {
                events.add(e);
            }
        }

        System.out.println(stats);

    }

    /**
     * Creates servers within ServerCluster.
     * @param serverNo Number of servers.
     * @return A ServerCluster of servers.
     */
    private static ServerCluster generateServers(int serverNo, int Qmax) {
        ArrayList<Server> servers = new ArrayList<Server>();
        for (int i = 0; i < serverNo; i++) {
            servers.add(new Server(i + 1, Qmax));
        }
        return new ServerCluster(servers);
    }

}   