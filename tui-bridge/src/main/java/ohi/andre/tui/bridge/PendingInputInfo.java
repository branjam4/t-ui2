package ohi.andre.tui.bridge;

/*
Retrieves info about what's inside the input field at the moment (a command, an app-name, ...).
This will be used to deliver Suggestions
 */
public class PendingInputInfo {
    public static class CommandType {
        public static int APPLICATION = 10;
        public static int TUI_COMMAND = 11;
        public static int ALIAS = 12;
    }

    private int currentCommandType;
    // contains the content associated with the current command (i.e. an AbstractCommand)
    private Object content;

    public PendingInputInfo(int currentCommandType, Object content) {
        this.currentCommandType = currentCommandType;
        this.content = content;
    }

    public int getCurrentCommandType() {
        return currentCommandType;
    }

    public Object getContent() {
        return content;
    }
}
