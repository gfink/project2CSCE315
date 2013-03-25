package eighteen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FanoronaGUI extends JFrame {
	//TODO if choice between pieces, pick attacking or retreating
	//TODO end move button
	//TODO show whose turn it is
	//TODO show how many pieces each side has
	//TODO show how many moves have been made
	//TODO show the utility value of the board (for the user)
	JButton helpb; //TODO add to a tool bar
	BoardManager boardMan;
	DrawnPiece[][] gamePieces;
	Container game = getContentPane();
	PieceListener clicked;
	DrawnPiece prevMove;
    public static FanoronaGUI GUI;
    
    public static void main(String[] args) {
       GUI =  new FanoronaGUI();
    }
    public void makePieces()
    {
    	JPanel board = new JPanel();
    	board.setLayout(new GridLayout(boardMan.ROWS,boardMan.COLUMNS,15,15));
        for(int x= 0;x<boardMan.ROWS;x++)
        {
        	for(int y = 0; y<boardMan.COLUMNS; y++)
        	{
        		if(boardMan.board.get(x,y) == Pieces.BLACK)
        		{
        			gamePieces[x][y] = new DrawnPiece(Pieces.BLACK,x,y);
        			gamePieces[x][y].setBackground(Color.BLACK);
        		}
        		else if(boardMan.board.get(x,y) == Pieces.WHITE)
        		{
        			gamePieces[x][y] = new DrawnPiece(Pieces.WHITE,x,y);
        			gamePieces[x][y].setBackground(Color.WHITE);
        		}
        		else
        		{
        			gamePieces[x][y] = new DrawnPiece(Pieces.EMPTY,x,y);
        			gamePieces[x][y].setBackground(Color.GRAY);
        		}
        		board.add(gamePieces[x][y]);
        	}
        }
        game.add(board,BorderLayout.CENTER);
    }
    public void makeHelpButton()
    {
    	 helpb = new JButton("Help");
         helpb.setBounds(50,60,80,30);
         helpb.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent event) {
                 HelpGUI helpgui = new HelpGUI();
                 helpgui.setVisible(true);
             }
         });
         game.add(helpb,BorderLayout.NORTH);
    }
    public void makePieceListeners()
    {
    	clicked = new PieceListener(); 
		for(int x= 0;x<boardMan.ROWS;x++)
        {
        	for(int y = 0; y<boardMan.COLUMNS; y++)
        	{
			gamePieces[x][y].addActionListener(clicked);
        	}
		}
    }
    public void makeBoard()
    {
    	//Point p1;
    	//Point p2;
       // for(int x= 0;x<boardMan.ROWS;x++)
        //{
        //	for(int y = 0; y<boardMan.COLUMNS; y++)
        //	{
        //		p1 = gamePieces[x][y].getLocation();
        //	}
       // }
    	//assumes that pieces(buttons) have been made, need their location
    	
    	
    }
    public FanoronaGUI() {
    	game.setLayout(new BorderLayout());
    	gamePieces = new DrawnPiece[BoardManager.ROWS][BoardManager.COLUMNS];
    	boardMan = new BoardManager();
    	prevMove = new DrawnPiece(Pieces.EMPTY,0,0);
    	
    	makePieces();
    	//makeBoard();
    	makeHelpButton();
    	makePieceListeners();
    	setSize(1000,700);
        setTitle("Fanorona");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    class PieceListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		// the user will first click a black or white piece to pick a piece to move, then click a highlighted piece to move there.
    		DrawnPiece gamePiece = (DrawnPiece) e.getSource();
    		Points pointsClicked = new Points(gamePiece.xLoc, gamePiece.yLoc);
    		ArrayList<Points> adjLocs = pointsClicked.adjacentLocations();
    		//first check if the piece clicked is an available piece
    		Color clickedColor = gamePiece.pColor;
    		if (gamePiece.pColor == Color.YELLOW)
    		{
    			//make the point clicked the color of the previous point clicked
    			gamePieces[pointsClicked.row][pointsClicked.column].pColor = prevMove.pColor;
    			gamePieces[pointsClicked.row][pointsClicked.column].repaint();
    			//make the previous point turn gray
    			gamePieces[prevMove.xLoc][prevMove.yLoc].pColor = Color.GRAY;
    			gamePieces[prevMove.xLoc][prevMove.yLoc].repaint();
    			//THIS IS THE POINT WHERE A MOVE SHOULD BE MADE
    			//ALL PIECES INVOLVED IN A MOVE NEED TO BE REPAINTED AT THIS POINT
    		}
    		else // on the initial click of a piece
    		{
    			//highlight all available moves, if not making a move, and if the piece clicked isn't gray
	    		for(Points p : adjLocs)
	    		{
	    			if (gamePieces[p.row][p.column].pColor == Color.GRAY && gamePiece.pColor != Color.GRAY)
	    			{
	    				gamePieces[p.row][p.column].pColor = Color.YELLOW;
	    				gamePieces[p.row][p.column].repaint();
	    			}
	    		}
    		}
    		//after click is done, set the previous move to the last piece clicked, records x, y, and color
    		prevMove=gamePiece;
    		System.out.print("Clicked x:" + gamePiece.xLoc+" y: "+gamePiece.yLoc+"\n");
    		
    	}
    }
    
}