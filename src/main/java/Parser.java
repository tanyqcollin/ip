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
            case "todo":
                ToDo toDoTask = new ToDo(words[1]);
                list.add(toDoTask);
                Ui.showTask(toDoTask, list.size());
                break;
            case "deadline":
                String[] deadlineDetail = words[1].split("/");
                Deadline deadlineTask = new Deadline(deadlineDetail[0],
                                                    deadlineDetail[1].substring(3)
                                                    );
                list.add(deadlineTask);
                Ui.showTask(deadlineTask, list.size());
                break;
            case "event":
                String[] eventDetail = words[1].split("/");
                Event eventTask = new Event(eventDetail[0],
                                            eventDetail[1].substring(5),
                                            eventDetail[2].substring(3)
                                            );
                list.add(eventTask);
                Ui.showTask(eventTask, list.size());
                break;
            default:
                list.add(new Task(input));
                Ui.echo(input);
        }
    }
}
