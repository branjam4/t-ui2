package ohi.andre.tui.core.apps;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import androidx.collection.ArraySet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ApplicationsList {

    private static ApplicationsList instance;

    // this method shouldn't run on the UI thread
    public ApplicationsList getInstance(Context context) {
        if(instance == null) {
            instance = new ApplicationsList();
            instance.gatherApps(context);
        }

        return instance;
    }

    private ApplicationsList() {}

    private Set<AppInfo> applications;

    // a string representing the installed applications.
    // remember to invalidate this value when the map changes.
    private String cachedList;

    // this method shouldn't run on the UI thread
    private void gatherApps(Context context) {
        cachedList = null;
        applications = new ArraySet<>();

        final PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
            AppInfo info = new AppInfo(packageInfo.loadLabel(pm).toString(), packageInfo.packageName);
            applications.add(info);
        }
    }

    public Set<AppInfo> getApplications() {
        return Collections.unmodifiableSet(applications);
    }

    // alphabetic order, capital letter divider
    public String listApps() {
        if(cachedList == null) {
            List<AppInfo> list = new ArrayList<>(applications);
            Collections.sort(list, new Comparator<AppInfo>() {
                @Override
                public int compare(AppInfo o1, AppInfo o2) {
                    return o1.label.toLowerCase().compareTo(o2.label.toLowerCase());
                }
            });

            cachedList = insertDividers(list);
        }

        return cachedList;
    }

    private String insertDividers(List<AppInfo> appList) {
        if(appList == null || appList.size() == 0) return null;

        StringBuilder builder = new StringBuilder();

        char lastChar = leadingChar(appList.get(0));
        for(AppInfo app : appList) {
            char c = leadingChar(app);
            if(lastChar != c) {
                lastChar = c;

                builder.append(c).append("\n");
                builder.append(app.label);
            } else {
                builder.append(", ").append(app.label);
            }
        }

        return builder.toString().trim();
    }

    // Settings -> S
    // mario kart -> M
    private char leadingChar(AppInfo app) {
        return app.label.toUpperCase().charAt(0);
    }
}
