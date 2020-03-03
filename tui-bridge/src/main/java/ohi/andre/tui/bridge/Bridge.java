package ohi.andre.tui.bridge;

import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import ohi.andre.tui.core.Core;

/*
This is the bridge between Termux and t-ui. Every command will pass through this class
 */
public class Bridge {
    public static Bridge instance;

    private final InputParser inputParser;

    private final ExecutorService workerThread = Executors.newSingleThreadExecutor();
    private Future<Boolean> tuiCommandAttempt;

    private Bridge() {
        inputParser = new InputParser();
    }

    public static synchronized Bridge getInstance() {
        if(instance == null) instance = new Bridge();
        return instance;
    }

    // accepts an incoming input from a TerminalSession
    public void input(TermuxSessionBridgeEnd bridgeEnd, String input) {
        inputParser.newInput(bridgeEnd, input);
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
                boolean isTuiCommand = Core.getInstance(bridgeEnd.sessionContext).tryCommand(command);
                if (!isTuiCommand) Bridge.this.sendBackToTermux(bridgeEnd, command);
                Log.d("andre", command);
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
