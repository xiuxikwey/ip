package chatbot;

import java.time.LocalDate;

import tasks.Deadline;
import tasks.EmptyStringException;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * Converts strings to objects or functionality.
 */
public class Parser {

    private static String DEADLINE_FAIL = "Try deadline A /by B.";
    private static String EVENT_FAIL = "Try event A /from B /to C.";
    private static String STORAGE_FAIL = "Storage parse failed.";
    /**
     * Calls functions given user input.
     * Returns false if program should terminate.
     * 
     * @param str User input.
     * @return false if program should terminate.
     */
    public static boolean parseUserInput(String str) {
        if (str.equalsIgnoreCase("bye")) {
            Ui.sayGoodbye();
            return false;

        } else if (str.equalsIgnoreCase("list")) {
            TaskList.readTasks();
            
        } else if (str.startsWith("mark ")) {
            String trimmed = removeFirstWord(str);
            TaskList.markAtIndex(trimmed, true);
            
        } else if (str.startsWith("unmark ")) {
            String trimmed = removeFirstWord(str);
            TaskList.markAtIndex(trimmed, false);
            
        } else if (str.startsWith("todo ")) {
            try {
                Task t = parseToDo(str);
                TaskList.storeTask(t);
            } catch (ParserException e) {
                Ui.speak(e.getMessage());
            }

        } else if (str.startsWith("deadline ")) {
            try {
                Task t = parseDeadline(str);
                TaskList.storeTask(t);
            } catch (ParserException e) {
                Ui.speak(e.getMessage());
            }

        } else if (str.startsWith("event ")) {
            try {
                Task t = parseEvent(str);
                TaskList.storeTask(t);
            } catch (ParserException e) {
                Ui.speak(e.getMessage());
            }
            
        } else if (str.startsWith("delete ")) {
            String trimmed = removeFirstWord(str);
            TaskList.deleteIndex(trimmed);
            
        } else if (str.startsWith("search ")) {
            String trimmed = removeFirstWord(str);
            TaskList.searchTask(trimmed);
            
        } else {
            Ui.echo(str);
        }
        return true;
    }

    private static String removeFirstWord(String str) {
        String trimmed = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                trimmed = str.substring(i + 1);
                break;
            }
        }
        return trimmed;
    }


    private static Task parseToDo(String str) throws ParserException {
        try {
            String trim = removeFirstWord(str);
            return new ToDo(trim);
        } catch (EmptyStringException e) {
            throw new ParserException(e.getMessage());
        }
    }

    private static Task parseDeadline(String str) throws ParserException {
        try {
            String trim = removeFirstWord(str);
            String[] sarr = trim.split(" /by ");
            if (sarr.length != 2) {
                throw new ParserException(DEADLINE_FAIL);
            }
            return new Deadline(sarr[0], parseDate(sarr[1]));
        } catch (EmptyStringException e) {
            throw new ParserException(e.getMessage());
        }
    }

    private static Task parseEvent(String str) throws ParserException {
        try {
            String trim = removeFirstWord(str);

            String[] nameArr = trim.split(" /from ");
            if (nameArr.length != 2) {
                throw new ParserException(EVENT_FAIL);
            }

            String[] timeArr = nameArr[1].split(" /to ");
            if (timeArr.length != 2) {
                throw new ParserException(EVENT_FAIL);
            }

            return new Event(nameArr[0], timeArr[0], timeArr[1]);
        } catch (EmptyStringException e) {
            throw new ParserException(e.getMessage());
        }
    }

    /**
     * Returns task given input from Storage.
     * 
     * @param str String stored in storage.
     * @return Task.
     * @throws ParserException If task is not created.
     */
    public static Task parseFileInput(String str) throws ParserException {
        if (str.startsWith("[T]")) {
            return parseFileToDo(str);
        } else if (str.startsWith("[D]")) {
            return parseFileDeadline(str);
        } else if (str.startsWith("[E]")) {
            return parseFileEvent(str);
        } else {
            throw new ParserException(STORAGE_FAIL);
        }
    }

    private static Task parseFileToDo(String str) throws ParserException {
        try {
            String trim = "";
            boolean isDone = false;
            if (str.startsWith("[T][ ] ")) {
                trim = str.substring(7);
                isDone = false;
            } else {
                trim = str.substring(10);
                isDone = true;
            }
            Task t = new ToDo(trim);
            t.setDone(isDone);
            return t;
        } catch (EmptyStringException e) {
            throw new ParserException(e.getMessage());
        }
    }

    private static Task parseFileDeadline(String str) throws ParserException {
        try {
            String trim = "";
            boolean isDone = false;
            if (str.startsWith("[D][ ] ")) {
                trim = str.substring(7);
                isDone = false;
            } else {
                trim = str.substring(10);
                isDone = true;
            }
            String[] sarr = trim.split(" \\(by: ");
            if (sarr.length != 2) {
                throw new ParserException(STORAGE_FAIL);
            }
            //delete newline character
            sarr[1] = sarr[1].substring(0, sarr[1].length() - 1);
            Task t = new Deadline(sarr[0], parseDate(sarr[1]));
            t.setDone(isDone);
            return t;
        } catch (EmptyStringException e) {
            throw new ParserException(e.getMessage());
        }
    }

    private static Task parseFileEvent(String str) throws ParserException {
        try {
            String trim = "";
            boolean isDone = false;
            if (str.startsWith("[E][ ] ")) {
                trim = str.substring(7);
                isDone = false;
            } else {
                trim = str.substring(10);
                isDone = true;
            }
            String[] nameArr = trim.split(" \\(from: ");
            if (nameArr.length != 2) {
                throw new ParserException(STORAGE_FAIL);
            }
            String[] timeArr = nameArr[1].split(" to: ");
            if (timeArr.length != 2) {
                throw new ParserException(STORAGE_FAIL);
            }
            timeArr[1] = timeArr[1].substring(0,
                    timeArr[1].length() - 1);
            Task t = new Event(nameArr[0], timeArr[0], timeArr[1]);
            t.setDone(isDone);
            return t;
        } catch (EmptyStringException e) {
            throw new ParserException(e.getMessage());
        }
    }

    /**
     * Returns date string, possibly in yyyy-mm-dd format.
     * Only parses dates like 03 Mar.
     * 
     * @param str Date string.
     * @return Date string.
     */
    private static String parseDate(String str) {
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

    private static String getMonthNumber(String month) {
        String[] months = {"jan", "feb", "mar", "apr", "may", "jun",
                            "jul", "aug", "sep", "oct", "nov", "dec"};
        for (int i = 0; i < 12; i++) {
            if (months[i].equalsIgnoreCase(month)) {
                return String.valueOf(i + 1);
            }
        }
        return month;
    }
}
