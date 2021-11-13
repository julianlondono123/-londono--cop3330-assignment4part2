package ucf.assignments.londono_app_part2_final;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    Task task = Task.buildTask("Brush", "09-02-2001", "Brush Hair", "Incomplete");
    Task app = new Task();
    @Test
    void getTitle() {

        assertEquals("Brush", task.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("Brush Hair", task.getDescription());
    }

    @Test
    void getDueDate() {
        assertEquals("09-02-2001", task.getDueDate());
    }

    @Test
    void getStatus() {
        assertEquals("Incomplete", task.getStatus());
    }

    @Test
    void setTitle() {
        assertEquals("Brush", task.getTitle());
    }

    @Test
    void setDescription() {
        task.setDescription("Black hair");
        assertEquals("Black hair", task.getDescription());
    }

    @Test
    void setDueDate() {
        task.setDueDate("03-24-2001");
        assertEquals("03-24-2001", task.getDueDate());
    }

    @Test
    void setStatus() {
        task.setStatus("Done");
        assertEquals("Done", task.getStatus());
    }

    @Test
    void buildTask() {
        assertEquals(task, task);
    }
}