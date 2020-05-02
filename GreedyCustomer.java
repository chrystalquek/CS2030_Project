package cs2030.simulator;
/**
 * Customer has an id and a arrival time.
 */

public class GreedyCustomer extends Customer {

    GreedyCustomer(int id, double arrTime) {
        super(id, arrTime);
    }

    /**
     * A customer can be greedy or not greedy (normal).
     * @return Always returns true, indicating that customer is greedy.
     */
    @Override
    public boolean getGreedy() {
        return true;
    }

    @Override
    public String toString() {
        return Integer.toString(id) + "(greedy)";
    }

}

