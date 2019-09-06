package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import inSeongJo.aHenIntoTheWild.model.vo.MediaThread;
import inSeongJo.aHenIntoTheWild.model.vo.User;
import inSeongJo.aHenIntoTheWild.view.MainPage.RoundedBorder;

public class StartPage extends JPanel {
	MainFrame mf;
	User user;
	StartPage startPage;
	MainStage mainStage;
	private Image background;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	public StartPage(MainFrame mf, User user) {
		this.mf = mf;
		this.user = user;
		startPage = this;
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
		mf.add(this);
		
		
		background = toolkit.getImage("images/YJimages/Main_title.png").getScaledInstance(1024, 768, 0);// 배경 이미지
		
		JButton newStart = new JButton();
		newStart.setIcon(new ImageIcon(toolkit.getImage("images/buttons/newStart.png").getScaledInstance(200, 110, Image.SCALE_SMOOTH)));
		newStart.setPressedIcon(new ImageIcon(toolkit.getImage("images/buttons/newStart_Pressed.png").getScaledInstance(200, 110, Image.SCALE_SMOOTH)));
		newStart.setBounds(300, 350, 200, 100);
		newStart.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		newStart.setContentAreaFilled(false);
		newStart.setBorderPainted(false);
		newStart.setBackground(Color.LIGHT_GRAY);
		newStart.setFocusPainted(false);
		
//		newStart.setBorder(new RoundedBorder(20));
		
		newStart.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(newStart);
		newStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startPage.setVisible(false);
				new VideoTest(mf, "intro", user, new MainStage(mf, user));
				MediaThread mt = new MediaThread(startPage, 50);
				
				mt.start();
				mf.remove(startPage);
			}
		});

		JButton continued = new JButton();
		continued.setIcon(new ImageIcon(toolkit.getImage("images/buttons/continued.png").getScaledInstance(200, 110, Image.SCALE_SMOOTH)));
		continued.setPressedIcon(new ImageIcon(toolkit.getImage("images/buttons/continued_Pressed.png").getScaledInstance(200, 110, Image.SCALE_SMOOTH)));
		continued.setBorderPainted(false);
		continued.setContentAreaFilled(false);
		continued.setFocusPainted(false);
		continued.setBounds(550, 350, 200, 100);
		continued.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(continued);
		
	}
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, null);
//		this.repaint();
	}
	
	class RoundedBorder implements Border {

		private int radius;

		RoundedBorder(int radius) {
			this.radius = radius;
		}

		public Insets getBorderInsets(Component c) {
			return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
		}

		public boolean isBorderOpaque() {
			return true;
		}

		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
		}
	}
}
