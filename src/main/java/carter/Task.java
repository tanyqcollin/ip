package carter;

/**
 *  Represents an abstract task in Carter
 */
public abstract class Task {
    protected boolean isDone;
    protected String description;

    /**
     * Construct a new Task with specified description.
     * The task is initially marked not done.
     *
     * @param description The description of task.
     */
    public Task(String description) {
        assert description != null : "description should not be null";

        this.isDone = false;
        this.description = description.trim();
    }

    /**
     * Construct a new Task with specified description and completion status.
     *
     * @param description The description of task.
     * @param isDone The completion status of task.
     */
    public Task(String description, boolean isDone) {
        assert description != null : "description should not be null";

        this.isDone = isDone;
        this.description = description.trim();
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return A string representation of task.
     */
    private String getStatus() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Return a string representation of the task,
     * including it description and completion status.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return getStatus() + " " + description;
    }

    /**
     * Returns a string representation of the task formatted for saving to a file.
     * This method must be implemented by subclass.
     *
     * @return A string representation of the task formatted for file storage.
     */
    public abstract String toFileString();
}
