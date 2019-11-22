import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BoardTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testBoard() {
		fail("Not yet implemented");
	}

	@Test
	void testHoverGridSquare() {
		fail("Not yet implemented");
	}

	@Test
	void testHoverGridSquarePieceString() {
		fail("Not yet implemented");
	}

	@Test
	void testPlacePiece() {
		fail("Not yet implemented");
	}

	@Test
	void testLegalMove() {

	}

	@Test
	void testLegalMovesRemain() {
		Game testGame = new Game(4,1,false);
		Board testBoard = new Board(testGame);
		Player testPlayer = new Player("#167BFF", "Player1", false);
		assertTrue(testBoard.legalMovesRemain(testPlayer));
		//fail("Not yet implemented");
	}

	@Test
	void testGetHint() {
		fail("Not yet implemented");
	}

}
