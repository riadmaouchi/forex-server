package org.forex;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import static org.eclipse.jetty.servlet.ServletContextHandler.NO_SESSIONS;

public class ForexMain {

    public static void main(String[] args) {
        new ForexMain().start(7070);
    }

    private void start(int port) {

        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(NO_SESSIONS);
        context.setContextPath("/");

        server.setHandler(context);

        final ForexServlet forexServlet = new ForexServlet();
        context.addServlet(new ServletHolder(forexServlet), "/v1/forex/*");

        server.setHandler(context);

        try {
            // TODO start prom server
            //  new HTTPServer(1234);

            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.destroy();
        }
    }
}
