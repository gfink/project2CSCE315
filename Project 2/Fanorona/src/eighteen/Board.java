package eighteen;

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

	public Pieces get(Points a)
	{
		return theBoard[a.row][a.column];
	}
	public Pieces get(int x, int y)
	{
		return theBoard[x][y];
	}
	public void set(Points a, Pieces color)
	{
		theBoard[a.row][a.column] = color;
	}
	
	public int Utility()
	{
		return whites - blacks;
	}
}
