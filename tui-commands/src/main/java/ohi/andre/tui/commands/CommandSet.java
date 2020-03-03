package ohi.andre.tui.commands;

import android.content.Context;

import java.util.HashMap;

import ohi.andre.tui.commands.raw.flash;
import ohi.andre.tui.commands.raw.uninstall;

public class CommandSet {
    private HashMap<String, AbstractCommand> commandSet;

    public CommandSet() {
        commandSet = new HashMap<>();

        // fill the set
        add(new flash());
        add(new uninstall());
    }

    private void add(AbstractCommand command) {
        commandSet.put(command.name(), command);
    }

    private AbstractCommand get(String commandName) {
        return commandSet.get(commandName);
    }

    public void dispose(Context context) {
        for (AbstractCommand command : commandSet.values()) {
            command.dispose(context);
        }
    }

    public CommandPack buildCommandPack(String command) {
        String[] split = command.split(" ");
        AbstractCommand tuiCommand = get(split[0]);
        if(tuiCommand == null) return null;

        // todo: parse parameters
        return new CommandPack(tuiCommand, null);
    }
}
