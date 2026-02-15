package chatbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import commands.AddCommand;
import commands.DeleteCommand;
import commands.EchoCommand;
import commands.ExitCommand;
import commands.ListCommand;
import commands.RedoCommand;
import commands.SearchCommand;
import commands.UndoCommand;
import commands.UpdateCommand;
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
    public void parseUserInput_normalInput() {
        Parser parser = new Parser();
        assertTrue(parser.parseUserInput("bye") instanceof ExitCommand);
        assertTrue(parser.parseUserInput("list") instanceof ListCommand);
        assertTrue(parser.parseUserInput("mark 0") instanceof UpdateCommand);
        assertTrue(parser.parseUserInput("mark abab") instanceof UpdateCommand);
        assertTrue(parser.parseUserInput("unmark 1000") instanceof UpdateCommand);
        assertTrue(parser.parseUserInput("unmark abab") instanceof UpdateCommand);
        assertTrue(parser.parseUserInput("todo a") instanceof AddCommand);
        assertTrue(parser.parseUserInput("deadline a a a a") instanceof AddCommand);
        assertTrue(parser.parseUserInput("event     ") instanceof AddCommand);
        assertTrue(parser.parseUserInput("delete 099") instanceof DeleteCommand);
        assertTrue(parser.parseUserInput("search    ") instanceof SearchCommand);
        assertTrue(parser.parseUserInput("undo") instanceof UndoCommand);
        assertTrue(parser.parseUserInput("redo") instanceof RedoCommand);
        assertTrue(parser.parseUserInput("nonsense") instanceof EchoCommand);
        assertTrue(parser.parseUserInput(" ") instanceof EchoCommand);
    }

    @Test
    public void parseUserInput_fail() {
        Parser parser = new Parser();
        assertTrue(parser.parseUserInput("bye bye") instanceof EchoCommand);
        assertTrue(parser.parseUserInput("list ") instanceof EchoCommand);
        assertTrue(parser.parseUserInput("mark0") instanceof EchoCommand);
        assertTrue(parser.parseUserInput("Mark abab") instanceof EchoCommand);
        assertTrue(parser.parseUserInput("Unmark 1000") instanceof EchoCommand);
        assertTrue(parser.parseUserInput("Unmark abab") instanceof EchoCommand);
        assertTrue(parser.parseUserInput("todoa") instanceof EchoCommand);
        assertTrue(parser.parseUserInput("Deadline a a a a") instanceof EchoCommand);
        assertTrue(parser.parseUserInput("event") instanceof EchoCommand);
        assertTrue(parser.parseUserInput("remove 0") instanceof EchoCommand);
        assertTrue(parser.parseUserInput("search") instanceof EchoCommand);
        assertTrue(parser.parseUserInput("undo 1") instanceof EchoCommand);
        assertTrue(parser.parseUserInput("redo 2") instanceof EchoCommand);
    }

    @Test
    public void parseFileInput_normalInput() {
        try {
            Parser parser = new Parser();
            Task a = new ToDo("s");
            Task b = new Deadline("a","b");
            Task c = new Event("a","b","c");
            assertEquals(parser.parseFileInput(a.toString()), a);
            assertEquals(parser.parseFileInput(b.toString()), b);
            assertEquals(parser.parseFileInput(c.toString()), c);
        } catch (EmptyStringException e) {
            throw new RuntimeException("Empty task name");
        } catch (ParserException e) {
            throw new RuntimeException("Parse failed");
        }
    }

    @Test
    public void userInputToTask_normalInput() {
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
    public void userInputToTask_fail1() {
        try {
            Parser parser = new Parser();
            parser.userInputToTask("deadline b /by b /by b");
        }catch (ParserException e) {
            assertEquals("Try deadline A /by B.", e.getMessage());
        }
    }

    @Test
    public void userInputToTask_fail2() {
        try {
            Parser parser = new Parser();
            parser.userInputToTask("event c /to c /from c");
        }catch (ParserException e) {
            assertEquals("Try event A /from B /to C.", e.getMessage());
        }
    }

    @Test
    public void userInputToTask_fail3() {
        try {
            Parser parser = new Parser();
            parser.userInputToTask("todo      ");
        }catch (ParserException e) {
            assertEquals("The task needs a name.", e.getMessage());
        }
    }
}
