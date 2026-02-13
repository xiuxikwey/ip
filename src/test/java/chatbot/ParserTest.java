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
            Parser parser = new Parser();
            Task a = new ToDo("s");
            Task b = new Deadline("a","b");
            Task c = new Event("a","b","c");
            assertEquals(parser.fileInputToTask(a.toString()), a);
            assertEquals(parser.fileInputToTask(b.toString()), b);
            assertEquals(parser.fileInputToTask(c.toString()), c);
        } catch (EmptyStringException e) {
            throw new RuntimeException("Empty task name");
        } catch (ParserException e) {
            throw new RuntimeException("Parse failed");
        }
    }

    @Test
    public void userToTaskRead() {
        try {
            Parser parser = new Parser();
            Task a = new ToDo("a");
            Task b = new Deadline("b","b");
            Task c = new Event("c","c","c");
            assertEquals(parser.userInputToTask("todo a"), a);
            assertEquals(parser.userInputToTask("deadline b /by b"), b);
            assertEquals(parser.userInputToTask("event c /from c /to c"), c);
        } catch (EmptyStringException e) {
            throw new RuntimeException("Empty task name");
        } catch (ParserException e) {
            throw new RuntimeException("Parse failed");
        }
    }

    @Test
    public void userToTaskFail1() {
        try {
            Parser parser = new Parser();
            parser.userInputToTask("deadline b /by b /by b");
        }catch (ParserException e) {
            assertEquals("Try deadline A /by B.", e.getMessage());
        }
    }

    @Test
    public void userToTaskFail2() {
        try {
            Parser parser = new Parser();
            parser.userInputToTask("event c /to c /from c");
        }catch (ParserException e) {
            assertEquals("Try event A /from B /to C.", e.getMessage());
        }
    }
}
