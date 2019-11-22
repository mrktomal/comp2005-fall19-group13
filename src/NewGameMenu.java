import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    	hint = true;
    	
        frame = new JFrame("Blokus The Game!");
        frame.setResizable(true);
        panel = new JPanel();
        label = new JLabel("New Game");
        label2 = new JLabel("Players:");
        label3 = new JLabel("Difficulty:");
        label4 = new JLabel("Settings");
        butStart = new JButton("Start");
        btwoPlayer = new JButton("2-Player");
        bthreePlayer = new JButton("3-Player");
        bfourPlayer = new JButton("4-Player");
        bEasy = new JButton("Easy");
        bMedium = new JButton("Medium");
        bHard = new JButton("Hard");
        bHinttog = new JButton("Hints: On");
        bVisAidtog = new JButton("Hints: Off");

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
        btwoPlayer.addActionListener(this);
        bthreePlayer.addActionListener(this);
        bfourPlayer.addActionListener(this);
        bEasy.addActionListener(this);
        bMedium.addActionListener(this);
        bHard.addActionListener(this);
        bHinttog.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object source = e.getSource();
        if(source ==butStart) {
            frame.setVisible(false);
            Game test = new Game(numOfPlayers, difficulty, hint);
            test.startGame();
        }
        else if(source ==btwoPlayer) {
        	numOfPlayers = 2;
        	label2.setText("Players: 2");
        }
        else if(source == bthreePlayer) {
        	numOfPlayers = 3;
        	label2.setText("Players: 3");
        }
        else if(source == bfourPlayer) {
        	numOfPlayers = 4;
        	label2.setText("Players: 4");
        }
        else if(source == bEasy) {
        	difficulty = 1;
        	label3.setText("Difficulty: Easy");
        }
        else if(source == bMedium) {
        	difficulty = 2;
        	label3.setText("Difficulty: Medium");
        }
        else if(source == bHard) {
        	difficulty = 3;
        	label3.setText("Difficulty: Hard");
        }
        else if(source == bHinttog) {
        	if (bHinttog.getText().contentEquals("Hints: On")){
        		hint = false;
        		bHinttog.setText("Hints: Off");
        	}
        	else {
        		hint = true;
        		bHinttog.setText("Hints: On");
        	}
        }
 
    }
}