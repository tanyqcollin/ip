import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Carter {
    public static void main(String[] args) {
        Ui.showWelcomeMessage();

        Storage s = new Storage("./data/Carter.txt");

        List<Task> taskList = new ArrayList<>();
        try {
            taskList = s.load();
            if(!taskList.isEmpty()) {
                Parser.parse("list", taskList);
            }
        } catch (CarterException e) {
            Ui.showError(e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        String input;
        do {
            input = sc.nextLine();
            try {
                if (!input.equals("bye")) Parser.parse(input, taskList);
            } catch (CarterException e){
                Ui.showError(e.getMessage());
            }
        } while (!input.equals("bye"));

        try {
            s.save(taskList);
        } catch (CarterException e) {
            Ui.showError(e.getMessage());
        }

        Ui.showEndingMessage();
    }
}
