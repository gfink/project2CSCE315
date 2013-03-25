package eighteen;

import eighteen.BoardManager.BadMoveException;
import java.util.ArrayList;
import java.util.List;

public class AI {
	// Will need to implement Thread at some point in the future
	
	private Tree minMaxTree;
	private Pieces myColor;
	private int levels;
	
	public AI(Pieces color) {
		minMaxTree = new Tree();
		myColor = color;
		levels = 1;
	}
	
	public void getNewLevel() throws BadMoveException {
		addLevel(minMaxTree.getRoot());
		levels++;
	}
	
	public void addLevel(TreeNode node) throws BadMoveException {
		if(!node.hasChildren()) {
			Points curLocation = new Points(0, 0);
			Pieces color;
			Move move = new Move();
			if(node.getMove().getColor() == Pieces.WHITE)
				color = Pieces.BLACK;
			else
				color = Pieces.WHITE;
			for(int x=0; x < BoardManager.ROWS; x++) {
				for(int y=0; y < BoardManager.COLUMNS; y++) {
					curLocation.row = x;
					curLocation.column = y;
					if(node.board.get(curLocation) == color) {
						List<Move> validMoves = node.board.getValidMoves(curLocation, color);
						for(Move newMove: validMoves) {
							Board newBoard = BoardManager.move(node.board, newMove);
							TreeNode newChild = new TreeNode(move, newBoard);
							node.addChild(newChild);
						}
					}
				}
			}
		}
		else
			for(TreeNode child: node.getChildren())
				addLevel(child);
	}
	
	private void alphaBetaSearch() {
		TreeNode root = minMaxTree.getRoot();
		int value;
		if (root.getMove().getColor() != myColor)
			value = maxValue(root, -999999, 999999);
		else
			value = minValue(root, -999999, 999999);
	}
	
	private int maxValue(TreeNode state, int alpha, int beta) {
		if(!state.hasChildren())
			return state.value;
		
		for(TreeNode child: state.getChildren()) {
			alpha = Math.max(alpha,  minValue(child, alpha, beta));
			if(beta <= alpha) {
				// Beta cut-off
				break;
			}
		}
		return alpha;
	}
	
	private int minValue(TreeNode state, int alpha, int beta) {
		if(!state.hasChildren())
			return state.value;
		
		for(TreeNode child: state.getChildren()) {
			beta = Math.min(beta, maxValue(child, alpha, beta));
			if(beta <= alpha) {
				// Alpha cut-off
				return state.traversalValue;
			}
		}
		return beta;
	}
}