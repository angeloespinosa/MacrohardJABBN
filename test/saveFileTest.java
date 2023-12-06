/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Angelo
 */
public class saveFileTest {
    
    private JABBN jabbn;
    private Stage stage;
    private File testFile;
    
    @Before
    public void setUp() throws IOException {
        new JFXPanel();
        
        Platform.runLater(() -> {
            jabbn = new JABBN();
            stage = new Stage();
        });
        
        testFile = File.createTempFile("testfile", ".txt");
    }
    
    @Test
    public void saveFileTest() {
        Platform.runLater(() -> {
            jabbn.start(stage);
            
            jabbn.model.setText("Hello World");
            
            jabbn.saveFile(stage);
            
            assertTrue(testFile.exists());
            try {
                String fileContent = new String(Files.readAllBytes(testFile.toPath()));
                assertEquals("Hello World", fileContent.trim());
            } catch (IOException e) {
                fail("IOException occurred: " + e.getMessage());
            }
        });
    }
    
}
