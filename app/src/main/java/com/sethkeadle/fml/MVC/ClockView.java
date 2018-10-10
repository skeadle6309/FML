package com.sethkeadle.fml.MVC;

import android.content.Context;
import android.provider.CalendarContract;
import android.view.View;

import java.util.Calendar;

public abstract class ClockView {

    private Context context;

    public ClockView(Context context) {
        this.context = context;
    }
    public View getView(){
        return null;
    }
    public void updateTime(Calendar cal) {

    }

}
