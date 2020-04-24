package cs2030.simulator;
import java.util.ArrayList;

/**
 * ArriveEvent represents a new Event with a Customer that has just arrived.
 */

public class ArriveEvent extends Event {
    
    ArriveEvent(Customer customer, double time) {
        super(customer, time, Event.arrives);
    }

    @Override
    public String toString() {
        return super.toString() + " arrives";
    }

}
