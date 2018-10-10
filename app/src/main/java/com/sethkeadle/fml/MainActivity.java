package com.sethkeadle.fml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sethkeadle.fml.Clock.DigitalClock;
import com.sethkeadle.fml.Command.CalendarCommand;
import com.sethkeadle.fml.MVC.ClockCtrl;
import com.sethkeadle.fml.MVC.ClockView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLayout;
    private ArrayList<ClockView> clockList;
    private ClockCtrl clockCtrl;
    private EditText timeEditText, dateEditText;

    //Command Classes
    private CalendarCommand calCMD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find the linear layout for clocks
        mLayout = findViewById(R.id.clockViewsLayout);

        //intialize vars
        clockList = new ArrayList<>();
        clockCtrl = ClockCtrl.getController_Instance(this);
        timeEditText = findViewById(R.id.timeText);
        dateEditText = findViewById(R.id.dateText);

        //Commaand intialize
        calCMD = new CalendarCommand(this);

        updateClocks();

    }

    public void addAnalogClock(View view) {
        clockList.add(clockCtrl.addClock(0));
        mLayout.addView(clockList.get(clockList.size()-1).getView());
    }

    public void addDigitalClock(View view) {
        DigitalClock dClock = new DigitalClock(this);
        mLayout.addView(dClock.getView());
    }

    public void changeTime(View view) throws ParseException {
        Calendar cal = Calendar.getInstance();
        String timeStr = timeEditText.getText().toString();
        DateFormat formatter = new SimpleDateFormat("kk:mm:ss");
        Date date = formatter.parse(timeStr);

        //regex verification
        String regEx = "^(([0]?[0-5][0-9]|[0-9]):([0-5][0-9]):([0-5][0-9]))$";
        if (timeStr.matches(regEx) && !timeStr.isEmpty())
        {
            cal.setTime(date);

            //push to command
            calCMD.execute(cal);

            updateClocks();
        }
        else
        {
            Toast.makeText(this, "Muse be 24 hour time format with correct ':' placement hh:mm:ss", Toast.LENGTH_LONG).show();
        }
    }

    public void undo(View view) {
        calCMD.undo();
        updateClocks();
    }

    public void Redo(View view) {
        calCMD.redo();
        updateClocks();
    }
    public void updateClocks() {
        for (ClockView c:clockList) {
            c.updateTime(clockCtrl.getCalendar());
        }
    }


}
