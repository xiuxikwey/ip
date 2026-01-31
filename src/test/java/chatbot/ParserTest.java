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
 * Note Parser.fileToTask depends on toString() of tasks.
 */
public class ParserTest {
    
    @Test
    public void fileToTaskRead() {
        try {
            Task a = new ToDo("s");
            Task b = new Deadline("a","b");
            Task c = new Event("a","b","c");
            assertEquals(Parser.fileToTask(a.toString()), a);
            assertEquals(Parser.fileToTask(b.toString()), b);
            assertEquals(Parser.fileToTask(c.toString()), c);
        } catch (EmptyStringException e) {
            throw new RuntimeException("Empty task name");
        } catch (ParserException e) {
            throw new RuntimeException("Parse failed");
        }
    }

    @Test
    public void userToTaskRead() {
        try {
            Task a = new ToDo("a");
            Task b = new Deadline("b","b");
            Task c = new Event("c","c","c");
            assertEquals(Parser.userToTask("todo a"), a);
            assertEquals(Parser.userToTask("deadline b /by b"), b);
            assertEquals(Parser.userToTask("event c /from c /to c"), c);
        } catch (EmptyStringException e) {
            throw new RuntimeException("Empty task name");
        } catch (ParserException e) {
            throw new RuntimeException("Parse failed");
        }
    }

    @Test
    public void userToTaskFail1() {
        try {
            Parser.userToTask("deadline b /by b /by b");
        }catch (ParserException e) {
            assertEquals("Try deadline A /by B.", e.getMessage());
        }
    }

    @Test
    public void userToTaskFail2() {
        try {
            Parser.userToTask("event c /to c /from c");
        }catch (ParserException e) {
            assertEquals("Try event A /from B /to C.", e.getMessage());
        }
    }
}
