package cs2030.simulator;
import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;

/**
 * ServerCluster contains all the servers.
 */

public class ServerCluster {
    /**
     * Contains all the servers.
     */
    private final ArrayList<Server> servers;

    ServerCluster(ArrayList<Server> servers) {
        this.servers = servers;
    }

    /**
     * Gets a server.
     * @param idx Indicates which server to get.
     * @return Server at that index.
     */
    public Server getServer(int idx) {
        if (idx >= this.servers.size()) {
            throw new IndexOutOfBoundsException("Asked for a server that does not exist");
        } else {
            return this.servers.get(idx);
        }
    }

    /**
     * Updates a server in servers.
     * @param idx Indicates which server to update.
     * @param server New server to update to.
     */
    void update(int idx, Server server) {
        this.servers.set(idx, server);
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
}