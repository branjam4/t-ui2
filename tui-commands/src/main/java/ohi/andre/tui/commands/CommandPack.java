package ohi.andre.tui.commands;

import ohi.andre.tui.commands.parameters.Parameter;

public class CommandPack {
    public final AbstractCommand tuiCommand;
    private final Parameter[] parameters;

    public int getParametersLength() {
        return parameters == null ? 0 : parameters.length;
    }

    public Parameter getParameter(int i) {
        return parameters[i];
    }

    public CommandPack(AbstractCommand tuiCommand, Parameter[] parameters) {
        this.tuiCommand = tuiCommand;
        this.parameters = parameters;
    }
}
