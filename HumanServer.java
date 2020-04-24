package cs2030.simulator;
import java.util.PriorityQueue;
import java.util.Optional;
/**
 * Server's job is to serve, reject, wait, finish serving customers.
 */
public class HumanServer extends Server {
    
    /**
     * Contains waiting customers.
     */
    // a self checkout dosent have its own unique queue. so its not a common trait among all servers
    private final PriorityQueue<Customer> customers;

    /**
     * Maximum in queue allowed.
     */
    private final int maxWaiting;

    private final double probRest;
    
    HumanServer(int id, int maxWaiting, double probRest) {
        super(id);
        this.customers = new PriorityQueue<>(new CustomerComparator());
        this.maxWaiting = maxWaiting;
        this.probRest = probRest;
    }

    HumanServer(int id, int maxWaiting, double probRest, double nextAvail, PriorityQueue<Customer> customers) {
        super(id, nextAvail);
        this.customers = customers;
        this.maxWaiting = maxWaiting;
        this.probRest = probRest;
    }

    /**
     * Determines whether that Customer should be served, wait or leave.
     * Leave returns -1, wait returns 0, serve returns 1.
     * @param time Arrival time of customer.
     * @return The new status of the Event.
     */
    @Override
    public int canServe(double time) {
        if (this.customers.size() >= this.maxWaiting) {
            return -1;
        } else if (time < super.nextAvail) {
            return 0;
        } else {
            return 1;
        }    
    }


    /**
     * Updates next available time of server.
     * @param time Time to update to.
     * @return A new server with updated next available time.
     */
    @Override
    public HumanServer updateServe(double time) {
        return new HumanServer(super.id, this.maxWaiting, this.probRest, time, this.customers);
    }

    /**
     * Updates customer queue of server.
     * @param customer Customer to add to queue.
     * @return A new server with updated customer queue.
     */
    @Override
    public void updateWait(Customer customer) {
        this.customers.add(customer);
    }

    /**
     * Polls out a customer from queue.
     * @return An optional that could contain the customer at the front of the customer queue.
     */
    @Override
    public Optional<Customer> getCustomer() {
        return Optional.ofNullable(this.customers.poll());
    }

    @Override
    public boolean getRest(double prob) {
        return prob < this.probRest;
    }


}


