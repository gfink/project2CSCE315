package eighteen;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FanoronaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	//should some of these be containers?
	JPanel game;
	JPanel board;
	JButton helpb;
	BoardManager boardMan;
	JButton[][] gamePieces;
	GridLayout boardLayout;
    public FanoronaGUI() {
    	setSize(1000,700);
        setTitle("Fanorona");
        //setLayout(null);
        //may need to move when replaying game
        boardMan = new BoardManager();
        gamePieces = new JButton[BoardManager.ROWS][BoardManager.COLUMNS];
        boardLayout = new GridLayout(BoardManager.ROWS,BoardManager.COLUMNS);
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
        		JButton pieceB;
        		if(boardMan.board[x][y] == Pieces.BLACK)
        		{
        			pieceB = new JButton("BLACK");
        			//pieceB.setSize(20,20);
        			gamePieces[x][y] = pieceB;
        			pieceB.setLayout(null);
        			board.add(pieceB);
        			//pieceB.setLocation(x*21,y*21);
        		}
        		else if(boardMan.board[x][y] == Pieces.WHITE)
        		{
        			pieceB = new JButton("WHITE");
        			//pieceB.setSize(20,20);
        			gamePieces[x][y] = pieceB;
        			pieceB.setLayout(null);
        			board.add(pieceB);
        			//pieceB.setLocation(x*21,y*21);
        		}
        		else
        		{
        			pieceB = new JButton("Empty");
        			//pieceB.setSize(20,20);
        			gamePieces[x][y] = pieceB;
        			pieceB.setLayout(null);
        			board.add(pieceB);
        			//pieceB.setLocation(x*21,y*21);
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


    public static void main(String[] args) {
        new FanoronaGUI();
    }
    
}