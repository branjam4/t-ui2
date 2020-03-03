package ohi.andre.tui.commands.raw;

import android.content.Context;

import ohi.andre.tui.commands.AbstractCommand;

public class uninstall implements AbstractCommand {
    @Override
    public String name() {
        return "uninstall";
    }

    @Override
    public String exec(Context context) {
        return null;
    }

    @Override
    public void dispose(Context context) {}
}
