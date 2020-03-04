package ohi.andre.tui.core;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
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
