package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Subclass of task with deadline field.
 */
public class Deadline extends Task{
    private String date;
    private LocalDate preciseDate;

    public Deadline(String name, String date) throws EmptyStringException {
        super(name);
        if (!date.matches("\\S.*+")) {
            throw new EmptyStringException("The task needs a deadline.");
        }
        this.date = date;
        try {
            preciseDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            preciseDate = null;
        }
    }

    @Override
    public String toString() {
        String s;
        if (preciseDate != null) {
            s = preciseDate.format(DateTimeFormatter.ofPattern("dd MMM uuuu"));
        } else {
            s = this.date;
        }
        return "[D]" + super.toString()
                + " (by: " + s + ")";
    }
}
