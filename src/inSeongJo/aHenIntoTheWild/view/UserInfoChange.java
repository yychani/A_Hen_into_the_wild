package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import inSeongJo.aHenIntoTheWild.controller.UserManager;
import inSeongJo.aHenIntoTheWild.model.dao.UserDao;
import inSeongJo.aHenIntoTheWild.model.vo.User;

public class UserInfoChange extends JPanel {
	private MainFrame mf;
	private JPanel UserInfoChange;
	private Image background = new ImageIcon("images/YJimages/main_none.png").getImage();
	// private JLabel label = new JLabel(background);
	private Graphics ScreenGraphics;
	private Image ScreenImage;
	private User user = new User();
	private User presentUser;
	private ArrayList<User> allUser;
	private String pass1 = "", pass2 = "";

	public UserInfoChange(MainFrame mf, User user) {
		this.mf = mf;
		this.user = user;
		UserInfoChange = this;
		this.setBounds(0, 0, 1024, 768);

		this.setLayout(null);

		mf.add(this);

		// 로그인한 유저 정보 가져오기
		UserDao userdao = new UserDao();
		allUser = userdao.readUserList();

		for (User u : allUser) {
			if (u.getId().equals(user.getId())) {
				if (u.getPassword().equals(user.getPassword())) {
					presentUser = u;
				} else {
				}

			}
		}

		//// JLabel text
		// 회원정보변경 텍스트
		JLabel changeInfoText = new JLabel("회원정보변경");
		changeInfoText.setBounds(380, 120, 300, 100);
		changeInfoText.setFont(new Font("맑은 고딕", Font.PLAIN, 50));
		changeInfoText.setForeground(Color.DARK_GRAY);
		add(changeInfoText);

		// 닉네임 텍스트
		JLabel nickNameText = new JLabel("닉네임");
		nickNameText.setBounds(270, 215, 300, 100);
		nickNameText.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		nickNameText.setForeground(Color.DARK_GRAY);
		add(nickNameText);

		// 이메일 텍스트
		JLabel emailText = new JLabel("이메일");
		emailText.setBounds(270, 295, 300, 100);
		emailText.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		emailText.setForeground(Color.DARK_GRAY);
		add(emailText);

		// 비밀번호 텍스트
		JLabel passwordText = new JLabel("비밀번호");
		passwordText.setBounds(265, 375, 300, 100);
		passwordText.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		passwordText.setForeground(Color.DARK_GRAY);
		add(passwordText);

		// 비밀번호 확인 텍스트
		JLabel repasswordText = new JLabel("비밀번호 확인");
		repasswordText.setBounds(240, 455, 300, 100);
		repasswordText.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		repasswordText.setForeground(Color.WHITE);
		add(repasswordText);

		// 비밀번호 재확인 알림 텍스트
		JLabel passwordCheckText = new JLabel("비밀번호 일치 확인이 필요합니다.");
		passwordCheckText.setBounds(365, 485, 500, 100);
		passwordCheckText.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		passwordCheckText.setForeground(Color.WHITE);
		add(passwordCheckText);
		

		// JTextField
		// 닉네임 변경 입력란
		JTextField nickNameTextField = new JTextField();
		nickNameTextField.setBounds(370, 250, 300, 30);
		nickNameTextField.setBorder(BorderFactory.createEmptyBorder());
		nickNameTextField.setText(presentUser.getNickName());
		add(nickNameTextField);

		// 이메일 변경 입력란
		JTextField emailTextField = new JTextField();
		emailTextField.setBounds(370, 330, 300, 30);
		emailTextField.setBorder(BorderFactory.createEmptyBorder());
		emailTextField.setText(presentUser.getEmail());
		add(emailTextField);

		// 비밀번호 변경 입력란
		JPasswordField passwordTextField = new JPasswordField();
		passwordTextField.setBounds(370, 410, 300, 30);
		passwordTextField.setBorder(BorderFactory.createEmptyBorder());
		add(passwordTextField);

		// 비밀번호 변경 재입력란
		JPasswordField repasswordTextField = new JPasswordField();
		repasswordTextField.setBounds(370, 490, 300, 30);
		repasswordTextField.setBorder(BorderFactory.createEmptyBorder());
		add(repasswordTextField);
		
		//비밀번호 재확인 알림 변경 이벤트
		repasswordTextField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				String password = "";
				char[] pass = passwordTextField.getPassword();

				for (int i = 0; i < pass.length; i++) {
					password += pass[i];
				}

				String repassword = "";
				char[] repass = repasswordTextField.getPassword();
				for (int i = 0; i < repass.length; i++) {
					repassword += repass[i];
				}
				
				if(password.equals(repassword)) {
					passwordCheckText.setText("비밀번호가 일치합니다.");
					passwordCheckText.setForeground(Color.BLUE);
				}else {
					passwordCheckText.setText("비밀번호가 일치하지 않습니다.");
					passwordCheckText.setForeground(Color.RED);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		

		// 수정 완료 버튼
		JButton modiCompleteButton = new JButton("수정 완료");
		modiCompleteButton.setBounds(450, 620, 120, 40);
		modiCompleteButton.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		modiCompleteButton.setBackground(Color.LIGHT_GRAY);
		modiCompleteButton.setBorderPainted(false);
		modiCompleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(modiCompleteButton);

		modiCompleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 회원 정보 변경 메소드
				UserManager um = new UserManager();
				// 패스워드 char -> String
				String password = "";
				char[] pass = passwordTextField.getPassword();

				for (int i = 0; i < pass.length; i++) {
					password += pass[i];
				}

				String repassword = "";
				char[] repass = repasswordTextField.getPassword();
				for (int i = 0; i < repass.length; i++) {
					repassword += repass[i];
				}

				// 비밀번호 미입력시 알림창
				if (password == "") {
					JOptionPane.showMessageDialog(null, "변경할 비밀번호를 입력해주세요.", "비밀번호 미입력", 1);
					return;
				}

				// 비민번호 동일 확인
				if (!password.equals(repassword)) {
					System.out.println("비밀번호 불일치");
					JOptionPane.showMessageDialog(null, "비밀번호가 불일치합니다. 다시 한번 입력해주세요.", "비밀번호 확인", 1);
					passwordTextField.setText("");
					repasswordTextField.setText("");
					return;
				}

				presentUser = um.UserInfoChagne(nickNameTextField.getText(), emailTextField.getText(), password, user);

				// 로그인 정보 페이지로 이동
				ChangePanel.changePanel(mf, UserInfoChange, new UserInformation(mf, presentUser));
			}
		});

		// 수정 취소 버튼
		JButton modiCancleButton = new JButton("수정 취소");
		modiCancleButton.setBounds(650, 620, 120, 40);
		modiCancleButton.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		modiCancleButton.setBackground(Color.LIGHT_GRAY);
		modiCancleButton.setBorderPainted(false);
		modiCancleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(modiCancleButton);

		modiCancleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ChangePanel.changePanel(mf, UserInfoChange, new UserInformation(mf, user));
			}
		});

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

	public String getPass1() {
		return pass1;
	}

	public void setPass1(String pass1) {
		this.pass1 = pass1;
	}

	public String getPass2() {
		return pass2;
	}

	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}

}
