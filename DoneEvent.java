package cs2030.simulator;

/**
 * DoneEvent is a Event after Customer is served.
 */
public class DoneEvent extends Event {

    DoneEvent(Customer customer, double time, Server server) {
        super(customer, server, time, Event.done);
    }

    @Override
    public String toString() {
        return super.toString() + " done serving by " + super.server;
    }

}


