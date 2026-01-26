package chatbot;

/**
 * Subclass of task with start and end.
 */
public class Event extends Task {
    private String start;
    private String end;

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
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.start + " to: " + this.end + ")";
    }
}
