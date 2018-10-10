package com.sethkeadle.fml.Clock;

import android.content.Context;

import com.turki.vectoranalogclockview.VectorAnalogClock;

public class AnalogClock extends VectorAnalogClock {
    public AnalogClock(Context ctx) {
        super(ctx);
        init();
    }
    private void init(){

        initializeSimple();
    }
}
