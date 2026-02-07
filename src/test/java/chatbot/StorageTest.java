package chatbot;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import tasks.Deadline;
import tasks.EmptyStringException;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;


/**
 * Tests Storage class, ensuring updateStorage() works with getList().
 * 
 * Note Storage.getList() calls Parser.fileToTask().
 */
public class StorageTest {

    @BeforeEach
    @AfterAll
    @Test
    public void emptyTest() {
        Storage.updateStorage(new ArrayList<>());
        assertTrue(Storage.getList().isEmpty());
    }

    @Test
    public void sizeTest() {
        ArrayList<Task> t1 = new ArrayList<Task>();
        for (int i = 0; i < 100; i++) {
            try {
                t1.add(new ToDo("s" + i));
            } catch (EmptyStringException e) {
                //fail the test
                throw new RuntimeException("Empty task name");
            }
        }
        Storage.updateStorage(t1);
        assertTrue(Storage.getList().equals(t1));
    }

    @Test
    public void callTest() {
        ArrayList<Task> t1 = new ArrayList<Task>();
        try {
            t1.add(new ToDo("s"));
        } catch (EmptyStringException e) {
            //fail the test
            throw new RuntimeException("Empty task name");
        }
        Storage.updateStorage(t1);
        Storage.getList();
        Storage.getList();
        assertTrue(Storage.getList().equals(t1));
    }

    @Test
    public void typeTest() {
        ArrayList<Task> t1 = new ArrayList<Task>();
        try {
            t1.add(new ToDo("s"));
            t1.add(new Deadline("s", "s"));
            t1.add(new Event("s","s","s"));
        } catch (EmptyStringException e) {
            //fail the test
            throw new RuntimeException("Empty task name");
        }
        Storage.updateStorage(t1);
        assertTrue(Storage.getList().equals(t1));
    }

}
