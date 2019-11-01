import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JButton but[], butStart, butCredits, butLoadGame, butNewGame;

    public MainMenu() {
        frame = new JFrame("Blokus The Game!");
        frame.setResizable(true);
        panel = new JPanel();
        label = new JLabel("Blokus");
        butStart = new JButton("Start Game");
        butCredits = new JButton("Credits");
        butNewGame = new JButton("New  Game");
        butLoadGame = new JButton("Load Game");
        butCredits.setBounds(50, 100, 100, 60);
    }
    public void init() {
        frame.setVisible(true);
        frame.setSize(1024, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        frame.add(panel);
        label.setBounds(500, 100, 100, 60);
        panel.add(label);
        butCredits.setBounds(420,200,200,60);
        panel.add(butCredits);
        butLoadGame.setBounds(250,400,150,60);
        panel.add(butLoadGame);
        butNewGame.setBounds(650,400,150,60);
        panel.add(butNewGame);
        butNewGame.addActionListener(this);
        //butStart.setBounds(200,150,100,60);
        //panel.add(butStart);



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        final Object source = e.getSource();

        if (source == butNewGame);{
            NewGameMenu NMen = new NewGameMenu();
            NMen.init();
            frame.setVisible(false);


        }


    }
}

