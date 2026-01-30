package tasks;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Subclass of task with start and end.
 */
public class Event extends Task {
    private String start;
    private String end;
    private LocalTime preciseStart;
    private LocalTime preciseEnd;
    

    public Event(String name, String s, String e) throws EmptyStringException {
        super(name);
        if (!s.matches("\\S.*+")) {
            throw new EmptyStringException("The task needs a start time.");
        }
        if (!e.matches("\\S.*+")) {
            throw new EmptyStringException("The task needs an end time.");
        }
        this.start = s;
        this.end = e;
        try {
            preciseStart = LocalTime.parse(s);
        } catch (DateTimeParseException exc) {
            preciseStart = null;
        }
        try {
            preciseEnd = LocalTime.parse(e);
        } catch (DateTimeParseException exc) {
            preciseEnd = null;
        }
    }

    @Override
    public String toString() {
        String s;
        if (preciseStart != null) {
            s = this.preciseStart.format(
                    DateTimeFormatter.ofPattern("hh:mma"));
        } else {
            s = this.start;
        }
        String e;
        if (preciseEnd != null) {
            e = this.preciseEnd.format(
                    DateTimeFormatter.ofPattern("hh:mma"));
        } else {
            e = this.end;
        }
        return "[E]" + super.toString()
                + " (from: " + s + " to: " + e + ")";
    }
}
