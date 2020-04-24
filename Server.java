package cs2030.simulator;
/**
 * Server's job is to serve, reject, wait, finish serving customers.
 */
public class Server {
    /**
     * nextAvail indicates the time that the server can serve the next Customer.
     */
    private final double nextAvail;
    
    /**
     * waiting is a boolean which indicates whether there is a waiting Customer.
     */
    private final int waiting;
    
    /**
     * ID indicates the serverID.
     */
    private final int id;
    
    /**
     * toServe is the time taken for the server to serve a Customer and is a constant.
     */
    // private static final double toServe = genServiceTime();
    private static final double toServe = 1.00;
    // + now?

    private int maxWaiting;

    Server(int id, int maxWaiting) {
        this.id = id;
        this.nextAvail = 0;
        this.maxWaiting = maxWaiting;
        this.waiting = 0;
    }

    Server(int id, int maxWaiting, double nextAvail, int waiting) {
        this.id = id;
        this.maxWaiting = maxWaiting;
        this.nextAvail = nextAvail;
        this.waiting = waiting;
    }

    /**
     * Determines whether that Customer should be served, wait or leave.
     * Leave returns -1, wait returns 0, serve returns 1.
     * @param time Arrival time of customer.
     * @return The new status of the Event.
     */
    public int canServe(double time) {
        if (this.waiting == maxWaiting) {
            return -1;
        } else if (time < this.nextAvail) {
            return 0;
        } else {
            return 1;
        }    
    }

    /**
     * Updates server to Served.
     * @param time Time to update to.
     * @return A new server with updated next available time and that it accepts waiting customers.
     */
    public Server updateServe(double time) {
        return new Server(this.id, this.maxWaiting, time + toServe, this.waiting);
    }

    /** 
     * Updates server to Wait.
     * @return A new server with same serving time but updates that it has a waiting customer.
     */
    public Server updateWait() {
        return new Server(this.id, this.maxWaiting, this.nextAvail, this.waiting + 1);
    }

    public double getNextAvail() {
        return this.nextAvail;
    }

    public int getID() {
        return this.id;
    }

}
