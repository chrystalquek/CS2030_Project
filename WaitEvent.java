package cs2030.simulator;
import java.util.ArrayList;
/**
 * ArriveEvent represents a Event with a Customer that is waiting.
 */

public class WaitEvent extends Event {
    /**
     * WaitEvent has a serverID.
     */
    private final int serverID;

    WaitEvent(Customer customer, double time, int serverID) {
        super(customer, time);
        this.serverID = serverID;
    }

    /**
    * WaitEvent can become a ServeEvent.
    * @param time when Event is going to be served
    * @return a ServeEvent
    */
    public ServeEvent toServed(double time) {
        return new ServeEvent(super.customer, time, this.serverID);
    }

    /**
     * Each subclass of Event has an execute method to represent an action.
     * @param servers Cluster of servers to choose and update server from
     * @param stats Keeps track of statistics required
     */
    @Override
    public Event execute(ServerCluster servers, Stats stats) {
        Server server = servers.getServer(this.serverID - 1);
        return toServed(server.getNextAvail());
    }

    @Override
    public String toString() {
        return super.toString() + " waits to be served by " + this.serverID;
    }

}

