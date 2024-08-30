package carter;

/**
 * Represents the user interface of the Carter application.
 */
public class Ui {
    private final static String DIVIDER = "____________________________________________________________";

    /**
     * Displays the welcome message when the application start.
     */
    public void showWelcomeMessage() {
        System.out.println(DIVIDER);
        System.out.println("Hello! I'm Carter");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER + "\n");
    }

    /**
     * Displays the ending message when user exist.
     */
    public void showEndingMessage() {
        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    /**
     * Displays a task when the task added to the list.
     *
     * @param description The description of the task.
     * @param numOfTasks The number of the task in list.
     */
    public void showTask(Task description, int numOfTasks) {
        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + description.toString());
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
        System.out.println(DIVIDER + "\n");
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The tasks that was marked as done.
     */
    public void showMarkTaskMessage(Task task) {
        System.out.println(DIVIDER);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    " + task.toString());
        System.out.println(DIVIDER + "\n");
    }

    /**
     * Displays a message when a task id marked as not done.
     *
     * @param task The tasks that was marked as not done.
     */
    public void showUnmarkTaskMessage(Task task) {
        System.out.println(DIVIDER);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("    " + task.toString());
        System.out.println(DIVIDER + "\n");
    }

    /**
     * Displays a list of task currently in the list.
     *
     * @param list A list of tasks to be displayed.
     */
    public void showList(Task[] list) {
        System.out.println(DIVIDER);
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= list.length; i++) {
            System.out.println(i + "." + list[i - 1].toString());
        }
        System.out.println(DIVIDER + "\n");
    }

    /**
     * Displays an error message when an error occur.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(DIVIDER);
        System.out.println("OOPS!! " + message);
        System.out.println(DIVIDER + "\n");
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task Task to be deleted.
     * @param size The current size of the list.
     */
    public void showTaskDeleted(Task task, int size) {
        System.out.println(DIVIDER);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println(DIVIDER + "\n");
    }

    /**
     * Displays a message when find tasks with keywords.
     *
     * @param list The list of tasks that match the keyword.
     */
    public void showMatchingTask(Task[] list) {
        System.out.println(DIVIDER);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= list.length; i++) {
            System.out.println(i + "." + list[i - 1].toString());
        }
        System.out.println(DIVIDER + "\n");
    }
}
