/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;
import javafx.print.PrinterJob;

public class Main extends Application {

    private TextArea textArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Text Editor");

        textArea = new TextArea();

        MenuBar menuBar = createMenuBar();

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(textArea);

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        // File Menu
        Menu fileMenu = new Menu("File");

        // Open
        MenuItem openItem = new MenuItem("Open");
        openItem.setOnAction(e -> openFile());
        // Save
        MenuItem saveItem = new MenuItem("Save");
        saveItem.setOnAction(e -> saveFile());
        // Print
        MenuItem printItem = new MenuItem("Print");
        printItem.setOnAction(e -> printFile());
        // Merge
        MenuItem mergeItem = new MenuItem("Merge");
        mergeItem.setOnAction(e -> mergeFiles());

        fileMenu.getItems().addAll(openItem, saveItem, printItem, mergeItem);

        menuBar.getMenus().add(fileMenu);

        return menuBar;
    }

    private void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                textArea.setText(content.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        File selectedFile = fileChooser.showSaveDialog(null);

        if (selectedFile != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void printFile() {
        // Implement printing functionality here
        // You may use PrinterJob and other printing APIs
        // Example: https://docs.oracle.com/javafx/2/printing/jfxpub-printing.html
        
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        printerJob.showPageSetupDialog(null);            
        if (printerJob != null) {
            boolean printed = printerJob.printPage(textArea);
            if (printed) {
                printerJob.endJob();}}
    
    }

    private void mergeFiles() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Merge Files");
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);

        if (selectedFiles != null) {
            StringBuilder mergedContent = new StringBuilder();
            for (File file : selectedFiles) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        mergedContent.append(line).append("\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            textArea.setText(mergedContent.toString());
        }
    }
}