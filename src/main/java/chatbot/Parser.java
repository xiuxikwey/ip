package chatbot;

import java.time.LocalDate;
import java.util.Scanner;

import tasks.Deadline;
import tasks.EmptyStringException;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * Converts strings to objects or functionality.
 */
public class Parser {

    /**
     * Calls functions based on user input.
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
                    try {
                        TaskList.storeTask(userToTask(str));
                    } catch (ParserException e) {
                        Ui.speak(e.getMessage());
                    }

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
     * Returns task given user input.
     * 
     * @param str User input.
     * @return Created task.
     * @throws ParserException If task is not created.
     */
    public static Task userToTask(String str) throws ParserException {
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
                    throw new ParserException("Try deadline A /by B.");
                }
            } else if (str.startsWith("event ")) {
                str = str.substring(6);
                String[] nameArr = str.split(" /from ");
                if (nameArr.length == 2) {
                    String[] timeArr = nameArr[1].split(" /to ");
                    if (timeArr.length == 2) {
                        newTask = new Event(nameArr[0], timeArr[0], timeArr[1]);
                    } else {
                        throw new ParserException("Try event A /from B /to C.");
                    }
                } else {
                    throw new ParserException("Try event A /from B /to C.");
                }
            } else {
                throw new ParserException("Not a type of task");
            }
        } catch (EmptyStringException e) {
            throw new ParserException(e.getMessage());
        }
        return newTask;
    }

    /**
     * Returns task given input from Storage.
     * 
     * @param str String stored in storage.
     * @return Task.
     * @throws ParserException If task is not created.
     */
    public static Task fileToTask(String str) throws ParserException {
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
                    throw new ParserException("Parse error");
                }
            } else if (str.startsWith("[E]")) {
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
                        throw new ParserException("Parse error");
                    }
                } else {
                    throw new ParserException("Parse error");
                }
            } else {
                throw new ParserException("Not a type of task");
            }
        } catch (EmptyStringException e) {
            throw new ParserException("Parse error");
        }
        if (newTask != null) {
            newTask.setDone(isDone);
        }
        return newTask;
    }

    /**
     * Returns date string, possibly in yyyy-mm-dd format.
     * Only for inputs like "3 Mar 2003".
     * 
     * @param str Date string.
     * @return Date string.
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
