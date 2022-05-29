package service;

public class Date {

    public int month;
    public int day;
    public int year;

    public int getMonth(String date)
    {
        if (date.charAt(2) == '/')
        {
            String month = date.substring(0, 2);
            this.month = Integer.parseInt(month);
        }
        else
            if (date.charAt(1) == '/')
                 month = Integer.parseInt(date.substring(0, 1));
            else
                month = -1;
        return month;
    }

    public int getDay(String date)
    {
        if (date.charAt(2) == '/')
        {
            if (date.charAt(5) == '/')
            {
                String day = date.substring(3, 5);
                this.day = Integer.parseInt(day);
            } else
                if (date.charAt(4) == '/')
                     day = Integer.parseInt(date.substring(3, 4));
                else
                     day = -1;
        } else
            if (date.charAt(1) == '/')
            {
                if (date.charAt(3) == '/')
                 {
                      String stringDay = date.substring(2, 3);
                     day = Integer.parseInt(stringDay);
                 }
                else
                 if (date.charAt(4) == '/')
                     day = Integer.parseInt(date.substring(2, 4));
                 else
                     day = -1;
            }
            else
                 day = -1;
        return day;
    }
}
