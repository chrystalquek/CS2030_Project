package cs2030.simulator;

/**
 * ServeEvent represents a Customer that is being served.
 */

public class ServeEvent extends Event {

    ServeEvent(Customer customer, double time, Server server) {
        super(customer, server, time, Event.served);
    }

    @Override
    public String toString() {
        return super.toString() + " served by " + super.server;
    }

}


