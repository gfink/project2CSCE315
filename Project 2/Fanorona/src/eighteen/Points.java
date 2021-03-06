package eighteen;
import java.util.ArrayList;

public class Points {
	public int row;
	public int column;
	
	public Points(int x, int y) {
		row = x;
		column = y;
		if(!isValidSpace()) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public Points(Points a) {
		this.row = a.row;
		this.column = a.column;
	}
	
	public boolean isValidSpace() {
		if(row < BoardManager.ROWS && row >= 0 && column < BoardManager.COLUMNS && column >= 0)
			return true;
		return false;
	}
	
	public static boolean isValidSpace(int s_row, int s_column) {
		if(s_row < BoardManager.ROWS && s_row >= 0 && s_column < BoardManager.COLUMNS && s_column >= 0)
			return true;
		return false;
	}
	
	public ArrayList<Points> adjacentLocations() {
		ArrayList<Points> adjacentLocations = new ArrayList<Points>();
		if(row + column % 2 == 0) {
			if(isValidSpace(row - 1, column - 1))
				adjacentLocations.add(new Points(row - 1, column - 1));
			if(isValidSpace(row - 1, column + 1))
				adjacentLocations.add(new Points(row - 1, column + 1));
			if(isValidSpace(row + 1, column - 1))
				adjacentLocations.add(new Points(row + 1, column - 1));
			if(isValidSpace(row + 1, column + 1))
				adjacentLocations.add(new Points(row + 1, column + 1));
		}
		if(isValidSpace(row - 1, column))
			adjacentLocations.add(new Points(row - 1, column));
		if(isValidSpace(row + 1, column))
			adjacentLocations.add(new Points(row + 1, column));
		if(isValidSpace(row, column - 1))
			adjacentLocations.add(new Points(row, column - 1));
		if(isValidSpace(row, column + 1))
			adjacentLocations.add(new Points(row, column + 1));
		return adjacentLocations;
	}
	
	public boolean equals(Points a)
	{
		if(a.row == row && a.column == column)
			return true;
		return false;
	}
}
