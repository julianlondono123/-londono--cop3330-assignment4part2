/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Julian Londono
 */
package ucf.assignments.londono_app_part2_final;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;


public class ToDoList {

    private String title;
    private ArrayList<Task> tasks = new ArrayList<Task>();


    // Getters
    public String getTitle() {
        return title;
    }

    // Setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    // Adds new task given inputs

    public void add_item(String title, String dueDate, String status,  String des) {
        Task new_task = Task.buildTask(title, dueDate, des, status);
        tasks.add(new_task);
    }

    // Returns list of tasks

    public ArrayList<Task> get_list () {
        return tasks;
    }

    // Adds task given task

    public void add_task(Task task) {
        tasks.add(task);
    }

    // Rmeoves tasks
    public void remove_task (Task task) {
        tasks.remove(task);
    }

    // Returns list of names within list

    public ArrayList<String> get_names () {
        ArrayList<String> names = new ArrayList<String>();
        for (Task mytask: tasks) {
            names.add(mytask.getTitle());
        }

        return names;
    }

    // Returns list for display
    public ArrayList<String> get_full_string () {
        ArrayList<String> full = new ArrayList<String>();
        for (Task mytask: tasks) {
            full.add("Name: " + mytask.getTitle() + " | Description " +mytask.getDescription() + " | Due Date: " + mytask.getDueDate());
        }

        return full;
    }

    // Returns filtered list

    public ArrayList<String> as_text(int x) {
        ArrayList <String> filtered = new ArrayList<String>();
        for (Task mytask : tasks) {
            if (x == 0) {
                filtered.add(mytask.getTitle());

            }
            if (x == 1) {
                if (mytask.getStatus().equals("Done") ) {
                    filtered.add(mytask.getTitle());
                }
            }
            else if (x==2) {
                if (mytask.getStatus().equals("Incomplete")) {
                    filtered.add(mytask.getTitle());
                }
            }
        }

        return filtered;
    }


    // Builder


    public static ToDoList buildList(String title, ArrayList<Task> tasks ) {
        ToDoList mylist = new ToDoList();

        mylist.setTitle(title);
        mylist.setTasks(tasks);


        return mylist;
    }


    // Converts list for file output
    public String tofile () {
        String x = "";
        for (Task task : tasks) {
            x = x + task.getTitle() + " " + task.getDescription() +
            " " + task.getDueDate() + " " + task.getStatus() + "\n";
        }

        return  x;
    }



}