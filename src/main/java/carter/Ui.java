package carter;

public class Ui {
    private final static String DIVIDER = "____________________________________________________________";

    public void showWelcomeMessage() {
        System.out.println(DIVIDER);
        System.out.println("Hello! I'm Carter");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER + "\n");
    }

    public void showEndingMessage() {
        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    public void showTask(Task description, int numOfTasks) {
        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + description.toString());
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
        System.out.println(DIVIDER + "\n");
    }

    public void showMarkTaskMessage(Task task) {
        System.out.println(DIVIDER);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    " + task.toString());
        System.out.println(DIVIDER + "\n");
    }

    public void showUnmarkTaskMessage(Task task) {
        System.out.println(DIVIDER);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("    " + task.toString());
        System.out.println(DIVIDER + "\n");
    }

    public void showList(Task[] list) {
        System.out.println(DIVIDER);
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= list.length; i++) {
            System.out.println(i + "." + list[i - 1].toString());
        }
        System.out.println(DIVIDER + "\n");
    }

    public void showError(String message) {
        System.out.println(DIVIDER);
        System.out.println("OOPS!! " + message);
        System.out.println(DIVIDER + "\n");
    }

    public void showTaskDeleted(Task task, int size) {
        System.out.println(DIVIDER);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println(DIVIDER + "\n");
    }
}
