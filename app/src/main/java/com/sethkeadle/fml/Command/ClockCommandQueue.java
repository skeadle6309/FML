package com.sethkeadle.fml.Command;

import java.util.Calendar;
import java.util.Stack;

public class ClockCommandQueue {
    private static ClockCommandQueue ourInstance = null;

    public static ClockCommandQueue getInstance() {

        if (ourInstance == null)
        {
            ourInstance = new ClockCommandQueue();
        }
        return ourInstance;
    }

    private Stack<Calendar> done;
    private Stack<Calendar> undone;

    private ClockCommandQueue() {
        done = new Stack<>();
        undone = new Stack<>();
    }

    //when user submits a time change
    public void doneAdd(Calendar cal) {
        done.add(cal);
    }


    public void undoAdd(Calendar cal) {
        undone.add(cal);
    }

    //when user presses undo takes the last executed command on done and retruns that command is now
    //the top command on the stack
    public Calendar popDone() {
        if (done.empty()){
            Calendar cal = null;
            return cal;
        }
        else if (done.size() == 1){
            Calendar cal = done.pop();
            undone.add(cal);
            cal = null;
            return cal;
        }
        {
            Calendar cal = done.pop();
            undone.add(cal);
            return done.pop();
        }

    }

    //when user presses redo
    public Calendar popUndo() {
        if (undone.empty())
        {
            Calendar cal = done.peek();
            return cal;
        }
        else
        {
            Calendar cal = undone.pop();
            return (cal);
        }

    }


}
