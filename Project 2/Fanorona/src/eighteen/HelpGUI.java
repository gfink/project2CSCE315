package eighteen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
public class HelpGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public HelpGUI() 
	{
		setTitle("Help");
		setSize(200,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
