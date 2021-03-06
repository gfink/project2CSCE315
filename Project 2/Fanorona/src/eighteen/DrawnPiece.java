package eighteen;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.*;
//using model code from here http://harryjoy.com/2011/08/21/different-button-shapes-in-swing/
public class DrawnPiece extends JButton{
	public int xLoc;
	public int yLoc;
	public Color pColor;
	Shape shape;
	public DrawnPiece(Pieces Piece, int x, int y)
	{
		
		super();
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
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);
		setContentAreaFilled(false);
		
	}
	public void showPotentialMove()
	{

	}
	protected void paintComponent(Graphics g)
	{
		g.setColor(pColor);
		g.fillOval(0,0,getSize().width-1,getSize().height-1);
		super.paintComponent(g);
	}
	protected void paintBorder(Graphics g)
	{
		g.setColor(pColor);
		g.drawOval(0,0,getSize().width-1,getSize().height-1);
	}
	public boolean contains(int x, int y)
	{
		if (shape==null || !shape.getBounds().equals(getBounds()))
		{
			shape = new Ellipse2D.Float(0,0,getWidth(),getHeight());
		}
		return shape.contains(x,y);
	}

}
