package eighteen;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FanoronaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	//should some of these be containers?
	JPanel game;
	JPanel board;
	JButton helpb; //TODO add to a tool bar
	BoardManager boardMan;
	DrawnPiece[][] gamePieces;
	GridLayout boardLayout;
    public FanoronaGUI() {
    	setSize(1000,700);
        setTitle("Fanorona");
        //may need to move when replaying game
        boardMan = new BoardManager();
        gamePieces = new DrawnPiece[BoardManager.ROWS][BoardManager.COLUMNS];
        boardLayout = new GridLayout(BoardManager.ROWS,BoardManager.COLUMNS,10,10);
        game = new JPanel();
        game.setPreferredSize(new Dimension(800, 600));
        board  = new JPanel(boardLayout);
        /*
         * Define help button things
         */
        helpb = new JButton("Help");
        helpb.setBounds(50,60,80,30);
        helpb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                HelpGUI helpgui = new HelpGUI();
                helpgui.setVisible(true);
            }
        });
        
        /*
         * Add objects to the screen
        */
        //loop for the entire board
        for(int x= 0;x<boardMan.ROWS;x++)
        {
        	for(int y = 0; y<boardMan.COLUMNS; y++)
        	{
        		DrawnPiece pieceDrawn;
        		if(boardMan.board.get(x,y) == Pieces.BLACK)
        		{
        			pieceDrawn = new DrawnPiece(x*50,y*50,Pieces.BLACK);
        			gamePieces[x][y] = pieceDrawn;
        			board.add(pieceDrawn);
        		}
        		else if(boardMan.board.get(x,y) == Pieces.WHITE)
        		{
        			pieceDrawn = new DrawnPiece(x*50,y*50,Pieces.WHITE);
        			pieceDrawn.setPreferredSize(new Dimension(30,30));
        			gamePieces[x][y] = pieceDrawn;
        			board.add(pieceDrawn);
        		}
        		else
        		{
        			pieceDrawn = new DrawnPiece(x*50,y*50,Pieces.EMPTY);
        			pieceDrawn.setPreferredSize(new Dimension(30,30));
        			gamePieces[x][y] = pieceDrawn;
        			board.add(pieceDrawn);
        		}
        	}
        }
        game.add(helpb);
        game.add(board);
        add(game);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static FanoronaGUI GUI;
    public static void main(String[] args) {
       GUI =  new FanoronaGUI();
    }
    
}