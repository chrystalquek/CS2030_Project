package cs2030.simulator;
import java.util.ArrayList;

/**
 * ArriveEvent represents a new Event with a Customer that has just arrived.
 */

public class ArriveEvent extends Event {
    
    ArriveEvent(Customer customer, double time) {
        super(customer, time);
    }

    /**
    * ArriveEvent can become a ServeEvent.
    * @param serverID the ID of the server
    * @return a ServeEvent
    */
    public ServeEvent toServed(int serverID) {
        return new ServeEvent(super.customer, super.time, serverID);
    }

    /**
    * ArriveEvent can become a WaitEvent.
    * @param serverID the ID of the server
    * @return a WaitEvent
    */
    public WaitEvent toWait(int serverID) {
        return new WaitEvent(super.customer, super.time, serverID);
    }

    /**
    * ArriveEvent can become a LeaveEvent.
    * @return a LeaveEvent
    */
    public LeaveEvent toLeaves() {
        return new LeaveEvent(super.customer, super.time);
    }


    /**
     * Each subclass of Event has an execute method to represent an action.
     * A server, if any, is chosen. A new event is generated based on the next state.
     * @param serverCluster Cluster of servers to choose and update server from
     * @param stats Keeps track of statistics required
     */
    @Override
    public Event execute(ServerCluster serverCluster, Stats stats) {

        Server server = serverCluster.chooseServer(super.time);

        if (server == null) {
            // leave
            stats.notserved();
            return toLeaves();
        } else {
            // wait or serve immediately
            int status = server.canServe(super.time);
            int serverID = server.getID();
            if (status == 1) {
                // serve immediately
                return toServed(serverID);
            } else {
                // wait
                Server newserver = server.updateWait();
                serverCluster.update(serverID - 1, newserver);
                stats.addWait(newserver.getNextAvail() - super.time);
                return toWait(serverID);
            }
            
        }  

    }

    @Override
    public String toString() {
        return super.toString() + " arrives";
    }

}
