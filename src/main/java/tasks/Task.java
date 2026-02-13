package tasks;

/**
 * Representation of task with fields name, isDone.
 */
public class Task {
    private String name;
    private boolean isDone;

    /**
     * Creates new task object.
     * 
     * @param name Name of object.
     * @throws EmptyStringException Thrown when name is empty string.
     */
    public Task(String name) throws EmptyStringException {
        if (name.isBlank()) {
            throw new EmptyStringException("The task needs a name.");
        }
        this.name = name;
        this.isDone = false;
    }

    public void setDone(boolean b) {
        this.isDone = b;
    }

    public boolean getDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        String s = " ";
        if (this.isDone) {
            s = "Done";
        }
        return "[" + s + "] " + this.name;
    }

    @Override
    public boolean equals(Object other) {
        return other.toString().equals(this.toString());
    }
}
