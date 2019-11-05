import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class NewGameMenu implements ActionListener{
    private JFrame frame;
    private JPanel panel;
    private JLabel label, label2, label3, label4;
    private JButton but[], btwoPlayer, bthreePlayer,
            bfourPlayer, bEasy, bMedium, bHard, bHinttog, bVisAidtog, butStart;
    
    private int difficulty; // 0- Easy 1- Medium 2- Hard 
    private int numOfPlayers; //
    private boolean hint;


    public NewGameMenu() {
    	
    	numOfPlayers = 4;
    	difficulty = 0;
    	
        frame = new JFrame("Blokus The Game!");
        frame.setResizable(true);
        panel = new JPanel();
        label = new JLabel("New Game");
        label2 = new JLabel("Players");
        label3 = new JLabel("Difficulty");
        label4 = new JLabel("Settings");
        butStart = new JButton("Start");
        btwoPlayer = new JButton("2-Player");
        bthreePlayer = new JButton("3-Player");
        bfourPlayer = new JButton("4-Player");
        bEasy = new JButton("Easy");
        bMedium = new JButton("Medium");
        bHard = new JButton("Hard");
        bHinttog = new JButton("Hint: On");
        bVisAidtog = new JButton("Hint: Off");

    }

    public void init() {
        frame.setVisible(true);
        frame.setSize(1024, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        frame.add(panel);
        label.setBounds(500, 100, 100, 60);
        panel.add(label);
        label2.setBounds(260, 200, 200,60);
        panel.add(label2);
        label3.setBounds(500,200, 200, 60 );
        panel.add(label3);
        label4.setBounds(720, 200, 200, 60);
        panel.add(label4);
        btwoPlayer.setBounds(210, 250, 150, 60);
        panel.add(btwoPlayer);
        bthreePlayer.setBounds(210, 350, 150, 60);
        panel.add(bthreePlayer);
        bfourPlayer.setBounds(210, 450, 150, 60);
        panel.add(bfourPlayer);
        butStart.setBounds(700, 500, 150, 60);
        panel.add(butStart);
        bEasy.setBounds(450, 250, 150, 60);
        panel.add(bEasy);
        bMedium.setBounds(450, 350, 150, 60);
        panel.add(bMedium);
        bHard.setBounds(450, 450, 150, 60);
        panel.add(bHard);
        bHinttog.setBounds(700, 250, 100, 60);
        panel.add(bHinttog);
        bVisAidtog.setBounds(700, 350, 100, 60);
        
        butStart.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object source = e.getSource();
        if(source ==butStart); {
            frame.setVisible(false);
            
            Game test = new Game();
            test.startGame();
            
            try {
				test.saveGame();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }

    }
}