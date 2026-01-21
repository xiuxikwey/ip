public class Event extends Task {
    private String start;
    private String end;

    public Event(String name, String s, String e) {
        super(name);
        this.start = s;
        this.end = e;
    }

    public String toString() {
        return "[E]" + super.toString()
        + " (from: " + this.start + " to: " + this.end + ")";
    }
}
