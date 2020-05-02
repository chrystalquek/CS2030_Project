package cs2030.simulator;
/**
 * Customer has an id and a arrival time.
 */

public class NormalCustomer extends Customer {

    NormalCustomer(int id, double arrTime) {
        super(id, arrTime);
    }

    /**
     * A customer can be greedy or not greedy (normal).
     * @return Always returns false, indicating that customer is not greedy.
     */
    @Override
    public boolean getGreedy() {
        return false;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }

}

