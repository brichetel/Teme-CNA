package service;
import io.grpc.stub.StreamObserver;
import proto.ZodiacGrpc;
import proto.ZodiacOuterClass;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ZodiacSign extends ZodiacGrpc.ZodiacImplBase {

    public ArrayList<ZodiacList> list=new ArrayList<ZodiacList>();
    public Date date=new Date();

    public void ReadFromFile()
    {
        try
        {
            File fin=new File("C:\\Users\\opsid\\Desktop\\CNA Teme\\Tema2\\Server\\src\\main\\resources\\Intervals.txt");
            Scanner read= new Scanner(fin);
            while(read.hasNext())
            {
                String zodiac=read.next();
                int monthStart=read.nextInt();
                int dayStart=read.nextInt();
                int monthEnd=read.nextInt();
                int dayEnd= read.nextInt();
                ZodiacList object=new ZodiacList(zodiac, monthStart, dayStart,monthEnd, dayEnd);
                list.add(object);
            }
            read.close();
        }
        catch(IOException exception)
        {
            System.out.println("File error! ");
        }
    }
    public String getSign(String dateOfBirth)
    {
        String zodiac="z";
        int month = date.getMonth(dateOfBirth);
        int day = date.getDay(dateOfBirth);
        ReadFromFile();
        for (int i=0; i< list.size(); i++)
        {
            ZodiacList sign=list.get(i);
           if((month==sign.getMonthStart() && day>= sign.getDayStart())
                   ||(month==sign.getMonthEnd() && day<=sign.getDayEnd()))
               return sign.getZodiac();
        }
        return "null";
    }

    @Override
    public void getData(ZodiacOuterClass.DataRequest request,
                        StreamObserver<ZodiacOuterClass.DataResponse> responseObserver)
    {
        ZodiacOuterClass.DataResponse response = ZodiacOuterClass.DataResponse.newBuilder().
                setZodiacSign(getSign(request.getDateOfBirth())).build();
        System.out.println("Date introduced: " + request.getDateOfBirth());
        System.out.println("Zodiac Sign returned: "+response.getZodiacSign());

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


}
