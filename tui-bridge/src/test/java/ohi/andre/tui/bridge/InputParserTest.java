package ohi.andre.tui.bridge;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ohi.andre.tui.commands.CommandSet;
import ohi.andre.tui.core.Core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InputParserTest {
    InputParser parser;
    String s1, s2;

    @Mock
    Core mockCore;

    @Mock
    CommandSet mockSet;

    @Before
    public void prepareParser() {
        when(mockCore.getCommandSet()).thenReturn(mockSet);

        parser = new InputParser();

        s1 = "test1";
        s2 = "test2";

        parser.newInput(mockCore, s1,s1);
        parser.newInput(mockCore, s2,s2);
    }

    @Test
    public void readCommandResets() {
        parser.get(s1);
        assertNull(parser.get(s1));
    }

    @Test
    public void correctStringForKey() {
        assertEquals(parser.get(s2), s2);
    }
}
