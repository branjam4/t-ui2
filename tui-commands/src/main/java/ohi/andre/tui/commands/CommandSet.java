package ohi.andre.tui.commands;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Iterator;

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

    /*
    return:
    1 - a CommandPack
    2 - input leftovers
     */
    public Object[] buildCommandPack(String input) {
        final String[] split = input.split(" ");
        AbstractCommand tuiCommand = get(split[0]);
        if(tuiCommand == null) return null;
        else {
            // todo: parse parameters
            return new Object[] {
                    new CommandPack(tuiCommand, null),
                    TextUtils.join(" ", new Iterable<String>() {
                        @NonNull
                        @Override
                        public Iterator<String> iterator() {
                            return new ArrayIterator<String>(split, 2);
                        }
                    })
            };
        }
    }

    private class ArrayIterator<T> implements Iterator<T> {
        private int index;
        private final T[] array;

        public ArrayIterator(T[] array, int startingIndex) {
            this.array = array;
            this.index = startingIndex;
        }

        @Override
        public boolean hasNext() {
            return array != null && index < array.length;
        }

        @Override
        public T next() {
            return array[index++];
        }
    }
}
