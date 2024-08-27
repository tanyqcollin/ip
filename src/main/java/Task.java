public abstract class Task {
    protected boolean isDone;
    protected String description;

    public Task(String description) {
        this.isDone = false;
        this.description = description.trim();
    }

    public Task(String description, boolean isDone) {
        this.isDone = isDone;
        this.description = description.trim();
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    private String getStatus() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    @Override
    public String toString() {
        return getStatus() + " " + description;
    }

    public abstract String toFileString();
}
