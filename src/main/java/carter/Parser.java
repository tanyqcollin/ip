package carter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Handles the parsing of the user command for Carter.
 */
public class Parser {

    /**
     * Parses the user input and performs the corresponding action.
     *
     * @param input The user input command as String.
     * @param tasks The TaskList containing the current tasks.
     * @param ui The Ui used to display message to the user.
     * @throws CarterException If the user input is invalid or cannot be recognized.
     */
    public static void parse(String input, TaskList tasks, Ui ui) throws CarterException {
        String[] words = input.split(" ", 2);
        String command = words[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        switch (command) {
        case "list":
            Task[] output = new Task[tasks.getLength()];
            ui.showList(tasks.getTasks().toArray(output));
            break;
        case "mark":
            if (words.length < 2) {
                throw new CarterException("Please provide the task number to mark.");
            }
            if (Integer.parseInt(words[1]) > tasks.getLength()) {
                throw new CarterException("Please provide a valid number");
            }
            int markIndex = Integer.parseInt(words[1]) - 1;
            Task currMarkTask = tasks.markTask(markIndex);
            ui.showMarkTaskMessage(currMarkTask);
            break;
        case "unmark":
            if (words.length < 2) {
                throw new CarterException("Please provide the task number to unmark.");
            }
            if (Integer.parseInt(words[1]) > tasks.getLength()) {
                throw new CarterException("Please provide a valid number");
            }
            int unmarkIndex = Integer.parseInt(words[1]) - 1;
            Task currUnmarkTask = tasks.unMarkTask(unmarkIndex);
            ui.showUnmarkTaskMessage(currUnmarkTask);
            break;
        case "todo":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new CarterException("The description of a todo cannot be empty.");
            }
            ToDo toDoTask = new ToDo(words[1]);
            tasks.addTask(toDoTask);
            ui.showTask(toDoTask, tasks.getLength());
            break;
        case "deadline":
            String[] deadlineDetail = words[1].split("/", 2);
            if (deadlineDetail.length < 2 || deadlineDetail[0].trim().isEmpty() || deadlineDetail[1].trim().isEmpty()) {
                throw new CarterException("The description and deadline of a deadline task cannot be empty.");
            }
            try {
                Deadline deadlineTask = new Deadline(deadlineDetail[0],
                        LocalDateTime.parse(deadlineDetail[1].substring(3), formatter)
                );
                tasks.addTask(deadlineTask);
                ui.showTask(deadlineTask, tasks.getLength());
            } catch (DateTimeParseException e) {
                throw new CarterException("Please fill in the detail with valid format");
            }
            break;
        case "event":
            String[] eventDetail = words[1].split("/");
            if (eventDetail.length < 3 || eventDetail[0].trim().isEmpty()
                    || eventDetail[1].trim().isEmpty() || eventDetail[2].trim().isEmpty()) {
                throw new CarterException("The description and times of an event task cannot be empty.");
            }
            try {
                Event eventTask = new Event(eventDetail[0],
                        LocalDateTime.parse(eventDetail[1].substring(5).trim(), formatter),
                        LocalDateTime.parse(eventDetail[2].substring(3).trim(), formatter)
                );
                tasks.addTask(eventTask);
                ui.showTask(eventTask, tasks.getLength());
            } catch (DateTimeParseException e) {
                throw new CarterException("Please fill in the detail with valid format");
            }
            break;
        case "delete":
            if (words.length < 2) {
                throw new CarterException("Please provide the task number to delete.");
            }
            int deleteTaskNumber = Integer.parseInt(words[1]) - 1;
            Task deletedTask = tasks.deleteTask(deleteTaskNumber);
            ui.showTaskDeleted(deletedTask, tasks.getLength());
            break;
        case "find":
            String keyword = words[1].trim();
            List<Task> taskList = tasks.find(keyword);
            ui.showMatchingTask(taskList.toArray(new Task[0]));
            break;
        default:
            throw new CarterException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static String parse(String input, TaskList tasks, Response res) throws CarterException {
        String[] words = input.split(" ", 2);
        String command = words[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        String result = "";
        switch (command) {
        case "list":
            Task[] output = new Task[tasks.getLength()];
            result = res.showList(tasks.getTasks().toArray(output));
            break;
        case "mark":
            if (words.length < 2) {
                throw new CarterException("Please provide the task number to mark.");
            }
            if (Integer.parseInt(words[1]) > tasks.getLength()) {
                throw new CarterException("Please provide a valid number");
            }
            int markIndex = Integer.parseInt(words[1]) - 1;
            Task currMarkTask = tasks.markTask(markIndex);
            result = res.showMarkTaskMessage(currMarkTask);
            break;
        case "unmark":
            if (words.length < 2) {
                throw new CarterException("Please provide the task number to unmark.");
            }
            if (Integer.parseInt(words[1]) > tasks.getLength()) {
                throw new CarterException("Please provide a valid number");
            }
            int unmarkIndex = Integer.parseInt(words[1]) - 1;
            Task currUnmarkTask = tasks.unMarkTask(unmarkIndex);
            result = res.showUnmarkTaskMessage(currUnmarkTask);
            break;
        case "todo":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new CarterException("The description of a todo cannot be empty.");
            }
            ToDo toDoTask = new ToDo(words[1]);
            tasks.addTask(toDoTask);
            result = res.showTask(toDoTask, tasks.getLength());
            break;
        case "deadline":
            String[] deadlineDetail = words[1].split("/", 2);
            if (deadlineDetail.length < 2 || deadlineDetail[0].trim().isEmpty() || deadlineDetail[1].trim().isEmpty()) {
                throw new CarterException("The description and deadline of a deadline task cannot be empty.");
            }
            try {
                Deadline deadlineTask = new Deadline(deadlineDetail[0],
                        LocalDateTime.parse(deadlineDetail[1].substring(3), formatter)
                );
                tasks.addTask(deadlineTask);
                result = res.showTask(deadlineTask, tasks.getLength());
            } catch (DateTimeParseException e) {
                throw new CarterException("Please fill in the detail with valid format");
            }
            break;
        case "event":
            String[] eventDetail = words[1].split("/");
            if (eventDetail.length < 3 || eventDetail[0].trim().isEmpty()
                    || eventDetail[1].trim().isEmpty() || eventDetail[2].trim().isEmpty()) {
                throw new CarterException("The description and times of an event task cannot be empty.");
            }
            try {
                Event eventTask = new Event(eventDetail[0],
                        LocalDateTime.parse(eventDetail[1].substring(5).trim(), formatter),
                        LocalDateTime.parse(eventDetail[2].substring(3).trim(), formatter)
                );
                tasks.addTask(eventTask);
                result = res.showTask(eventTask, tasks.getLength());
            } catch (DateTimeParseException e) {
                throw new CarterException("Please fill in the detail with valid format");
            }
            break;
        case "delete":
            if (words.length < 2) {
                throw new CarterException("Please provide the task number to delete.");
            }
            int deleteTaskNumber = Integer.parseInt(words[1]) - 1;
            Task deletedTask = tasks.deleteTask(deleteTaskNumber);
            result = res.showTaskDeleted(deletedTask, tasks.getLength());
            break;
        case "find":
            String keyword = words[1].trim();
            List<Task> taskList = tasks.find(keyword);
            result = res.showMatchingTask(taskList.toArray(new Task[0]));
            break;
        default:
            throw new CarterException("I'm sorry, but I don't know what that means :-(");
        }
        return result;
    }
}
