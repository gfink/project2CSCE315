package eighteen;

public class Move {
	private Points start;
	private Points end;
	private Pieces color;
	
	public Move() {}
	
	public Move(Points start, Points end, Pieces color) {
		this.start = start;
		this.end = end;
		this.color = color;
	}
	
	public void setStart(Points start) {
		this.start = start;
	}
	
	public Points getStart() {
		return start;
	}
	
	public void setEnd(Points end) {
		this.end = end;
	}
	
	public Points getEnd() {
		return end;
	}
	
	public void setColor(Pieces color) {
		this.color = color;
	}
	
	public Pieces getColor() {
		return color;
	}
}
