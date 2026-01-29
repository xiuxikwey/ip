package chatbot;

import java.time.LocalDate;
import java.util.Scanner;

import tasks.Deadline;
import tasks.EmptyStringException;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * Converts text to objects or functions
 */
public class Parser {

    /**
     * Converts user input to actions
     */
    public static void takeInput() {
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
                    TaskList.updateIndex(str, true);

                } else if (str.startsWith("unmark ")) {
                    str = str.substring(7);
                    TaskList.updateIndex(str, false);

                } else if (str.startsWith("todo ") 
                        || str.startsWith("deadline ")
                        || str.startsWith("event ")) {
                    TaskList.storeTask(userToTask(str));

                } else if (str.startsWith("delete ")) {
                    str = str.substring(7);
                    TaskList.deleteIndex(str);

                } else {
                    Ui.echo(str);
                }
            }
        }
    }

    /**
     * Converts user string to task
     * 
     * @param str
     * @return Task object or null if bad input
     */
    public static Task userToTask(String str) {
        Task newTask = null;
        try {
            if (str.startsWith("todo ")) {
                str = str.substring(5);
                newTask = new ToDo(str);
            } else if (str.startsWith("deadline ")) {
                str = str.substring(9);
                String[] sarr = str.split(" /by ");
                if (sarr.length == 2) {
                    newTask = new Deadline(sarr[0], parseDate(sarr[1]));
                } else {
                    Ui.speak("Try deadline A /by B.");
                }
            } else {
                //starts with "event "
                str = str.substring(6);
                String[] nameArr = str.split(" /from ");
                if (nameArr.length == 2) {
                    String[] timeArr = nameArr[1].split(" /to ");
                    if (timeArr.length == 2) {
                        newTask = new Event(nameArr[0], timeArr[0], timeArr[1]);
                    } else {
                        Ui.speak("Try event A /from B /to C.");
                    }
                } else {
                    Ui.speak("Try event A /from B /to C.");
                }
            }
        } catch (EmptyStringException e) {
            Ui.speak(e.getMessage());
        }
        return newTask;
    }

    /**
     * Converts stored string to task
     * 
     * @param str string stored in storage
     * @return task
     * @throws StorageException is task is not created
     */
    public static Task fileToTask(String str) throws StorageException {
        Task newTask = null;
        boolean isDone = false;
        try {
            if (str.startsWith("[T]")) {
                if (str.startsWith("[T][ ] ")) {
                    str = str.substring(7);
                } else {
                    str = str.substring(10);
                    isDone = true;
                }
                newTask = new ToDo(str);
            } else if (str.startsWith("[D]")) {
                if (str.startsWith("[D][ ] ")) {
                    str = str.substring(7);
                } else {
                    str = str.substring(10);
                    isDone = true;
                }
                String[] sarr = str.split(" \\(by: ");
                if (sarr.length == 2) {
                    sarr[1] = sarr[1].substring(0, sarr[1].length() - 1);
                    newTask = new Deadline(sarr[0], parseDate(sarr[1]));
                } else {
                    throw new StorageException("Parse error");
                }
            } else {
                //starts with "event "
                if (str.startsWith("[E][ ] ")) {
                    str = str.substring(7);
                } else {
                    str = str.substring(10);
                    isDone = true;
                }
                String[] nameArr = str.split(" \\(from: ");
                if (nameArr.length == 2) {
                    String[] timeArr = nameArr[1].split(" to: ");
                    if (timeArr.length == 2) {
                        timeArr[1] = timeArr[1].substring(0,
                                timeArr[1].length() - 1);
                        newTask = new Event(nameArr[0], timeArr[0], timeArr[1]);
                    } else {
                        throw new StorageException("Parse error");
                    }
                } else {
                    throw new StorageException("Parse error");
                }
            }
        } catch (EmptyStringException e) {
            throw new StorageException("Empty string");
        }
        if (newTask != null) {
            newTask.setDone(isDone);
        }
        return newTask;
    }

    /**
     * Tries to convert date string to yyyy-mm-dd
     * 
     * @param str date string
     * @return date string
     */
    public static String parseDate(String str) {
        if (str.matches("^\\d{2} \\w{3} \\d{4}$")) {
            return str.substring(7, 11) + "-"
                    + getMonthNumber(str.substring(3,6))
                    + "-" + str.substring(0,2);
        } else if (str.matches("^\\d{1} \\w{3} \\d{4}$")) {
            return str.substring(6, 10) + "-"
                    + getMonthNumber(str.substring(2,5))
                    + "-0" + str.substring(0,1);
        } else if (str.matches("^\\d{2}/\\w{3}/\\d{4}$")) {
            return str.substring(7, 11) + "-"
                    + getMonthNumber(str.substring(3,6))
                    + "-" + str.substring(0,2);
        } else if (str.matches("^\\d{1}/\\w{3}/\\d{4}$")) {
            return str.substring(6, 10) + "-"
                    + getMonthNumber(str.substring(2,5))
                    + "-0" + str.substring(0,1);
        } else if (str.matches("^\\d{2} \\w{3}$")) {
            return LocalDate.now().getYear() + "-"
                    + getMonthNumber(str.substring(3,6))
                    + "-" + str.substring(0,2);
        } else {
            return str;
        } 

        
    }

    private static String getMonthNumber(String s) {
        if (s.equalsIgnoreCase("jan")) {
            return "01";
        } else if (s.equalsIgnoreCase("feb")) {
            return "02";
        }  else if (s.equalsIgnoreCase("mar")) {
            return "03";
        }  else if (s.equalsIgnoreCase("apr")) {
            return "04";
        }  else if (s.equalsIgnoreCase("may")) {
            return "05";
        }  else if (s.equalsIgnoreCase("jun")) {
            return "06";
        }  else if (s.equalsIgnoreCase("jul")) {
            return "07";
        }  else if (s.equalsIgnoreCase("aug")) {
            return "08";
        }  else if (s.equalsIgnoreCase("sep")) {
            return "09";
        }  else if (s.equalsIgnoreCase("oct")) {
            return "10";
        }  else if (s.equalsIgnoreCase("nov")) {
            return "11";
        }  else if (s.equalsIgnoreCase("dec")) {
            return "11";
        } else {
            return s;
        }
    }
}
