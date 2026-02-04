package tasks;

/**
 * Subclass of task with no new fields.
 */
public class ToDo extends Task{

    /**
     * Creates new Todo object.
     * 
     * @param name Name of task.
     * @throws EmptyStringException Thrown when name is empty.
     */
    public ToDo(String name) throws EmptyStringException {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
