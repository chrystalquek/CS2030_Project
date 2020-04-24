package cs2030.simulator;

/**
 * ServerBack represents a new Event of a server halting service temporarily.
 */

public class ServerRest extends Event {
    private final int serverID;
    
    ServerRest(double time, int serverID) {
        super(null, time, Event.serverRest);
        this.serverID = serverID;
    }

    @Override
    public int getServerID() {
        return serverID;
    }

}