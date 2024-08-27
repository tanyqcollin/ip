import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws CarterException {
        List<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else {
                List<String> data = new LinkedList<>();
                Scanner s = new Scanner(file);

                while (s.hasNext()) {
                    data.add(s.nextLine());
                }

                for (String row : data) {
                    String[] parts = row.split("\\|");
                    String taskType = parts[0].trim();
                    System.out.println(taskType);
                    boolean isDone = parts[1].trim().equals("1");

                    switch(taskType) {
                    case "T":
                        tasks.add(new ToDo(parts[2], isDone));
                        break;
                    case "D":
                        tasks.add(new Deadline(parts[2], parts[3], isDone));
                        break;
                    case "E":
                        tasks.add(new Event(parts[2], parts[3], parts[4], isDone));
                        break;
                    default:
                        throw new CarterException("File has been corrupted");
                    }
                }
            }

        } catch (IOException e) {
            throw new CarterException("Error loading task from the file");
        }
        return tasks;
    }

    public void save(List<Task> tasks) throws CarterException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task.toFileString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new CarterException("Error saving data to file");
        }
    }
}
