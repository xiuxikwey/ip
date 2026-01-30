package tasks;

/**
 * Representation of task with fields name, isDone.
 */
public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) throws EmptyStringException {
        if (!name.matches("\\S.*+")) {
            throw new EmptyStringException("The task needs a name.");
        }
        this.name = name;
        this.isDone = false;
    }

    public void setDone(boolean b) {
        this.isDone = b;
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
