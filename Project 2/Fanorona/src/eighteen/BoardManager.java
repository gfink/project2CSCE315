package eighteen;

public class BoardManager {
	public class BadMoveException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public BadMoveException(String message){
			super(message);
		}
	}
	
	Board board;
	
	Pieces turn;
	
	private int moves;
	private int whites;
	private int blacks;
	
	public static final int ROWS = 5;
	public static final int COLUMNS = 9;
	
	public BoardManager() {
		resetBoard();
	}
	private void resetBoard() {
		board = new Board();
		moves = 0;
		
		turn = Pieces.WHITE;
	}
	
	//Returns whether the game is over
	public boolean move(Move mov) throws BadMoveException {
		if(!isValidMove(mov))
			throw new BadMoveException("Bad move at [" + mov.getStart().row + ", " + mov.getStart().column + "] to [" + mov.getEnd().row + ", " + mov.getEnd().column + "]");
		
		board.set(mov.getEnd(), board.get(mov.getStart()));
		board.set(mov.getStart(), Pieces.EMPTY);
		
		moves++;
		if(turn == Pieces.WHITE) {
			turn = Pieces.BLACK;
		}
		else {
			turn = Pieces.WHITE;
		}
		if(moves >= 50 || whites == 0 || blacks == 0) {
			return true;
		}
		return false;
	}
	
	private boolean isValidMove(Move mov) {
		//Space is taken
		if(board.get(mov.getEnd()) != Pieces.EMPTY) {
			return false;
		}
		if(Points.isValidSpace(mov.getStart()) && Points.isValidSpace(mov.getEnd()) && !mov.getStart().equals(mov.getEnd())) {
			//Checks for Diagonal moves
			if(mov.getStart().adjacentLocations.contains(mov.getEnd())) {
				return true;
			}
		}
		return false;
	}
}
