package chatbot;

import java.util.Scanner;

/**
 * Chatbot called Oliver.
 */
public class Oliver {
    
    private static void event_handler() {
        try(Scanner sc = new Scanner(System.in)) {
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                if (str.equalsIgnoreCase("bye")) {
                    Ui.sayGoodbye();
                    break;
                } else if (str.equalsIgnoreCase("list")) {
                    TaskList.readTasks();
                } else if (str.startsWith("mark ")) {
                    str = str.substring(5);
                    TaskList.updateTask(str, true);
                } else if (str.startsWith("unmark ")) {
                    str = str.substring(7);
                    TaskList.updateTask(str, false);
                } else if (str.startsWith("todo ") 
                        || str.startsWith("deadline ")
                        || str.startsWith("event ")) {
                    TaskList.storeTask(str);
                } else if (str.startsWith("delete ")) {
                    str = str.substring(7);
                    TaskList.deleteTask(str);
                } else {
                    Ui.echo(str);
                }
            }
        }
    }

    public static void main(String[] args) {
        Ui.greet();
        event_handler();
    }
}
