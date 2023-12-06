package application;

import org.junit.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;

public class MergeFilesTest {
    private JABBN jabbn;
    private Stage stage;
    private File testFile1;
    private File testFile2;
    
    @BeforeClass
    public static void initJFX(){
        new JFXPanel();
    }
    
    @Before
    public void setUp() throws IOException {
        jabbn = new JABBN();
        Platform.runLater(() -> {
            stage = new Stage();
        
            try {
                testFile1 = File.createTempFile("testfile1", ".txt");
                testFile2 = File.createTempFile("testfile2", ".txt");
        
                try (FileWriter writer1 = new FileWriter(testFile1)){
                    writer1.write("Hello");
                }
        
                try (FileWriter writer2 = new FileWriter(testFile2)){
                    writer2.write("World");
                }
            } catch (IOException e){
                e.printStackTrace();
            }       
        });        
    }
    
    @Test
    public void testMergeFiles() {    
        Platform.runLater(() -> {
            jabbn.mergeFiles(stage);
            
            String expectedContent = ("Hello\n\nWorld\n");
            assertEquals(expectedContent, jabbn.model.getText());
        });
    }
}

