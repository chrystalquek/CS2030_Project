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
        super(customer, time, Event.served);
        this.serverID = serverID;
    }

    @Override
    public int getServerID() {
        return serverID;
    }

    @Override
    public String toString() {
        return super.toString() + " served by ";
    }

}


