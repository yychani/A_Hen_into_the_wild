package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import inSeongJo.aHenIntoTheWild.model.dao.UserDao;
import inSeongJo.aHenIntoTheWild.model.vo.User;

public class UserInformation extends JPanel {

	Image img;
	JLabel id = new JLabel("아이디");
	JLabel nik = new JLabel("닉네임");
	JLabel email = new JLabel("이메일");
	JLabel rank = new JLabel("랭킹");
	JLabel stage = new JLabel("Stage");
	User user;
	User presentUser;

	JTextField tarr[] = new JTextField[5];

	JButton btn1 = new JButton("회원정보 수정");
	JButton btn2 = new JButton("로그아웃");

	private Font f1;
	private JPanel UserInformation; // 화면 전환을 위해 changePanel메소드에서 사용할 필드.
	private ArrayList<User> allUser;
	MainPage mainPage;
	
	public UserInformation(MainFrame mf, User user) {
		this.user = user;
		UserInformation = this;
		setLayout(null); // JFrame에 얹을 컴포넌트의 배치방법을 지정하는 메소드
		setSize(1024, 768);

		Toolkit tk = Toolkit.getDefaultToolkit();

		Dimension d = tk.getScreenSize();
		UserDao userdao = new UserDao();// userdao에 있는 파일 읽어들이는 메소드 불러오기 위함
		allUser = userdao.readUserList();
		
		//회원정보 텍스트
		JLabel userInfoText = new JLabel("회원정보");
		userInfoText.setBounds(380, 120, 300, 100);
		userInfoText.setFont(new Font("맑은 고딕", Font.PLAIN, 50));
		userInfoText.setForeground(Color.DARK_GRAY);
		add(userInfoText);

		// User 타입의 변수 u에 allUser라는 ArrayList를 대입하며 출력
		// 입력한 id와 데이터에 저장된 아이디를 비교해 같으면 출력
		for (User u : allUser) {
			if (u.getId().equals(user.getId())) {
				if (u.getPassword().equals(user.getPassword())) {
					presentUser = u;
				}
			}
		}

		try {
			img = tk.getImage("images/YJimages/Main_none.png").getScaledInstance(1024, 768, Image.SCALE_SMOOTH);

		} catch (Exception e) {
			e.getMessage();
		}

		f1 = new Font("맑은 고딕", Font.PLAIN, 20);

		add(id);
		add(nik);
		add(email);
		add(rank);
		add(stage);

		id.setBounds(150, 150, 50, 50);
		id.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		nik.setBounds(150, 250, 50, 50);
		nik.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		email.setBounds(150, 350, 50, 50);
		email.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rank.setBounds(550, 150, 50, 50);
		rank.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		stage.setBounds(550, 250, 50, 50);
		stage.setFont(new Font("맑은 고딕", Font.PLAIN, 20));

		// TextField를 panel에 붙이고 위치를 지정하는 동시에 TextField에 회원 정보 표시
		for (int i = 0; i < tarr.length; i++) {
			tarr[i] = new JTextField();
			add(tarr[i]);
			switch (i) {
			case 0:
				tarr[i].setBounds(200, 150, 250, 30);
				tarr[i].setBorder(BorderFactory.createEmptyBorder());
				tarr[i].setText(presentUser.getId()); // 회원정보 중 id표시
				tarr[i].setEditable(false); // 값 변경하지 못하게 하는 메소드
				break;

			case 1:
				tarr[i].setBounds(200, 250, 250, 30);
				tarr[i].setBorder(BorderFactory.createEmptyBorder());
				tarr[i].setText(presentUser.getNickName()); // 회원정보 중 닉네임 표시
				tarr[i].setEditable(false);
				break;

			case 2:
				tarr[i].setBounds(200, 350, 250, 30);
				tarr[i].setBorder(BorderFactory.createEmptyBorder());
				tarr[i].setText(presentUser.getEmail()); // 회원 정보 중 이메일 표시
				tarr[i].setEditable(false);
				break;

			case 3:
				tarr[i].setBounds(600, 150, 250, 30);
				tarr[i].setBorder(BorderFactory.createEmptyBorder());
				tarr[i].setText(presentUser.getTotalScore() + "");// 회원정보 중 전체 점수 표시 (단, totalscore가 int형이므로 문자열로 변환)
				tarr[i].setEditable(false);

			case 4:
				tarr[i].setBounds(600, 250, 250, 50);
				if (presentUser.getStage4Score() == 0) {
					if (presentUser.getStage3Score() == 0) {
						if (presentUser.getStage2Score() == 0) {
							if (presentUser.getStage1Score() == 0) {
								tarr[i].setBorder(BorderFactory.createEmptyBorder());
								tarr[i].setText("클리어한 스테이지가 없습니다.");
								tarr[i].setEditable(false);
								break;
							}
							tarr[i].setText("1");
							tarr[i].setEditable(false);
						}
						tarr[i].setText("2");
						tarr[i].setEditable(false);
						break;
					}
					tarr[i].setText("3");
					tarr[i].setEditable(false);
					break;
				} else {
					tarr[i].setText("4");
					tarr[i].setEditable(false);
					break;
				}
			}
		}

		btn1.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		btn2.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		btn1.setBackground(Color.LIGHT_GRAY);
		btn2.setBackground(Color.LIGHT_GRAY);
		btn1.setBorderPainted(false);
		btn2.setBorderPainted(false);
		add(btn1);
		add(btn2);

		btn1.setBounds(300, 620, 150, 40);
		btn2.setBounds(500, 620, 150, 40);
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ChangePanel.changePanel(mf, UserInformation, new MainPage(mf));
			}
		});
		
		

		// 홈버튼
		Image homeImage = new ImageIcon("images/YJimages/home.png").getImage().getScaledInstance(70, 70,
				Image.SCALE_SMOOTH);
		Image homePressedImage = new ImageIcon("images/YJimages/home_pressed.png").getImage().getScaledInstance(70, 70,
				Image.SCALE_SMOOTH);
		JButton homeButton = new JButton(new ImageIcon(homeImage));
		homeButton.setBorderPainted(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setFocusPainted(false);
		homeButton.setBounds(20, 20, 70, 70);
		homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		homeButton.setPressedIcon(new ImageIcon(homePressedImage));
		;
		add(homeButton);

		// 메인 스테이지로 이동
		homeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ChangePanel.changePanel(mf, UserInformation, new MainStage(mf, user));

			}
		});
		// 회원정보 변경으로 이동
		btn1.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {
				ChangePanel.changePanel(mf, UserInformation, new UserInfoChange(mf, user));
			}
		});

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 0, 0, this);
		paintComponents(g);
		this.repaint();
	}

}
