package ohi.andre.tui.bridge;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InputParserTest {
    InputParser parser;
    String s1, s2;

    @Before
    public void prepareParser() {
        parser = new InputParser();

        s1 = "test1";
        s2 = "test2";

        parser.newInput(s1,s1);
        parser.newInput(s2,s2);
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
