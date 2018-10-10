package com.sethkeadle.fml.Clock;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.widget.Toast;

import com.sethkeadle.fml.MVC.ClockCtrl;

import java.util.Calendar;
import java.util.Date;

public class TimeKeeper_Model extends Thread{

    private static TimeKeeper_Model timeKeeper_Model_instance = null;

    private int second, minute, hour, day, month, year;
    private Calendar cal;
    private ClockCtrl clockController;
    private Context context;
    public Thread thread;


    private TimeKeeper_Model(Context context) {
        this.context = context;
        long timestamp = new Date().getTime();
        clockController = ClockCtrl.getController_Instance(context);
        cal = clockController.getCalendar();
        cal.setTimeInMillis(timestamp);
        second = cal.get(Calendar.SECOND);
        minute = cal.get(Calendar.MINUTE);
        hour = cal.get(Calendar.HOUR);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        timeThread();
    }

    public static TimeKeeper_Model getInstance(Context context)
    {
        if (timeKeeper_Model_instance == null) {
            timeKeeper_Model_instance = new TimeKeeper_Model(context);
        }
        return timeKeeper_Model_instance;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public Calendar getCal() {
        cal.set(year,month,day,hour,minute,second);
        return cal;
    }

    public void setCal(Calendar cal) {
        this.cal = cal;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    private void timeUpdater() {
        this.second+=1;
        if (second %60 == 0) {
            this.second=0;
            this.minute +=1;
        }
        if (minute % 60 == 0) {
            this.minute = 0;
            this.hour =+1;
        }
        if (hour % 24 == 0) {
            this.hour = 0;
            this.day =+ 1;
        }
        if (month % 28 == 0 && month == 2) {
            this.month = 0;
            this.year =+ 1;
        }
        else if (month % 30 == 0 && (month == 4 || month == 5 || month == 9 || month == 11)) {
            this.month = +1;
        }
        else if (month % 31 == 0 && (month == 1 || month == 3 || month == 6 || month == 7 || month == 8 || month == 10)) {
            this.month = +1;
        }
        else if (month % 31 == 0 && month == 12)
        {
            this.month = 0;
            this.year =+ 1;
        }
    }

    private void timeThread() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(1000);
                        timeUpdater();
                        clockController.changeCal(getCal());
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
