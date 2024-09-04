package carter;

import java.util.Scanner;

/**
 * The main application for Carter chat box system.
 */
public class Carter {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Response res;

    /**
     * Constructs a new instance of Carter with specified file path for storage.
     *
     * @param filePath The file path to store the tasks.
     */
    public Carter(String filePath) {
        ui = new Ui();
        res = new Response();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (CarterException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Carter application, handle user input in the loop until user exit.
     */
    public void run() {
        ui.showWelcomeMessage();

        boolean isExit = false;
        Scanner sc = new Scanner(System.in);
        while (!isExit) {
            try {
                String input = sc.nextLine();
                if (!input.equals("bye")) {
                    Parser.parse(input, tasks, ui);
                } else {
                    isExit = true;
                }
            } catch (CarterException e) {
                ui.showError(e.getMessage());
            }
        }
        sc.close();

        try {
            storage.save(tasks.getTasks());
        } catch (CarterException e) {
            ui.showError(e.getMessage());
        }

        ui.showEndingMessage();
    }

    /**
     * Returns String of response with specified input
     * @param input
     * @return String of response
     */
    public String getResponse(String input) {
        try {
            if (!input.equals("bye")) {
                return Parser.parse(input, tasks, res);
            } else {
                saveData();
                return res.showEndingMessage();
            }
        } catch (CarterException e) {
            return res.showError(e.getMessage());
        }
    }

    public void saveData() {
        try {
            storage.save(tasks.getTasks());
        } catch (CarterException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * The main method that initializes and runs the Carter application.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        new Carter("./data/Carter.txt").run();
    }
}
