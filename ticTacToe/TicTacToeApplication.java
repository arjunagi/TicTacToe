package ticTacToe;

import java.util.Scanner;

public class TicTacToeApplication {

	public static void main(String[] args) {
		
		Scanner inp = new Scanner(System.in);
		boolean continuePlaying = true;
		while(continuePlaying) {
			System.out.println("Welcome to Tic-Tac-Toe!\nEnter a single character for yourself: ");
			char playerToken = inp.next().charAt(0);
			System.out.println("Enter a single character for your opponent, the supremely intelligent computer!");
			char computerToken = inp.next().charAt(0);
			TicTacToe game = new TicTacToe(playerToken, computerToken);
			ComputerWith160IQ computer = new ComputerWith160IQ(game);
		}

	}

}
