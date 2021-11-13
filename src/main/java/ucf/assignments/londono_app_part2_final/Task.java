/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Julian Londono
 */
package ucf.assignments.londono_app_part2_final;

import java.util.Date;




public class Task {

    private String title;
    private String dueDate;
    private String status;
    private String description;

    // Getters
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }


    public String getStatus() {
        return status;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Buidler
    public static Task buildTask(String title, String dueDate, String des, String status ) {
        Task task = new Task();


        task.setTitle(title);
        task.setDueDate(dueDate);
        task.setDescription(des);
        task.setStatus(status);

        return task;
    }


}