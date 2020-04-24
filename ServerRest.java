package cs2030.simulator;

/**
 * ServerBack represents a new Event of a server halting service temporarily.
 */

public class ServerRest extends Event {
    
    ServerRest(double time, Server server) {
        super(null, server, time, Event.serverRest);
    }

}