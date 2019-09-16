package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import inSeongJo.aHenIntoTheWild.model.vo.User;

public class Ending extends JPanel{
	private Image loadingImage;
	private MainFrame mf;
	private JPanel ending = this;
	Media media = new Media();
	private User user;
	boolean endingB = true;
	private int backY = 0;

	public Ending(MainFrame mf, User user, Media media) {
		this.user = user;
		this.media = media;
		this.mf = mf;
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
//		mf.add(this);
		media.soundStop();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		JButton endingSkip = new JButton("Credit Skip >>");
		endingSkip.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		endingSkip.setForeground(Color.WHITE);
		endingSkip.setBorderPainted(false);
		endingSkip.setContentAreaFilled(false);
		endingSkip.setFocusPainted(false);
		endingSkip.setBounds(780, 680, 250, 70);

		endingSkip.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(endingSkip);
		endingSkip.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("마우스 눌렀다 땜, 재시작");
				media.soundStop();
				endingB = false;
				ChangePanel.changePanel(mf, ending, new MainStage(mf, user, new Media()));
			}
		});
		loadingImage = toolkit.getImage("images/endingCredit.png").getScaledInstance(1024, 2068, 0);//배경 이미지
		Thread timer = new Thread(new Runnable() {
			int i = 0;
			@Override
			public void run() {
				while (endingB) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					i++;
					repaint();
				}
//				media.soundStop();
			}
		});
		timer.start();
		Thread changeY = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(41000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				while (true) {
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					backY -= 2;
					if (backY == -1400) {
						break;
					}
					
				}

			}
		});
		changeY.setDaemon(true);
		changeY.start();
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(loadingImage, 0, backY, this);// 배경 그리기
	}
}
