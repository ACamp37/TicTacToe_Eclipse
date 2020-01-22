package tictactoe;

import java.util.Scanner;

public class PlayTicTacToe {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(true) {
			Board.playTicTacToe();
			System.out.println();
			System.out.print("Do you want to play again?");
			String answer = input.nextLine().toLowerCase();
			if(answer.length() != 1 || answer.charAt(0) != 'y') break;
		}
		input.close();
	}
}
