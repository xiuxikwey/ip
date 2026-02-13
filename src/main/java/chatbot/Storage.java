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
 * Stores list of tasks in file, which can be recovered.
 */
public class Storage {
    private static final String PATH = "./toAdd.txt";
    
    /**
     * Updates storage.
     */
    public void updateStorage(ArrayList<Task> tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(PATH));
        for (Task t : tasks) {
            writer.write(t.toString(), 0, t.toString().length());
            writer.newLine();
        }
        writer.close();
    }

    /**
     * Retrieves stored tasks.
     * 
     * @return ArrayList of stored tasks
     */
    public ArrayList<Task> getList(Parser parser) throws
            FileNotFoundException, IOException, ParserException {
        ArrayList<Task> result = new ArrayList<Task>();
        BufferedReader reader = new BufferedReader(new FileReader(PATH));
        while (reader.ready()) {
            String str = reader.readLine();
            result.add(parser.fileInputToTask(str));
        }
        reader.close();
        return result;
    }
}
