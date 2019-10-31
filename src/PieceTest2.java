import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class PieceTest2 {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testPiece() {
		Piece piece1 = new  Piece(8,"Blue");
		
		System.out.println(piece1);
	}

	@Test
	public void testRotate() {
		Piece piece2 = new  Piece(8,"Blue");
		System.out.println("Rotating piece (1)... \n");
		piece2.rotate();
		System.out.println(piece2);
		System.out.println("Rotating piece (2)... \n");
		piece2.rotate();
		System.out.println(piece2);
		System.out.println("Rotating piece (3)... \n");
		piece2.rotate();
		System.out.println(piece2);
	}

	@Test
	public void testFlipV() {
		Piece piece2 = new  Piece(8,"Blue");
		System.out.println("Flipping piece Vertically... \n");
		piece2.flipV();
		System.out.println(piece2);
	}

	@Test
	public void testFlipH() {
		Piece piece2 = new  Piece(8,"Blue");
		System.out.println("Flipping piece Horizontally... \n");
		piece2.flipH();
		System.out.println(piece2);
	}

}
