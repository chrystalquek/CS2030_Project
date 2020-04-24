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

        PriorityQueue<Event> events = generateEvents(customers, rng);

        Shop shop = generateServers(noServers, Qmax);

        Stats stats = new Stats();

        while (events.peek() != null) {
            Event e = events.poll();
            displayEvent(e, shop);
            e = getNextEvent(e, shop, stats, rng);

            if (e != null) {
                events.add(e);
            }
        }

        System.out.println(stats);

    }

    /**
     * Sends each event in priority queue to be executed and returns new event.
     * @param e previous event
     * @param shop contains all servers
     * @param stats contains stats of simulator
     * @param rng generate timings randomly
     */
    private static Event getNextEvent(Event e, Shop shop, Stats stats, RandomGenerator rng) {
        double time = e.getTime();
        Customer c = e.getCustomer();
        if (e.getStatus() == Event.arrives) {
            Server server = shop.chooseServer(time);
            if (server == null) {
                // leave
                stats.notserved();
                return new LeaveEvent(c, time);
            } else {
                // wait or serve immediately
                int status = server.canServe(time);
                int serverID = server.getID();
                if (status == 1) {
                    // serve immediately
                    return new ServeEvent(c, time, serverID);
                } else {     
                    // wait  
                    return new WaitEvent(c, time, serverID);
                }
            }
        } else if (e.getStatus() == Event.served) {
            Server server = shop.getServer(e.getServerID());
            Server newserver = server.updateServe(time + rng.genServiceTime());
            shop.update(e.getServerID(), newserver);
        
            stats.served(time - c.getArrTime());

            return new DoneEvent(c, newserver.getNextAvail(), e.getServerID());

        } else if (e.getStatus() == Event.waits) {
            Server server = shop.getServer(e.getServerID());
            Server newserver = server.updateWait(c);
            shop.update(e.getServerID(), newserver);
            return null;

        } else if (e.getStatus() == Event.done) {
            Server server = shop.getServer(e.getServerID());
            return server.getCustomer().map(x -> new ServeEvent(x, time, e.getServerID())).orElse(null);     
            
        } else {
            return null;

        }

    }

    /**
     * Simulator's job to display event details. Reduce responsibility of getNextEvent
     * @param e previous event
     * @param shop contains all servers
     * @param stats contains stats of simulator
     * @param rng generate timings randomly
     */
    private static void displayEvent(Event e, Shop shop) {
        if (e.getStatus() == Event.served || e.getStatus() == Event.waits || e.getStatus() == Event.done) {
            System.out.println(e.toString() + shop.getServer(e.getServerID()));
        } else {
            System.out.println(e.toString());
        }
    }

    /**
     * Creates events containing customers.
     * @param customers Number of customers
     * @param rng generate arrival timings randomly
     * @return A priority queue of events
     */
    private static PriorityQueue<Event> generateEvents(int customers, RandomGenerator rng) {
        PriorityQueue<Event> events = 
            new PriorityQueue<>(new EventComparator());
        int i = 0;
        double now = 0;
        while (i < customers) {
            events.add(new ArriveEvent(new Customer(++i, now), now));
            now += rng.genInterArrivalTime();
        }
        return events;
    }

    /**
     * Creates servers within Shop.
     * @param serverNo Number of servers.
     * @return A Shop of servers.
     */
    private static Shop generateServers(int serverNo, int Qmax) {
        ArrayList<Server> servers = new ArrayList<Server>();
        for (int i = 0; i < serverNo; i++) {
            servers.add(new HumanServer(i + 1, Qmax));
        }
        return new Shop(servers);
    }

}   