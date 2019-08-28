package inSeongJo.aHenIntoTheWild.view;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	public MainFrame() {
		this.setSize(1024, 768);
		
		this.add(new Stage01());
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
