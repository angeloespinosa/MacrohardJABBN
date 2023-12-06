package application;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ClearTextTest {
    
    private Model model;

    @Before
    public void setUp() {
        model = new Model();
    }

    @Test
    public void test() {
        //set some text in the editor
        model.setText("Hello world");

        //clear the text using the clearText method
        model.clearText();

        //check to make sure the text is now empty
        assertTrue(model.getText()=="");
    }

}