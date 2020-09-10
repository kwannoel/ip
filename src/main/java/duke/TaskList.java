package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList implements java.io.Serializable {
    private ArrayList<Task> store;

    public TaskList(ArrayList<Task> store) {
        this.store = store;
    }

    public TaskList() {
        this.store = new ArrayList<>();
    }
    public void addTask(Task task) {
        store.add(task);
        assert store.size() >= 0;
    }

    public void deleteTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= store.size()) {
            throw new MissingTaskException();
        }
        Task task = store.remove(taskIndex);
        assert store.size() >= 0;
        System.out.println("duke.Task deleted:");
        System.out.println(task);
    }
    public String completeTask(Integer taskIndex) {
        // If the task doesn't exist (It's index is missing)
        if (taskIndex < 0 || taskIndex >= store.size()) {
            throw new MissingTaskException();
        }

        // Set the task to done
        Task task = store.get(taskIndex);
        task.done();
        return String.format("duke task marked as complete: %s", task);
    }

    public String list() {
        String listText = "";
        for (int i = 0; i < store.size(); i++) {
            listText += String.format("\n %d. %s", i + 1, store.get(i));
        }
        return listText;
    }

    public String dumpTasks() {
        // Format the store output as a string
        String data = "";
        for ( Task task : store ) {
            data += task.toString() + "\n";
        }
        return data;
    }

    public ArrayList<Task> find(String text) {
        List<Task> matchedTasks = store.stream().filter(
                task -> task.getName().contains(text)
        ).collect(Collectors.toList());

        return new ArrayList<>(matchedTasks);
    }
}
