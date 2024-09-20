package carter;

/**
 * The main application for the Carter chat box system.
 */
public class Carter {
    private Storage storage;
    private TaskList tasks;
    private Response response;

    /**
     * Constructs a new instance of Carter with a specified file path for storage.
     *
     * @param filePath The file path to store the tasks.
     */
    public Carter(String filePath) {
        response = new Response();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (CarterException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Displays the welcome message when the user starts the application.
     *
     * @return A string representing the welcome message.
     */
    public String showWelcomeMessage() {
        return response.showWelcomeMessage();
    }

    /**
     * Processes the user's input and returns the appropriate response.
     *
     * @param input The input provided by the user.
     * @return A string containing the chatbot's response based on the user input.
     */
    public String getResponse(String input) {
        try {
            switch (input) {
            case "bye":
                saveData();
                return response.showEndingMessage();
            case "archive":
                return archiveTask();
            default:
                return Parser.parse(input, tasks, response);
            }
        } catch (CarterException e) {
            return response.showError(e.getMessage());
        }
    }

    /**
     * Archives the current tasks and returns a confirmation message.
     *
     * @return A string that confirms the task has been archived.
     * @throws CarterException If there is an error during archiving.
     */
    private String archiveTask() throws CarterException {
        storage.archive(tasks.getTasks());
        TaskList originalTasks = tasks;
        tasks = tasks.archiveTask();
        return response.showArchiveTask(originalTasks.getTasks().toArray(new Task[0]));
    }

    /**
     * Saves the current task list to the file.
     * If an error occurs during saving, it logs the error message.
     */
    public void saveData() {
        try {
            storage.save(tasks.getTasks());
        } catch (CarterException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }
}
