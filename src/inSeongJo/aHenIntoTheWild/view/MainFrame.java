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
		//this.add(new MainPage()); // new Stage01�� �ڽ��� �г��� �־ ����

		new UserInfoChange(this);
		
		setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
