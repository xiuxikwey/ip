package chatbot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tasks.Deadline;
import tasks.EmptyStringException;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * Tests for Parser.
 * 
 * Note Parser.parseFileInput() depends on toString() of tasks.
 */
public class ParserTest {

    @Test
    public void parseFileInput_normalInput() {
        try {
            Task a = new ToDo("s");
            Task b = new Deadline("a","b");
            Task c = new Event("a","b","c");
            assertEquals(Parser.parseFileInput(a.toString()), a);
            assertEquals(Parser.parseFileInput(b.toString()), b);
            assertEquals(Parser.parseFileInput(c.toString()), c);
        } catch (EmptyStringException e) {
            throw new RuntimeException("Empty task name");
        } catch (ParserException e) {
            throw new RuntimeException("Parse failed");
        }
    }

    @Test
    public void parseFileInput_malformedInput() {
        try {
            Parser.parseFileInput("[?][ ] s");
        }catch (ParserException e) {
            assertEquals("Storage parse failed.", e.getMessage());
        }
    }
}
