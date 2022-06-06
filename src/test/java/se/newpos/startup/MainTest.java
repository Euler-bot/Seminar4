package se.newpos.startup;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class MainTest {

	private Main mainToTest;

	@BeforeEach
	public void setup() {
		this.mainToTest = new Main();
	}

	@Test
	public void testMain() {
		// TODO: initialize args
		String[] args = null; 

		Main.main(args);

		// TODO: assert scenario
	}
}
