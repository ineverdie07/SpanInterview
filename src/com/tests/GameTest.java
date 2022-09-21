package com.tests;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import com.controller.Game;
import com.exceptions.GameException;

@RunWith(JUnit4.class)
public class GameTest {

	@Test
	public void readInput_Happypath() throws GameException {
		String test = "Lions 3, Snakes 3\n"
				+ "Tarantulas 1, FC Awesome 0\n"
				+ "Lions 1, FC Awesome 1\n"
				+ "Tarantulas 3, Snakes 1\n"
				+ "Lions 4, Grouches 0\n";
		String expected = "1. Tarantulas, 6 pts\n"
				+ "2. Lions, 5 pts\n"
				+ "3. FC Awesome, 1 pt\n"
				+ "4. Snakes, 1 pt\n"
				+ "5. Grouches, 0 pts";
		
		assertTrue(Game.readInput(test).equals(expected));
	}
	
	@Test
	public void readInput_EmptyMessage_TriggerException() {
		Exception exception = assertThrows(GameException.class, () -> {
			Game.readInput("");});
		assertTrue("No values in the message".equals(exception.getMessage()));
	}
	
	@Test
	public void readInput_MissingScore_TriggerException() {
		String test = "Lions 3, Snakes 3\n"
				+ "Tarantulas 1, FC Awesome 0\n"
				+ "Lions 1, FC Awesome 1\n"
				+ "Tarantulas 3, Snakes \n"
				+ "Lions 4, Grouches 0\n";
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Game.readInput(test);});
		assertTrue("The final score should be compose by team name and final score".equals(exception.getMessage()));
	}
	
}
