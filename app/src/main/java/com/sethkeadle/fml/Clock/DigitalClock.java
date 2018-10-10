package com.sethkeadle.fml.Clock;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.sethkeadle.fml.MVC.ClockCtrl;
import com.sethkeadle.fml.MVC.ClockView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DigitalClock extends ClockView{
    private Context context;
    private TextView textView;
    private ClockCtrl clockCtrl;
    //private TimeKeeper_Model timeKeeper;


    public DigitalClock(Context context) {
        super(context);
        this.context = context;
        textView = new TextView(context);
        clockCtrl = ClockCtrl.getController_Instance(context);
    }
    public View getView() {
        configureView();
        return textView;
    }
    private void configureView(){
       Calendar cal = clockCtrl.getCalendar();
       cal.add(Calendar.DATE, 1);
       Date date = cal.getTime();
       SimpleDateFormat format1 = new SimpleDateFormat("hh-mm-ss");
       String date1 = format1.format(date);
       textView.setText(date1);
       //Toast.makeText(context, date1, Toast.LENGTH_LONG).show();
    }

    public View updateTime() {
        return getView();
    }
}
