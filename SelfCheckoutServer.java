package cs2030.simulator;
import java.util.PriorityQueue;
import java.util.Optional;
/**
 * Server's job is to serve, reject, wait, finish serving customers.
 */
public class SelfCheckoutServer extends Server {

    private final SelfCheckoutServerManager manager;
    
    SelfCheckoutServer(int id, SelfCheckoutServerManager manager) {
        super(id);
        this.manager = manager;
    }

    SelfCheckoutServer(int id, double nextAvail, SelfCheckoutServerManager manager) {
        super(id, nextAvail);
        this.manager = manager;
    }

    public int canServe(double time) {
        if (manager.cannotWait()) {
            return -1;
        } else if (time < super.nextAvail) {
            return 0;
        } else {
            return 1;
        }    
    }


    /**
     * Updates server to Served.
     * @param time Time to update to.
     * @return A new server with updated next available time and that it accepts waiting customers.
    //  */
    @Override
    public SelfCheckoutServer updateServe(double time) {
        return new SelfCheckoutServer(super.id, time, this.manager);
    }

    /**
     * Updates common customer queue of server.
     * @param customer Customer to add to queue.
     */
    @Override
    public Server updateWait(Customer customer) {
        manager.add(customer);
        return new SelfCheckoutServer(super.id, super.nextAvail, this.manager);
    }

    @Override
    public Optional<Customer> getCustomer() {
        return Optional.ofNullable(manager.getCustomerFromQueue());
    }

    /** 
     * Informs if wants to rest.
     * @return boolean indicating whether it wants to rest.
     */
    @Override
    public boolean getRest(double prob) {
        return false;
    }

    @Override
    public boolean getHuman() {
        return false;
    }

    @Override
    public String toString() {
        return "self-check " + super.id;
    }



}


