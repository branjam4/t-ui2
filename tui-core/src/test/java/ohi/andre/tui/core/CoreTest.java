package ohi.andre.tui.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class CoreTest {
    Core core;

    @Before
    public void initialize() {
        core = Core.getInstance(null);
    }

    @Test
    public void testRandomCommand() {
        assertNotNull(core.createTuiRunnable("flash"));
        assertNotNull(core.createTuiRunnable("uninstall"))
    }
}
