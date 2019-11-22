import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class GameFrame extends JFrame implements ActionListener{
	
	private Board board;
	private JPanel mainPane;
	private JPanel boardContainer;
	private Game currentGame;
	private JPanel buttonDisplay;
	private ArrayList<JButton> buttons;
	private JPanel pieceDisplay;
	private ArrayList<PButton> pieceButtons;
	private boolean hint;
	

	public GameFrame(Board brd, ArrayList<Player> players, Game gm, boolean h) {
		super("Blokus!");
		board = brd;
		currentGame = gm;
		hint = h;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPane = new JPanel();
		mainPane.setLayout(new BorderLayout());
		
		//add Board
		boardContainer = new JPanel();
		GridBagLayout boardLayout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		boardContainer.setLayout(boardLayout);
		boardContainer.add(board, gbc);
		mainPane.add(boardContainer, BorderLayout.CENTER);
		
		//Piece Bench
		pieceDisplay = new JPanel();
		pieceDisplay.setLayout(new FlowLayout(FlowLayout.CENTER,1,1));
		//pieceDisplay.setBorder(new EmptyBorder(10,10,10,10));
		pieceDisplay.setPreferredSize(new Dimension(1000,200));
		pieceDisplay.setOpaque(false);
		pieceButtons = new ArrayList<PButton>(21);

		mainPane.add(pieceDisplay, BorderLayout.PAGE_END);
		drawPieceBench();
		
		
		//Buttons
		buttons = new ArrayList<JButton>();
		buttonDisplay = new JPanel();
		buttonDisplay.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonDisplay.setPreferredSize(new Dimension(800,50));
		buttons.add(new JButton("Rotate"));
		buttons.add(new JButton("Flip - Horizontal"));
		buttons.add(new JButton("Flip - Vertical"));
		buttons.add(new JButton("Save Game"));
		buttons.add(new JButton("End Game"));
		if(hint) {buttons.add(new JButton("Get Hint"));}
		buttons.forEach(b -> b.addActionListener(this));
		buttons.forEach(b -> buttonDisplay.add(b));
		mainPane.add(buttonDisplay, BorderLayout.PAGE_START);
		
		add(mainPane);
		setSize(1200,1000);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	 
    
    public void drawPieceBench() {
    	pieceDisplay.setVisible(false);
    	pieceButtons.clear();
    	pieceDisplay.removeAll();
    	pieceDisplay.revalidate();
    	currentGame.getCurrentPlayer().getPieces().forEach(pc -> pieceButtons.add(new PButton(pc)));
    	pieceButtons.forEach(b -> b.addActionListener(this));
    	pieceButtons.forEach(b -> pieceDisplay.add(b));
    	pieceDisplay.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		final Object source = e.getSource();
		if(buttons.contains(source)) {
			int indx = buttons.indexOf(source);
			System.out.println("Button: "+indx);
			switch(indx) {
				case 0: board.getSelectedPiece().rotate();
						break;
				case 1: board.getSelectedPiece().flipH();
						break;
				case 2: board.getSelectedPiece().flipV();
						break;
				case 3: try{currentGame.saveGame();
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
						break;
				case 4: currentGame.endGame();
				break;
				case 5: board.getHint();
				break;
			}
		}
		else if(pieceButtons.contains(source)) {
			int ID = pieceButtons.get(pieceButtons.indexOf(source)).getID();
			board.setSelectedPiece(currentGame.getCurrentPlayer().getPiece(ID));
		}
		
	}
	
	protected class PButton extends JButton{
		/**
		 * 
		 */
		private static final long serialVersionUID = 6824477338618608484L;
		private int ID;
		
		public PButton(Piece p) {
			super();
			ID = p.getID();
			String text = Integer.toString(ID);
			String colour = p.getColour();
			
			this.setMaximumSize(new Dimension(100,100));
			this.setMinimumSize(new Dimension(20,20));
			
			ImageIcon icon = null;
			
			switch(colour) {
			case "#167BFF": 
				icon = new ImageIcon("src/Piece_Images/"+Integer.toString(ID+1)+"_B.png", text); break;
			case "#25BE00": 
				icon = new ImageIcon("src/Piece_Images/"+Integer.toString(ID+1)+"_G.png", text); break;
			case "#BE0000": 
				icon = new ImageIcon("src/Piece_Images/"+Integer.toString(ID+1)+"_R.png", text); break;
			case "#F3DF13": 
				icon = new ImageIcon("src/Piece_Images/"+Integer.toString(ID+1)+"_Y.png", text); break;
			default: break;
			}
			Image img = icon.getImage() ;    
			icon = new ImageIcon(img);
			
			this.setBorderPainted(false);
			this.setOpaque(false);
			this.setContentAreaFilled(false);
			this.setIcon(icon);
			
		}
		
		public PButton(int ID) {
			super(Integer.toString(ID));
			this.ID = ID;
		}
		
		public int getID() {
			return ID;
		}
		
	}

}
