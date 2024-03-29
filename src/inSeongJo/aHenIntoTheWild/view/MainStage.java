package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import inSeongJo.aHenIntoTheWild.model.dao.UserDao;
import inSeongJo.aHenIntoTheWild.model.vo.MediaThread;
import inSeongJo.aHenIntoTheWild.model.vo.User;

public class MainStage extends JPanel {
	private MainFrame mf;
	private MainStage mainstage;
	User user;
	private Image background = new ImageIcon("images/YJimages/main_none.png").getImage();
	// private JLabel label = new JLabel(background);
	private Graphics ScreenGraphics;
	private Image ScreenImage;
	private UserDao ud = new UserDao();
	ArrayList<User> ulist = ud.readUserList();
	Media media = new Media();

	public MainStage(MainFrame mf, User user, Media media) {
		this.mf = mf;
		this.user = user;
		this.media = media;
		mainstage = this;
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
		// mf.add(this);
		media.sound("Sleepy_Wood");
		//// 유저 객체 정보 출력
		JLabel loginInfo = new JLabel("환영합니다. " + user.getNickName() + "님");
		loginInfo.setBounds(100, 8, 300, 100);
		loginInfo.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		loginInfo.setForeground(Color.DARK_GRAY);
		add(loginInfo);

		// 유저 버튼
		Image userImage = new ImageIcon("images/YJimages/user2.png").getImage().getScaledInstance(70, 70,
				Image.SCALE_SMOOTH);
		Image userPressedImage = new ImageIcon("images/YJimages/user2_pressed.png").getImage().getScaledInstance(70, 70,
				Image.SCALE_SMOOTH);
		JButton userButton = new JButton(new ImageIcon(userImage));
		userButton.setBorderPainted(false);
		userButton.setContentAreaFilled(false);
		userButton.setFocusPainted(false);
		userButton.setBounds(20, 20, 70, 70);
		userButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		userButton.setPressedIcon(new ImageIcon(userPressedImage));
		;
		add(userButton);

		userButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				media.soundStop();
				ChangePanel.changePanel(mf, mainstage, new UserInformation(mf, user));
			}
		});

		// 저장 버튼
		Image saveImage = new ImageIcon("images/YJimages/save.png").getImage().getScaledInstance(55, 55,
				Image.SCALE_SMOOTH);
		Image savePressedImage = new ImageIcon("images/YJimages/save_pressed.png").getImage().getScaledInstance(55, 55,
				Image.SCALE_SMOOTH);
		JButton saveButton = new JButton(new ImageIcon(saveImage));
		saveButton.setBorderPainted(false);
		saveButton.setContentAreaFilled(false);
		saveButton.setFocusPainted(false);
		saveButton.setBounds(930, 20, 70, 70);
		saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		saveButton.setPressedIcon(new ImageIcon(savePressedImage));
		add(saveButton);
		saveButton.addActionListener(new ActionListener() {

			// 파일 저장 기능
			// 기존 회원 정보와 로그인한 유저의 회원 정보 비교해 맞으면 그 정보를 파일에 저장
			@Override
			public void actionPerformed(ActionEvent e) {
				int i=0;
				for(User u: ulist) {
					if(user.getId().equals(u.getId())) {
						if(user.getPassword().equals(u.getPassword())) {
							user.setTotalScore(user.getStage1Score()+user.getStage2Score()
							+user.getStage3Score()+user.getStage4Score());
							ulist.set(i, user);
							ud.writeUserList(ulist);
							
						}
					}
					i++;
				}
				System.out.println(ulist);
				JOptionPane.showMessageDialog(null, "파일 저장 완료!");

			}
		});

		// 잎싹이 이미지
		Image ipssak = new ImageIcon("images/YJimages/ipssak.png").getImage().getScaledInstance(180, 250, 0);
		JLabel ipssakImage = new JLabel(new ImageIcon(ipssak));
		ipssakImage.setBounds(800, 500, 200, 200);
		add(ipssakImage);

		// 잎싹이 대사 텍스트
		JLabel ipssakText = new JLabel("안녕? 난 잎싹이라고 해.");
		ipssakText.setBounds(350, 510, 500, 100);
		ipssakText.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		ipssakText.setForeground(Color.DARK_GRAY);
		add(ipssakText);
		JLabel ipssakText2 = new JLabel("양계장은 너무 갑갑하지 않아? 난 이곳을 나가고 싶어.");
		ipssakText2.setBounds(270, 545, 500, 100);
		ipssakText2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		ipssakText2.setForeground(Color.DARK_GRAY);
		add(ipssakText2);
		JLabel ipssakText3 = new JLabel("우리 같이 마당으로 나가볼까?");
		ipssakText3.setBounds(330, 580, 500, 100);
		ipssakText3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		ipssakText3.setForeground(Color.DARK_GRAY);
		add(ipssakText3);

		// 잎싹이 버블텍스트
		Image bubble = new ImageIcon("images/YJimages/bubble.png").getImage().getScaledInstance(850, 230, 0);
		JLabel bubbleText = new JLabel(new ImageIcon(bubble));
		bubbleText.setBounds(30, 500, 900, 200);
		add(bubbleText);

		Image lockImage = new ImageIcon("images/buttons/lockbutton.png").getImage().getScaledInstance(200, 200, 0);
		// STAGE1 버튼
		Image stage1Image = new ImageIcon("images/YJimages/STAGE1.png").getImage().getScaledInstance(200, 200, 0);
		JButton stage1Button = new JButton(new ImageIcon(stage1Image));
		stage1Button.setBorderPainted(false);
		stage1Button.setContentAreaFilled(false);
		stage1Button.setFocusPainted(false);
		stage1Button.setBounds(20, 200, 300, 300);
		stage1Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		stage1Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				media.soundStop();
				if (user.isStage1Video()) {
					ChangePanel.changePanel(mf, mainstage, new Stage01_infoPage(mf, user, media));
				} else {
					mainstage.setVisible(false);
					new VideoTest(mf, "scene1", user, new Stage01_infoPage(mf, user, media), media);
					MediaThread mt = new MediaThread(mainstage, 51);
					user.setStage1Video(true);
					mt.start();
					mf.remove(mainstage);
					media.soundStop();
				}
			}
		});
		add(stage1Button);

		// STAGE2 버튼
		Image stage2Image = new ImageIcon("images/YJimages/STAGE2.png").getImage().getScaledInstance(200, 200, 0);
		JButton stage2Button = new JButton(new ImageIcon(stage2Image));
		stage2Button.setBorderPainted(false);
		stage2Button.setContentAreaFilled(false);
		stage2Button.setDisabledIcon(new ImageIcon(lockImage));
		stage2Button.setFocusPainted(false);
		stage2Button.setBounds(250, 200, 300, 300);
		stage2Button.setCursor(new Cursor(Cursor.HAND_CURSOR));

		stage2Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				media.soundStop();
				if (user.isStage2Video()) {
					ChangePanel.changePanel(mf, mainstage, new Stage02_infoPage(mf, user, media));
				} else {
					mainstage.setVisible(false);
					new VideoTest(mf, "scene2", user, new Stage02_infoPage(mf, user, media), media);
					MediaThread mt = new MediaThread(mainstage, 70);
					user.setStage2Video(true);
					mt.start();
					mf.remove(mainstage);
					media.soundStop();
				}
			}
		});

		// STAGE3 버튼
		Image stage3Image = new ImageIcon("images/YJimages/STAGE3.png").getImage().getScaledInstance(200, 200, 0);
		JButton stage3Button = new JButton(new ImageIcon(stage3Image));
		stage3Button.setBorderPainted(false);
		stage3Button.setContentAreaFilled(false);
		stage3Button.setFocusPainted(false);
		stage3Button.setDisabledIcon(new ImageIcon(lockImage));
		stage3Button.setBounds(470, 200, 300, 300);
		stage3Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		stage3Button.addActionListener(new ActionListener() {
//		@Override
//		public void actionPerformed(ActionEvent e) { // 안뚫림 미확인용
		stage3Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				media.soundStop();
				if (user.isStage3Video()) {
					ChangePanel.changePanel(mf, mainstage, new Stage03InfoPage(mf, user, media));
				} else {
					mainstage.setVisible(false);
					new VideoTest(mf, "scene3", user, new Stage03InfoPage(mf, user, media), media);
					MediaThread mt = new MediaThread(mainstage, 60);
					user.setStage3Video(true);
					mt.start();
					mf.remove(mainstage);
				}
			}
		});

		// STAGE4 버튼
		Image stage4Image = new ImageIcon("images/YJimages/STAGE4.png").getImage().getScaledInstance(200, 200, 0);
		JButton stage4Button = new JButton(new ImageIcon(stage4Image));
		stage4Button.setBorderPainted(false);
		stage4Button.setContentAreaFilled(false);
		stage4Button.setFocusPainted(false);
		stage4Button.setDisabledIcon(new ImageIcon(lockImage));
		stage4Button.setBounds(710, 200, 300, 300);
		stage4Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
//
//		stage4Button.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) { // 안뚫림 미확인용
		stage4Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				media.soundStop();
				if (user.isStage4Video()) {
					ChangePanel.changePanel(mf, mainstage, new Stage04infoPage(mf, user, media));
				} else {
					mainstage.setVisible(false);
					new VideoTest(mf, "scene4", user, new Stage04infoPage(mf, user, media), media);
					MediaThread mt = new MediaThread(mainstage, 75);
					user.setStage4Video(true);
					mt.start();
					mf.remove(mainstage);
				}
				
			}
		});

		if (user.getStage1Score() == 0) {
			stage2Button.setEnabled(false);
			stage3Button.setEnabled(false);
			stage4Button.setEnabled(false);
		} else {
			if (user.getStage2Score() == 0) {
				stage3Button.setEnabled(false);
				stage4Button.setEnabled(false);
			} else {
				if (user.getStage3Score() == 0) {
					stage4Button.setEnabled(false);
				}
			}

		}
		System.out.println(stage2Button.isEnabled() + " " + stage3Button.isEnabled() + " " + stage4Button.isEnabled());
		add(stage2Button);
		add(stage3Button);
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

}