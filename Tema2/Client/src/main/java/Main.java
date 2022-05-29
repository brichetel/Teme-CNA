import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import proto.ZodiacGrpc;
import proto.ZodiacOuterClass;

public class Main {
    public String dateOfBirth;
    public static Boolean Verification(String dateOfBirth)
    {
        String formatString = "MM/dd/yyyy";

        try {
            SimpleDateFormat format = new SimpleDateFormat(formatString);
            format.setLenient(false);
            format.parse(dateOfBirth);
        } catch (ParseException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }

        return true;
    }
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",
                8999).usePlaintext().build();
        ZodiacGrpc.ZodiacBlockingStub zodiacStub = ZodiacGrpc.newBlockingStub(channel);
        Scanner scanner = new Scanner(System.in);
        boolean isRunning=true;
        while(isRunning)
        {
            System.out.println("Insert the date: ");
            String dateOfBirth=scanner.next();
            if(Verification(dateOfBirth)==true)
            {
                ZodiacOuterClass.DataResponse date =
                        zodiacStub.getData(ZodiacOuterClass.DataRequest.newBuilder().setDate(dateOfBirth).build());
                System.out.println("Zodiac Sign: "+date.getZodiacSign());
                System.out.println();
            }
            else
                System.out.println("Wrong date format!");
        }
    }
}
