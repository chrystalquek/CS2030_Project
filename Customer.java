package cs2030.simulator;
/**
 * Customer has an id and a arrival time.
 */

public abstract class Customer {
    
    /**
     * A Customer has an id.
     */
    protected final int id;

    /**
     * A Customer has an arrival time.
     */
    protected final double arrTime;
    

    Customer(int id, double arrTime) {
        this.id = id;
        this.arrTime = arrTime;
    }

    public int getID() {
        return id;
    }

    public double getArrTime() {
        return arrTime;
    }

    /**
     * A customer can be greedy or not greedy (normal).
     * @return Boolean indicating if customer is greedy or not greedy.
     */
    public abstract boolean getGreedy();

}

