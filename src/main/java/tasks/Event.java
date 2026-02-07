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
    
    /**
     * Creates new event object.
     * 
     * @param name Name of task.
     * @param start Start time.
     * @param end End time.
     * @throws EmptyStringException Thrown when field empty.
     */
    public Event(String name, String start, String end) throws EmptyStringException {
        super(name);
        if (!start.matches("\\S.*+")) {
            throw new EmptyStringException("The task needs a start time.");
        }
        if (!end.matches("\\S.*+")) {
            throw new EmptyStringException("The task needs an end time.");
        }
        this.start = start;
        this.end = end;
        try {
            preciseStart = LocalTime.parse(start);
        } catch (DateTimeParseException exc) {
            preciseStart = null;
        }
        try {
            preciseEnd = LocalTime.parse(end);
        } catch (DateTimeParseException exc) {
            preciseEnd = null;
        }
    }

    @Override
    public String toString() {
        String startString = "";
        if (preciseStart != null) {
            startString = this.preciseStart.format(
                    DateTimeFormatter.ofPattern("hh:mma"));
        } else {
            startString = this.start;
        }
        String endString = "";
        if (preciseEnd != null) {
            endString = this.preciseEnd.format(
                    DateTimeFormatter.ofPattern("hh:mma"));
        } else {
            endString = this.end;
        }
        return "[E]" + super.toString()
                + " (from: " + startString + " to: " + endString + ")";
    }
}
