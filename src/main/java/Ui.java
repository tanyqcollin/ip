public class Ui {
    private static String divider = "____________________________________________________________";

    public static void showWelcomeMessage() {
        System.out.println(divider);
        System.out.println("Hello! I'm Carter");
        System.out.println("What can I do for you?");
        System.out.println(divider + "\n");
    }

    public static void showEndingMessage() {
        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(divider);
    }

    public static void echo(String message) {
        System.out.println(divider);
        System.out.println("added: " + message);
        System.out.println(divider + "\n");
    }

    public static void showTask(Task description, int numOfTasks) {
        System.out.println(divider);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + description.toString());
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
        System.out.println(divider + "\n");
    }

    public static void showMarkTaskMessage(Task task) {
        System.out.println(divider);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    " + task.toString());
        System.out.println(divider + "\n");
    }

    public static void showUnmarkTaskMessage(Task task) {
        System.out.println(divider);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("    " + task.toString());
        System.out.println(divider + "\n");
    }

    public static void showList(Task[] list) {
        System.out.println(divider);
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= list.length; i++) {
            System.out.println(i + "." + list[i - 1].toString());
        }
        System.out.println(divider + "\n");
    }

    public static void showError(String message) {
        System.out.println(divider);
        System.out.println("OOPS!! " + message);
        System.out.println(divider + "\n");
    }
}
