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
    public static void simulate(int seed, int noServers, int Nself, int Qmax, int customers, 
        double arrivalRate, double serviceRate, double restingRate, double probRest) {

        RandomGenerator rng = new RandomGenerator(seed, arrivalRate, serviceRate, restingRate);

        PriorityQueue<Event> events = generateEvents(customers, rng);

        Shop shop = generateServers(noServers, Nself, Qmax, probRest);

        Stats stats = new Stats();

        while (events.peek() != null) {
            Event e = events.poll();
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
        Server s = e.getServer();
        if (e.getStatus() == Event.arrives) {
            System.out.println(e);
            Server server = shop.chooseServer(time);
            if (server == null) {
                // leave
                stats.notserved();
                return new LeaveEvent(c, time);
            } else {
                // wait or serve immediately
                int status = server.canServe(time);
                if (status == 1) {
                    // serve immediately
                    return new ServeEvent(c, time, server);
                } else {     
                    // wait  
                    return new WaitEvent(c, time, server);
                }
            }
        } else if (e.getStatus() == Event.served) {
            System.out.println(e);
            Server newServer = s.updateServe(time + rng.genServiceTime());
            shop.update(s.getID(), newServer);
            stats.served(time - c.getArrTime());
            return new DoneEvent(c, newServer.getNextAvail(), newServer);

        } else if (e.getStatus() == Event.waits) {
            System.out.println(e);
            Server newServer = s.updateWait(c);
            shop.update(s.getID(), newServer);
            return null;
        } else if (e.getStatus() == Event.done) {
            System.out.println(e);
            if (s.getHuman() && s.getRest(rng.genRandomRest())) {
                return new ServerRest(time, s);
            } else {
                return s.getCustomer().map(x -> new ServeEvent(x, time, s)).orElse(null);   
            }  
        } else if (e.getStatus() == Event.serverRest) {
            double newTime = time + rng.genRestPeriod();
            Server newServer = s.updateServe(newTime);
            shop.update(s.getID(), newServer);
            return new ServerBack(newTime, newServer);

        } else if (e.getStatus() == Event.serverBack) {
            return s.getCustomer().map(x -> new ServeEvent(x, time, s)).orElse(null);    
        } else {
            System.out.println(e);
            return null;

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
    private static Shop generateServers(int serverNo, int Nself, int Qmax, double probRest) {
        ArrayList<Server> servers = new ArrayList<Server>();
        for (int i = 0; i < serverNo; i++) {
            servers.add(new HumanServer(i + 1, Qmax, probRest));
        }
        SelfCheckoutServerManager manager = new SelfCheckoutServerManager(Qmax);
        for (int i = serverNo; i < serverNo + Nself; i++) {
            // same ID
            servers.add(new SelfCheckoutServer(i + 1, manager));
        }
        return new Shop(servers);
    }

}   