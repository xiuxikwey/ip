package chatbot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tasks.EmptyStringException;
import tasks.Task;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;

/**
 * Tests fileToTask from Parser
 * 
 * Note Parser.fileToTask depends on toString() of tasks
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
    public void fileToTaskFail() {
        try {
            Task a = new ToDo("s");
            String malformed = a.toString().substring(1);
            Parser.fileToTask(malformed);
        } catch (EmptyStringException e) {
            throw new RuntimeException("Empty task name");
        } catch (ParserException e) {
            assertEquals("Not a type of task", e.getMessage());;
        }
    }
}
