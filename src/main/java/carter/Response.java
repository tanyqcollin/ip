package carter;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Response {
    public String showWelcomeMessage() {
        return "Hello! I'm Carter" + "\n"
                + "What can I do for you";
    }

    public String showEndingMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public String showTask(Task description, int numOfTask) {
        return "Got it. I've added this task:" + "\n"
                + "    " + description.toString()
                + "Now you have" + numOfTask + " tasks in the list.";
    }

    public String showMarkTaskMessage(Task task) {
        return "Nice! I've marked this task as done:"
                + "    " + task.toString();
    }

    public String showUnmarkTaskMessage(Task task) {
        return "OK, I've marked this task as not done yet:" + "\n"
                + "    " + task.toString();
    }

    public String showList(Task... list) {
        StringBuilder s = new StringBuilder("Here are the tasks in your list:");
        s.append("\n").append(
                IntStream.range(0, list.length)
                        .mapToObj(i -> (i + 1) + "." + list[i].toString())
                        .collect(Collectors.joining("\n"))
        );
        return s.toString();
    }

    public String showError(String message) {
        return "OOPS!! " + message;
    }

    public String showTaskDeleted(Task task, int size) {
        return " Noted. I've removed this task:" + "\n"
                + "    " + task + "\n"
                + " Now you have " + size + " tasks in the list.";
    }

    public String showMatchingTask(Task... list) {
        StringBuilder s = new StringBuilder("Here are the matching tasks in your list:");

        s.append("\n").append(
                IntStream.range(0, list.length)
                        .mapToObj(i -> (i + 1) + "." + list[i].toString())
                        .collect(Collectors.joining("\n"))
        );

        return s.toString();
    }
}
