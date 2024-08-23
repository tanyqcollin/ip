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
        System.out.println(message);
        System.out.println(divider + "\n");
    }
}
