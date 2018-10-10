package com.sethkeadle.fml.MVC;

import android.content.Context;
import android.widget.Toast;

import com.sethkeadle.fml.Clock.AnalogClock_Wrapper;
import com.sethkeadle.fml.Clock.DigitalClock;
import com.sethkeadle.fml.Clock.TimeKeeper_Model;

import java.util.Calendar;

public class ClockCtrl {

    private static ClockCtrl controller_Instance = null;

    private Context context;
    private ClockModel clockModel;

    public static ClockCtrl getController_Instance(Context context) {
        if (controller_Instance == null) {
            controller_Instance = new ClockCtrl(context);
        }
        return controller_Instance;
    }

    private ClockCtrl(Context context) {
        this.context = context;
        clockModel = new ClockModel(context);
        tickToc();
    }

    public ClockView addClock(int clockType) {
        if (clockType == 0) {
            AnalogClock_Wrapper temp = new AnalogClock_Wrapper(context);
            temp.updateTime(clockModel.getCal());
            return temp;
        }
//        else if (clockType == 1) {
//            DigitalClock temp = new DigitalClock(context);
//            temp.updateTime(clockModel.getCal());
//        }
        else
            return null;
    }

    public void changeCal(Calendar cal) {
        clockModel.setCalendar(cal);
        //Toast.makeText(context, "cal changed", Toast.LENGTH_SHORT).show();
    }
    public Calendar getCalendar() {
        return clockModel.getCal();
    }

    private void tickToc() {

        new Thread(new Runnable() {
            Calendar cal = clockModel.getCal();
            @Override
            public void run() {
                try {
                    while (true) {

                        long begin = System.currentTimeMillis();
                        Thread.sleep(1000);
                        long end = System.currentTimeMillis();
                        long update = end - begin;
                        cal.setTimeInMillis(cal.getTime().getTime() + update);
                        changeCal(cal);
                    }
                } catch (InterruptedException e) {

                }

            }
        }).start();
    }
}
