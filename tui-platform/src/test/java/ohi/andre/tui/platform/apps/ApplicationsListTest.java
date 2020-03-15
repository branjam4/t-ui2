package ohi.andre.tui.platform.apps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ApplicationsListTest {
    @Test
    public void test_leadingChar() {
        assertEquals('B', ApplicationsList.leadingChar(new AppInfo("busioskk", null)));
        assertEquals('_', ApplicationsList.leadingChar(new AppInfo("_busioskk", null)));
    }

    public void test_insertDividers() {
        List<AppInfo> info = new ArrayList<>();
        info.add(new AppInfo("aereo", null));

        assertEquals("A\naereo", ApplicationsList.insertDividers(info));
    }
}