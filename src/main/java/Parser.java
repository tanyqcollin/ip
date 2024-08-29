import java.util.List;

public class Parser {

    public static void parse(String input, List<Task> list) throws CarterException {
        String[] words = input.split(" ", 2);
        String command = words[0];

        switch (command) {
        case "list":
            Task[] output = new Task[list.size()];
            Ui.showList(list.toArray(output));
            break;
        case "mark":
            if (words.length < 2) throw new CarterException("Please provide the task number to mark.");
            if (Integer.parseInt(words[1]) > list.size()) throw new CarterException("Please provide a valid number");
            int markIndex = Integer.parseInt(words[1]) - 1;
            Task currMarkTask = list.get(markIndex);
            currMarkTask.markAsDone();
            Ui.showMarkTaskMessage(currMarkTask);
            break;
        case "unmark":
            if (words.length < 2) throw new CarterException("Please provide the task number to unmark.");
            if (Integer.parseInt(words[1]) > list.size()) throw new CarterException("Please provide a valid number");
            int unmarkIndex = Integer.parseInt(words[1]) - 1;
            Task currUnmarkTask = list.get(unmarkIndex);
            currUnmarkTask.markAsNotDone();
            Ui.showUnmarkTaskMessage(currUnmarkTask);
            break;
        case "todo":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new CarterException("The description of a todo cannot be empty.");
            }
            ToDo toDoTask = new ToDo(words[1]);
            list.add(toDoTask);
            Ui.showTask(toDoTask, list.size());
            break;
        case "deadline":
            String[] deadlineDetail = words[1].split("/", 2);
            if (deadlineDetail.length < 2 || deadlineDetail[0].trim().isEmpty() || deadlineDetail[1].trim().isEmpty()) {
                throw new CarterException("The description and deadline of a deadline task cannot be empty.");
            }
            try {
                Deadline deadlineTask = new Deadline(deadlineDetail[0],
                        deadlineDetail[1].substring(3)
                );
                list.add(deadlineTask);
                Ui.showTask(deadlineTask, list.size());
            } catch (Exception e) {
                throw new CarterException("Please fill in the detail with valid format");
            }
            break;
        case "event":
            String[] eventDetail = words[1].split("/");
            if (eventDetail.length < 3 || eventDetail[0].trim().isEmpty() || eventDetail[1].trim().isEmpty() || eventDetail[2].trim().isEmpty()) {
                throw new CarterException("The description and times of an event task cannot be empty.");
            }
            try {
                Event eventTask = new Event(eventDetail[0],
                        eventDetail[1].substring(5),
                        eventDetail[2].substring(3)
                );
                list.add(eventTask);
                Ui.showTask(eventTask, list.size());
            } catch (Exception e) {
                throw new CarterException("Please fill in the detail with valid format");
            }
            break;
        case "delete":
            if (words.length < 2) {
                throw new CarterException("Please provide the task number to delete.");
            }
            int deleteTaskNumber = Integer.parseInt(words[1]) - 1;
            Task deletedTask = list.remove(deleteTaskNumber);
            Ui.showTaskDeleted(deletedTask, list.size());
            break;
        default:
            throw new CarterException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
