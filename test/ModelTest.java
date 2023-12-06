package application;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ModelTest {
    private StringBuilder text;

	@Before
	public void setUp() throws Exception {
        text = new StringBuilder();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String newText = "testing string";
        text = new StringBuilder(newText);
        System.out.println(text);
        assertTrue(newText.equalsIgnoreCase(text.toString()));
	}

}
