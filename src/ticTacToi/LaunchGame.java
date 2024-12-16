package ticTacToi;

import java.util.Random;
import java.util.Scanner;


//Main class represents the Tic-Toc-Toe game
class TicTacToe{
	
	//2D array[3*3] to represents the game board 
	static char[][] board;
	
	
	//constructor to initialize the board
	public TicTacToe() {
		board = new char[3][3];
		initBoard();
	}
	
	//method to initialize the board with empty spaces
	void initBoard() {
		for(int i=0;i<=2;i++) {
			for(int j=0;j<=2;j++) {
				board[i][j] = ' ';
			}
		}
	}
	
	//method to display current state of board
	static void displayBoard() {
		System.out.println("-------------");
		for(int i=0;i<=2;i++) {
			System.out.print("| ");
			for(int j=0;j<=2;j++) {
				System.out.print(board[i][j] + " | ");				
			}
			System.out.println();
			System.out.println("-------------");
		}
	}
	
	//method to place[x,o] on the board at particular position
	static void placeMark(int row, int col, char mark) {
		if(row>=0 && row<=2 && col>=0 && col<=2) {
			board[row][col]= mark;
		}
		else {
			System.out.println("Invalid Position");
		}
	}
	
	//method to check for a win in any column
	static boolean checkColWin() {
		for(int j=0;j<=2;j++) {
			
			if(board[0][j]!=' ' && board[0][j] == board[1][j]
					&& board[1][j] == board[2][j]) {
				return true;
			}
		}	
		
		return false;
	}
	
	//method to check for a win in any row
	static boolean checkRowWin() {
		for(int i=0;i<=2;i++) {
			if(board[i][0]!=' ' && board[i][0]== board[i][1] 
					&& board[i][1]==board[i][2]) {
				return true;
			}
		}
		return false;
	}
	
	//method to check for a diagonal win
	static boolean checkDiagWin() {
		if(board[0][0]!=' ' && board[0][0]==board[1][1]
				&& board[1][1]==board[2][2] 
				|| board[0][2]!=' ' && 
				board[0][2]==board[1][1] && board[1][1]== board[2][0]) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//method to check if the game is draw
	static boolean checkDraw() {
		for(int i=0;i<=2;i++) {
			for(int j=0;j<=2;j++) {
				if(board[i][j]==' ') {
					return false;
				}
			}
		}
		return true;
	}
}


//Abstract class representing a generic player
abstract class Player{
	String name;
	char mark;
	
	//abstract method to be implemented bt subclasses for making a move
	abstract void makeMove();
	
	//method to check if the move is valid within bounds or empty cell
	boolean isValidMove(int row, int col) {
		if(row>= 0 && row<=2 &&
				col >= 0 && col<= 2) {
			if(TicTacToe.board[row][col]== ' ') {
				return true;
			}
		}
		return false;
	}
}

//HumanPlayer class extending Player class,representing human player
class HumanPlayer extends Player
{
	
	
	//constructor to initializing the human player with a name and mark
	HumanPlayer(String name, char mark){
		this.name=name;
		this.mark=mark;
	}
	
	
	//method for human player to make a move
	@Override
	void makeMove() {
		
		Scanner scan=new Scanner(System.in);
		int row;
		int col;
		
		
		//loop until a valid move is entered
		do {
			System.out.println("enter row and col");
			row=scan.nextInt();
			col=scan.nextInt();
		}while(!isValidMove(row,col));
		
		
		//place the human player mark on board
		TicTacToe.placeMark(row, col, mark);
	}
	
}


//AIPlayer class extending Player, representing an AI player
class AIPlayer extends Player
{
	
	
	//constructor to initializing the AI player with a name and mark
	AIPlayer(String name, char mark){
		this.name=name;
		this.mark=mark;
	}
	
	
	//method for the AI player to make a move
	@Override
	void makeMove() {
		//Scanner scan = new Scanner(System.in);
		int row;
		int col;
		
		Random r=new Random(); 
		
		//loop until a valid move genered
		do {
			row=r.nextInt(3);
			col=r.nextInt(3);
		}while(!isValidMove(row,col));
		
		
		//place the AI player's mark on board
		TicTacToe.placeMark(row, col, mark);		
		
	}
	
}



//LaunchGame class containing the main method to start the game
public class LaunchGame {

	public static void main(String[] args) {
		
		//Initialize the Tic-Tok-Toe game
		TicTacToe t = new TicTacToe();
		
		
		//create a Human Player and An AI Player
		HumanPlayer p1= new HumanPlayer("Jayanth", 'X');
		AIPlayer p2= new AIPlayer("JayantAI",'O');
		
		
		//set the current player to human player
		Player cp;
		cp = p1;
		
		//game loop
		while(true)
		{
			System.out.println(cp.name + " turn");
			
			
			//current player make a move
			cp.makeMove();
			
			
			//Display the current state of the board
			TicTacToe.displayBoard();
			
			//check if the current player has won
			if(TicTacToe.checkColWin() || TicTacToe.checkDiagWin()
					||TicTacToe.checkRowWin())
			{
				System.out.println(cp.name + " has won");
				 break;
			}
			
			
			//check if game draw
			else if(TicTacToe.checkDraw())
			{
				System.out.println("Game draw");
				break;
			}
			else {
				
				//Switch the current player
				cp = (cp == p1) ? p2: p1;
				
			}
		}
	}

}

