package com.sethkeadle.fml.MVC;

import android.content.Context;

import com.sethkeadle.fml.Clock.AnalogClock_Wrapper;
import com.sethkeadle.fml.Clock.TimeKeeper_Model;

import java.util.Calendar;

public class ClockModel {
    private Calendar cal;

    public ClockModel(Context context) {
        cal = Calendar.getInstance();
    }

    public void setCalendar(Calendar cal) {
        this.cal = cal;
    }
    public Calendar getCal() {
        return cal;
    }



}
