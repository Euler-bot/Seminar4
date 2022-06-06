package se.newpos.view;


import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.newpos.controller.Controller;
import se.newpos.integration.ExternalCreator;
import se.newpos.integration.Printer;



public class ViewTest {
	private View instanceToTest;
	private ByteArrayOutputStream printOutBuffer;
	private PrintStream originalOutputMessage;

	@BeforeEach
	public void setup() {
		Controller controller = new Controller(new ExternalCreator(), new Printer());
		this.instanceToTest = new View(controller);
		
		printOutBuffer = new ByteArrayOutputStream();
		PrintStream inMemPrintStream = new PrintStream(printOutBuffer);
		originalOutputMessage = System.out;
		System.setOut(inMemPrintStream);
	}
	@AfterEach
	public void tearDown(){
		instanceToTest = null;
		printOutBuffer = null;
		System.setOut(originalOutputMessage);
	}

	@Test
	public void testFakeSale() {
		
		instanceToTest.fakeSale();
		String printout = printOutBuffer.toString();
		String expectedOutput = "completed";
		assertTrue(printout.contains(expectedOutput), "UI did not complete correctly. ");
	}
}
