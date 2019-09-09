package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import inSeongJo.aHenIntoTheWild.controller.UserManager;
import inSeongJo.aHenIntoTheWild.model.dao.SendEmail;
import inSeongJo.aHenIntoTheWild.model.dao.VerifyEmail;
import inSeongJo.aHenIntoTheWild.model.vo.User;

public class JoinPage extends JPanel {
	private MainFrame mf;
	private JPanel JoinPage;
	private Image background = new ImageIcon("images/YJimages/main_none.png").getImage();
	// private JLabel label = new JLabel(background);
	private Graphics ScreenGraphics;
	private Image ScreenImage;
	private JTextField idTextField;
	private JPasswordField passwordTextField;
	private JTextField nickNameTextField;
	private JTextField emailTextField;
	private JTextField emailTextField2;
	private String password;
	private UserManager um = new UserManager();
	private String pass1 = "", pass2 = "";

	public JoinPage(MainFrame mf) {
		this.mf = mf;
		JoinPage = this;
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);

		// mf.add(this);

		// 회원가입 텍스트
		JLabel joinText = new JLabel("회원가입");
		joinText.setBounds(450, 150, 300, 100);
		joinText.setFont(new Font("맑은 고딕", Font.PLAIN, 35));
		joinText.setForeground(Color.DARK_GRAY);
		add(joinText);

		// 아이디 텍스트
		JLabel idText = new JLabel("아이디");
		idText.setBounds(235, 215, 300, 100);
		idText.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		// idText.setBorder(BorderFactory.createBevelBorder(1, Color.black, shadow));
		idText.setForeground(Color.DARK_GRAY);
		add(idText);

		// 아이디 중복확인 메세지
		JLabel idCheckText = new JLabel("아이디 중복확인을 해주세요.");
		idCheckText.setBounds(350, 240, 500, 100);
		idCheckText.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		idCheckText.setForeground(Color.DARK_GRAY);
		add(idCheckText);

		// 비밀번호 텍스트
		JLabel passwordText = new JLabel("비밀번호");
		passwordText.setBounds(225, 285, 300, 100);
		passwordText.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		passwordText.setForeground(Color.DARK_GRAY);
		add(passwordText);

		// 비밀번호 확인 텍스트
		JLabel repasswordText = new JLabel("비밀번호 확인");
		repasswordText.setBounds(210, 355, 300, 100);
		repasswordText.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		repasswordText.setForeground(Color.DARK_GRAY);
		add(repasswordText);

		// 닉네임 텍스트
		JLabel nickNameText = new JLabel("닉네임");
		nickNameText.setBounds(235, 425, 300, 100);
		nickNameText.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		nickNameText.setForeground(Color.DARK_GRAY);
		add(nickNameText);

		// 이메일 텍스트
		JLabel emailText = new JLabel("이메일");
		emailText.setBounds(235, 495, 300, 100);
		emailText.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		emailText.setForeground(Color.DARK_GRAY);
		add(emailText);

		// 이메일 인증 유뮤 텍스트
		JLabel emailVeriTest = new JLabel("이메일 인증이 필요합니다.");
		emailVeriTest.setBounds(350, 520, 300, 100);
		emailVeriTest.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		emailVeriTest.setForeground(Color.WHITE);
		add(emailVeriTest);

		// 아이디 입력란
		/* JTextField */ idTextField = new JTextField();
		idTextField.setBounds(350, 250, 300, 30);
		idTextField.setBorder(BorderFactory.createEmptyBorder());
		idTextField.setBorder(BorderFactory.createBevelBorder(-1));
		add(idTextField);

		// 비밀번호 입력란
		/* JPasswordField */ passwordTextField = new JPasswordField();
		passwordTextField.setBounds(350, 320, 300, 30);
		passwordTextField.setBorder(BorderFactory.createEmptyBorder());
		add(passwordTextField);

		// 비밀번호 재확인 입력란
		JPasswordField repasswordTextField = new JPasswordField();
		repasswordTextField.setBounds(350, 390, 300, 30);
		repasswordTextField.setBorder(BorderFactory.createEmptyBorder());
		add(repasswordTextField);

		// 비밀번호 재확인 알림 텍스트
		JLabel passwordCheckText = new JLabel("비밀번호 일치 확인이 필요합니다.");
		passwordCheckText.setBounds(350, 380, 500, 100);
		passwordCheckText.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		passwordCheckText.setForeground(Color.WHITE);
		add(passwordCheckText);

		// 비밀번호 재확인 알림 변경 이벤트
		repasswordTextField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				password = "";
				char[] pass = passwordTextField.getPassword();

				for (int i = 0; i < pass.length; i++) {
					password += pass[i];
				}

				String repassword = "";
				char[] repass = repasswordTextField.getPassword();
				for (int i = 0; i < repass.length; i++) {
					repassword += repass[i];
				}

				if (password.equals(repassword)) {
					passwordCheckText.setText("비밀번호가 일치합니다.");
					passwordCheckText.setForeground(Color.BLUE);
				} else {
					passwordCheckText.setText("비밀번호가 일치하지 않습니다.");
					passwordCheckText.setForeground(Color.RED);
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		// 닉네임 입력란
		/* JTextField */ nickNameTextField = new JTextField();
		nickNameTextField.setBounds(350, 460, 300, 30);
		nickNameTextField.setBorder(BorderFactory.createEmptyBorder());
		add(nickNameTextField);

		// 이메일 입력란
		/* JTextField */ emailTextField = new JTextField();
		emailTextField.setBounds(350, 530, 90, 30);
		emailTextField.setBorder(BorderFactory.createEmptyBorder());
		add(emailTextField);
		
		// 이메일 주소 입력란
		emailTextField2 = new JTextField();
		emailTextField2.setBounds(445, 530, 100, 30);
		emailTextField2.setBorder(BorderFactory.createEmptyBorder());
		add(emailTextField2);

		String[] emails = { "직접입력", "@naver.com", "@daum.net", "@gmail.com", "@hotmail.com", "@nate.com",
				"@yahoo.co.kr", "@paran.com", "@empas.com", "@dreamwiz.com" };
		JComboBox emailList = new JComboBox(emails);

		emailList.setSelectedIndex(0);

		emailList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JComboBox cb = (JComboBox) e.getSource();

				String email = (String) cb.getSelectedItem();

				emailTextField2.setText(email);

			}
		});
		emailList.setBounds(550, 530, 120, 30);
		add(emailList);

		// 이메일 인증 버튼
		JButton verifyButton = new JButton("인증번호 전송");
		verifyButton.setBounds(690, 530, 150, 30);
		verifyButton.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		verifyButton.setBackground(Color.LIGHT_GRAY);
		add(verifyButton);

		verifyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String verificationNumber = um.verificationNumber();
				String userEmailAddress = emailTextField.getText() + emailTextField2.getText();

				// 인증번호 메일 보내기
				VerifyEmail ve = new VerifyEmail();
				ve.MailSend(userEmailAddress, nickNameTextField.getText(), verificationNumber);

				// 인증번호 입력 창
				JFrame df = new JFrame();
				df.setLayout(null);
				df.setSize(500, 230);
				JPanel panel = new JPanel();
				Dialog dl = new Dialog(df, "email");
				dl.setBounds(300, 200, 200, 200);
				

				JLabel text = new JLabel("인증번호를 입력해주세요.");
				text.setBounds(20, 20, 200, 20);
				df.add(text);

				JTextField verifyTextField = new JTextField();
				verifyTextField.setBounds(20, 50, 200, 20);
				verifyTextField.setBorder(BorderFactory.createEmptyBorder());
				df.add(verifyTextField);

				JButton verifyButton = new JButton("인증확인");
				verifyButton.setBounds(100, 80, 100, 20);
				df.add(verifyButton);
				
				df.setVisible(true);

				verifyButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (verifyTextField.getText().equals(verificationNumber)) {
							JOptionPane.showMessageDialog(null, "인증완료! 회원가입을 완료해주세요", "인증완료", 1);
							emailVeriTest.setText("이메일 인증 완료");
							emailVeriTest.setForeground(Color.BLUE);
							System.out.println("인증번호 동일 가입 가능");
						} else {
							JOptionPane.showMessageDialog(null, 
									"인증번호 불일치! 다시 한번 확인해주세요", "인증번호 불일치", 1);
							emailVeriTest.setForeground(Color.RED);
							System.out.println("인증번호 불일치 가입 블가능");
						}

					}
				});

			}
		});
		

		// 중복확인 버튼
		JButton checkButton = new JButton("아이디 중복확인");
		checkButton.setBounds(670, 245, 180, 40);
		checkButton.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		checkButton.setBackground(Color.LIGHT_GRAY);
		add(checkButton);

		// 아이디 중복확인 이벤트
		checkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 아이디 텍스트 필드 미입력시
				if (idTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "아이디 미입력", 1);
					return;
				}

				// 아이디 중복확인
				if (um.idCheck(idTextField.getText())) {
					// true -> "사용하실 수 없는 아이디입니다."
					// false -> "사용하실 수 있는 아이디입니다."
					idCheckText.setText("사용하실 수 없는 아이디입니다.");
					idCheckText.setForeground(Color.RED);
					idTextField.setText("");
				} else {
					idCheckText.setText("사용하실 수 있는 아이디입니다.");
					idCheckText.setForeground(Color.BLUE);
				}

			}
		});

		// 완료 버튼
		JButton completeButton = new JButton("완료");
		completeButton.setBounds(670, 620, 120, 40);
		completeButton.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		completeButton.setBackground(Color.LIGHT_GRAY);
		completeButton.setBorderPainted(false);
		add(completeButton);

		// char[] 패스워드 -> String으로 변환 & 유저 객체 생성 & 메인 스테이지로 이동
		completeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// 패스워드 char -> String
				password = "";
				char[] pass = passwordTextField.getPassword();

				for (int i = 0; i < pass.length; i++) {
					password += pass[i];
				}

				String repassword = "";
				char[] repass = repasswordTextField.getPassword();
				for (int i = 0; i < repass.length; i++) {
					repassword += repass[i];
				}

				// 아이디 중복 확인했는지 확인
				if (um.idCheck(idTextField.getText())) {
					// true -> "사용하실 수 없는 아이디입니다."
					// false -> "사용하실 수 있는 아이디입니다."
					JOptionPane.showMessageDialog(null, "아이디를 다시 한번 확인해주세요.", "아이디 확인", 1);
				} else {
					// 비밀번호 일치 확인
					if (!password.equals(repassword)) {
						System.out.println("비밀번호 불일치");
						JOptionPane.showMessageDialog(null, "비밀번호가 불일치합니다. 다시 한번 입력해주세요.", "비밀번호 확인", 1);
						passwordCheckText.setText("다시 한번 입력해주세요.");
						passwordCheckText.setForeground(Color.RED);
						passwordTextField.setText("");
						repasswordTextField.setText("");
					} else {
						System.out.println("비밀번호 일치");
						// 닉네임 입력란 공백 확인
						if (nickNameTextField.getText().equals("")) {
							System.out.println("닉네임 미입력");
							JOptionPane.showMessageDialog(null, "닉네입을 입력해주세요.", "닉네임 미입력", 1);
						} else {
							if (emailTextField.getText().equals("")) {
								System.out.println("이메일 미입력");
								JOptionPane.showMessageDialog(null, "이메일을 입력해주세요.", "이메일 미입력", 1);
							} else {
								// 모든 회원가입 조건 통과
								// 새로운 유저 객체 생성
								um.insertUser(inputUser());
								// 이메일 보내기
								SendEmail se = new SendEmail();
								se.MailSend(emailTextField.getText(), nickNameTextField.getText());
								// 가입완료 팝업창
								JOptionPane.showMessageDialog(null, "가입완료", "회원가입", 1);
								// 메인 페이지 이동
								ChangePanel.changePanel(mf, JoinPage, new MainPage(mf));
							}
						}
					}

				}

				// 비밀번호 일치 확인
				/*
				 * if(password.equals(repassword)){ System.out.println("비밀번호 일치"); }else {
				 * System.out.println("비밀번호 불일치"); JOptionPane.showMessageDialog(null,
				 * "비밀번호가 불일치합니다. 다시 한번 입력해주세요.", "비밀번호 확인", 1);
				 * passwordCheckText.setText("다시 한번 입력해주세요.");
				 * passwordCheckText.setForeground(Color.RED); passwordTextField.setText("");
				 * repasswordTextField.setText(""); }
				 */

				// um.insertUser(inputUser());

				// JOptionPane.showMessageDialog(null, "가입완료", "회원가입", 1);

				// ChangePanel.changePanel(mf, JoinPage, new MainStage(mf));
			}
		});

		// 가입취소 버튼
		JButton cancleButton = new JButton("가입취소");
		cancleButton.setBounds(820, 620, 120, 40);
		cancleButton.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		cancleButton.setBackground(Color.LIGHT_GRAY);
		cancleButton.setBorderPainted(false);
		add(cancleButton);

		// 가입취소 -> 메인페이지로 이동
		cancleButton.addMouseListener(new MyMouseAdapter());

	}

	// 가입취소 -> 메인페이지로 이동
	class MyMouseAdapter extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			ChangePanel.changePanel(mf, JoinPage, new MainPage(mf));
		}
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

	public User inputUser() {

		User u = new User();

		u.setId(idTextField.getText());
		u.setPassword(password);
		u.setNickName(nickNameTextField.getText());
		u.setEmail(emailTextField.getText());

		return u;
	}
}
