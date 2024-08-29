import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from.trim(), formatter);
        this.to = LocalDateTime.parse(to.trim(), formatter);
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = LocalDateTime.parse(from.trim(), formatter);
        this.to = LocalDateTime.parse(to.trim(), formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + "|"
                + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "|"
                + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
