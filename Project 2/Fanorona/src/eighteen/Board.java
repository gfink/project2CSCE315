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
	
	public List<Move> getValidMoves(Pieces color) throws BadMoveException {
		ArrayList<Move> withAttack = new ArrayList<Move>();
		ArrayList<Move> noAttack = new ArrayList<Move>();
		Points curLocation = new Points(0,0);
		
		for(int x=0; x < BoardManager.ROWS; x++) {
			for(int y=0; y < BoardManager.COLUMNS; y++) {
				curLocation.row = x;
				curLocation.column = y;
				Move move = new Move();
				if(get(curLocation) == color) {
					move.setStart(curLocation);
					move.setColor(color);
					for(Points end: curLocation.adjacentLocations) {
						move.setEnd(end);
						move.updateDirection();
						int rowAdv = 0;
						int rowRetr = 0;
						int colAdv = 0;
						int colRetr = 0;
						if(BoardManager.isValidMove(this, move)) {
							Pieces advancing;
							Pieces retracting;
							switch(move.getDirection()) {
							case UP:
								rowAdv = -2;
								colAdv = 0;
								rowRetr = 1;
								colRetr = 0;
								break;
							case UPRIGHT:
								rowAdv = -2;
								colAdv = 2;
								rowRetr = 1;
								colRetr = -1;
								break;
							case UPLEFT:
								rowAdv = -2;
								colAdv = -2;
								rowRetr = 1;
								colRetr = 1;
								break;
							case DOWN:
								rowAdv = 2;
								colAdv = 0;
								rowRetr = -1;
								colRetr = 0;
								break;
							case DOWNRIGHT:
								rowAdv = 2;
								colAdv = 2;
								rowRetr = -1;
								colRetr = -1;
								break;
							case DOWNLEFT:
								rowAdv = 2;
								colAdv = -2;
								rowRetr = -1;
								colRetr = 1;
								break;
							case LEFT:
								rowAdv = 0;
								colAdv = -2;
								rowRetr = 0;
								colRetr = 1;
								break;
							case RIGHT:
								rowAdv = 0;
								colAdv = 2;
								rowRetr = 0;
								colRetr = -1;
								break;
							}
							if(Points.isValidSpace(curLocation.row + rowAdv, curLocation.column + colAdv) && Points.isValidSpace(curLocation.row + rowRetr, curLocation.column + colRetr)) {
								advancing = theBoard[curLocation.row + rowAdv][curLocation.column + colAdv];
								retracting = theBoard[curLocation.row + rowRetr][curLocation.column + colRetr];
								if(advancing != move.getColor() && advancing != Pieces.EMPTY) {
									Move newMove = new Move(curLocation, end, color, true, true);
									withAttack.add(newMove);
								}
								if(retracting != move.getColor() && retracting != Pieces.EMPTY) {
									Move newMove = new Move(curLocation, end, color, false, true);
									withAttack.add(newMove);
								}
							}
							else if(Points.isValidSpace(curLocation.row + rowAdv, curLocation.column + colAdv)) {
								advancing = theBoard[curLocation.row + rowAdv][curLocation.column + colAdv];
								if(advancing != move.getColor() && advancing != Pieces.EMPTY) {
									Move newMove = new Move(curLocation, end, color, false, true);
									withAttack.add(newMove);
								}
							}
							else if(Points.isValidSpace(curLocation.row + rowRetr, curLocation.column + colRetr)) {
								retracting = theBoard[curLocation.row + rowRetr][curLocation.column + colRetr];
								if(retracting != move.getColor() && retracting != Pieces.EMPTY) {
									Move newMove = new Move(curLocation, end, color, true, true);
									withAttack.add(newMove);
								}
							}
							else if(withAttack.isEmpty()) {
									Move newMove = new Move(curLocation, end, color, false, false);
									noAttack.add(newMove);
							}
						}
					}
				}
			}
		}
		if(!withAttack.isEmpty())
			return withAttack;
		else
			return noAttack;
	}
}
