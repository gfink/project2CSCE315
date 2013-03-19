package eighteen;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
public class DrawnPiece extends JPanel implements MouseListener{
	private int xLoc;
	private int yLoc;
	private Color pColor;
	private Boolean isVisible;
	public DrawnPiece(int x,int y,Pieces Piece)
	{
		xLoc = x;
		yLoc = y;
		if (Piece == Piece.WHITE)
		{
			pColor = Color.WHITE;
		}
		else if(Piece == Piece.BLACK)
		{
			pColor = Color.BLACK;
		}
		else
		{
			pColor = Color.GRAY;
		}
		this.setPreferredSize(new Dimension(70,70));
		addMouseListener(this);
	}
	protected void paintComponent(Graphics g){
		int h = getHeight();
		int w = getWidth();
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(pColor);
		g2d.fillOval(0, 0, 70, 70);
		
	}
	public void mousePressed(MouseEvent e) {
			System.out.println("Mouse pressed (# of clicks: "
	                    + e.getClickCount() + ")");
	    }

	    public void mouseReleased(MouseEvent e) {
	    	System.out.println("Mouse released (# of clicks: "
	                    + e.getClickCount() + ")");
	    }

	    public void mouseEntered(MouseEvent e) {
	    	System.out.println("Mouse entered");
	    }

	    public void mouseExited(MouseEvent e) {
	    	System.out.println("Mouse exited");
	    }

	    public void mouseClicked(MouseEvent e) {
	    	System.out.println("Mouse clicked (# of clicks: "
	                    + e.getClickCount() + ")");
	    }

}
