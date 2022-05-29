import io.grpc.Server;
import io.grpc.ServerBuilder;
import service.ZodiacSign;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {

            Server server = ServerBuilder.forPort(8999).addService(new ZodiacSign()).build();
            server.start();
            System.out.println("Server started at " + server.getPort());
            server.awaitTermination();
        } catch (IOException exception) {
            System.out.println("Error: " + exception);
        } catch (InterruptedException exception) {
            System.out.println("Error: " + exception);
        }
    }
}

