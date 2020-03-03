package ohi.andre.tui.bridge;

import java.util.ArrayList;
import java.util.List;

// This class holds input coming from different TerminalSession(s) (hence the CustomMap holding references to TerminalSession).
public class InputParser {
    // Store pending inputs for each TerminalSession alive
    private CustomMap inputStorage;

    public InputParser() {
        inputStorage = new CustomMap();
    }

    // Called every time a new input arrives to TerminalSession. We need to update the corresponding pending input
    public void newInput(Object key, String newInput) {
        String pendingInput = (String) inputStorage.get(key);
        if(pendingInput == null) pendingInput = newInput;
        else pendingInput = pendingInput + newInput;

        inputStorage.set(key, pendingInput);
    }

    // whenever you request a command, it's deleted
    public String get(Object key) {
        return (String) inputStorage.remove(key);
    }
}

/*
A very simple Map which evaluates keys using "==" (the only way to compare two TerminalSession objects).
I'm not using generics to ensure that keys are an instance of TerminalSession because I would need to add a dependency tui-bridge -> terminal-emulator. We don't need this.
 */
class CustomMap {
    List keys;
    List values;

    public CustomMap(List keys, List values) throws UnsupportedOperationException {
        if(keys == null || values == null || keys.size() != values.size()) throw new UnsupportedOperationException("CustomMap got invalid initial arguments");

        this.keys = new ArrayList(keys);
        this.values = new ArrayList(values);
    }

    public CustomMap() {
        keys = new ArrayList<>();
        values = new ArrayList<>();
    }

    // we're comparing keys using "=="
    public Object get(Object key) {
        for(int i = 0; i < keys.size(); i++) {
            if(keys.get(i) == key) return values.get(i);
        }
        return null;
    }

    // this method will be always successful. we add the couple if the key doesn't exist
    public void set(Object key, Object value) {
        for(int i = 0; i < keys.size(); i++) {
            if(keys.get(i) == key) {
                values.set(i, value);
                return;
            }
        }

        // if there isn't such a key, add it
        add(key, value);
    }

    public void add(Object key, Object value) {
        keys.add(key);
        values.add(value);
    }

    public Object remove(Object key) {
        for(int i = 0; i < keys.size(); i++) {
            if(keys.get(i) == key) {
                keys.remove(i);
                return values.remove(i);
            }
        }
        return null;
    }
}