package cs2030.simulator;

import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;

/**
 * Shop contains all the servers.
 */

public class Shop {
    /**
     * Contains all the servers.
     */
    private final ArrayList<Server> servers;

    Shop(ArrayList<Server> servers) {
        this.servers = servers;
    }

    /**
     * Gets a server.
     * @param idx Indicates which server to get.
     * @return Server at that index.
     */
    public Server getServer(int idx) {
        if (idx > this.servers.size() || idx <= 0) {
            throw new IndexOutOfBoundsException("Asked for a server that does not exist");
        } else {
            return this.servers.get(idx - 1);
        }
    }

    /**
     * Updates a server in servers.
     * @param idx Indicates which server to update.
     * @param server New server to update to.
     */
    public void update(int idx, Server server) {
        if (idx > this.servers.size() || idx <= 0) {
            throw new IndexOutOfBoundsException("Asked for a server that does not exist");
        } else {
            this.servers.set(idx - 1, server);
        }
    }

        
    /**
     * Scans through arraylist of servers and chooses one or none.
     * @param time Arrival time of customer.
     * @return A server if customer is to be served immediately or wait, otherwise null.
     */
    public Server chooseServer(double time) {
        for (Server s: this.servers) {
            if (s.canServe(time) == 1) {
                return s;
            } 
        }

        for (Server s: this.servers) {
            if (s.canServe(time) == 0) {
                return s;
            } 
        }
        
        return null;
    }

    /**
     * Same as chooseServer but caters to greedy customers.
     * @param time Arrival time of customer.
     * @return A server that has the shortest queue, or none if all servers all full.
     */
    public Server chooseServerForGreedy(double time) {
        for (Server s: this.servers) {
            if (s.canServe(time) == 1) {
                return s;
            } 
        }

        Server server = null;
        int min = Integer.MAX_VALUE;

        for (Server s: this.servers) {
            if (s.canServe(time) == 0 && s.queueSize() < min) {
                min = s.queueSize();
                server = s;
            } 
        }
        
        return server;
    }

    
}