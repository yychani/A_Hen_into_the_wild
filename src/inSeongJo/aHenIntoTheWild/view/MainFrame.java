package inSeongJo.aHenIntoTheWild.view;

import javax.swing.JFrame;

import inSeongJo.aHenIntoTheWild.model.vo.Stage01_Thread;

public class MainFrame extends JFrame {
	private Stage01_Thread s1Thread;
	private Stage01 stage01;

	public MainFrame() {
		this.setSize(1024, 768);
		setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// this.add(new MainPage()); // new Stage01을 자신의 패널을 넣어서 실험

		new LoadingPage(this);
//		new VideoTest(this);
		
	}
}
