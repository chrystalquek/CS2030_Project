package cs2030.simulator;
import java.util.Optional;
/**
 * Server's job is to serve, reject, wait, finish serving customers.
 */
public abstract class Server {
    /**
     * nextAvail indicates the time that the server can serve the next Customer.
     */
    protected final double nextAvail;
    
    /**
     * ID indicates the serverID.
     */
    protected final int id;

    // private int maxWaiting;

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
     * @return The new status of the Event.
     */
    public abstract int canServe(double time);

    /**
     * Updates server to Served.
     * @param time Time to update to.
     * @return A new server with updated next available time and that it accepts waiting customers.
     */
    public abstract Server updateServe(double time);

    /** 
     * Updates server to Wait.
     * @return A new server with same serving time but updates that it has a waiting customer.
     */
    public abstract Server updateWait(Customer customer);

    public abstract Optional<Customer> getCustomer();

    public double getNextAvail() {
        return this.nextAvail;
    }

    public int getID() {
        return this.id;
    }

    @Override
    public String toString() {
        return "server " + this.id;
    }

}
