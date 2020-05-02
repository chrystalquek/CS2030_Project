package cs2030.simulator;

import java.util.PriorityQueue;
import java.util.Optional;

/**
 * SelfCheckoutServer's job is to serve, reject, finish serving customers.
 * Works together with SelfCheckoutServerManager which manages the queue.
 */
public class SelfCheckoutServer extends Server {
    /**
     * manager keeps tracking of waiting customers, all SelfCheckoutServers use the same manager.
     */
    private final SelfCheckoutServerManager manager;
    
    SelfCheckoutServer(int id, SelfCheckoutServerManager manager) {
        super(id);
        this.manager = manager;
    }

    SelfCheckoutServer(int id, double nextAvail, SelfCheckoutServerManager manager) {
        super(id, nextAvail);
        this.manager = manager;
    }

    /**
     * Determines whether that Customer should be served, wait or leave.
     * Leave returns -1, wait returns 0, serve returns 1.
     * @param time Arrival time of customer.
     * @return Integer signalling availability of server.
     */
    public int canServe(double time) {
        if (manager.cannotWait()) {
            return -1;
        } else if (time < super.nextAvail) {
            return 0;
        } else {
            return 1;
        }    
    }


    /**
     * Updates server to Served.
     * @param time Time to update to.
     * @return A new server with updated next available time and that it accepts waiting customers.
    //  */
    @Override
    public SelfCheckoutServer updateServe(double time) {
        return new SelfCheckoutServer(super.id, time, this.manager);
    }

    /**
     * Updates common customer queue of server.
     * @param customer Customer to add to queue.
     * @return Server with updated manager.
     */
    @Override
    public Server updateWait(Customer customer) {
        manager.add(customer);
        return new SelfCheckoutServer(super.id, super.nextAvail, this.manager);
    }

    /**
     * Polls out a customer from queue, requests for manager.
     * @return An optional that could contain the customer that was at the front of the queue.
     */
    @Override
    public Optional<Customer> getCustomer() {
        return manager.getCustomerFromQueue();
    }

    /**
     * Indicates if server wants to rest.
     * @param prob A double created from random number generator.
     * @return A boolean always returning false as self-check does not need to rest.
     */
    @Override
    public boolean getRest(double prob) {
        return false;
    }

    /**
     * Indicates if server is human or self-check.
     * @return A boolean indicating if server is human or self-check.
     */
    @Override
    public boolean getHuman() {
        return false;
    }

    /**
     * Number of people in customer queue.
     * @return An integer indicating the number of customers in the waiting queue.
     */
    @Override
    public int queueSize() {
        return manager.cqueueSize();
    }

    @Override
    public String toString() {
        return "self-check " + super.id;
    }

}


