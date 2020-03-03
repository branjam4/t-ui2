package ohi.andre.tui.commands;

import android.content.Context;

import java.util.HashMap;

import ohi.andre.tui.commands.raw.flash;

public class CommandSet {
    private HashMap<String, AbstractCommand> commandSet;

    public CommandSet() {
        commandSet = new HashMap<>();

        // fill the set
        add(new flash());
    }

    private void add(AbstractCommand command) {
        commandSet.put(command.name(), command);
    }

    public AbstractCommand get(String commandName) {
        return commandSet.get(commandName);
    }

    public void dispose(Context context) {
        for (AbstractCommand command : commandSet.values()) {
            command.dispose(context);
        }
    }
}
