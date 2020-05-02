package cs2030.simulator;

import java.util.Comparator;

/**
 * EventComparator sorts based on time, type of event and customer id.
 */

public class EventComparator implements Comparator<Event> {

    /*
     * Checks if event is SERVER_REST, SERVER_BACK.
     * @param c Event.
     * @return boolean indicating if event is SERVER_REST or SERVER_BACK.
     */
    private boolean isServerEvent(Event c) {
        return c.getStatus() == Event.serverRest || c.getStatus() == Event.serverBack;
    }

    /*
     * compares two customers and returns an int based on order
     * @param c First Event
     * @param d Fecond Event
     * @return integer indicating order
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
