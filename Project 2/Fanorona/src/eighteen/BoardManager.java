package eighteen;

import java.util.ArrayList;

public class BoardManager {
	public static class BadMoveException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public BadMoveException(String message){
			super(message);
		}
	}
	
	Board board;
	
	boolean chain;
	Pieces chainColor;
	Points previousSpot;
	Direction previousDirection;
	
	ArrayList<Points> previousLocations;
	
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
		
		chain = false;
		//The first mover is white
		chainColor = Pieces.WHITE;
		//0,0 will never be a problem for a first move in the middle, which will reset it
		previousSpot = new Points(0,0);
		//As white moves first, this will be reset and not be a problem
		previousDirection = Direction.LEFT;
		previousLocations = new ArrayList<Points>();
	}
	
	//Returns whether the game is over
	public boolean move(Move mov) throws BadMoveException {
		if(!isValidMove(mov)) {
			throw new BadMoveException("Bad move at [" + mov.getStart().row + ", " + mov.getStart().column + "] to [" + mov.getEnd().row + ", " + mov.getEnd().column + "]");
		}
		
		boolean isChain = true;
		
		if(chain) {
			if(board.get(mov.getStart()) != chainColor) {
				isChain = false;
				previousLocations = new ArrayList<Points>();
				chainColor = board.get(mov.getStart());
			}
			else if(mov.getStart() != previousSpot) {
				throw new BadMoveException("Bad move at [" + mov.getStart().row + ", " + mov.getStart().column + "] -> Wrong starting spot");
			}
			else if(previousLocations.contains(mov.getEnd())) {
				throw new BadMoveException("Bad move at [" + mov.getStart().row + ", " + mov.getStart().column + "] -> That space has already been moved to in this chain");
			}
			else if(previousDirection == mov.getDirection()) {
				throw new BadMoveException("Bad move at [" + mov.getStart().row + ", " + mov.getStart().column + "] -> Same direction as previous move in chain");
			}
		}
		chain = isChain;
		
		
		board.set(mov.getEnd(), board.get(mov.getStart()));
		board.set(mov.getStart(), Pieces.EMPTY);
		
		int iterateVertical = 0;
		int iterateHorizontal = 0;
		switch(mov.getDirection()) {
		case UP:
			iterateVertical = -1;
			break;
		case UPRIGHT:
			iterateVertical = -1;
			iterateHorizontal = 1;
			break;
		case UPLEFT:
			iterateVertical = -1;
			iterateHorizontal = -1;
			break;
		case DOWN:
			iterateVertical = 1;
			break;
		case DOWNRIGHT:
			iterateVertical = 1;
			iterateHorizontal = 1;
			break;
		case DOWNLEFT:
			iterateVertical = 1;
			iterateHorizontal = -1;
			break;
		case LEFT:
			iterateHorizontal = -1;
			break;
		case RIGHT:
			iterateHorizontal = 1;
		}
		
		if(!mov.getAdvancing()) {
			iterateVertical *= -1;
			iterateHorizontal *= -1;
		}
		
		Points nextPoint;
		if(mov.getAdvancing()) {
			nextPoint = new Points(mov.getEnd());
		}
		else {
			nextPoint = new Points(mov.getStart());
		}
		
		try {
			while(true) {
				nextPoint = new Points(nextPoint.row + iterateHorizontal, nextPoint.column + iterateVertical);
				if(board.get(nextPoint) == chainColor || board.get(nextPoint) == Pieces.EMPTY) {
					break;
				}
				board.set(nextPoint, Pieces.EMPTY);
				if(chainColor == Pieces.WHITE) {
					blacks--;
				}
				else {
					whites--;
				}
			}
		}
		finally {}
		chainColor = board.get(mov.getEnd());
		previousSpot = mov.getStart();
		previousDirection = mov.getDirection();
		previousLocations.add(mov.getStart());
		
		if(!chain) {
			moves++;
		}
		if(moves >= 50 || whites == 0 || blacks == 0) {
			return true;
		}
		return false;
		
	}
	
	public static Board move(Board b, Move mov) throws BadMoveException {
		if(!isValidMove(b, mov))
			throw new BoardManager.BadMoveException("Bad move at [" + mov.getStart().row + ", " + mov.getStart().column + "] to [" + mov.getEnd().row + ", " + mov.getEnd().column + "]");
		
		Board ret = new Board(b);
		
		ret.set(mov.getEnd(), ret.get(mov.getStart()));
		ret.set(mov.getStart(), Pieces.EMPTY);
		
		return ret;
	}
	
	private boolean isValidMove(Move mov) {
		//Space is taken
		if(board.get(mov.getEnd()) != Pieces.EMPTY) {
			return false;
		}
		if(Points.isValidSpace(mov.getStart().row, mov.getStart().column) && Points.isValidSpace(mov.getEnd().row, mov.getEnd().column) && !mov.getStart().equals(mov.getEnd())) {
			//Checks for Diagonal moves
			if(mov.getStart().adjacentLocations().contains(mov.getEnd())) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isValidMove(Board b, Move mov) {
		//Space is taken
		if(b.get(mov.getEnd()) != Pieces.EMPTY) {
			return false;
		}
		if(Points.isValidSpace(mov.getStart().row, mov.getStart().column) && Points.isValidSpace(mov.getEnd().row, mov.getEnd().column) && !mov.getStart().equals(mov.getEnd())) {
			//Checks for Diagonal moves
			if(mov.getStart().adjacentLocations().contains(mov.getEnd())) {
				return true;
			}
		}
		return false;
	}
}
