package cs2030.simulator;

import java.util.PriorityQueue;
import java.util.Optional;

/**
 * HumanServer's job is to serve, reject, finish serving and store waiting customers.
 */
public class HumanServer extends Server {
    
    /**
     * Contains waiting customers.
     */
    private final PriorityQueue<Customer> customers;

    /**
     * Maximum in queue allowed.
     */
    private final int maxWaiting;

    /**
     * Probability of rest.
     */
    private final double probRest;
    
    HumanServer(int id, int maxWaiting, double probRest) {
        super(id);
        this.customers = new PriorityQueue<>(new CustomerComparator());
        this.maxWaiting = maxWaiting;
        this.probRest = probRest;
    }

    HumanServer(int id, int maxWaiting, double probRest, double nextAvail, 
        PriorityQueue<Customer> customers) {
        super(id, nextAvail);
        this.customers = customers;
        this.maxWaiting = maxWaiting;
        this.probRest = probRest;
    }

    /**
     * Determines whether that Customer should be served, wait or leave.
     * Leave returns -1, wait returns 0, serve returns 1.
     * @param time Arrival time of customer.
     * @return Integer signalling availability of server.
     */
    @Override
    public int canServe(double time) {
        if (this.customers.size() >= this.maxWaiting) {
            return -1;
        } else if (time < super.nextAvail) {
            return 0;
        } else {
            return 1;
        }    
    }


    /**
     * Updates next available time of server.
     * @param time Time to update to.
     * @return A new HumanServer with updated next available time.
     */
    @Override
    public HumanServer updateServe(double time) {
        return new HumanServer(super.id, this.maxWaiting, this.probRest, time, this.customers);
    }

    /**
     * Updates customer queue of server.
     * @param customer Customer to add to queue.
     * @return A new HumanServer with updated queue.
     */
    @Override
    public Server updateWait(Customer customer) {
        this.customers.add(customer);
        return new HumanServer(super.id, this.maxWaiting, this.probRest, super.nextAvail, 
            this.customers);
    }

    /**
     * Polls out a customer from queue.
     * @return An optional that could contain the customer that was at the front of the queue.
     */
    @Override
    public Optional<Customer> getCustomer() {
        return Optional.ofNullable(this.customers.poll());
    }

    /**
     * Compares probability and indicates whether server needs to rest.
     * @param prob A double created from random number generator.
     * @return A boolean indicating if server needs to rest.
     */
    @Override
    public boolean getRest(double prob) {
        return prob < this.probRest;
    }


    /**
     * Indicates if server is human or self-check.
     * @return A boolean indicating if server is human or self-check.
     */
    @Override
    public boolean getHuman() {
        return true;
    }

    /**
     * Number of people in customer queue.
     * @return An integer indicating the number of customers in the waiitng queue.
     */
    @Override
    public int queueSize() {
        return this.customers.size();
    }

    @Override
    public String toString() {
        return "server " + super.id;
    }


}


