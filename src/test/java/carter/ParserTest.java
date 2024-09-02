package carter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParserTest {
    private Ui ui;
    private TaskList tasks;

    @BeforeEach
    public void setUp() {
        ui = new Ui();
        tasks = new TaskList();
    }

    @Test
    public void test() {

    }

    @Test
    public void testAddTask_success() {
        try {
            Parser.parse("todo read book", tasks, ui);
            assertEquals(1, tasks.getLength());

            Parser.parse("deadline return book /by 2019-10-15 18:00", tasks, ui);
            assertEquals(2, tasks.getLength());

            Parser.parse("event project meeting /from 2019-10-15 14:00 /to 2019-10-15 16:00", tasks, ui);
            assertEquals(3, tasks.getLength());
        } catch (CarterException e) {
            fail();
        }
    }

    @Test
    public void testAddTask_fail() {
        try {
            Parser.parse("todo ", tasks, ui);
            fail();
        } catch (CarterException e) {
            assertEquals("The description of a todo cannot be empty.", e.getMessage());
        }

        try {
            Parser.parse("deadline return book", tasks, ui);
            fail();
        } catch (CarterException e) {
            assertEquals("The description and deadline of a deadline task cannot be empty.", e.getMessage());
        }

        try {
            Parser.parse("event ", tasks, ui);
            fail();
        } catch (CarterException e) {
            assertEquals("The description and times of an event task cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void testMark_success() {
        try {
            Parser.parse("todo read book", tasks, ui);
            Parser.parse("mark 1", tasks, ui);
            Parser.parse("unmark 1", tasks, ui);
        } catch (CarterException e) {
            fail();
        }
    }

    @Test
    public void testMark_fail() {
        try {
            Parser.parse("todo read book", tasks, ui);
            Parser.parse("mark 2", tasks, ui);
            fail();
        } catch (CarterException e) {
            assertEquals("Please provide a valid number", e.getMessage());
        }

        try {
            Parser.parse("todo read book", tasks, ui);
            Parser.parse("mark", tasks, ui);
            fail();
        } catch (CarterException e) {
            assertEquals("Please provide the task number to mark.", e.getMessage());
        }
    }
}
