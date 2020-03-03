package ohi.andre.tui.commands;

import android.content.Context;

import ohi.andre.tui.commands.parameters.Parameter;

public interface AbstractCommand {
    String name();
    String exec(Context context, Parameter[] parameters);
    void dispose(Context context);
    Parameter[] parameters();
}
