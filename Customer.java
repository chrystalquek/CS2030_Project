package cs2030.simulator;
/**
 * Customer only has an id tagged to him.
 */

public class Customer {
    
    /**
     * A Customer has a Customer id that is not changed.
     */
    private final int id;
    

    Customer(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }

}

