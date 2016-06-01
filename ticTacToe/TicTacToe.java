package ticTacToe;

/**
 * How the User and Computer sees the board (The indexing does not start from 0):
 * | 1 | 2 | 3
 * ------------
 * | 4 | 5 | 6
 * ------------
 * | 7 | 8 | 9
 * 
 * But, the character array used to represent the board starts from the index 0.
 */
public class TicTacToe {
	
	protected char[] board; // 3x3 board with 9 squares
	protected char playerToken; // the token selected by the player
	protected char computerToken; // the token for the computer selected by the player
	protected char winner;
	protected char currentToken; // To indicate who is currently playing - player of computer?
	protected char initialToken; // The characters on the board before the game begins
	
	/**
	 * Constructor initializes the player and computer tokens as selected by the user.
	 * Also sets up the board. 
	 * @param playerToken
	 * @param computerToken
	 */
	public TicTacToe(char playerToken, char computerToken) {
		this.board = setUpBoard(this.playerToken, this.computerToken);
		this.playerToken = playerToken;
		this.computerToken = computerToken;
		this.initialToken = ' ';
		this.winner = '-';
	}
	
	/**
	 * Initialize all the squares of the board with the initial value.
	 * @param playerToken
	 * @param computerToken
	 * @return the character array representing the board.
	 */
	private char[] setUpBoard(char playerToken, char computerToken) {
		char[] board = new char[9];
		for(int i=0; i<9; i++)
			board[i] = this.initialToken;
		return board;
	}
	
	/**
	 * Marks the chosen square (if valid) with the appropriate token (player or computer).
	 * Then, depending on who played this turn, hand over the next turn to the player or computer.
	 * @param chosenSquare - the square number on the board (between 1 and 9)
	 * @return true if this turn was played successfully, else return false.
	 */
	public boolean playTurn(int chosenSquare) {
		boolean isSquareValid = (isWithinRange(chosenSquare) && !isSquareTaken(chosenSquare));
		if(isSquareValid) {
			board[chosenSquare-1] = currentToken;
			currentToken = (currentToken == playerToken) ? computerToken:playerToken;
		}
		return isSquareValid;
	}

	/**
	 * Check if the chosen square has already been marked.
	 * @param chosenSquare - the square number on the board (between 1 and 9)
	 * @return true if the chosen square has already been marked, else return false.
	 */
	private boolean isSquareTaken(int chosenSquare) {
		return (board[chosenSquare-1] != this.initialToken);
	}

	/**
	 * Check if the entered square number is valid (lies between 1 and 9).
	 * @param chosenSquare - the square number on the board (between 1 and 9)
	 * @return true is number is valid, else false.
	 */
	private boolean isWithinRange(int chosenSquare) {
		return (0<chosenSquare && chosenSquare<board.length+1);
	}
	
	
	/**
	 * Print how the board looks at the start of the game.
	 * |  |  |
	 * ---------
	 * |  |  |
	 * ---------
	 * |  |  |
	 */
	public void printBoard() {
		System.out.println();
		for(int i=0; i<board.length; i++) {
			if(i%3 == 0 && i!= 0) {
				System.out.println();
				System.out.println("-------------");
			}
			System.out.print(" | " + board[i]);
		}
		System.out.println();
	}
	
	
	/**
	 * Check if there is a winner.
	 * @return true if there is a winner, else false
	 */
	public boolean isThereAWinner() {
		// Check if the right diagonal, left diagonal, middle row or middle column is completed. 
		boolean diagonalsAndMiddles = (rightDiagonal() || leftDiagonal() || middleRow() || middleColumn()) && board[4]!=this.initialToken;
		boolean topAndFirst = (topRow() || firstColumn()) && board[0]!=this.initialToken;
		boolean bottomAndThird = (bottomRow() || thirdColumn()) && board[8]!= this.initialToken;
		
		if(diagonalsAndMiddles) this.winner = board[4];
		else if(topAndFirst) this.winner = board[0];
		else if(bottomAndThird) this.winner = board[8];
		
		return diagonalsAndMiddles || topAndFirst || bottomAndThird;
	}

	/**
	 * Check if the right diagonal squares have the same tokens.  
	 * @return true if they have same tokens, else false.
	 */
	private boolean rightDiagonal() {
		return (board[0]==board[4] && board[4]==board[8]);
	}
	
	/**
	 * Check if the left diagonal squares have the same tokens.  
	 * @return true if they have same tokens, else false.
	 */
	private boolean leftDiagonal() {
		return (board[2]==board[4] && board[4]==board[6]);
	}
	
	/**
	 * Check if the top row squares have the same tokens.  
	 * @return true if they have same tokens, else false.
	 */
	private boolean topRow() {
		return (board[0]==board[1] && board[1]==board[2]);
	}
	
	/**
	 * Check if the middle row squares have the same tokens.  
	 * @return true if they have same tokens, else false.
	 */
	private boolean middleRow() {
		return (board[3]==board[4] && board[4]==board[5]);
	}
	
	/**
	 * Check if the bottom row squares have the same tokens.  
	 * @return true if they have same tokens, else false.
	 */
	private boolean bottomRow() {
		return (board[6]==board[7] && board[7]==board[8]);
	}
	
	/**
	 * Check if the first column squares have the same tokens.  
	 * @return true if they have same tokens, else false.
	 */
	private boolean firstColumn() {
		return (board[0]==board[3] && board[3]==board[6]);
	}
	
	/**
	 * Check if the middle column squares have the same tokens.  
	 * @return true if they have same tokens, else false.
	 */
	private boolean middleColumn() {
		return (board[1]==board[4] && board[4]==board[7]);
	}
	
	/**
	 * Check if the third column squares have the same tokens.  
	 * @return true if they have same tokens, else false.
	 */
	private boolean thirdColumn() {
		return (board[2]==board[5] && board[5]==board[8]);
	}
	
	
	/**
	 * Check if all the squares in the board have been marked.
	 * @return true if all squares are marked, else false.
	 */
	private boolean isTheBoardFilled() {
		for(int i=0; i<board.length; i++) {
			if(board[i] == initialToken)
				return false;
		}
		return true;
	}
	
	/**
	 * Check if the game is over - is there a winner or is it a draw.
	 * @return The appropriate string in the case of a win, a draw or if the game is not yet over.
	 */
	public String isGameOver() {
		boolean didSomeoneWin = isThereAWinner();
		if(didSomeoneWin)
			return this.winner + " won the game!";
		else if(isTheBoardFilled())
			return "Game over! It's a draw!";
		else 
			return "It's not over yet!";
	}
	
	
}// end of class TicTacToeSetUp
