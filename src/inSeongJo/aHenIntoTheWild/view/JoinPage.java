package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class JoinPage extends JPanel {
	private MainFrame mf;
	private JPanel JoinPage;
	private Image background = new ImageIcon("images/YJimages/main_none.jpg").getImage();
	// private JLabel label = new JLabel(background);
	private Graphics ScreenGraphics;
	private Image ScreenImage;

	public JoinPage(MainFrame mf) {
		this.mf = mf;
		JoinPage = this;
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);

		// mf.add(this);

		// 회원가입 텍스트
		JLabel joinText = new JLabel("회원가입");
		joinText.setBounds(450, 150, 300, 100);
		joinText.setFont(new Font("나눔스퀘어 ExtraBold", Font.PLAIN, 35));
		joinText.setForeground(Color.WHITE);
		add(joinText);

		// 아이디 텍스트
		JLabel idText = new JLabel("아이디");
		idText.setBounds(235, 215, 300, 100);
		idText.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 20));
		idText.setForeground(Color.WHITE);
		add(idText);

		// 비밀번호 텍스트
		JLabel passwordText = new JLabel("비밀번호");
		passwordText.setBounds(225, 285, 300, 100);
		passwordText.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 20));
		passwordText.setForeground(Color.WHITE);
		add(passwordText);

		// 비밀번호 확인 텍스트
		JLabel repasswordText = new JLabel("비밀번호 확인");
		repasswordText.setBounds(210, 355, 300, 100);
		repasswordText.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 20));
		repasswordText.setForeground(Color.WHITE);
		add(repasswordText);

		// 닉네임 텍스트
		JLabel nickNameText = new JLabel("닉네임");
		nickNameText.setBounds(235, 425, 300, 100);
		nickNameText.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 20));
		nickNameText.setForeground(Color.WHITE);
		add(nickNameText);

		// 이메일 텍스트
		JLabel emailText = new JLabel("이메일");
		emailText.setBounds(235, 495, 300, 100);
		emailText.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 20));
		emailText.setForeground(Color.WHITE);
		add(emailText);

		// 아이디 입력란
		JTextField idTextField = new JTextField();
		idTextField.setBounds(350, 250, 300, 30);
		idTextField.setBorder(BorderFactory.createEmptyBorder());
		idTextField.setBorder(BorderFactory.createBevelBorder(-1));
		add(idTextField);

		// 비밀번호 입력란
		JPasswordField passwordTextField = new JPasswordField();
		passwordTextField.setBounds(350, 320, 300, 30);
		passwordTextField.setBorder(BorderFactory.createEmptyBorder());
		add(passwordTextField);

		// 비밀번호 재확인 입력란
		JPasswordField repasswordTextField = new JPasswordField();
		repasswordTextField.setBounds(350, 390, 300, 30);
		repasswordTextField.setBorder(BorderFactory.createEmptyBorder());
		add(repasswordTextField);

		// 닉네임 입력란
		JTextField nickNameTextField = new JTextField();
		nickNameTextField.setBounds(350, 460, 300, 30);
		nickNameTextField.setBorder(BorderFactory.createEmptyBorder());
		add(nickNameTextField);

		// 이메일 입력란
		JTextField emailTextField = new JTextField();
		emailTextField.setBounds(350, 530, 300, 30);
		emailTextField.setBorder(BorderFactory.createEmptyBorder());
		add(emailTextField);

		// 중복확인 버튼
		JButton checkButton = new JButton("중복확인");
		checkButton.setBounds(670, 245, 120, 40);
		checkButton.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 20));
		checkButton.setBackground(Color.LIGHT_GRAY);
		add(checkButton);

		// 완료 버튼
		JButton completeButton = new JButton("완료");
		completeButton.setBounds(670, 620, 120, 40);
		completeButton.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 20));
		completeButton.setBackground(Color.LIGHT_GRAY);
		completeButton.setBorderPainted(false);
		add(completeButton);


		// 가입취소 버튼
		JButton cancleButton = new JButton("가입취소");
		cancleButton.setBounds(820, 620, 120, 40);
		cancleButton.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 20));
		cancleButton.setBackground(Color.LIGHT_GRAY);
		cancleButton.setBorderPainted(false);
		add(cancleButton);

		// 가입취소 -> 메인페이지로 이동
		cancleButton.addMouseListener(new MyMouseAdapter());

	}

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
}
