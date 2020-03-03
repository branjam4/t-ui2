package ohi.andre.tui.commands.parameters;

import java.util.List;

public class ApplicationParameter extends Parameter {
    public ApplicationParameter(Object content) {
        super(content);
    }

    // todo: create an ApplicationInfo class
    public static ApplicationParameter fromString(List<Object> appList) {
        return new ApplicationParameter(null);
    }
}
