package inSeongJo.aHenIntoTheWild.model.vo;

import javax.swing.JPanel;

import inSeongJo.aHenIntoTheWild.view.MainStage;
import inSeongJo.aHenIntoTheWild.view.VideoTest;

public class MediaThread extends Thread{
	JPanel jPanel;
	private int time;
	public MediaThread(JPanel jPanel, int time) {
		this.jPanel = jPanel;
		this.time = time;
	}
	@Override
	public void run() {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jPanel.setVisible(true);
		
	}
}
