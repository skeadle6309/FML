package com.sethkeadle.fml.Command;

import android.content.Context;

import com.sethkeadle.fml.Clock.TimeKeeper_Model;
import com.sethkeadle.fml.MVC.ClockCtrl;
import com.sethkeadle.fml.MVC.ClockView;

import java.util.Calendar;

public class CalendarCommand implements Commander {
    private ClockCommandQueue singleton = ClockCommandQueue.getInstance();
    private ClockCtrl clockCtrl;

    public CalendarCommand(Context context) {
        clockCtrl = ClockCtrl.getController_Instance(context);
    }

    @Override
    public void execute(Calendar cal) {
        if (cal == null) {
            clockCtrl.changeCal(Calendar.getInstance());
        }
        else
        {
            singleton.doneAdd(cal);
            clockCtrl.changeCal(cal);
        }

    }

    @Override
    public void undo() {
        execute(singleton.popDone());
    }

    @Override
    public void redo() {
        execute(singleton.popUndo());

    }
}
