package com.haulmont.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.*;

import javax.persistence.EntityManager;
import java.net.URI;
import java.net.URL;

public class JettyServer {
    private static final String JAR_PATTERN = "org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern";
    private static final String DESCRIPTOR = "src/main/webapp/web.xml";
    private static String httpPort = System.getenv("PORT");

    public static void main(String[] args) throws Exception {

        URL webRootLocation = JettyServer.class.getResource("");
        URI webRootUri = webRootLocation.toURI();

        WebAppContext context = new WebAppContext();
        context.setBaseResource(Resource.newResource(webRootUri));
        context.setContextPath("/");
        context.setAttribute(JAR_PATTERN , ".*");
        context.setConfigurationDiscovered(true);
        context.setDescriptor(DESCRIPTOR);

        int port = httpPort == null ? 8080 : Integer.parseInt(httpPort);
        Server server = new Server(port);
        server.setHandler(context);
        server.start();
        server.join();
    }
}