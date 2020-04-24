package cs2030.simulator;
/**
 * DoneEvent is a Event that is at the last possible state, after Customer is served.
 */
public class DoneEvent extends Event {

    /**
     * DoneEvent has a serverID.
     */
    private final int serverID;

    DoneEvent(Customer customer, double time, int serverID) {
        super(customer, time);
        this.serverID = serverID;
    }

    /**
     * Each subclass of Event has an execute method to represent an action.
     * A DoneEvent does not become a new Event.
     * @param servers Cluster of servers to choose and update server from
     * @param stats Keeps track of statistics required
     */
    @Override
    public Event execute(ServerCluster servers, Stats stats) {
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + " done serving by " + serverID;
    }

}


