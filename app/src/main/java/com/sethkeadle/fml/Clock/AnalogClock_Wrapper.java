package com.sethkeadle.fml.Clock;

import android.content.Context;
import android.view.View;

import com.sethkeadle.fml.MVC.ClockView;

import java.util.Calendar;

public class AnalogClock_Wrapper extends ClockView {
    private AnalogClock analogClock;
    public AnalogClock_Wrapper(Context context) {
        super(context);
        analogClock = new AnalogClock(context);
    }

    //returns the view
    @Override
    public View getView()
    {
        return analogClock;
    }

    //updates the time with a calander object
    public void updateTime(Calendar cal) {
        analogClock.setCalendar(cal);
    }
}
