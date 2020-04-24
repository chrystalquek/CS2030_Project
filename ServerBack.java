package cs2030.simulator;

/**
 * ServerBack represents a new Event of a server returning back to service.
 */

public class ServerBack extends Event {
    private final int serverID;
    
    ServerBack(double time, int serverID) {
        super(null, time, Event.serverBack);
        this.serverID = serverID;
    }

    @Override
    public int getServerID() {
        return serverID;
    }

}