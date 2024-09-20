package carter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the storage for tasks in the Carter application.
 */
public class Storage {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final String filePath;

    /**
     * Constructs a new instance of Storage with the specified file path.
     *
     * @param filePath The file path where the tasks will be stored.
     */
    public Storage(String filePath) {
        assert filePath != null : "The file path should not be null";
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the storage file. If the file does not exist, it will be created.
     *
     * @return A list of tasks from the storage file.
     * @throws CarterException If the file is corrupted or cannot be read.
     */
    public List<Task> load() throws CarterException {
        List<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                createFile(file);
                return tasks;
            }

            List<String> data = readFile(file);
            for (String row : data) {
                tasks.add(parseTask(row));
            }
        } catch (IOException e) {
            throw new CarterException("Error loading tasks from the file");
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the storage file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws CarterException If there is an error saving the tasks to the file.
     */
    public void save(List<Task> tasks) throws CarterException {
        assert tasks != null : "Task list should not be null";
        writeFile(tasks, filePath);
    }

    /**
     * Archives the list of tasks into an archive file.
     *
     * @param tasks The list of tasks to be archived.
     * @throws CarterException If there is an error archiving the tasks to the file.
     */
    public void archive(List<Task> tasks) throws CarterException {
        File originalFile = new File(filePath);
        File directory = originalFile.getParentFile();
        File archiveFile = new File(directory, "archive.txt");

        try {
            createFile(archiveFile);
            writeFile(tasks, archiveFile.getPath());
        } catch (IOException e) {
            throw new CarterException("Error creating archive file");
        }
    }

    /**
     * Writes the tasks to the specified file.
     *
     * @param tasks    The list of tasks to be written.
     * @param filePath The file path where tasks will be written.
     * @throws CarterException If there is an error writing to the file.
     */
    private void writeFile(List<Task> tasks, String filePath) throws CarterException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : tasks) {
                assert task != null : "Task should not be null";
                fw.write(task.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new CarterException("Error saving data to file");
        }
    }

    /**
     * Creates a new file if it does not exist.
     *
     * @param file The file to be created.
     * @throws IOException If there is an error creating the file.
     */
    private void createFile(File file) throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Reads the content of the specified file.
     *
     * @param file The file to be read.
     * @return A list of lines from the file.
     * @throws IOException If there is an error reading the file.
     */
    private List<String> readFile(File file) throws IOException {
        List<String> data = new LinkedList<>();
        try (Scanner s = new Scanner(file)) {
            while (s.hasNext()) {
                data.add(s.nextLine());
            }
        }
        return data;
    }

    /**
     * Parses a line of text from the file into a Task object.
     *
     * @param line The line of text to be parsed.
     * @return The corresponding Task object.
     * @throws CarterException If the task type is invalid or the file is corrupted.
     */
    private Task parseTask(String line) throws CarterException {
        String[] parts = line.split("\\|");
        String taskType = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");

        switch (taskType) {
        case "T":
            return new ToDo(parts[2].trim(), isDone);
        case "D":
            return new Deadline(parts[2].trim(), LocalDateTime.parse(parts[3].trim(), FORMATTER), isDone);
        case "E":
            return new Event(parts[2].trim(),
                    LocalDateTime.parse(parts[3].trim(), FORMATTER),
                    LocalDateTime.parse(parts[4].trim(), FORMATTER), isDone);
        default:
            throw new CarterException("File is corrupted. Invalid task type: " + taskType);
        }
    }
}
