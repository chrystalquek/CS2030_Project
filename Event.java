package cs2030.simulator;
/**
 * An Event is an action that could produce a new event and change the status of servers.
 */
public abstract class Event {
    /**
     * Customer is tagged to an Event.
     */
    protected final Customer customer;

    /**
     * Each Event has a time.
     */
    protected final double time;

    Event(Customer customer, double time) {
        this.customer = customer;
        this.time = time;
    }

    // need for comparator
    public Customer getCustomer() {
        return this.customer;
    }

    public double getTime() {
        return this.time;
    }

    /**
     * Each subclass of Event has an execute method to represent an action.
     * @param servers Cluster of servers to choose and update server from
     * @param stats Keeps track of statistics required
     * @return A new Event
     */
    public abstract Event execute(ServerCluster servers, Stats stats);

    @Override
    public String toString() {
        return String.format("%.3f", time) + " " + customer.toString();
    }
    

}