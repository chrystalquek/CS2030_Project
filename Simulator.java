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
     * @param seed an int value representing the seed.
     * @param noServers an int value representing the number of servers
     * @param noSelf an int value representing the number of self-checkout counters.
     * @param max an int value for the maximum queue length
     * @param customers an int representing the number of customers.
     * @param arrivalRate a positive double parameter for the arrival rate, λ.
     * @param serviceRate a positive double parameter for the service rate, μ
     * @param restingRate a positive double parameter for the resting rate, ρ.
     * @param probRest a double parameter for the probability of resting, Pr.
     * @param probGreedy a double parameter for the probability of a greedy customer occurring, Pg.
     */
    public static void simulate(int seed, int noServers, int noSelf, int max, int customers, 
        double arrivalRate, double serviceRate, double restingRate, 
            double probRest, double probGreedy) {

        RandomGenerator rng = new RandomGenerator(seed, arrivalRate, serviceRate, restingRate);

        PriorityQueue<Event> events = generateEvents(customers, rng, probGreedy);

        Shop shop = generateServers(noServers, noSelf, max, probRest);

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
     * @return next event
     */
    private static Event getNextEvent(Event e, Shop shop, Stats stats, RandomGenerator rng) {
        double time = e.getTime();
        Customer c = e.getCustomer();
        Server s = e.getServer();
        if (e.getStatus() == Event.arrives) {
            System.out.println(e);
            Server server;
            if (!c.getGreedy()) {
                server = shop.chooseServer(time);
            } else {
                server = shop.chooseServerForGreedy(time);
            }
            if (server == null) {
                stats.notserved();
                return new LeaveEvent(c, time);
            } else {
                int status = server.canServe(time);
                if (status == 1) {
                    return new ServeEvent(c, time, server);
                } else {     
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
     * @param rng Generate arrival timings randomly
     * @param probGreedy Probability that customer created is a greedy customer.
     * @return A priority queue of events
     */
    private static PriorityQueue<Event> generateEvents(int customers, 
        RandomGenerator rng, double probGreedy) {
        PriorityQueue<Event> events = 
            new PriorityQueue<>(new EventComparator());
        int i = 0;
        double now = 0;
        while (i < customers) {
            // events.add(new ArriveEvent(new Customer(++i, now), now));
            if (rng.genCustomerType() < probGreedy) {
                events.add(new ArriveEvent(new GreedyCustomer(++i, now), now));
            } else {
                events.add(new ArriveEvent(new NormalCustomer(++i, now), now));
            }
            now += rng.genInterArrivalTime();
        }
        return events;
    }

    /**
     * Creates servers within Shop.
     * @param serverNo Number of HumanServers.
     * @param self number of SelfCheckoutServers.
     * @param max Maximum size of queue.
     * @param probRest Probability of a HumanServer resting.
     * @return A Shop of servers.
     */
    private static Shop generateServers(int serverNo, int self, int max, double probRest) {
        ArrayList<Server> servers = new ArrayList<Server>();
        for (int i = 0; i < serverNo; i++) {
            servers.add(new HumanServer(i + 1, max, probRest));
        }
        SelfCheckoutServerManager manager = new SelfCheckoutServerManager(max);
        for (int i = serverNo; i < serverNo + self; i++) {
            servers.add(new SelfCheckoutServer(i + 1, manager));
        }
        return new Shop(servers);
    }

}   