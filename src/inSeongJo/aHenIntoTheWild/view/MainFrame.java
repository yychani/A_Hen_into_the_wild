package inSeongJo.aHenIntoTheWild.view;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	public MainFrame() {
		this.setSize(1024, 768);
		
		this.add(new Stage01()); // new Stage01을 자신의 패널을 넣어서 실험
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
