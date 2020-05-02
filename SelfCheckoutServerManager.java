package cs2030.simulator;

import java.util.PriorityQueue;
import java.util.Optional;

/** 
 * SelfCheckoutServerManager helps all SelfCheckoutServers manage their customer queue.
*/
class SelfCheckoutServerManager {

    /**
     * customers stores the waiting queue to all SelfCheckoutServers.
     */
    private final PriorityQueue<Customer> customers;

    /**
     * Maximum in queue allowed.
     */
    private final int maxWaiting;

    SelfCheckoutServerManager(int maxWaiting) {
        this.maxWaiting = maxWaiting;
        this.customers = new PriorityQueue<Customer>(new CustomerComparator());
    }

    /** 
     * Compares current length of customer queue with maximum allowed.
     * @return A boolean indicating whether it can accept any more waiting customers.
    */
    public boolean cannotWait() {
        return customers.size() >= maxWaiting;
    }

    /**
     * Updates common customer queue of all SelfCheckoutServers.
     * @param customer Customer to add to queue.
     */
    public void add(Customer customer) {
        customers.add(customer);
    }

    /**
     * Polls out a customer from queue.
     * @return An optional that could contain the customer that was at the front of the queue.
     */
    public Optional<Customer> getCustomerFromQueue() {
        return Optional.ofNullable(customers.poll());
    }

    /**
     * Number of people in customer queue.
     * @return An integer indicating the number of customers in the common queue.
     */
    public int cqueueSize() {
        return this.customers.size();
    }

}