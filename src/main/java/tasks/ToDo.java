package tasks;

/**
 * Subclass of task with no new fields.
 */
public class ToDo extends Task{

    public ToDo(String s) throws EmptyStringException {
        super(s);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
