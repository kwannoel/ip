package duke;

public class DoneCommand implements Command {
    Storage store;
    public DoneCommand(Storage store) {
        this.store = store;
    }

    public String execute() {
        return "Done";
    }
}
