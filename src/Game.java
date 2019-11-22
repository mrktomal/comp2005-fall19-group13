import javax.swing.SwingUtilities;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Game implements Serializable{

	private ArrayList<Player> players;
	private Player currentPlayer;
	private Board board;
	private GameFrame window;
	private int numOfPlayers;
	private int difficulty;
	private boolean hint;
	private boolean visImpaired;
	private boolean playing;
	
	private File boardSaveFile;
	private File playersSaveFile;
	
	//Colours: Color.decode(String)
	//Blue: #167BFF
	//Green: #25BE00
	//Red: #BE0000
	//Yellow: #F3DF13
	
	public Game(int numOfPlayers, int difficulty, boolean h) {
		this.numOfPlayers = numOfPlayers;
		this.difficulty = difficulty;
		this.hint = h;
		players = new ArrayList<Player>(numOfPlayers);
		if(this.numOfPlayers == 4){
			players.add(new Player("#167BFF", "Player1", false));
			players.add(new Player("#F3DF13", "Player2", false));
			players.add(new Player("#BE0000", "Player3", false));
			players.add(new Player("#25BE00", "Player4", false));
		}
		else if(this.numOfPlayers == 3){
			players.add(new Player("#167BFF", "Player1", false));
			players.add(new Player("#F3DF13", "Player2", false));
			players.add(new Player("#BE0000", "Player3", false));
			players.add(new Player("#25BE00", "Player4", true));
		}
		else if(this.numOfPlayers == 2){
			players.add(new Player("#167BFF", "Player1", false));
			players.add(new Player("#F3DF13", "Player2", false));
			players.add(new Player("#BE0000", "Player3", true));
			players.add(new Player("#25BE00", "Player4", true));
		}
		else if(this.numOfPlayers == 1){
			players.add(new Player("#167BFF", "Player1", false));
			players.add(new Player("#F3DF13", "Player2", true));
			players.add(new Player("#BE0000", "Player3", true));
			players.add(new Player("#25BE00", "Player4", true));
		}
	}
	
	public Game() {
		currentPlayer = null;
	}
	
	
	public void startGame(){
		board = new Board(this);
		board.drawBoard(false);
		currentPlayer = players.get(0);	
		currentPlayer.setActive(true);
		window = new GameFrame(board, players, this, hint);
		play();
	}

	public void endGame(){
		new GameOver(players);
		window.dispose();
	}
	
	public void play() {
		playing = true;
		board.setSelectedPiece(currentPlayer.getPieces().get(0));
		if (currentPlayer.isBot()){playComputerTurn();}
	}
	
	public void piecePlayed(Piece currPiece) {
		currentPlayer.getPieces().removeIf(pc -> pc.getID()==currPiece.getID());
		if(currPiece.getID()==0 && !currentPlayer.hasPieces()) {currentPlayer.bonusPoints();}
		nextPlayer();
		window.drawPieceBench();
	}
	
	public void nextPlayer() {
		boolean gameOver = false;
		currentPlayer.setActive(false);
		Player prevPlayer = currentPlayer;
		currentPlayer = players.get((players.indexOf(currentPlayer)+1)%players.size());
		while((!currentPlayer.hasPieces() || !board.legalMovesRemain(currentPlayer)) && !gameOver) {
			currentPlayer = players.get((players.indexOf(currentPlayer)+1)%players.size());
			if(currentPlayer.equals(prevPlayer)) {
				gameOver = true;
			}
		}
		currentPlayer.setActive(true);
		if(gameOver) {
			playing = false;
			this.endGame();
		}
		else{
			play();
		}
		
	}
	
	public void saveGame() throws IOException{
		//save board to a textfile called prevBoard.txt
		boardSaveFile = new File ("prevBoard.txt");
		FileOutputStream boardFileOutStream = new FileOutputStream(boardSaveFile);
		ObjectOutputStream boardObjectOutStream = new ObjectOutputStream(boardFileOutStream);
		boardObjectOutStream.writeObject(board);
		boardObjectOutStream.close();

		//save players linked list to text file called prevPlayers.txt
		playersSaveFile = new File ("prevPlayers.txt");
		FileOutputStream playersFileOutStream = new FileOutputStream(playersSaveFile);
		ObjectOutputStream playersObjectOutStream = new ObjectOutputStream(playersFileOutStream);
		playersObjectOutStream.writeObject(players);
		playersObjectOutStream.close();
	}
	
	public void loadGame() throws IOException, ClassNotFoundException{
		//board = prevBoard.txt
		FileInputStream boardFileInStream = new FileInputStream ("prevBoard.txt");
		ObjectInputStream boardObjectInStream = new ObjectInputStream (boardFileInStream);
		board = (Board) boardObjectInStream.readObject();
		boardObjectInStream.close();

		//players = prevPlayers.txt
		FileInputStream playersFileInStream = new FileInputStream ("prevPlayers.txt");
		ObjectInputStream playersObjectInStream = new ObjectInputStream (playersFileInStream);
		players = (ArrayList<Player>) playersObjectInStream.readObject();
		playersObjectInStream.close();
		
		Player ActivePlayer = players.stream().filter(pl -> pl.isActive()).findFirst().get();
		
		currentPlayer = ActivePlayer;
		
		window = new GameFrame(board, players, this, hint);

	}
	
	public boolean saveExists() {
		
		boolean exists = false;
		
		File test1 = new File("prevBoard.txt");
		File test2 = new File("prevPlayers.txt");

		
		if ((test1.exists() & !test1.isDirectory()) && (test2.exists() && !test2.isDirectory())){
			exists = true;
			System.out.println("Save Exists");			
		}
		else {
			exists  = false;
		}
		
		return exists;
				
	}
	
	public void loadBoard() {
		board.drawBoard(true);
	}

	public void toggleHints(){
		if(hint == true){
			hint = false;
		}
		else{
			hint = true;
		}
	}

	public void toggleVis(){
		if(visImpaired == true){
			visImpaired = false;
		}
		else{
			visImpaired = true;
		}
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void playComputerTurn(){
		if(currentPlayer.isBot()){
			if(this.difficulty == 1){ 							// 1 will denote Easy
				if(currentPlayer.getPieces().size() == 21){
					if(currentPlayer.getName() == "Player3"){
						board.setSelectedPiece(currentPlayer.getPieces().get(0));
						board.placePiece(board.getBoard()[2][21]);
						return;
					}
					else if(currentPlayer.getName() == "Player4"){
						board.setSelectedPiece(currentPlayer.getPieces().get(0));
						board.placePiece(board.getBoard()[21][21]);
						return;
					}
					else{
						board.setSelectedPiece(currentPlayer.getPieces().get(0));
						board.placePiece(board.getBoard()[2][2]);
						return;
					}
				}
				for(int r = 3; r < 21; r++){
					for(int c = 3; c < 21; c++){
						if(board.legalMove(currentPlayer.getPieces().get(0), board.getBoard()[r][c])){
							board.setSelectedPiece(currentPlayer.getPieces().get(0)); // play whatever piece is first in array
							board.placePiece(board.getBoard()[r][c]);
							return;
						}
					}
				}
			}
			else if(this.difficulty == 2){					// 2 will denote Medium
				Random rand = new Random();
				int x = rand.nextInt(currentPlayer.getPieces().size()); //genetates a random int from 0 to currentPlayer.getPieces().size() - 1

				if(currentPlayer.getPieces().size() == 21){
					if(currentPlayer.getName() == "Player3"){
						board.setSelectedPiece(currentPlayer.getPieces().get(0));
						board.placePiece(board.getBoard()[2][21]);
						return;
					}
					else if(currentPlayer.getName() == "Player4"){
						board.setSelectedPiece(currentPlayer.getPieces().get(0));
						board.placePiece(board.getBoard()[21][21]);
						return;
					}
					else{
						board.setSelectedPiece(currentPlayer.getPieces().get(15));
						board.placePiece(board.getBoard()[3][2]);
						return;
					}
				}
				for(int r = 3; r < 21; r++){
					for(int c = 3; c < 21; c++){
						if(board.legalMove(currentPlayer.getPieces().get(x), board.getBoard()[r][c])){
							board.setSelectedPiece(currentPlayer.getPieces().get(x));
							board.placePiece(board.getBoard()[r][c]);
							return;
						}
					}
				}
			}
			else if(this.difficulty == 3){ 					// 3 will denote Hard
				if(currentPlayer.getPieces().size() == 21){
					if(currentPlayer.getName() == "Player4"){
						board.setSelectedPiece(findCurrentPlayersLargestPiece());
						board.placePiece(board.getBoard()[21][20]);
						return;
					}
					else if(currentPlayer.getName() == "Player3"){
						board.setSelectedPiece(currentPlayer.getPieces().get(14));
						board.placePiece(board.getBoard()[4][21]);
						return;
					}
					else{
						board.setSelectedPiece(currentPlayer.getPieces().get(11));
						board.placePiece(board.getBoard()[4][2]);
						return;
					}
				}
				for(int r = 3; r < 21; r++){
					for(int c = 3; c < 21; c++){
						if(board.legalMove(findCurrentPlayersLargestPiece(), board.getBoard()[r][c])){
							board.setSelectedPiece(findCurrentPlayersLargestPiece());
							board.placePiece(board.getBoard()[r][c]);
							return;
						}
						if(board.legalMove(findCurrentPlayersLargestPiece(), board.getBoard()[r][c])){
							board.setSelectedPiece(findCurrentPlayersLargestPiece());
							board.placePiece(board.getBoard()[r][c]);
							return;
						}
					}
				}

			}

		}
	}


	public Piece findCurrentPlayersLargestPiece(){
		int biggest = 0;
		for(int i = 0; i < currentPlayer.getPieces().size(); i++){
			int temp = currentPlayer.getPieces().get(i).getSize();
			if(temp > biggest){
				biggest = i;
			}
		}
		return currentPlayer.getPieces().get(biggest);
	}

	
	
	
	
	
	
}
