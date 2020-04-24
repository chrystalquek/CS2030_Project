package cs2030.simulator;
import java.util.Comparator;

/**
 * CustomerComparator sorts customers based on time then ID.
 */

public class CustomerComparator implements Comparator<Customer> {

    /*
     * Compares two customers and returns an int based on order.
     * @param c first Customer
     * @param d second Customer
     * @return order returns either -1 or 1
     */
    @Override
    public int compare(Customer c, Customer d) {
        if (c.getID() > d.getID()) {
            return 1;
        } else if (c.getID() < d.getID()) {
            return -1;
        } 
    }
}
