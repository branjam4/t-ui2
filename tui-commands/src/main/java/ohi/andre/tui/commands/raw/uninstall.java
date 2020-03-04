package ohi.andre.tui.commands.raw;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import ohi.andre.tui.commands.AbstractCommand;
import ohi.andre.tui.commands.parameters.ApplicationParameter;
import ohi.andre.tui.commands.parameters.Parameter;

public class uninstall implements AbstractCommand {
    @Override
    public String name() {
        return "uninstall";
    }

    @Override
    public String exec(Context context, Parameter[] parameters) {
        context.startActivity(new Intent(Intent.ACTION_UNINSTALL_PACKAGE, Uri.parse("package:" + parameters[0].content)));
        return null;
    }

    @Override
    public void dispose(Context context) {}

    Parameter[] parameters = {ApplicationParameter.representative};
    @Override
    public Parameter[] parameters() {
        return parameters;
    }
}
