package ticTacToe;

import java.util.ArrayList;

public class ComputerWith160IQ {
	
	public int pickSquare(TicTacToe game) {
		ArrayList<Integer> possibleSquares = new ArrayList<Integer>();
		for(int i=0; i<game.board.length; i++){
			if(game.board[i] != game.initialToken)
				possibleSquares.add(i+1); // The board indexing is from 1 to 9 even for the computer.
		}
		Random rand = new Random();
	}
}
