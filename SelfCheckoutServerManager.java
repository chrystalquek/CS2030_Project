package cs2030.simulator;

import java.util.PriorityQueue;

class SelfCheckoutServerManager {

    private final PriorityQueue<Customer> customers;

    private final int maxWaiting;

    SelfCheckoutServerManager(int maxWaiting) {
        this.maxWaiting = maxWaiting;
        this.customers = new PriorityQueue<Customer>(new CustomerComparator());
    }

    public boolean cannotWait() {
        return customers.size() >= maxWaiting;
    }

    public void add(Customer customer) {
        customers.add(customer);
    }

    public Customer getCustomerFromQueue() {
        return customers.poll();
    }

}