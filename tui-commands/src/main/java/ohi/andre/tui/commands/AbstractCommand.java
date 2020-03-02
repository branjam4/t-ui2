package ohi.andre.tui.commands;

import android.content.Context;

public interface AbstractCommand {
    String name();
    String exec(Context context);
    void dispose(Context context);
}
