package eighteen;

public class BoardManager {
	private Pieces[][] board;
	
	public static final int ROWS = 5;
	public static final int COLUMNS = 9;
	
	public BoardManager() {
		resetBoard();
	}
	hghg
	private void resetBoard() {
		board = new Pieces[ROWS][COLUMNS];
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS) {
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
	}
	
	public void move(Point start, Point end) {
	}
	
	private boolean isValidMove(int rowStart, int columnStart, int rowEnd, int columnEnd) {
		if(isValidSpace(rowStart, columnStart) && isValidSpace(rowEnd, columnEnd) && ((rowStart != rowEnd) && (columnStart != columnEnd)))
		{
			if(rowStart + columnStart % 2 == 0) {
				if(rowStart)
			}
			else {
				
			}
		}
	}
}