package cs2030.simulator;
import java.util.ArrayList;

/**
 * ServeEvent represents a Customer that is being served.
 */

public class ServeEvent extends Event {
    /**
     * ServeEvent has a serverID.
     */
    private final int serverID;

    ServeEvent(Customer customer, double time, int serverID) {
        super(customer, time);
        this.serverID = serverID;
    }

    /**
    * ServeEvent can become a DoneEvent.
    * @param time when Event is done
    * @return a DoneEvent
    */
    public DoneEvent toDone(double time) {
        return new DoneEvent(super.customer, time, this.serverID);
    }

    /**
     * Each subclass of Event has an execute method to represent an action.
     * @param servers Cluster of servers to choose and update server from
     * @param stats Keeps track of statistics required
     */
    @Override
    public Event execute(ServerCluster servers, Stats stats) {
        stats.served();
        Server server = servers.getServer(this.serverID - 1);
        Server newserver = server.updateServe(super.time);
        servers.update(this.serverID - 1, newserver);
        return toDone(newserver.getNextAvail());
    }

    @Override
    public String toString() {
        return super.toString() + " served by " + serverID;
    }

}


