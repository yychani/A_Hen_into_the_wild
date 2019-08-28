package inSeongJo.aHenIntoTheWild.view;

import javax.swing.JFrame;

import inSeongJo.aHenIntoTheWild.model.vo.Stage01_Thread;

public class MainFrame extends JFrame{
	private Stage01_Thread s1thread;
	private Stage01 stage01;
	public MainFrame() {
		this.setSize(1024, 768);
		
		stage01 = new Stage01();
		this.add(stage01); // new Stage01�� �ڽ��� �г��� �־ ����
		
		s1thread = new Stage01_Thread();
		s1thread.start();
		
		setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
