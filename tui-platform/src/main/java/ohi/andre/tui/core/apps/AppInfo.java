package ohi.andre.tui.core.apps;

import androidx.annotation.NonNull;

public class AppInfo {
    public final String label, packageName;

    public AppInfo(String label, String packageName) {
        this.label = label;
        this.packageName = packageName;
    }

    @NonNull
    @Override
    public String toString() {
        return label + "-" + packageName;
    }
}
