import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class GameFrame extends JFrame implements ActionListener{
	
	private Board board;
	private JPanel mainPane;
	private Game currentGame;
	private ArrayList<JButton> buttons;
	private JPanel pieceDisplay;
	private ArrayList<PButton> pieceButtons;
	
	private int BOARD_POS = 0;
	private int BOARD_WIDTH = 24;
	private int BUTTON_START_POS = BOARD_WIDTH+1;
	private int PIECEDISPLAY_POS = BOARD_WIDTH+1;

	public GameFrame(Board brd, ArrayList<Player> players, Game gm) {
		super("Blokus!");
		board = brd;
		currentGame = gm;
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPane = new JPanel();
		mainPane.setLayout(layout);
		
		//add Board
		gbc.gridx = BOARD_POS;
		gbc.gridy = BOARD_POS;
		gbc.gridheight = BOARD_WIDTH;
		gbc.gridwidth = BOARD_WIDTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(0,0,0,0);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		mainPane.add(board, gbc);
		
		//Piece Bench
		pieceDisplay = new JPanel();
		pieceDisplay.setLayout(new FlowLayout());
		pieceButtons = new ArrayList<PButton>(21);
		gbc.gridx = BOARD_POS; // In line with board.
		gbc.gridy = PIECEDISPLAY_POS;
		gbc.gridwidth = BOARD_WIDTH;
		gbc.gridheight = 1;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.fill = GridBagConstraints.VERTICAL;
		mainPane.add(pieceDisplay, gbc);
		drawPieceBench();
		
		
		//Buttons
		buttons = new ArrayList<JButton>();
		buttons.add(new JButton("Rotate"));
		buttons.add(new JButton("Flip - Horizontal"));
		buttons.add(new JButton("Flip - Vertical"));
		buttons.forEach(b -> b.addActionListener(this));
		buttons.forEach(b -> addButton(b, mainPane, gbc, BUTTON_START_POS, buttons.indexOf(b)));
		
		add(mainPane);
		setSize(1000,1000);
		setVisible(true);
		
	}
	
    public void addButton(Component obj, Container cont, GridBagConstraints gbc, int gridx, int gridy){
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        cont.add(obj, gbc);   
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
			switch(indx) {
				case 0: board.getSelectedPiece().rotate();
				case 1: board.getSelectedPiece().flipH();
				case 2: board.getSelectedPiece().flipV();
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
			this.setText(Integer.toString(ID));;
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
