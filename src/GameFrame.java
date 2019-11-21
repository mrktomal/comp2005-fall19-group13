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
		//gbc.gridx = BOARD_POS;
		//gbc.gridy = BOARD_POS;
		gbc.gridheight = BOARD_WIDTH;
		gbc.gridwidth = BOARD_WIDTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(10,10,10,10);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		mainPane.add(board, gbc);
		
		//Piece Bench
		pieceDisplay = new JPanel();
		pieceDisplay.setLayout(new FlowLayout());
		pieceButtons = new ArrayList<PButton>(21);
		gbc.gridx = BOARD_POS; // In line with board.
		gbc.gridy = PIECEDISPLAY_POS;
		gbc.gridwidth = BOARD_WIDTH;
		gbc.gridheight = 1;
		gbc.weightx = 0.2;
		gbc.weighty = 0.2;
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
		buttons.add(new JButton("Save Game"));
		buttons.forEach(b -> b.addActionListener(this));
		buttons.forEach(b -> addButton(b, mainPane, gbc, BUTTON_START_POS, buttons.indexOf(b)+BOARD_WIDTH/2));
		
		add(mainPane);
		setSize(1920,1080);
		setVisible(true);
		
	}
	
    public void addButton(Component obj, Container cont, GridBagConstraints gbc, int gridx, int gridy){
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.insets = new  Insets(10,10,10,10);
        gbc.ipadx = 0;
        gbc.ipady = 10;
        //gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.BOTH;
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
			System.out.println("Button: "+indx);
			switch(indx) {
				case 0: board.getSelectedPiece().rotate();
						break;
				case 1: board.getSelectedPiece().flipH();
						break;
				case 2: board.getSelectedPiece().flipV();
						break;
				case 3:
						if (currentGame.saveExists()) {
							int reply = JOptionPane.showConfirmDialog(null, "A save exists, would you like to overwrite?", "Warning", JOptionPane.YES_NO_OPTION);
							if (reply == JOptionPane.YES_OPTION) {
								try{currentGame.saveGame();
								} catch (IOException e1) {
									
									e1.printStackTrace();
								}
							}
							else {
		
							}
						}
						else {
							try{currentGame.saveGame();
							} catch (IOException e1) {
								
								e1.printStackTrace();
							}
						}
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
