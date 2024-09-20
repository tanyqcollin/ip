package carter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Handles the parsing of the user command for Carter.
 */
public class Parser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Parses the user input and performs the corresponding action.
     *
     * @param input The user input command as a String.
     * @param tasks The TaskList containing the current tasks.
     * @param res The response used to display messages to the user.
     * @return A string representation of the response.
     * @throws CarterException If the user input is invalid or cannot be recognized.
     */
    public static String parse(String input, TaskList tasks, Response res) throws CarterException {
        String[] words = input.split(" ", 2);
        String command = words[0];

        switch (command) {
        case "list":
            return handleListCommand(tasks, res);
        case "mark":
            return handleMarkCommand(words, tasks, res);
        case "unmark":
            return handleUnmarkCommand(words, tasks, res);
        case "todo":
            return handleTodoCommand(words, tasks, res);
        case "deadline":
            return handleDeadlineCommand(words, tasks, res);
        case "event":
            return handleEventCommand(words, tasks, res);
        case "delete":
            return handleDeleteCommand(words, tasks, res);
        case "find":
            return handleFindCommand(words, tasks, res);
        default:
            throw new CarterException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses a string representing a task index and validates it.
     *
     * @param indexStr The string representing the index.
     * @param taskLength The length of the task list.
     * @return The integer index of the task.
     * @throws CarterException If the index is not a valid number or out of bounds.
     */
    private static int parseTaskIndex(String indexStr, int taskLength) throws CarterException {
        try {
            int index = Integer.parseInt(indexStr) - 1;
            if (index < 0 || index >= taskLength) {
                throw new CarterException("Please provide a valid task number.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new CarterException("Please provide a valid task number.");
        }
    }

    private static String handleListCommand(TaskList tasks, Response res) {
        Task[] output = new Task[tasks.getLength()];
        return res.showList(tasks.getTasks().toArray(output));
    }

    private static String handleMarkCommand(String[] words, TaskList tasks, Response res) throws CarterException {
        validateInputLength(words, 2, "Please provide the task number to mark.");
        int markIndex = parseTaskIndex(words[1], tasks.getLength());
        Task currMarkTask = tasks.markTask(markIndex);
        return res.showMarkTaskMessage(currMarkTask);
    }

    private static String handleUnmarkCommand(String[] words, TaskList tasks, Response res) throws CarterException {
        validateInputLength(words, 2, "Please provide the task number to unmark.");
        int unmarkIndex = parseTaskIndex(words[1], tasks.getLength());
        Task currUnmarkTask = tasks.unMarkTask(unmarkIndex);
        return res.showUnmarkTaskMessage(currUnmarkTask);
    }

    private static String handleTodoCommand(String[] words, TaskList tasks, Response res) throws CarterException {
        validateInputLength(words, 2, "The description of a todo cannot be empty.");
        ToDo toDoTask = new ToDo(words[1].trim());
        tasks.addTask(toDoTask);
        return res.showTask(toDoTask, tasks.getLength());
    }

    private static String handleDeadlineCommand(String[] words, TaskList tasks, Response res) throws CarterException {
        validateInputLength(words, 2, "The description and deadline of a deadline task cannot be empty.");
        String[] deadlineDetail = words[1].split("/", 2);
        if (deadlineDetail.length < 2 || deadlineDetail[0].trim().isEmpty() || deadlineDetail[1].trim().isEmpty()) {
            throw new CarterException("The description and deadline of a deadline task cannot be empty.");
        }
        try {
            Deadline deadlineTask = new Deadline(deadlineDetail[0].trim(),
                    LocalDateTime.parse(deadlineDetail[1].substring(3).trim(), FORMATTER));
            tasks.addTask(deadlineTask);
            return res.showTask(deadlineTask, tasks.getLength());
        } catch (DateTimeParseException e) {
            throw new CarterException("Please fill in the detail with a valid format (yyyy-MM-dd HH:mm).");
        }
    }

    private static String handleEventCommand(String[] words, TaskList tasks, Response res) throws CarterException {
        validateInputLength(words, 2, "The description and times of an event task cannot be empty.");
        String[] eventDetail = words[1].split("/");
        if (eventDetail.length < 3 || eventDetail[0].trim().isEmpty()
                || eventDetail[1].trim().isEmpty() || eventDetail[2].trim().isEmpty()) {
            throw new CarterException("The description and times of an event task cannot be empty.");
        }
        try {
            Event eventTask = new Event(eventDetail[0].trim(),
                    LocalDateTime.parse(eventDetail[1].substring(5).trim(), FORMATTER),
                    LocalDateTime.parse(eventDetail[2].substring(3).trim(), FORMATTER));
            tasks.addTask(eventTask);
            return res.showTask(eventTask, tasks.getLength());
        } catch (DateTimeParseException e) {
            throw new CarterException("Please fill in the detail with a valid format (yyyy-MM-dd HH:mm).");
        }
    }

    private static String handleDeleteCommand(String[] words, TaskList tasks, Response res) throws CarterException {
        validateInputLength(words, 2, "Please provide the task number to delete.");
        int deleteTaskNumber = parseTaskIndex(words[1], tasks.getLength());
        Task deletedTask = tasks.deleteTask(deleteTaskNumber);
        return res.showTaskDeleted(deletedTask, tasks.getLength());
    }

    private static String handleFindCommand(String[] words, TaskList tasks, Response res) throws CarterException {
        validateInputLength(words, 2, "Please provide a keyword to search for.");
        List<Task> taskList = tasks.find(words[1].trim());
        return res.showMatchingTask(taskList.toArray(new Task[0]));
    }

    /**
     * Validates if the input has the required number of elements.
     *
     * @param words The split input array.
     * @param requiredLength The required number of elements.
     * @param errorMessage The error message to throw if validation fails.
     * @throws CarterException If the input does not meet the required length.
     */
    private static void validateInputLength(String[] words, int requiredLength, String errorMessage) throws CarterException {
        if (words.length < requiredLength || words[1].trim().isEmpty()) {
            throw new CarterException(errorMessage);
        }
    }
}
