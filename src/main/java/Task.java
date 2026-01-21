public class Task {
    private String name;
    private boolean done;

    public Task(String s) {
        this.name = s;
        this.done = false;
    }

    public void setDone(boolean b) {
        this.done = b;
    }

    public String toString() {
        String s = " ";
        if (this.done) {
            s = "Done";
        }
        return "[" + s + "] " + this.name;
    }
}
