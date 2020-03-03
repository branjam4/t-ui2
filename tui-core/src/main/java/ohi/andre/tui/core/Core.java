package ohi.andre.tui.core;

import android.content.Context;

import ohi.andre.tui.commands.AbstractCommand;
import ohi.andre.tui.commands.CommandSet;

/*
This class serves as a gateway between Termux and t-ui. This is the tui-end of the Bridge.
All the operations done by Core are synchronous. Be careful
 */
public class Core {
    public static Core instance;

    private final Context context;
    private final CommandSet commandSet;

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

    public boolean tryCommand(String commandName) {
        AbstractCommand command = commandSet.get(commandName);
        if(command != null) {
            command.exec(context);
            return true;
        } else {
            return false;
        }
    }

    public void dispose() {
        commandSet.dispose(context);
    }
}
