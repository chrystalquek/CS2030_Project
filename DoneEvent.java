package cs2030.simulator;
/**
 * DoneEvent is a Event after Customer is served.
 */
public class DoneEvent extends Event {

    /**
     * DoneEvent has a serverID.
     */
    private final int serverID;

    DoneEvent(Customer customer, double time, int serverID) {
        super(customer, time, Event.done);
        this.serverID = serverID;
    }

    @Override
    public int getServerID() {
        return serverID;
    }

    @Override
    public String toString() {
        return super.toString() + " done serving by ";
    }

}


