package chatbot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;

import tasks.Task;

/**
 * Stores taskList in file, which can be recovered
 */
public class Storage {
    private static final String PATH = "./toAdd.txt";
    
    /**
     * Updates storage
     */
    public static void updateStorage(ArrayList<Task> tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(PATH));
            for (Task t : tasks) {
                writer.write(t.toString(), 0, t.toString().length());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            Ui.speak("I forgot how to write :P");
        }
    }

    /**
     * Retrieves stored tasks
     * 
     * @return stored tasks
     */
    public static ArrayList<Task> getList() {
        ArrayList<Task> result = new ArrayList<Task>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PATH));
            while (reader.ready()) {
                String str = reader.readLine();
                result.add(Parser.fileToTask(str));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            Ui.speak("Getting a new list...");
        } catch (IOException e) {
            Ui.speak("I forgot how to read :P");
        } catch (StorageException e) {
            Ui.speak("Lost my old list...");
            return new ArrayList<>();
        }
        return result;

    }
}
