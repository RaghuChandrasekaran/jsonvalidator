package com.github.raghuchandrasekaran.jsonvalidator.handler;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Jersey Resources Configuration
 */
public class Handler  {

    public ServletContextHandler getHandler(){
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "com.github.raghuchandrasekaran.jsonvalidator");
        return context;
    }
}
