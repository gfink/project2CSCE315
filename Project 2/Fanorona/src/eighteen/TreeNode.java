package eighteen;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	private Move move;
	private List<TreeNode> children;
	private TreeNode parent;
	Pieces[][] board;
	int value;

	public TreeNode() {
		children = new ArrayList<TreeNode>();
		value = 0;
	}
	
	public TreeNode(Move move, Pieces[][] board) {
		this();
		setMove(move);
		setBoard(board);
		
	}
	
	public void setMove(Move move) {
		this.move = move;
	}
	
	public Move getMove() {
		return move;
	}
	
	public void setBoard(Pieces[][] board) {
		this.board = board;
	}
	
	public List<TreeNode> getChildren() {
		return children;
	}
	
	public int getNumberOfChildren() {
		return children.size();
	}
	
	public boolean hasChildren() {
		return (getNumberOfChildren() > 0);
	}
	
	public TreeNode getParent() {
		return parent;
	}
}
