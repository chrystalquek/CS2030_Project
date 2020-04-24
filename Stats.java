package cs2030.simulator;
/**
 * Keeps track of statistics of simulator.
 */

public class Stats {
    /**
     * Number of served customers.
     */
    private int served;

    /**
     * Number of not served customers.
     */
    private int notServed;

    /**
     * Total waiting time.
     */
    private double totalWait;

    Stats() {
        this.served = 0;
        this.notServed = 0;
        this.totalWait = 0.0;
    }

    /**
     * Increment notServed by 1.
     */
    void notserved() {
        this.notServed++;
    }

    /**
     * Increment served by 1.
     */
    void served() {
        this.served++;
    }

    /**
     * Add waiting time.
     * @param wait Waiting time to add
     */
    void addWait(double wait) {
        this.totalWait += wait;
    }

    /**
     * Calculate average waiting time.
     * @return Average waiting time.
     */
    double calcWait() {
        return totalWait / served;
    }

    @Override 
    public String toString() {
        return "[" + String.format("%.3f", calcWait()) + " " + 
            this.served + " " + this.notServed + "]";
    }

}