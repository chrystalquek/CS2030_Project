package cs2030.simulator;
import java.util.ArrayList;
/**
 * WaitEvent represents a Event with a Customer that is waiting.
 */

public class WaitEvent extends Event {

    /**
     * WaitEvent has a serverID.
     */
    private final int serverID;

    WaitEvent(Customer customer, double time, int serverID) {
        super(customer, time, Event.waits);
        this.serverID = serverID;
    }

    @Override
    public int getServerID() {
        return serverID;
    }

    @Override
    public String toString() {
        return super.toString() + " waits to be served by ";
    }

}

