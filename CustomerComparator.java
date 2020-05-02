package cs2030.simulator;

import java.util.Comparator;

/**
 * CustomerComparator sorts customers based on ID.
 */

public class CustomerComparator implements Comparator<Customer> {

    /*
     * Compares two customers and returns an int based on order.
     * @param c First Customer.
     * @param d Second Customer.
     * @return Integer indicating order.
     */
    @Override
    public int compare(Customer c, Customer d) {
        if (c.getID() > d.getID()) {
            return 1;
        } else if (c.getID() < d.getID()) {
            return -1;
        } else {
            return 0;
        }
    }
}
