package cs2030.simulator;
/**
 * WaitEvent represents a Event with a Customer that is waiting.
 */

public class WaitEvent extends Event {

    WaitEvent(Customer customer, double time, Server server) {
        super(customer, server, time, Event.waits);
    }

    @Override
    public String toString() {
        return super.toString() + " waits to be served by " + super.server;
    }

}

