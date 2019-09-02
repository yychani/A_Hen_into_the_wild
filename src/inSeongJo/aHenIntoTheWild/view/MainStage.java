package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import inSeongJo.aHenIntoTheWild.model.dao.UserDao;
import inSeongJo.aHenIntoTheWild.model.vo.User;
import inSeongJo.aHenIntoTheWild.view.MainPage.MyMouseAdapter;

public class MainStage extends JPanel {
	private MainFrame mf;
	private JPanel MainStage;
	User user;
	private Image background = new ImageIcon("images/YJimages/main_none.png").getImage();
	// private JLabel label = new JLabel(background);
	private Graphics ScreenGraphics;
	private Image ScreenImage;
	public MainStage(MainFrame mf, User user) {
		this.mf = mf;
		this.user = user;
		MainStage = this;
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
//		mf.add(this);
		
		//유저 객체 정보 출력
		JLabel loginInfo = new JLabel(user.getNickName());
		loginInfo.setBounds(100, 100, 300, 100);
		loginInfo.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 20));
		loginInfo.setForeground(Color.WHITE);
		add(loginInfo);
		
		//유저 버튼
		Image userImage = new ImageIcon("images/YJimages/user2.png").getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		Image userPressedImage = new ImageIcon("images/YJimages/user2_pressed.png").getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		JButton userButton = new JButton(new ImageIcon(userImage));
		userButton.setBorderPainted(false);
		userButton.setContentAreaFilled(false);
		userButton.setFocusPainted(false);
		userButton.setBounds(20, 20, 70, 70);
		userButton.setPressedIcon(new ImageIcon(userPressedImage));;
		add(userButton);
		
		userButton.addMouseListener(new MyMouseAdapter());
		
		//저장 버튼
		Image saveImage = new ImageIcon("images/YJimages/save.png").getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
		Image savePressedImage = new ImageIcon("images/YJimages/save_pressed.png").getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
		JButton saveButton = new JButton(new ImageIcon(saveImage));
		saveButton.setBorderPainted(false);
		saveButton.setContentAreaFilled(false);
		saveButton.setFocusPainted(false);
		saveButton.setBounds(930, 20, 70, 70);
		saveButton.setPressedIcon(new ImageIcon(savePressedImage));;
		add(saveButton);
		
		
		
		//잎싹이 이미지
		Image ipssak = new ImageIcon("images/YJimages/ipssak.png").getImage().getScaledInstance(180, 180, 0);
		JLabel ipssakImage = new JLabel(new ImageIcon(ipssak));
		ipssakImage.setBounds(800, 500, 200, 200);
		add(ipssakImage);
		
		//잎싹이 버블텍스트
		Image bubble = new ImageIcon("images/YJimages/bubbletext.png").getImage().getScaledInstance(850, 190, 0);
		JLabel bubbleText = new JLabel(new ImageIcon(bubble));
		bubbleText.setBounds(30, 500, 900, 200);
		add(bubbleText);
		
		//STAGE1 버튼
		Image stage1Image = new ImageIcon("images/YJimages/STAGE1.png").getImage().getScaledInstance(200, 200, 0);
		JButton stage1Button = new JButton(new ImageIcon(stage1Image));
		stage1Button.setBorderPainted(false);
		stage1Button.setContentAreaFilled(false);
		stage1Button.setFocusPainted(false);
		stage1Button.setBounds(20, 200, 300, 300);
		stage1Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChangePanel.changePanel(mf, MainStage, new Stage01(mf));
			}
		});
		add(stage1Button);
		
		
		//STAGE2 버튼
		Image stage2Image = new ImageIcon("images/YJimages/STAGE1.png").getImage().getScaledInstance(200, 200, 0);
		JButton stage2Button = new JButton(new ImageIcon(stage1Image));
		stage2Button.setBorderPainted(false);
		stage2Button.setContentAreaFilled(false);
		stage2Button.setFocusPainted(false);
		stage2Button.setBounds(250, 200, 300, 300);
		add(stage2Button);
		
		//STAGE3 버튼
		Image stage3Image = new ImageIcon("images/YJimages/STAGE1.png").getImage().getScaledInstance(200, 200, 0);
		JButton stage3Button = new JButton(new ImageIcon(stage1Image));
		stage3Button.setBorderPainted(false);
		stage3Button.setContentAreaFilled(false);
		stage3Button.setFocusPainted(false);
		stage3Button.setBounds(470, 200, 300, 300);
		add(stage3Button);
		
		//STAGE4 버튼
		Image stage4Image = new ImageIcon("images/YJimages/STAGE1.png").getImage().getScaledInstance(200, 200, 0);
		JButton stage4Button = new JButton(new ImageIcon(stage1Image));
		stage4Button.setBorderPainted(false);
		stage4Button.setContentAreaFilled(false);
		stage4Button.setFocusPainted(false);
		stage4Button.setBounds(710, 200, 300, 300);
		add(stage4Button);
		
		
		
	}

	public void paint(Graphics g) {
		ScreenImage = createImage(1024, 768);
		ScreenGraphics = ScreenImage.getGraphics();
		screenDraw(ScreenGraphics);
		g.drawImage(ScreenImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		paintComponents(g);
		this.repaint();
	}
	
	
	class MyMouseAdapter extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("클릭되었습니다.");
		}
	}
	

}