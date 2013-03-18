package eighteen;

public class BoardManager {
	public class BadMoveException extends Exception {
		public BadMoveException(String message){
			super(message);
		}
	}
	
	Pieces[][] board;
	
	private int moves;
	private int whites;
	private int blacks;
	
	public static final int ROWS = 5;
	public static final int COLUMNS = 9;
	
	public BoardManager() {
		resetBoard();
	}
	private void resetBoard() {
		board = new Pieces[ROWS][COLUMNS];
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				if(i < ROWS/2) {
					board[i][j] = Pieces.BLACK;
				}
				else {
					board[i][j] = Pieces.WHITE;
				}
			}
		}
		board[ROWS/2][0] = Pieces.BLACK;
		board[ROWS/2][1] = Pieces.WHITE;
		board[ROWS/2][2] = Pieces.BLACK;
		board[ROWS/2][3] = Pieces.WHITE;
		board[ROWS/2][5] = Pieces.BLACK;
		board[ROWS/2][6] = Pieces.WHITE;
		board[ROWS/2][7] = Pieces.BLACK;
		board[ROWS/2][8] = Pieces.WHITE;
		moves = 0;
		whites = 22;
		blacks = 22;
	}
	
	//Returns whether the game is over
	public boolean move(Points start, Points end) throws BadMoveException {
		if(!isValidMove(start.row, start.column, end.row, end.column))
			throw new BadMoveException("Bad move at [" + start.row + ", " + start.column + "] to [" + end.row + ", " + end.column + "]");
		
		board[end.row][end.column] = board[start.row][start.column];
		board[start.row][start.column] = Pieces.EMPTY;
		
		moves++;
		if(moves >= 50 || whites == 0 || blacks == 0) {
			return true;
		}
		return false;
	}
	
	private boolean isValidMove(int rowStart, int columnStart, int rowEnd, int columnEnd) {
		//Space is taken
		if(board[rowEnd][columnEnd] != Pieces.EMPTY) {
			return false;
		}
		if(Points.isValidSpace(rowStart, columnStart) && Points.isValidSpace(rowEnd, columnEnd) && ((rowStart != rowEnd) && (columnStart != columnEnd)))
		{
			//Checks for Diagonal moves
			if(rowStart + columnStart % 2 == 0) {
				if((rowStart + 1 == rowEnd) || (rowStart - 1 == rowEnd)) {
					if((columnStart + 1 == columnEnd) || (columnStart - 1 == columnEnd)) {
						return true;
					}
				}
			}
			//Checks for horizontal moves
			if(((rowStart + 1 == rowEnd) || (rowStart - 1 == rowEnd)) && (columnStart == columnEnd)) {
				return true;
			}
			if(((columnStart + 1 == columnEnd) || (columnStart - 1 == columnEnd)) && (rowStart == rowEnd)) {
				return true;
			}
		}
		return false;
	}
}
