package cs2030.simulator;

/**
 * ServerBack represents a new Event of a server returning back to service.
 */

public class ServerBack extends Event {
    
    ServerBack(double time, Server server) {
        super(null, server, time, Event.serverBack);
    }

}