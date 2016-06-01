package ticTacToe;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class which runs the application. Initially, the board is set up as below:
 * 
 * | - | - | -
 * ------------
 * | - | - | -
 * ------------
 * | - | - | -
 * 
 * During gameplay:
 * | X | - | -
 * ------------
 * | - | O | -
 * ------------
 * | - | - | X
 * 
 * @author Karthik
 *
 */
public class TicTacToeApplication {

	public static void main(String[] args) {
		
		Scanner inp = new Scanner(System.in);
		boolean continuePlaying = true;
		
		while(continuePlaying) {
			
			// Get the player and computer tokens from the user.
			System.out.println("Welcome to Tic-Tac-Toe!\nEnter a single character for yourself: ");
			char playerToken = inp.next().charAt(0);
			System.out.println("Enter a single character for your opponent, the supremely intelligent computer!");
			char computerToken = inp.next().charAt(0);
			
			// Set up the game
			TicTacToe game = new TicTacToe(playerToken, computerToken);
			ComputerWith160IQ computer = new ComputerWith160IQ();
			
			System.out.println("\nWe can start the game now! Type the square number and your token will be added there!");
			game.printBoardWithSquareNumbers();
			
			while(game.isGameOver().equals("notOver")) {
				
				if(game.currentToken == game.playerToken) {
					// User's turn
					int playerChoice  = 0;
					do {
						try {
							System.out.println("\nIt's your turn! Enter your choice!");
							playerChoice = inp.nextInt();
						}
						catch(InputMismatchException e) {
							// Exception is thrown only if a non-integer is entered.
							System.out.println("\nInvalid choice! Please select a number between 1 and 9!\n");
						}
						inp.nextLine();
					} while(playerChoice<=0);
					
					while(!game.playTurn(playerChoice)) {
						// If the entered number is not between 1 and 9 or is already taken.
						System.out.println("The square " + playerChoice + " is invalid. It is either taken of is out of range. Try again!");
						playerChoice =inp.nextInt();
					}
					System.out.print("You picked: square" + playerChoice);
				}
				
				else {
					// The computer's turn
					int computerChoice = computer.pickSquare(game);
					game.playTurn(computerChoice);
					System.out.print("\n\nIt's my turn! I choose square " + computerChoice);
				}
				System.out.println();
				game.printBoard(); // Print the board with current status.
			}
			
			System.out.println();
			System.out.println(game.isGameOver());
			
			System.out.println();
			System.out.println("Do you want to play again? Enter y if yes, else press any onther key!");
			continuePlaying = (inp.next().charAt(0)=='y');
			System.out.println();
			System.out.println();
		}
		
	} // end of main 

} //end of class TicTacToeApplication
