package cs2030.simulator;

import java.util.Optional;

/**
 * Server's job is to serve, reject, wait, finish serving customers.
 */
public abstract class Server {
    /**
     * nextAvail indicates the time that the server can serve the next (waiting) Customer.
     */
    protected final double nextAvail;
    
    /**
     * ID indicates the serverID.
     */
    protected final int id;

    Server(int id) {
        this.id = id;
        this.nextAvail = 0;
    }

    Server(int id, double nextAvail) {
        this.id = id;
        this.nextAvail = nextAvail;
    }

    /**
     * Determines whether that Customer should be served, wait or leave.
     * Leave returns -1, wait returns 0, serve returns 1.
     * @param time Arrival time of customer.
     * @return Integer signalling availability of server.
     */
    public abstract int canServe(double time);

    /**
     * Updates next available time of server.
     * @param time Time to update to.
     * @return A new server with updated next available time.
     */
    public abstract Server updateServe(double time);

    /**
     * Updates customer queue of server.
     * @param customer Customer to add to queue.
     * @return A new server with updated queue.
     */
    public abstract Server updateWait(Customer customer);

    /**
     * Polls out a customer from queue.
     * @return An optional that could contain the customer that was at the front of the queue.
     */
    public abstract Optional<Customer> getCustomer();

    /**
     * Compares probability and indicates whether server needs to rest.
     * @param prob A double created from random number generator.
     * @return A boolean indicating if server needs to rest.
     */
    public abstract boolean getRest(double prob);

    /**
     * Indicates if server is human or self-check.
     * @return A boolean indicating if server is human or self-check.
     */
    public abstract boolean getHuman();

    /**
     * Number of people in customer queue.
     * @return An integer indicating the number of customers in the waiting queue.
     */
    public abstract int queueSize();

    public double getNextAvail() {
        return this.nextAvail;
    }

    public int getID() {
        return this.id;
    }

}
