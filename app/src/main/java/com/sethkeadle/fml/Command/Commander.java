package com.sethkeadle.fml.Command;

import java.nio.channels.CancelledKeyException;
import java.util.Calendar;

public interface Commander {
    public void execute(Calendar cal);
    public void undo();
    public void redo();
}
