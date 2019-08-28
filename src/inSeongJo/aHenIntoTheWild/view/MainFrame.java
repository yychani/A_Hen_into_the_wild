package inSeongJo.aHenIntoTheWild.view;

import javax.swing.JFrame;

import inSeongJo.aHenIntoTheWild.model.vo.Stage01_Thread;

public class MainFrame extends JFrame{
	private Stage01_Thread s1Thread;
	private Stage01 stage01;
	public MainFrame() {
		this.setSize(1024, 768);
		
		s1Thread = new Stage01_Thread();
		stage01 = s1Thread.getStage01();
		this.add(stage01); // new Stage01�� �ڽ��� �г��� �־ ����

		s1Thread.start();
		
		setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
