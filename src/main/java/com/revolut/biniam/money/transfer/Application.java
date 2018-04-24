package com.revolut.biniam.money.transfer;

import io.swagger.jaxrs.config.DefaultJaxrsConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Biniam Asnake
 */
public class Application {

    private static final Integer SERVER_PORT = 8080;

    public static void main(String[] args) {

        Server server = new Server(SERVER_PORT);

        ServletContextHandler context =
                new ServletContextHandler(ServletContextHandler.NO_SESSIONS);

        context.setContextPath("/");
        server.setHandler(context);

        // Setup API resources
        ServletHolder serHol = context.addServlet(ServletContainer.class, "/api/*");
        serHol.setInitOrder(1);
        serHol.setInitParameter("jersey.config.server.provider.packages",
                "com.revolut.biniam.money.transfer.controller;io.swagger.jaxrs.json;io.swagger.jaxrs.listing");

        // Setup Swagger servlet
        ServletHolder swaggerServlet = context.addServlet(DefaultJaxrsConfig.class, "/swagger-core");
        swaggerServlet.setInitOrder(2);
        swaggerServlet.setInitParameter("api.version", "1.0.0");
        swaggerServlet.setInitParameter("description", "Revolut Money Transfer!");
        swaggerServlet.setInitParameter("swagger.api.title", "API Documentation for Revolut Money Transfer");
        swaggerServlet.setInitParameter("swagger.api.basepath", "http://localhost:" + SERVER_PORT + "/api");
        swaggerServlet.setInitParameter("swagger.pretty.print", "true");

        // Setup Swagger-UI static resources
        String resourceBasePath = Application.class.getResource("/webapp").toExternalForm();
        context.setWelcomeFiles(new String[] {"index.html"});
        context.setResourceBase(resourceBasePath);
        context.addServlet(new ServletHolder(new DefaultServlet()), "/*");

        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            server.destroy();
        }
    }
}