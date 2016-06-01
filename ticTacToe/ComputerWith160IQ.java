package ticTacToe;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class which simulates the computer player. The computer randomly selects
 * a square from the available ones.
 * @author Karthik
 *
 */
public class ComputerWith160IQ {
	
	/**
	 * The computer randomly selects a square from the available squares.
	 * @param game - The TicTacToe game object.
	 * @return the selected square.
	 */
	public int pickSquare(TicTacToe game) {
		ArrayList<Integer> possibleSquares = new ArrayList<Integer>();
		for(int i=0; i<game.board.length; i++){
			if(game.board[i] == game.initialToken)
				possibleSquares.add(i+1); // The board indexing is from 1 to 9 even for the computer.
		}
		Random rand = new Random();
		int choice = possibleSquares.get(rand.nextInt(possibleSquares.size())); // Get a random index from the array.
		return choice;
	}

} // end of class ComputerWith160IQ

