package cs2030.simulator;
/**
 * LeaveEvent represents a Event with a Customer that is leaving.
 */
public class LeaveEvent extends Event {

    LeaveEvent(Customer customer, double time) {
        super(customer, time);
    }

    /**
     * Each subclass of Event has an execute method to represent an action.
     * A LeaveEvent does not become a new Event.
     * @param servers Cluster of servers to choose and update server from
     * @param stats Keeps track of statistics required
     */
    @Override
    public Event execute(ServerCluster servers, Stats stats) {
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + " leaves";
    }

}


