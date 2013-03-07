package eighteen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class FanoronaGUI extends JFrame{

	private static final long serialVersionUID = 1L;

	public FanoronaGUI() 
	{
		initGUI();
	}
	public final void initGUI()
	{
		setTitle("Fanorona Game Team 18");
		setSize(600,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel mainWin = new JPanel();
		getContentPane().add(mainWin);
		mainWin.setLayout(null);
		
		JButton helpButton = new JButton("Help");
		helpButton.setBounds(50,60,80,30);
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				HelpGUI help = new HelpGUI();
				help.setVisible(true);
			}
		});
		mainWin.add(helpButton);
	}
	public static void main (String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
					FanoronaGUI fgui = new FanoronaGUI();
					fgui.setVisible(true);
				}
			}
		);
	}
}
