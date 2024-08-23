import java.util.ArrayList;

public class Parser {
    private static ArrayList<String> list = new ArrayList<>();

    public static void parse(String input) {
        if (!input.equals("list")) {
            list.add(input);
            Ui.echo(input);
        } else {
            Ui.showList(list.toArray());
        }
    }
}
