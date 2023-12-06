package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class JABBN extends Application {

    private Model model;
    private View view;
    private Controller controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        model = new Model();
        view = new View();
        controller = new Controller(model, view);

        MenuBar menuBar = new MenuBar();

        // File Menu
        Menu fileMenu = new Menu("File");

        // Open MenuItem
        MenuItem openMenuItem = new MenuItem("Open");
        openMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        openMenuItem.setOnAction(e -> openFile(primaryStage));
        fileMenu.getItems().add(openMenuItem);

        // Save MenuItem
        MenuItem saveMenuItem = new MenuItem("Save");
        saveMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        saveMenuItem.setOnAction(e -> saveFile(primaryStage));
        fileMenu.getItems().add(saveMenuItem);

        // Print MenuItem
        MenuItem printMenuItem = new MenuItem("Print");
        printMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));
        printMenuItem.setOnAction(e -> printFile(primaryStage));
        fileMenu.getItems().add(printMenuItem);

        // Merge MenuItem
        MenuItem mergeMenuItem = new MenuItem("Merge");
        mergeMenuItem.setOnAction(e -> mergeFiles(primaryStage));
        fileMenu.getItems().add(mergeMenuItem);

        menuBar.getMenus().add(fileMenu);

        // Set the MenuBar in the top of the BorderPane
        view.setTop(menuBar);

        Scene scene = new Scene(view, 1920, 1080);
        primaryStage.setTitle("Macrohard JABBN");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void openFile(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");
        File file = fileChooser.showOpenDialog(primaryStage);

        if (file != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    content.append(line).append("\n");
                }
                model.setText(content.toString());
                view.setText(content.toString());
            } catch (IOException e) {
                showAlert("Error", "Error opening file", e.getMessage());
            }
        }
    }

    private void saveFile(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Text File");
        File file = fileChooser.showSaveDialog(primaryStage);

        if (file != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write(model.getText());
            } catch (IOException e) {
                showAlert("Error", "Error saving file", e.getMessage());
            }
        }
    }

    private void printFile(Stage primaryStage) {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob != null) {
            boolean success = printerJob.showPrintDialog(primaryStage);
            if (success) {
               // printerJob.printText(model.getText());
                printerJob.endJob();
            }
        } else {
            showAlert("Error", "Error creating PrinterJob", "Cannot create PrinterJob.");
        }
    }

    private void mergeFiles(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Merge Text Files");
        File firstFile = fileChooser.showOpenDialog(primaryStage);
        File secondFile = fileChooser.showOpenDialog(primaryStage);

        if (firstFile != null && secondFile != null) {
            try (BufferedReader br1 = new BufferedReader(new FileReader(firstFile));
                 BufferedReader br2 = new BufferedReader(new FileReader(secondFile))) {

                StringBuilder mergedContent = new StringBuilder();

                String line1;
                while ((line1 = br1.readLine()) != null) {
                    mergedContent.append(line1).append("\n");
                }

                mergedContent.append("\n"); // Add a separator between files

                String line2;
                while ((line2 = br2.readLine()) != null) {
                    mergedContent.append(line2).append("\n");
                }

                model.setText(mergedContent.toString());
                view.setText(mergedContent.toString());

            } catch (IOException e) {
                showAlert("Error", "Error merging files", e.getMessage());
            }
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}


