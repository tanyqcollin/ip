package carter;

import java.util.Scanner;

public class Carter {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Carter(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (CarterException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

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
    public static void main(String[] args) {
        new Carter("./data/Carter.txt").run();
    }
}
