import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Piece_Test {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Piece test = new Piece(10, "#BE0000");
		System.out.println(test);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	

	@Test
	void testRotate() {
		Piece test = new Piece(10, "#BE0000");
		System.out.println("Rotating...");
		test.rotate();
		System.out.println(test);
	}

	@Test
	void testFlipV() {
		Piece test = new Piece(10, "#BE0000");
		System.out.println("Flipping...");
		test.flipV();
		System.out.println(test);
	}

	@Test
	void testFlipH() {
		Piece test = new Piece(10, "#BE0000");
		System.out.println("Flipping...");
		test.flipH();
		System.out.println(test);
	}

	@Test
	void testPiecePath() {
		Piece test = new Piece(10, "#BE0000");
		System.out.println("Getting path...");
		System.out.println(test.piecePath());
		System.out.println();
	}

}
