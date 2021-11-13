package ucf.assignments.londono_app_part2_final;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {


    ArrayList<Task> testertasks= new ArrayList<Task>();

    ToDoList mylist = ToDoList.buildList("New", testertasks);
    ToDoList app = new ToDoList();


    @Test
    void getTitle() {
        assertEquals("New", mylist.getTitle());
    }

    @Test
    void setTitle() {
        mylist.setTitle("newlist");
        assertEquals("newlist", mylist.getTitle());
    }

    @Test
    void setTasks() {
        Task task1 = Task.buildTask("Brush", "09-02-2001", "Brush Hair", "Incomplete");
        Task task2 = Task.buildTask("Wash", "09-02-2001", "Wash car", "Incomplete");
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(task1);
        tasks.add(task2);
        mylist.setTasks(tasks);

        assertEquals("Wash", mylist.get_names().get(1));

    }

    @Test
    void add_item() {
        mylist.add_item("Brush", "09-02-2001", "Brush Hair", "Incomplete");
        assertEquals("Brush", mylist.get_names().get(0));
    }

    @Test
    void get_list() {
        Task task1 = Task.buildTask("Brush", "09-02-2001", "Brush Hair", "Incomplete");
        Task task2 = Task.buildTask("Wash", "09-02-2001", "Wash car", "Incomplete");
        ArrayList<Task> tasks = new ArrayList<Task>();
        mylist.setTasks(tasks);

        assertEquals(tasks, mylist.get_list());
    }

    @Test
    void add_task() {
        Task task1 = Task.buildTask("Brush", "09-02-2001", "Brush Hair", "Incomplete");
        mylist.add_task(task1);
        assertEquals("Brush", mylist.get_names().get(0));
    }

    @Test
    void remove_task() {
        Task task1 = Task.buildTask("Brush", "09-02-2001", "Brush Hair", "Incomplete");
        Task task2 = Task.buildTask("Wash", "09-02-2001", "Wash car", "Incomplete");
        ArrayList<Task> tasks = new ArrayList<Task>();
        mylist.setTasks(tasks);
        mylist.remove_task(task1);
        assertEquals(mylist.get_names().size(), 0);
    }

    @Test
    void get_names() {
        Task task1 = Task.buildTask("Brush", "09-02-2001", "Brush Hair", "Incomplete");
        Task task2 = Task.buildTask("Wash", "09-02-2001", "Wash car", "Incomplete");
        ArrayList<Task> tasks = new ArrayList<Task>();
        mylist.setTasks(tasks);
        assertEquals(0, mylist.get_names().size());
    }

    @Test
    void get_full_string() {
        Task mytask = Task.buildTask("Brush", "09-02-2001", "Brush Hair", "Incomplete");
        mylist.add_task(mytask);
        assertEquals("Name: " + mytask.getTitle() + " | Description " +mytask.getDescription() + " | Due Date: " + mytask.getDueDate()
        ,mylist.get_full_string().get(0) );
    }




}