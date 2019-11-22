import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PlayerTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testAddScore() {
		Player test = new Player("#167BFF", "Player1", false);
		test.addScore();
		assertTrue(test.getScore()==-89);
	}

	@Test
	void testPiecesRemaining() {
		Player test = new Player("#167BFF", "Player1", false);
		assertEquals(test.piecesRemaining(),21);
	}

	@Test
	void testHasPieces() {
		Player test = new Player("#167BFF", "Player1", false);
		assertTrue(test.hasPieces());
	}

}
