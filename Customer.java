package cs2030.simulator;
/**
 * Customer has an id and a arrival time.
 */

public class Customer {
    
    /**
     * A Customer has a Customer id that is not changed.
     */
    private final int id;

    /**
     * A Customer has an arrival time.
     */
    private final double arrTime;
    

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

    @Override
    public String toString() {
        return Integer.toString(id);
    }

}

