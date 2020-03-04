package ohi.andre.tui.bridge;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import ohi.andre.tui.core.Core;

/*
This is the bridge between Termux and t-ui. Every command will pass through this class
 */
public class Bridge {
    public static Bridge instance;

    private final Core tuiCore;

    private final InputParser inputParser;

    private final ExecutorService workerThread = Executors.newSingleThreadExecutor();
    private Future<Boolean> tuiCommandAttempt;

    private Bridge(Context context) {
        inputParser = new InputParser();
        tuiCore = Core.getInstance(context);
    }

    public static synchronized Bridge getInstance(Context context) {
        if(instance == null) instance = new Bridge(context);
        return instance;
    }

    // accepts an incoming input from a TerminalSession
    public PendingInputInfo input(TermuxSessionBridgeEnd bridgeEnd, String input) {
        // todo: bring the analysis of the pending input to another thread
        return inputParser.newInput(tuiCore, bridgeEnd, input);
    }

    // accepts an incoming newline from a TerminalSession, and decides if the command will be managed by t-ui or by the shell
    public void newline(TermuxSessionBridgeEnd bridgeEnd) {
        String command = inputParser.get(bridgeEnd);
        attemptTuiCommand(bridgeEnd, command);
    }

    /*
     tries to execute command as a t-ui command (apps, alias, tui-command, ..)
     this is an asynchronous method
     */
    private void attemptTuiCommand(final TermuxSessionBridgeEnd bridgeEnd, final String command) {
        if(tuiCommandAttempt != null) tuiCommandAttempt.cancel(true);

        tuiCommandAttempt = workerThread.submit(new Runnable() {
            @Override
            public void run() {
                Runnable runnableCommand = tuiCore.createTuiRunnable(command);
                if (runnableCommand == null) Bridge.this.sendBackToTermux(bridgeEnd, command);
                else runnableCommand.run();
            }
        }, null);
    }

    /*
     send back the command to termux
     */
    private void sendBackToTermux(TermuxSessionBridgeEnd bridgeEnd, String command) {
        bridgeEnd.sendBackCommand(command);
    }
}
