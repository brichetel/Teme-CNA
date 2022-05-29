package service;

import io.grpc.stub.StreamObserver;
import proto.Person;
import proto.PersonServiceGrpc;

import java.util.Calendar;

public class PersonData extends PersonServiceGrpc.PersonServiceImplBase
{
    public static int getYear(String cnp)
    {
        String year=null;
        for(int i=0;i<cnp.length();i++)
        {
           if( cnp.charAt(i)=='1'|| cnp.charAt(i)=='2')
           {
               year = "19";
               break;
           }
            if( cnp.charAt(i)=='5'|| cnp.charAt(i)=='6')
            {
                year = "20";
                break;
            }
        }
        StringBuilder y = new StringBuilder();
        y.append(year + cnp.substring(1, 3));
        int yearInt = Integer.parseInt(String.valueOf(y));
        return yearInt;
    }

    public static String calculateAge(String cnp)
    {

        int actualYear= Calendar.getInstance().get(Calendar.YEAR);
       int dif=actualYear-getYear(cnp);
        String age=String.valueOf(dif);
        return age;
    }

    public static String calculateGender(String cnp)
    {
        String gender =null;
        for(int i=0;i<cnp.length();i++)
        {
            if( cnp.charAt(i)=='1'|| cnp.charAt(i)=='3'||cnp.charAt(i)=='5')
            {
                gender = "Male";
                break;
            }
            if( cnp.charAt(i)=='2'||cnp.charAt(i)=='4'|| cnp.charAt(i)=='6')
            {
                gender = "Female";
                break;
            }
        }
        return gender;

    }
    @Override
    public void getData(Person.DataRequest request, StreamObserver<Person.DataResponse> responseObserver)
    {
        Person.DataResponse response=Person.DataResponse.newBuilder().setName((request.getName()))
                .setGender(calculateGender(request.getCnp())).setAge(calculateAge(request.getCnp())).build();
        System.out.println("Name: " + request.getName() + ", gender: " + response.getGender() +
                ", age: " + response.getAge());
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
