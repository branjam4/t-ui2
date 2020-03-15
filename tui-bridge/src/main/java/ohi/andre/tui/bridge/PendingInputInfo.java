package ohi.andre.tui.bridge;

import ohi.andre.tui.commands.AbstractCommand;
import ohi.andre.tui.commands.parameters.Parameter;

/*
Retrieves info about what's inside the input field at the moment (a command, an app-name, ...).
This will be used to deliver Suggestions
 */
public class PendingInputInfo {
    public static class CommandType {
        public static int APPLICATION = 10;

        // PendingInputInfo will contain a CommandPack which describes the command and the current state of parameters
        public static int TUI_COMMAND = 11;
        public static int ALIAS = 12;
    }

    public final int currentCommandType;

    // contains the content associated with the current command (i.e. an AbstractCommand)
    public final Object content;

    // contains the current complete parameters in the input
    private final Parameter[] parameters;

    // contains what the user has typed for the next parameter so far
    public final String inputLeftovers;

    public PendingInputInfo(int currentCommandType, Object content, Parameter[] parameters, String inputLeftovers) {
        this.currentCommandType = currentCommandType;
        this.content = content;
        this.parameters = parameters;
        this.inputLeftovers = inputLeftovers;
    }

    public int getCurrentCommandType() {
        return currentCommandType;
    }

    public Object getContent() {
        return content;
    }

    public Parameter getParameter(int i) {
        return parameters[i];
    }

    public int getParametersSize() {
        return parameters.length;
    }

    // return the next parameter wanted by the current command
    public Parameter nextParameter() {
        if(currentCommandType != CommandType.TUI_COMMAND) return null;
        else {
            AbstractCommand tuiCommand = (AbstractCommand) content;
            Parameter[] commandParameters = tuiCommand.parameters();

            if(getParametersSize() >= commandParameters.length) return null;
            else return commandParameters[getParametersSize()];
        }
    }
}
