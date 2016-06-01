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
public class TicTacToeSetUp {
	
	protected char[] board; // 3x3 board with 9 squares
	protected char playerToken; // the token selected by the player
	protected char computerToken; // the token for the computer selected by the player
	protected char winner;
	protected char currentToken; // To indicate who is currently playing - player of computer?
	protected char initialToken; // The characters on the board before the game begins
	
	public TicTacToeSetUp(char playerToken, char computerToken) {
		this.board = setUpBoard(this.playerToken, this.computerToken);
		this.playerToken = playerToken;
		this.computerToken = computerToken;
		this.initialToken = ' ';
		this.winner = '-';
	}
	
	/**
	 * 
	 * @param playerToken
	 * @param computerToken
	 * @return
	 */
	private char[] setUpBoard(char playerToken, char computerToken) {
		char[] board = new char[9];
		for(int i=0; i<9; i++)
			board[i] = this.initialToken;
		return board;
	}
	
	/**
	 * 
	 * @param chosenSquare
	 * @return
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
	 * 
	 * @param chosenSquare
	 * @return
	 */
	private boolean isSquareTaken(int chosenSquare) {
		return (board[chosenSquare-1] != this.initialToken);
	}

	/**
	 * 
	 * @param chosenSquare
	 * @return
	 */
	private boolean isWithinRange(int chosenSquare) {
		return (0<chosenSquare && chosenSquare<board.length+1);
	}
	
	
	/**
	 * Print how the board looks before the game is started
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
	 * 
	 * @return
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
	 * 
	 * @return
	 */
	private boolean rightDiagonal() {
		return (board[0]==board[4] && board[4]==board[8]);
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean leftDiagonal() {
		return (board[2]==board[4] && board[4]==board[6]);
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean topRow() {
		return (board[0]==board[1] && board[1]==board[2]);
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean middleRow() {
		return (board[3]==board[4] && board[4]==board[5]);
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean bottomRow() {
		return (board[6]==board[7] && board[7]==board[8]);
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean firstColumn() {
		return (board[0]==board[3] && board[3]==board[6]);
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean middleColumn() {
		return (board[1]==board[4] && board[4]==board[7]);
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean thirdColumn() {
		return (board[2]==board[5] && board[5]==board[8]);
	}
	
	
	/**
	 * 
	 * @return
	 */
	private boolean isTheBoardFilled() {
		for(int i=0; i<board.length; i++) {
			if(board[i] == initialToken)
				return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @return
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
