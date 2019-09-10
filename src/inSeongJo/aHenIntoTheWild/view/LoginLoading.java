package inSeongJo.aHenIntoTheWild.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import inSeongJo.aHenIntoTheWild.model.vo.User;

public class LoginLoading extends JPanel{
	private Image loadingImage;
	private MainFrame mf;
	private JPanel loading = this;
	Media media = new Media();
	private User user;

	public LoginLoading(MainFrame mf, User user, Media media) {
		this.user = user;
		this.media = media;
		this.mf = mf;
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
		mf.add(this);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		loadingImage = toolkit.getImage("images/loginLoading.gif").getScaledInstance(1024, 740, 0);//배경 이미지
		Thread timer = new Thread(new Runnable() {
			int i = 0;
			@Override
			public void run() {
//				media.sound("keyboard");
				while (i < 1500) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					i++;
					repaint();
				}
//				media.soundStop();
				ChangePanel.changePanel(mf, loading, new StartPage(mf, user, media));
			}
		});
		timer.start();
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(loadingImage, 0, 0, this);// 배경 그리기
	}
}
