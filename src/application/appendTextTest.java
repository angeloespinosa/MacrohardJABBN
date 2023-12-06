package application;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AppendTextTest {

    @Test
    public void testAppendText() {
        // Create a Model instance
        Model model = new Model();

        // Call the appendText method
        model.appendText("Appended Text");

        // Verify the result
        String expectedText = "Appended Text";
        String actualText = model.getText();

        assertEquals(expectedText, actualText);
    }
}
