package com.haulmont.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.*;

import java.net.URI;
import java.net.URL;

public class JettyServer {
    private static final String JAR_PATTERN = "org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern";
    private static final String DESCRIPTOR = "src/main/webapp/web.xml";
    private static int httpPort = 8080;

    public static void main(String[] args) throws Exception {

        URL webRootLocation = JettyServer.class.getResource("/webapp/");
        URI webRootUri = webRootLocation.toURI();

        WebAppContext context = new WebAppContext();
        context.setBaseResource(Resource.newResource(webRootUri));
        context.setContextPath("/");
        context.setAttribute(JAR_PATTERN , ".*");
        context.setConfigurationDiscovered(true);
        context.setDescriptor(DESCRIPTOR);

        Server server = new Server(httpPort);
        server.setHandler(context);
        server.start();
        server.join();
    }
}