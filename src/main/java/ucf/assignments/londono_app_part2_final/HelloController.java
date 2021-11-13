/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Julian Londono
 */
package ucf.assignments.londono_app_part2_final;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HelloController implements Initializable {

    private ToDoList main_list = new ToDoList();

    @FXML
    private TextField date;

    @FXML
    private TextField file_path;

    @FXML
    private Button apply;

    @FXML
    private TextField file_name;

    @FXML
    private ChoiceBox<String> edit_sel;

    @FXML
    private Button show;

    @FXML
    private ChoiceBox<String> filters;

    @FXML
    private Button Add_Item;

    @FXML
    private Button imp;

    @FXML
    private Button remove;

    @FXML
    private TextField des;

    @FXML
    private TextField new_des;

    @FXML
    private TextField name;

    @FXML
    private TextField new_date;

    @FXML
    private Button export;

    @FXML
    private ChoiceBox<String> remove_sel;

    @FXML
    private ChoiceBox<String> status;

    @FXML
    private TextArea output;

    @FXML
    private ListView<String> table;

    FileChooser fileChooser = new FileChooser();
    FileChooser fileChooser_in = new FileChooser();

    // Adds new item to main list when add button is pressed with correct parmaters
    @FXML
    void add_pressed(ActionEvent event) {
        if (name.getLength()> 0 && des.getLength() > 0 && isDateValid(date.getText()) && !main_list.get_names().contains(name.getText()) ) {
            Task mytask = Task.buildTask(name.getText(), date.getText(), des.getText(), "Incomplete");
            main_list.add_task(mytask);
            output.setText("Task Added");
            refresh();

        }
        else {
            output.setText("Please make you have entered an input into all required fields and that the" +
                    " the date if of correct format YYYY-MM-DD");
        }

    }

    // Removes item from list
    @FXML
    void remove_pressed(ActionEvent event) {
        for (int i = 0; i< main_list.get_names().size(); i++) {
            Task task = main_list.get_list().get(i);
            if (remove_sel.getValue().equals(task.getTitle())) {
                main_list.remove_task(task);
            }
        }

        output.setText("Item Removed");
        refresh();

    }



    // Applies changes to selected item
    @FXML
    void apply_pressed(ActionEvent event) {
        for (int i = 0; i < main_list.get_names().size(); i++) {
            Task task = main_list.get_list().get(i);
            if (edit_sel.getValue().equals(task.getTitle())) {
                if (new_des.getLength() > 0) {
                    task.setDescription(new_des.getText());
                }
                if (isDateValid(new_date.getText())) {
                    task.setDueDate(new_date.getText());
                }
                if (!status.getSelectionModel().isEmpty()) {
                    task.setStatus(status.getValue());
                }
            }


        }
        refresh();
        output.setText("Items Updated");
    }

    // Showcases filtered items

    @FXML
    void show_pressed(ActionEvent event) {
        int x = -1;
        if (filters.getValue().equals("Done")) {
            x = 1;
        }
        else if (filters.getValue().equals("Incomplete")) {
            x = 2;
        }

        else {
            x = 0;
        }

        ArrayList<String> filtered = main_list.as_text(x);
        table.getItems().clear();
        table.getItems().addAll(filtered);
        table.refresh();


    }

    // Exports file

    @FXML
    void export_pressed(ActionEvent event) {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", ".txt"));
        fileChooser.setInitialFileName("untitled.txt");

        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            String path = file.getAbsolutePath();
            try {
                FileOutputStream fos = new FileOutputStream(path);
                byte[] strToBytes = main_list.tofile().getBytes();
                fos.write(strToBytes);
                fos.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // Imports file
    @FXML
    void imp_pressed(ActionEvent event) throws FileNotFoundException {
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser_in.getExtensionFilters().add(extFilter);
        File file = fileChooser_in.showOpenDialog(new Stage());
        main_list = readfile(file);
        refresh();


    }

    // Checks if date is valid

    final static String DATE_FORMAT = "dd-MM-yyyy";

    public static boolean isDateValid(String date)
    {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Initizlizes data

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> filter = new ArrayList<String>();
        filter.add("Done");
        filter.add("Incomplete");
        filter.add("All");
        ObservableList<String> myfilterlist = FXCollections.observableList(filter);
        filters.getItems().addAll(myfilterlist);


        ArrayList<String> statuses = new ArrayList<String>();
        statuses.add("Done");
        statuses.add("Incomplete");
        ObservableList<String> mystatuslist = FXCollections.observableList(statuses);
        status.getItems().addAll(mystatuslist);

        fileChooser.setInitialDirectory(new File("C:\\Users\\j\\londono_app_part2_final\\src\\main\\java\\ucf\\assignments\\londono_app_part2_final"));
    }

    // Refreshes values

    public void refresh () {
        remove_sel.getItems().clear();
        remove_sel.getItems().addAll(main_list.get_names());

        table.getItems().clear();
        table.getItems().addAll(main_list.get_full_string());
        table.refresh();

        date.setText("");
        name.setText("");
        des.setText("");
        table.refresh();

        edit_sel.getItems().clear();
        edit_sel.getItems().addAll(main_list.get_names());

        new_date.setText("");
        new_des.setText("");
        status.getSelectionModel().clearSelection();

    }

    // Reads input file

    public ToDoList readfile (File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (scan.hasNext()) {
            String current = scan.nextLine();
            String [] tmp = current.split(" ");
            tasks.add(Task.buildTask(tmp[0], tmp[1], tmp[2], tmp[3]));
        }

        ToDoList newlist = ToDoList.buildList("New", tasks);

        return newlist;
    }
}