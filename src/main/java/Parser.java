import java.util.ArrayList;

public class Parser {
    private static ArrayList<Task> list = new ArrayList<>();

    public static void parse(String input) {
        String[] words = input.split(" ", 2);
        String command = words[0];

        switch (command) {
            case "list":
                Task[] output = new Task[list.size()];
                Ui.showList(list.toArray(output));
                break;
            case "mark":
                int markIndex = Integer.parseInt(words[1]) - 1;
                Task currMarkTask = list.get(markIndex);
                currMarkTask.markAsDone();
                Ui.showMarkTaskMessage(currMarkTask);
                break;
            case "unmark":
                int unmarkIndex = Integer.parseInt(words[1]) - 1;
                Task currUnmarkTask = list.get(unmarkIndex);
                currUnmarkTask.markAsNotDone();
                Ui.showUnmarkTaskMessage(currUnmarkTask);
                break;
            default:
                list.add(new Task(input));
                Ui.echo(input);
        }
    }
}
