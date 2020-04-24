package cs2030.simulator;
/**
 * LeaveEvent represents a Event with a Customer that is leaving.
 */
public class LeaveEvent extends Event {

    LeaveEvent(Customer customer, double time) {
        super(customer, time, Event.leaves);
    }

    @Override
    public String toString() {
        return super.toString() + " leaves";
    }

}


