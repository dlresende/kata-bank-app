package net.diegolemos.bankapp;

import static org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory.createHttpServer;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

public class MyServer {
    public static final String BANK_APP = "http://localhost:8081/api/";

    public static HttpServer startServer() throws MalformedURLException {
        HttpServer httpServer = createHttpServer(URI.create(BANK_APP), new BankAppConfig());
        addStaticContent(httpServer);
        return httpServer;
    }

    public static HttpServer startServer(AbstractBinder binder) {
        ResourceConfig config = new ResourceConfig();
        config.packages("net.diegolemos.bankapp");
        config.register(binder);
        HttpServer httpServer = createHttpServer(URI.create(BANK_APP), config);
        addStaticContent(httpServer);
        return httpServer;
    }

    private static void addStaticContent(HttpServer httpServer) {
        httpServer.getServerConfiguration().addHttpHandler(new StaticHttpHandler("src/main/webapp/"));
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BANK_APP));
        System.in.read();
        server.stop();
    }
}

