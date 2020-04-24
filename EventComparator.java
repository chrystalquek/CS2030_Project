package cs2030.simulator;
import java.util.Comparator;

/**
 * CustomerComparator sorts customers based on time then ID.
 */

public class EventComparator implements Comparator<Event> {

    private boolean isServerEvent(Event c) {
        return c.getStatus() == Event.serverRest || c.getStatus() == Event.serverBack;
    }

    /*
     * compares two customers and returns an int based on order
     * @param c first Event
     * @param d second Event
     * @return order returns either -1 or 1
     */
    @Override
    public int compare(Event c, Event d) {
        if (c.getTime() > d.getTime()) {
            return 1;
        } else if (c.getTime() < d.getTime()) {
            return -1;
        } else {
            if (isServerEvent(c) && !isServerEvent(d)) {
                return -1;
            } else if (!isServerEvent(c) && isServerEvent(d)) {
                return 1;
            } else if (isServerEvent(c) && isServerEvent(d)) {
                return 0;
            } else {
                if (c.getCustomer().getID() > d.getCustomer().getID()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        } 
    }
}
