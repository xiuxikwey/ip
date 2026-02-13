package chatbot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import tasks.Deadline;
import tasks.EmptyStringException;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;


/**
 * Tests TaskList class.
 */
public class TaskListTest {

    @Test
    public void getTasksTest() {
        try {
            TaskList t = new TaskList();
            ArrayList<Task> arr = new ArrayList<>();
            arr.add(new ToDo("name"));
            arr.add(new Deadline("a","b"));
            arr.add(new Event("a", "b", "c"));
            t.getTasksFrom(arr);
            assertEquals(t.getList(), arr);
        } catch (EmptyStringException e) {
            throw new RuntimeException();
        }
    }

    @Test
    public void sizeTest() {
        TaskList list = new TaskList();
        ArrayList<Task> arr = new ArrayList<Task>();
        for (int i = 0; i < 100; i++) {
            try {
                arr.add(new ToDo("s" + i));
            } catch (EmptyStringException e) {
                //fail the test
                throw new RuntimeException("Empty task name");
            }
        }
        list.getTasksFrom(arr);
        assertEquals(list.getList(), arr);
    }

    @Test
    public void markTest() {
        TaskList list = new TaskList();
        ToDo task;
        try {
            task = new ToDo("s");
        } catch (EmptyStringException e) {
            throw new RuntimeException();
        }
        list.storeTask(task);
        list.markAtIndex("0", true);
        assertEquals(task.getDone(), true);
    }

    @Test
    public void unmarkTest() {
        TaskList list = new TaskList();
        ToDo task;
        try {
            task = new ToDo("s");
            task.setDone(true);
        } catch (EmptyStringException e) {
            throw new RuntimeException();
        }
        list.storeTask(task);
        list.markAtIndex("0", false);
        assertEquals(task.getDone(), false);
    }
    
    @Test
    public void deleteIndexTest() {
        TaskList list = new TaskList();
        try {
            list.storeTask(new ToDo("s"));
            list.storeTask(new Deadline("s", "s"));
            list.storeTask(new Event("s","s","s"));
        } catch (EmptyStringException e) {
            throw new RuntimeException("Empty task name");
        }
        list.deleteAtIndex("2");
        list.deleteAtIndex("0");
        assertEquals(list.readTasks()
                , """
                ###################
                ## SEIZE THE DAY
                ## 0: [D][ ] s (by: s)""");
    }

    
    @Test
    public void deleteTaskTest() {
        TaskList list = new TaskList();
        ToDo task;
        try {
            task = new ToDo("s");
        } catch (EmptyStringException e) {
            throw new RuntimeException();
        }
        list.storeTask(task);
        list.deleteAtIndex("0");
        assertEquals(list.readTasks()
                , """
                ###################
                ## SEIZE THE DAY""");
    }

    @Test
    public void searchTaskTest() {
        TaskList list = new TaskList();
        ToDo task;
        try {
            task = new ToDo("s");
        } catch (EmptyStringException e) {
            throw new RuntimeException();
        }
        list.storeTask(task);
        assertEquals(list.searchTask("s")
                , """
                None shall escape
                ## 0: [T][ ] s""");
    }
}
