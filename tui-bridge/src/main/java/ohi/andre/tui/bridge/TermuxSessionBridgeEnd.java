package ohi.andre.tui.bridge;

import android.content.Context;

/*
One instance for each session
 */
public abstract class TermuxSessionBridgeEnd {
    public final Context sessionContext;

    public TermuxSessionBridgeEnd(Context context) {
        this.sessionContext = context;
    }

    public abstract void sendBackCommand(String command);
}
