package eighteen;
import java.util.ArrayList;

public class Points {
	public int row;
	public int column;
	ArrayList<Points> adjacentLocations;
	
	public Points(int x, int y) {
		row = x;
		column = y;
		if(!isValidSpace()) {
			throw new IndexOutOfBoundsException();
		}
		makeAdjacentLocations();
	}
	
	public boolean isValidSpace() {
		if(row < BoardManager.ROWS && row >= 0 && column < BoardManager.COLUMNS && column >= 0)
			return true;
		return false;
	}
	
	public static boolean isValidSpace(Points s) {
		if(s.row < BoardManager.ROWS && s.row >= 0 && s.column < BoardManager.COLUMNS && s.column >= 0)
			return true;
		return false;
	}
	
	private void makeAdjacentLocations() {
		if(row + column % 2 == 0) {
			if(isValidSpace(new Points(row - 1, column - 1)))
				adjacentLocations.add(new Points(row - 1, column - 1));
			if(isValidSpace(new Points(row - 1, column + 1)))
				adjacentLocations.add(new Points(row - 1, column + 1));
			if(isValidSpace(new Points(row + 1, column - 1)))
				adjacentLocations.add(new Points(row + 1, column - 1));
			if(isValidSpace(new Points(row + 1, column + 1)))
				adjacentLocations.add(new Points(row + 1, column + 1));
		}
		if(isValidSpace(new Points(row - 1, column)))
			adjacentLocations.add(new Points(row - 1, column));
		if(isValidSpace(new Points(row + 1, column)))
			adjacentLocations.add(new Points(row + 1, column));
		if(isValidSpace(new Points(row, column - 1)))
			adjacentLocations.add(new Points(row, column - 1));
		if(isValidSpace(new Points(row, column + 1)))
			adjacentLocations.add(new Points(row, column + 1));
	}
	
	public boolean equals(Points a)
	{
		if(a.row == row && a.column == column)
			return true;
		return false;
	}
}
