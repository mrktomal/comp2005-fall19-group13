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
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 24;
		gbc.gridwidth = 24;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		mainPane.add(board, gbc);
		
		//Buttons
		buttons = new ArrayList<JButton>();
		buttons.add(new JButton("Rotate"));
		buttons.add(new JButton("Flip - Horizontal"));
		buttons.add(new JButton("Flip - Vertical"));
		buttons.forEach(b -> b.addActionListener(this));
		buttons.forEach(b -> addButton(b, mainPane, gbc, 25, buttons.indexOf(b)+25));
		
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
        //gbc.anchor = GridBagConstraints.LINE_END;
        cont.add(obj, gbc);   
    }
    
    public void drawPieceBench() {
    	
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
		
	}

}
