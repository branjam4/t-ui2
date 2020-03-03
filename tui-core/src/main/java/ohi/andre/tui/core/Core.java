package ohi.andre.tui.core;

import android.content.Context;

import ohi.andre.tui.commands.CommandPack;
import ohi.andre.tui.commands.CommandSet;

/*
This is the tui-end of the Bridge.
All the operations done by Core are synchronous. Be careful
 */
public class Core {
    private static Core instance;

    private final Context context;
    public final CommandSet commandSet;

    private Core(Context context) {
        this.context = context;
        this.commandSet = new CommandSet();
    }

    public static synchronized Core getInstance(Context context) {
        if(instance == null) instance = new Core(context);
        return instance;
    }

    // this is only to be used by TermuxActivity, it may return a null reference
    public static synchronized Core getInstance() {
        return instance;
    }

    public Runnable createTuiRunnable(String command) {
        final CommandPack tuiCommandPack = commandSet.buildCommandPack(command);
        if(tuiCommandPack != null) {
            return new Runnable() {
                @Override
                public void run() {
                    tuiCommandPack.tuiCommand.exec(context, tuiCommandPack.parameters);
                }
            };
        } else {
            // todo: apps, alias, ...
            return null;
        }
    }

    public void dispose() {
        commandSet.dispose(context);
    }
}
