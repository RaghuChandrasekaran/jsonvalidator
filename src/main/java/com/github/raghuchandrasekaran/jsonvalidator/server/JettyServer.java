package com.github.raghuchandrasekaran.jsonvalidator.server;

import com.github.raghuchandrasekaran.jsonvalidator.handler.Handler;
import org.eclipse.jetty.server.Server;


/**
 * Jetty Embedded Server
 *
 */
public class JettyServer {
    private Server jettyServer;

    public static void main(String[] args) {
        try {
            JettyServer server = new JettyServer();
            server.startServer(new Handler(), System.getProperty("port", "8080"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startServer(Handler handler, String portNumber) {
        try {
            int port = Integer.parseInt(portNumber);
            jettyServer = new Server(port);
            jettyServer.setHandler(handler.getHandler());
            jettyServer.start();
            jettyServer.join();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            stopServer();
        }
    }

    private void stopServer(){
        try {
            jettyServer.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
