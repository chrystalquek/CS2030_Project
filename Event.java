package cs2030.simulator;
/**
 * An Event is an action that could produce a new event and change the status of servers.
 */
public abstract class Event {
    
    /**
     * An Event can have five possible states - ARRIVES, SERVED, WAITS, LEAVES and DONE, SERVER_REST, SERVER_BACK
     */
    public static final int arrives = 1;
    public static final int served = 2;
    public static final int waits = 3;
    public static final int leaves = 4;
    public static final int done = 5;
    public static final int serverRest = 6;
    public static final int serverBack = 7;

    /**
     * Customer is tagged to an Event.
     */
    protected final Customer customer;

    /**
     * Server is tagged to an Event.
     */
    protected final Server server;

    /**
     * Each Event has a time.
     */
    protected final double time;

    /**
     * Each Event has a status.
     */
    protected final int status;

    Event(Customer customer, Server server, double time, int status) {
        this.customer = customer;
        this.server = server;
        this.time = time;
        this.status = status;
    }

    // need for comparator
    public Customer getCustomer() {
        return this.customer;
    }

    public Server getServer() {
        return this.server;
    }

    public double getTime() {
        return this.time;
    }

    public int getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return String.format("%.3f", time) + " " + customer;
    }
    
}