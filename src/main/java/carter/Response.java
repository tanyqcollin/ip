package carter;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a response handler for various messages in the Carter application.
 */
public class Response {

    /**
     * Returns a welcome message.
     *
     * @return A welcome message string.
     */
    public String showWelcomeMessage() {
        return "Hello! I'm Carter\nWhat can I do for you?";
    }

    /**
     * Returns an ending message.
     *
     * @return A farewell message string.
     */
    public String showEndingMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a message when a task is added to the list.
     *
     * @param task      The task that was added.
     * @param numOfTasks The current number of tasks in the list.
     * @return A message confirming the task addition.
     */
    public String showTask(Task task, int numOfTasks) {
        assert task != null : "Task should not be null";
        assert numOfTasks >= 0 : "Number of tasks should be non-negative";

        return "Got it. I've added this task:\n"
                + "    " + task.toString()
                + "\nNow you have " + numOfTasks + " tasks in the list.";
    }

    /**
     * Returns a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     * @return A message confirming the task completion.
     */
    public String showMarkTaskMessage(Task task) {
        assert task != null : "Task should not be null";

        return "Nice! I've marked this task as done:\n"
                + "    " + task.toString();
    }

    /**
     * Returns a message when a task is marked as not done.
     *
     * @param task The task that was marked as not done.
     * @return A message confirming the task is not done yet.
     */
    public String showUnmarkTaskMessage(Task task) {
        assert task != null : "Task should not be null";

        return "OK, I've marked this task as not done yet:\n"
                + "    " + task.toString();
    }

    /**
     * Returns a list of tasks currently in the list.
     *
     * @param tasks The list of tasks to be displayed.
     * @return A message listing all tasks.
     */
    public String showList(Task... tasks) {
        assert tasks != null : "Task list should not be null";

        return "Here are the tasks in your list:\n"
                + IntStream.range(0, tasks.length)
                        .mapToObj(i -> (i + 1) + ". " + tasks[i].toString())
                        .collect(Collectors.joining("\n"));
    }

    /**
     * Returns an error message when an error occurs.
     *
     * @param message The error message to be displayed.
     * @return The formatted error message.
     */
    public String showError(String message) {
        assert message != null : "Error message should not be null";

        return "OOPS!! " + message;
    }

    /**
     * Returns a message when a task is deleted.
     *
     * @param task The task that was deleted.
     * @param size The current number of tasks in the list.
     * @return A message confirming the task deletion.
     */
    public String showTaskDeleted(Task task, int size) {
        assert task != null : "Task should not be null";
        assert size >= 0 : "Size should be non-negative";

        return "Noted. I've removed this task:\n"
                + "    " + task.toString()
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Returns a message listing all tasks that match the keyword in the provided list.
     *
     * @param tasks The list of matching tasks to be displayed.
     * @return A message listing all matching tasks.
     */
    public String showMatchingTask(Task... tasks) {
        assert tasks != null : "Task list should not be null";

        return "Here are the matching tasks in your list:\n"
                + IntStream.range(0, tasks.length)
                        .mapToObj(i -> (i + 1) + ". " + tasks[i].toString())
                        .collect(Collectors.joining("\n"));
    }

    /**
     * Returns a message listing all archived tasks.
     *
     * @param tasks The tasks that have been archived.
     * @return A message listing all archived tasks.
     */
    public String showArchiveTask(Task... tasks) {
        return "Here are your archived tasks:\n"
                + IntStream.range(0, tasks.length)
                        .mapToObj(i -> (i + 1) + ". " + tasks[i].toString())
                        .collect(Collectors.joining("\n"));
    }
}
