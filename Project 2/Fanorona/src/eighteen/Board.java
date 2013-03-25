package eighteen;

import java.util.ArrayList;
import java.util.List;

import eighteen.BoardManager.BadMoveException;

public class Board {

	Pieces[][] theBoard;
	int blacks;
	int whites;
	
	public Board()
	{
		theBoard = new Pieces[BoardManager.ROWS][BoardManager.COLUMNS];
		for(int i = 0; i < BoardManager.ROWS; i++) {
			for(int j = 0; j < BoardManager.COLUMNS; j++) {
				if(i < BoardManager.ROWS/2) {
					theBoard[i][j] = Pieces.BLACK;
				}
				else {
					theBoard[i][j] = Pieces.WHITE;
				}
			}
		}
		theBoard[BoardManager.ROWS/2][0] = Pieces.BLACK;
		theBoard[BoardManager.ROWS/2][1] = Pieces.WHITE;
		theBoard[BoardManager.ROWS/2][2] = Pieces.BLACK;
		theBoard[BoardManager.ROWS/2][3] = Pieces.WHITE;
		theBoard[BoardManager.ROWS/2][4] = Pieces.EMPTY;
		theBoard[BoardManager.ROWS/2][5] = Pieces.BLACK;
		theBoard[BoardManager.ROWS/2][6] = Pieces.WHITE;
		theBoard[BoardManager.ROWS/2][7] = Pieces.BLACK;
		theBoard[BoardManager.ROWS/2][8] = Pieces.WHITE;
		whites = 22;
		blacks = 22;
	}
	
	public Board(Board b) {
		theBoard = b.theBoard;
		blacks = b.blacks;
		whites = b.whites;
	}

	public Pieces get(Points a) {
		return theBoard[a.row][a.column];
	}
	
	public Pieces get(int x, int y) {
		return theBoard[x][y];
	}
	
	public void set(Points a, Pieces color) {
		theBoard[a.row][a.column] = color;
	}
	
	public int Utility() {
		return whites - blacks;
	}
	
	public List<Move> getValidMoves(Points start, Pieces color) throws BadMoveException {
		ArrayList<Move> withAttack = new ArrayList<Move>();
		ArrayList<Move> noAttack = new ArrayList<Move>();
		Move move = new Move();
		move.setStart(start);
		move.setColor(color);
		for(Points end: start.adjacentLocations) {
			move.setEnd(end);
			move.updateDirection();
			if(BoardManager.isValidMove(this, move)) {
				Pieces advancing;
				Pieces retracting;
				switch(move.getDirection()) {
				case UP:
					if(Points.isValidSpace(start.row - 2, start.column) && Points.isValidSpace(start.row + 1, start.column)) {
						advancing = theBoard[start.row - 2][start.column];
						retracting = theBoard[start.row + 1][start.column];
						if(advancing != move.getColor() && advancing != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, true, true);
							withAttack.add(newMove);
						}
						if(retracting != move.getColor() && retracting != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, false, true);
							withAttack.add(newMove);
						}
					}
					else if(Points.isValidSpace(start.row - 2, start.column)) {
						advancing = theBoard[start.row - 2][start.column];
						if(advancing != move.getColor() && advancing != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, false, true);
							withAttack.add(newMove);
						}
					}
					else if(Points.isValidSpace(start.row + 1, start.column)) {
						retracting = theBoard[start.row + 1][start.column];
						if(retracting != move.getColor() && retracting != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, true, true);
							withAttack.add(newMove);
						}
					}
					else if(withAttack.isEmpty()) {
							Move newMove = new Move(start, end, color, false, false);
							noAttack.add(newMove);
					}
					break;
				case UPRIGHT:
					if(Points.isValidSpace(start.row - 2, start.column + 2) && Points.isValidSpace(start.row + 1, start.column - 1)) {
						advancing = theBoard[start.row - 2][start.column + 2];
						retracting = theBoard[start.row + 1][start.column - 1];
						if(advancing != move.getColor() && advancing != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, true, true);
							withAttack.add(newMove);
						}
						if(retracting != move.getColor() && retracting != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, false, true);
							withAttack.add(newMove);
						}
					}
					else if(Points.isValidSpace(start.row - 2, start.column + 2)) {
						advancing = theBoard[start.row - 2][start.column + 2];
						if(advancing != move.getColor() && advancing != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, false, true);
							withAttack.add(newMove);
						}
					}
					else if(Points.isValidSpace(start.row + 1, start.column - 1)) {
						retracting = theBoard[start.row + 1][start.column - 1];
						if(retracting != move.getColor() && retracting != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, true, true);
							withAttack.add(newMove);
						}
					}
					else if(withAttack.isEmpty()) {
						Move newMove = new Move(start, end, color, false, false);
						noAttack.add(newMove);
					}
					break;
				case UPLEFT:
					if(Points.isValidSpace(start.row - 2, start.column - 2) && Points.isValidSpace(start.row + 1, start.column + 1)) {
						advancing = theBoard[start.row - 2][start.column - 2];
						retracting = theBoard[start.row + 1][start.column + 1];
						if(advancing != move.getColor() && advancing != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, true, true);
							withAttack.add(newMove);
						}
						if(retracting != move.getColor() && retracting != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, false, true);
							withAttack.add(newMove);
						}
					}
					else if(Points.isValidSpace(start.row - 2, start.column - 2)) {
						advancing = theBoard[start.row - 2][start.column - 2];
						if(advancing != move.getColor() && advancing != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, false, true);
							withAttack.add(newMove);
						}
					}
					else if(Points.isValidSpace(start.row + 1, start.column + 1)) {
						retracting = theBoard[start.row + 1][start.column + 1];
						if(retracting != move.getColor() && retracting != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, true, true);
							withAttack.add(newMove);
						}
					}
					else if(withAttack.isEmpty()) {
						Move newMove = new Move(start, end, color, false, false);
						noAttack.add(newMove);
					}
					break;
				case DOWN:
					if(Points.isValidSpace(start.row + 2, start.column) && Points.isValidSpace(start.row - 1, start.column)) {
						advancing = theBoard[start.row + 2][start.column];
						retracting = theBoard[start.row - 1][start.column];
						if(advancing != move.getColor() && advancing != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, true, true);
							withAttack.add(newMove);
						}
						if(retracting != move.getColor() && retracting != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, false, true);
							withAttack.add(newMove);
						}
					}
					else if(Points.isValidSpace(start.row + 2, start.column)) {
						advancing = theBoard[start.row + 2][start.column];
						if(advancing != move.getColor() && advancing != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, false, true);
							withAttack.add(newMove);
						}
					}
					else if(Points.isValidSpace(start.row - 1, start.column)) {
						retracting = theBoard[start.row - 1][start.column];
						if(retracting != move.getColor() && retracting != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, true, true);
							withAttack.add(newMove);
						}
					}
					else if(withAttack.isEmpty()) {
						Move newMove = new Move(start, end, color, false, false);
						noAttack.add(newMove);
					}
					break;
				case DOWNRIGHT:
					if(Points.isValidSpace(start.row + 2, start.column + 2) && Points.isValidSpace(start.row - 1, start.column - 1)) {
						advancing = theBoard[start.row + 2][start.column + 2];
						retracting = theBoard[start.row - 1][start.column - 1];
						if(advancing != move.getColor() && advancing != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, true, true);
							withAttack.add(newMove);
						}
						if(retracting != move.getColor() && retracting != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, false, true);
							withAttack.add(newMove);
						}
					}
					else if(Points.isValidSpace(start.row + 2, start.column + 2)) {
						advancing = theBoard[start.row + 2][start.column + 2];
						if(advancing != move.getColor() && advancing != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, false, true);
							withAttack.add(newMove);
						}
					}
					else if(Points.isValidSpace(start.row - 1, start.column - 1)) {
						retracting = theBoard[start.row - 1][start.column - 1];
						if(retracting != move.getColor() && retracting != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, true, true);
							withAttack.add(newMove);
						}
					}
					else if(withAttack.isEmpty()) {
						Move newMove = new Move(start, end, color, false, false);
						noAttack.add(newMove);
					}
					break;
				case DOWNLEFT:
					if(Points.isValidSpace(start.row + 2, start.column - 2) && Points.isValidSpace(start.row - 1, start.column + 1)) {
						advancing = theBoard[start.row + 2][start.column - 2];
						retracting = theBoard[start.row - 1][start.column + 1];
						if(advancing != move.getColor() && advancing != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, true, true);
							withAttack.add(newMove);
						}
						if(retracting != move.getColor() && retracting != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, false, true);
							withAttack.add(newMove);
						}
					}
					else if(Points.isValidSpace(start.row + 2, start.column - 2)) {
						advancing = theBoard[start.row + 2][start.column - 2];
						if(advancing != move.getColor() && advancing != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, false, true);
							withAttack.add(newMove);
						}
					}
					else if(Points.isValidSpace(start.row - 1, start.column + 1)) {
						retracting = theBoard[start.row - 1][start.column + 1];
						if(retracting != move.getColor() && retracting != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, true, true);
							withAttack.add(newMove);
						}
					}
					else if(withAttack.isEmpty()) {
						Move newMove = new Move(start, end, color, false, false);
						noAttack.add(newMove);
					}
					break;
				case LEFT:
					if(Points.isValidSpace(start.row, start.column - 2) && Points.isValidSpace(start.row, start.column + 1)) {
						advancing = theBoard[start.row][start.column - 2];
						retracting = theBoard[start.row][start.column + 1];
						if(advancing != move.getColor() && advancing != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, true, true);
							withAttack.add(newMove);
						}
						if(retracting != move.getColor() && retracting != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, false, true);
							withAttack.add(newMove);
						}
					}
					else if(Points.isValidSpace(start.row, start.column - 2)) {
						advancing = theBoard[start.row][start.column - 2];
						if(advancing != move.getColor() && advancing != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, false, true);
							withAttack.add(newMove);
						}
					}
					else if(Points.isValidSpace(start.row, start.column + 1)) {
						retracting = theBoard[start.row][start.column + 1];
						if(retracting != move.getColor() && retracting != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, true, true);
							withAttack.add(newMove);
						}
					}
					else if(withAttack.isEmpty()) {
						Move newMove = new Move(start, end, color, false, false);
						noAttack.add(newMove);
					}
					break;
				case RIGHT:
					if(Points.isValidSpace(start.row, start.column + 2) && Points.isValidSpace(start.row, start.column - 1)) {
						advancing = theBoard[start.row][start.column + 2];
						retracting = theBoard[start.row][start.column - 1];
						if(advancing != move.getColor() && advancing != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, true, true);
							withAttack.add(newMove);
						}
						if(retracting != move.getColor() && retracting != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, false, true);
							withAttack.add(newMove);
						}
					}
					else if(Points.isValidSpace(start.row, start.column + 2)) {
						advancing = theBoard[start.row][start.column + 2];
						if(advancing != move.getColor() && advancing != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, false, true);
							withAttack.add(newMove);
						}
					}
					else if(Points.isValidSpace(start.row, start.column - 1)) {
						retracting = theBoard[start.row][start.column - 1];
						if(retracting != move.getColor() && retracting != Pieces.EMPTY) {
							Move newMove = new Move(start, end, color, true, true);
							withAttack.add(newMove);
						}
					}
					else if(withAttack.isEmpty()) {
						Move newMove = new Move(start, end, color, false, false);
						noAttack.add(newMove);
					}
					break;
				}
			}
		}
		if(!withAttack.isEmpty())
			return withAttack;
		else
			return noAttack;
	}
}
