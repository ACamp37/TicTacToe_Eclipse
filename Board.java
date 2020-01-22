package tictactoe;

import java.util.Scanner;

public final class Board {

	//public static final byte SQUARECOUNT = 9;
	public static final byte X = 1;
	public static final byte O = 2;
	byte[][] board = new byte[3][3];
	
	private Board() {
		
	}

	public void move(byte xOrO, byte oneBasedIndex) {
		oneBasedIndex -= 1;
		int row = oneBasedIndex / 3, col = oneBasedIndex % 3;
		if (board[row][col] != 0)
			throw new IllegalMoveException("Square is already used.");
		board[row][col] = xOrO;
	}

	public boolean isAWinner(byte xOrO) {
		boolean found = false;
		// Check rows
		for (int row = 0; row < 3; row++) {
			found = true;
			for (int col = 0; col < 3; ++col)
				if (board[row][col] != xOrO) {
					found = false;
					break;
				}
			if (found)
				return true;
		}
		// Check columns
		for (int col = 0; col < 3; ++col) {
			found = true;
			for (int row = 0; row < 3; ++row)
				if (board[row][col] != xOrO) {
					found = false;
					break;
				}
			if (found)
				return true;
		}
		// Check diagonals
		if (board[0][0] == xOrO && board[1][1] == xOrO && board[2][2] == xOrO)
			return true;
		if (board[2][0] == xOrO && board[1][1] == xOrO && board[0][2] == xOrO)
			return true;
		return false;
	}

	public void draw() {
	//	System.out.println("----------");
		for (int i = 0; i < 3; i++) {
			System.out.print("|");
			for (int j = 0; j < 3; ++j) {
				char c = board[i][j] == 0 ? ' ' : board[i][j] == 1 ? 'X' : 'O';
				System.out.print(c + "|");
			}
		}
	//	System.out.println("----------");

	}
	
	
	public boolean isFull() {
		for(int row=0;row<3;++row) {
			for(int col=0;col<3;++col) {
				if(board[row][col]==0) return false;
			}
		}
		return true;
	}
	
	private static byte getNextMove(Scanner input, char c) {
		while(true) {
			System.out.printf("Player %c - enter your position (1-9): ", c);
			try {
				return input.nextByte();
			}
			catch(Exception ex) {
				System.out.println("Sorry - invalid input.");
				if(input.hasNext()) input.nextLine();
			}
		}
	}

	public static void playTicTacToe() {
		byte currentPlayer = X;
		Board board = new Board();
		Scanner input = new Scanner(System.in);
		while(true) {
			board.draw();
			char c = (currentPlayer == X) ? 'X' : 'O';
			do {
				byte sqr = getNextMove(input, c);
				try {
					board.move(currentPlayer, sqr);
					break;
				}
				catch(IllegalMoveException ex) {
					System.out.println("Stop cheating!  Try again.");
				}
			} while(true);
			if (board.isAWinner(currentPlayer)) {
				System.out.println("Congratulations!  You win!");
				board.draw();
				return;
			} else if(board.isFull()) {
				System.out.println("Sorry - no winner this time!");
				return;
			}
			currentPlayer = (currentPlayer == X) ? O : X; //If player is x, put x else O
		}
	}
}