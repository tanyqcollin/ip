package carter;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public Task markTask(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }
    public Task unMarkTask(int index) {
        Task task = tasks.get(index);
        task.markAsNotDone();
        return task;
    }

    public int getLength() {
        return tasks.size();
    }

    public List<Task> find(String keyword) {
        List<Task> task = new ArrayList<>();
        for (Task t : tasks) {
            boolean isFound = t.toString().contains(keyword);
            if (isFound) {
                task.add(t);
            }
        }
        return task;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }
}
