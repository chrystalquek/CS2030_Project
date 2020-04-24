package cs2030.simulator;

/**
 * ArriveEvent represents a new Event with a Customer that has just arrived.
 */

public class ArriveEvent extends Event {
    
    ArriveEvent(Customer customer, double time) {
        super(customer, null, time, Event.arrives);
    }

    @Override
    public String toString() {
        return super.toString() + " arrives";
    }

}
