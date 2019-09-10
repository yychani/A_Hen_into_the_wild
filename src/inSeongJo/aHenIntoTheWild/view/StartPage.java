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
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import inSeongJo.aHenIntoTheWild.model.dao.UserDao;
import inSeongJo.aHenIntoTheWild.model.vo.MediaThread;
import inSeongJo.aHenIntoTheWild.model.vo.User;

public class StartPage extends JPanel {
	MainFrame mf;
	User user;
	StartPage startPage;
	MainStage mainStage;
	private Image background;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	UserDao ud = new UserDao();
	ArrayList<User> ulist = ud.readUserList();
	
	public StartPage(MainFrame mf, User user, Media media) {
		this.mf = mf;
		this.user = user;
		startPage = this;
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
		mf.add(this);
		
		media.soundStop();
		background = toolkit.getImage("images/YJimages/Main_title.png").getScaledInstance(1024, 768, 0);// 배경 이미지
		//새로하기 버튼
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
		//새로하기 기능
		newStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i=0; 
				for(User u: ulist) {
					if(user.getId().equals(u.getId())) {
						if(user.getPassword().equals(u.getPassword())) {
							
							user.setStage1Score(0);
							user.setStage2Score(0);
							user.setStage3Score(0);
							user.setStage4Score(0);
							
							user.setStage1Video(false);
							user.setStage2Video(false);
							user.setStage3Video(false);
							user.setStage4Video(false);
							
							ulist.set(i, user);
							ud.readUserList();
						}
						i++;		
					}
				
				}
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
		//이어하기 기능
		continued.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int i=0; 
				for(User u: ulist) {
					if(user.getId().equals(u.getId())) {
						if(user.getPassword().equals(u.getPassword())) {
							
							ulist.set(i, user);
							ud.readUserList();
						}
					}
					i++;
				}
				
				ChangePanel.changePanel(mf, startPage , new MainStage(mf, user));
			}
		});
		if(user.getStage1Score() == 0) {
			continued.setEnabled(false);
		}
		
	}
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, null);
		repaint();
	}
}
