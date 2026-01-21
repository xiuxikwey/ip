public class Deadline extends Task{
    private String date;

    public Deadline(String name, String date) throws EmptyStringException {
        super(name);
        if (!date.matches("\\S.*+")) {
            throw new EmptyStringException("The task needs a deadline.");
        }
        this.date = date;
    }

    public String toString() {
        return "[D]" + super.toString()
        + " (by: " + this.date + ")";
    }
}
