package carter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class StorageTest {
    @TempDir
    private Path tempDir;

    private Storage storage;
    private File testFile;

    @BeforeEach
    public void setup() {
        testFile = tempDir.resolve("testTasks.txt").toFile();
        storage = new Storage(testFile.getPath());
    }

    @Test
    public void testSaveNullTaskList() {
        assertThrows(AssertionError.class, () -> storage.save(null), "Should throw assertion error for null task list");
    }

    @Test
    void testLoadEmptyFile() throws CarterException {
        List<Task> tasks = storage.load();
        assertTrue(tasks.isEmpty(), "Tasks list should be empty for a new file");
    }

    @Test
    public void testSaveAndLoadTasks() throws Exception {
        List<Task> tasks = List.of(
                new ToDo("Buy milk", false),
                new Deadline("Submit assignment", LocalDateTime.now(), false)
        );

        storage.save(tasks);
        List<Task> loadedTasks = storage.load();

        // Verify that the tasks loaded are the same as those saved
        assertTrue(loadedTasks.size() == tasks.size(), "Loaded tasks should match saved tasks");
        assertTrue(loadedTasks.get(0).description.equals("Buy milk"), "First task should be a ToDo task");
    }

    @Test
    public void testArchiveTasks() throws Exception {
        List<Task> tasks = List.of(new ToDo("Walk the dog", true));

        storage.save(tasks);
        storage.archive(tasks);

        File archiveFile = new File(tempDir.toFile(), "archive.txt");
        assertTrue(archiveFile.exists(), "Archive file should be created");

        List<Task> archivedTasks = new Storage(archiveFile.getPath()).load();
        assertTrue(archivedTasks.size() == 1, "Archived tasks should be loaded correctly");
    }

    @Test
    public void testFileCreationOnLoad() throws Exception {
        File nonExistentFile = tempDir.resolve("nonExistentFile.txt").toFile();
        Storage nonExistentStorage = new Storage(nonExistentFile.getPath());

        List<Task> tasks = nonExistentStorage.load();
        assertTrue(tasks.isEmpty(), "Tasks list should be empty for a new file");

        assertTrue(nonExistentFile.exists(), "File should be created when it doesn't exist");
    }
}
