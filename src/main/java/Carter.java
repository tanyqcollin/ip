import java.util.Scanner;

public class Carter {
    public static void main(String[] args) {
        Ui.showWelcomeMessage();

        Scanner sc = new Scanner(System.in);
        String input;
        do {
            input = sc.nextLine();
            if(!input.equals("bye")) Parser.parse(input);
        } while (!input.equals("bye"));
        Ui.showEndingMessage();

    }
}
