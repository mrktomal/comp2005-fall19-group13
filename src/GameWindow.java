import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame implements ActionListener{
	
	private JFrame frame;
	private JPanel panel;

	private Board gameBoard;
	
	public GameWindow() {
		
		gameBoard = new Board();
		
		frame = new JFrame("Blokus!");
		panel = new JPanel(new GridBagLayout());
		GridBagConstraints constr = new GridBagConstraints();
		constr.fill = GridBagConstraints.BOTH;
		constr.gridx = 0;
		constr.gridy = 0;
				
		
		this.add(gameBoard, constr);
		
		gameBoard.drawBoard();
		this.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}


}
