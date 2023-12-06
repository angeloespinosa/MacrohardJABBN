package application;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Angelo
 */
public class openFileTest {
    private JABBN jabbn;
    private Stage stage;
    private File testFile;
    
    @BeforeClass
    public static void initJFX(){
        new JFXPanel();
    } 
    
    @Before
    public void setUp() throws IOException{
        Platform.runLater(() ->{
            jabbn = new JABBN();
            stage = new Stage();
            try {
                testFile = File.createTempFile("testfile", ".txt");
        
                try (FileWriter writer = new FileWriter(testFile)){
                    writer.write("Hello World");  
                }
            } catch (IOException e){
                e.printStackTrace();                
            }       
        });
    }
          
    @Test
    public void openFileTest() {
        Platform.runLater(() -> {
            jabbn.start(stage);
            jabbn.openFile(stage);
        
            assertEquals("Hello World", jabbn.model.getText());
        });
    }    
}
