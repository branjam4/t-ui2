package ohi.andre.tui.core;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertNotNull;

public class CoreTest {
    Core core;
    @Mock
    Context mockContext;

    @Before
    public void initialize() {
        core = Core.getInstance(mockContext);
    }

    @Test
    public void testRandomCommand() {
        assertNotNull(core.createTuiRunnable("flash"));
        assertNotNull(core.createTuiRunnable("uninstall"));
    }
}
