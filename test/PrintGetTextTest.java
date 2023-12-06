package application;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javafx.print.PrinterJob;

public class PrintGetTextTest {

	private Model model;

	@Before
	public void setUp() throws Exception {
		model = new Model();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testGetText() {
		String testingString = "testing string";
		model.setText(testingString);
		System.out.println(model.getText());
		assertTrue(testingString.equalsIgnoreCase(model.getText()));
	}

	@Test
	public void testPrint() {
		PrinterJob printerJob = PrinterJob.createPrinterJob();
		if (printerJob != null) {
			boolean success = printerJob.showPrintDialog(primaryStage);
			if (success) {
				// printerJob.printText(model.getText());
				printerJob.endJob();
			}
		}
	}
}
