public class ToDo extends Task{

    public ToDo(String s) throws EmptyStringException {
        super(s);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
