package carter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent the list of tasks of the Carter application.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with existing list of tasks.
     *
     * @param tasks The list of task to initialize the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task into the TaskList.
     *
     * @param task Task to be added to the TaskList.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from TaskList with specified index.
     *
     * @param index The index of the task in TaskList.
     * @return The deleted task from the TaskList.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks the task as done with specified index.
     *
     * @param index The index of the task in TaskList.
     * @return The marked task from TaskList.
     */
    public Task markTask(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Marks the task as not done with specified index.
     *
     * @param index The index of the task in TaskList.
     * @return The unmarked task from TaskList.
     */
    public Task unMarkTask(int index) {
        Task task = tasks.get(index);
        task.markAsNotDone();
        return task;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getLength() {
        return tasks.size();
    }

    /**
     * Return the list of tasks in the TaskList.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }
}
