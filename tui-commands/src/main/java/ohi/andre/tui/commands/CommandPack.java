package ohi.andre.tui.commands;

import ohi.andre.tui.commands.parameters.Parameter;

public class CommandPack {
    public AbstractCommand tuiCommand;
    public Parameter[] parameters;

    public CommandPack(AbstractCommand tuiCommand, Parameter[] parameters) {
        this.tuiCommand = tuiCommand;
        this.parameters = parameters;
    }
}
