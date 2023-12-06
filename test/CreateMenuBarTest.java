package application;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateMenuBarTest extends ApplicationTest{

    private JABBN jabbn;

    @Before
    public void setUp() throws Exception {
        //start the JavaFX application on the JavaFX application thread
        Platform.startup(() -> {
            jabbn = new JABBN();
            jabbn.start(new Stage());
        });
    }

    @Test
    public void test() {
        //run JavaFX operations on the JavaFX application thread
        Platform.runLater(() -> {
            jabbn.createMenuBar(null); //passing null for Stage since it's not needed for this test

            //get the MenuBar from the JABBN instance
            MenuBar menuBar = (MenuBar) jabbn.view.getTop();

            //check if MenuBar is not null
            assertNotNull("MenuBar should not be null", menuBar);

            //check the number of menus in the MenuBar
            assertEquals("MenuBar should have one menu", 1, menuBar.getMenus().size());

            //check the number of menu items in the "File" menu
            Menu fileMenu = menuBar.getMenus().get(0);
            assertEquals("File menu should have four items", 4, fileMenu.getItems().size());

            //check the text of the first menu item
            MenuItem openMenuItem = fileMenu.getItems().get(0);
            assertEquals("First menu item should be 'Open'", "Open", openMenuItem.getText());

            //check the text of the second menu item
            MenuItem saveMenuItem = fileMenu.getItems().get(1);
            assertEquals("Second menu item should be 'Save'", "Save", saveMenuItem.getText());

            //check the text of the third menu item
            MenuItem printMenuItem = fileMenu.getItems().get(2);
            assertEquals("Third menu item should be 'Print'", "Print", printMenuItem.getText());

            //check the text of the fourth menu item
            MenuItem mergeMenuItem = fileMenu.getItems().get(3);
            assertEquals("Fourth menu item should be 'Merge'", "Merge", mergeMenuItem.getText());

        });
     
    }
}