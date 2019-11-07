import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class TestingInterface extends JFrame implements ActionListener {
	
	private JFrame frame;
	private JPanel panel;
	private JButton bRotate, bFlipH, bFlipV, bSave, bNextPiece, bNextPlayer;
	private JTextArea display;
	//private JLabel lRotate, lFlipH, lFlipV, lSave;
	
	private Board gameBoard;
	private ArrayList<Player> testPlayers;
	private Player currentPlayer;
	
	public TestingInterface(Board gameBoard, ArrayList<Player> players) {
		
		testPlayers = players; 
		currentPlayer = testPlayers.get(0);
		this.gameBoard = gameBoard;
		
		frame = new JFrame("Testing");
		frame.setResizable(true);
		panel = new JPanel();
		
		display = new JTextArea(10,8);
		bRotate = new JButton("Rotate");
		bFlipH = new JButton("Flip - Horizontal");
		bFlipV = new JButton("Flip - Vertical");
		bSave = new JButton("Save");
		bNextPiece = new JButton("Next Piece");
		bNextPlayer = new JButton("Next Player");
		
	}
	
	public void init() {
		frame.setLayout(new FlowLayout());
		//frame.setComponentOrientation(ComponentOrientation.TOP_TO_BOTTOM);
		frame.setSize(800, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.add(display);
		panel.add(bRotate);
		panel.add(bFlipH);
		panel.add(bFlipV);
		panel.add(bSave);
		panel.add(bNextPiece);
		panel.add(bNextPlayer);
		
		bRotate.addActionListener(this);
		bFlipH.addActionListener(this);
		bFlipV.addActionListener(this);
		bSave.addActionListener(this);
		bNextPiece.addActionListener(this);
		//bNextPlayer.addActionListener(this);
		
		
		gameBoard.setSelectedPiece(currentPlayer.getPieces().get(0));
		display.setText(gameBoard.getSelectedPiece().toString());
		
		frame.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		final Object source = e.getSource();
		
		if(source == bRotate){
			gameBoard.getSelectedPiece().rotate();
			display.setText(gameBoard.getSelectedPiece().toString());
		} 
		else if(source == bFlipH){
			gameBoard.getSelectedPiece().flipH();
			display.setText(gameBoard.getSelectedPiece().toString());
		}
		else if(source == bFlipV){
			gameBoard.getSelectedPiece().flipV();
			display.setText(gameBoard.getSelectedPiece().toString());
		}
		else if(source == bNextPiece) {
			Piece curPiece = gameBoard.getSelectedPiece();
			gameBoard.setSelectedPiece(currentPlayer.getPieces().get((curPiece.getID()+1)%21));
			display.setText(gameBoard.getSelectedPiece().toString());

		}
		
	}
	
}
