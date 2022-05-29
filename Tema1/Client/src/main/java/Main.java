import proto.Person;
import proto.PersonServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8999).usePlaintext().build();
        PersonServiceGrpc.PersonServiceBlockingStub personStub = PersonServiceGrpc.newBlockingStub(channel);

        boolean isRunning = true;
        while (isRunning)
        {
            Scanner scan = new Scanner(System.in);

            System.out.println("Name: ");
            String name = scan.next();

            System.out.println("CNP: ");
            String CNP = scan.next();
            Person.DataResponse data=personStub.getData((Person.DataRequest.newBuilder().setName(name).setCnp(CNP).build()));

        }
        channel.shutdown();
    }

}
