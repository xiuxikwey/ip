package chatbot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class StorageTest {
    
    @Test
    public void initEmptyTest() {
        Storage.updateStorage(new ArrayList<>());
        assertTrue(Storage.getList().isEmpty());
    }
}
