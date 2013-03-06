package eighteen;

public class BoardManager {
	private Pieces[][] board;
	
	public BoardManager() {
		resetBoard();
	}
	
	private void resetBoard() {
		board = new Pieces[5][9];
		for(int i = 0; i < 9; i++) {
			board[0][i] = Pieces.BLACK;
			board[1][i] = Pieces.BLACK;
			board[3][i] = Pieces.WHITE;
			board[4][i] = Pieces.WHITE;
		}
		board[2][0] = Pieces.BLACK;
		board[2][1] = Pieces.WHITE;
		board[2][2] = Pieces.BLACK;
		board[2][3] = Pieces.WHITE;
		board[2][5] = Pieces.BLACK;
		board[2][6] = Pieces.WHITE;
		board[2][7] = Pieces.BLACK;
		board[2][8] = Pieces.WHITE;
	}
	
	public void move(int rowStart, int columnStart, int rowEnd, int columnEnd) {
	}
	
	public boolean isValidSpace(int row, int column) {
		if(row < 5 && row >= 0 && column < 9 && column >= 0)
			return true;
		return false;
	}
	
	private boolean isValidMove(int rowStart, int columnStart, int rowEnd, int columnEnd) {
		if(isValidSpace(rowStart, columnStart) && isValidSpace(rowEnd, columnEnd) && ((rowStart != rowEnd) && (columnStart != columnEnd)))
		{
			if(rowStart + columnStart % 2 == 0)
			{
				if(rowStart)
			}
			else
			{
				
			}
		}
	}
}
