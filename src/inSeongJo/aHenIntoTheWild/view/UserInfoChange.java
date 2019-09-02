package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UserInfoChange extends JPanel {
	private MainFrame mf;
	private JPanel UserInfoChange;
	private Image background = new ImageIcon("images/YJimages/main_none.png").getImage();
	// private JLabel label = new JLabel(background);
	private Graphics ScreenGraphics;
	private Image ScreenImage;

	public UserInfoChange(MainFrame mf) {
		this.mf = mf;
		UserInfoChange = this;
		this.setBounds(0, 0, 1024, 768);

		this.setLayout(null);

		mf.add(this);

		// 회원정보변경 텍스트 
		JLabel changeInfoText = new JLabel("회원정보변경");
		changeInfoText.setBounds(450, 150, 300, 100);
		changeInfoText.setFont(new Font("나눔스퀘어 ExtraBold", Font.PLAIN, 35));
		changeInfoText.setForeground(Color.WHITE);
		add(changeInfoText);
		
		//닉네임 텍스트
		JLabel nickNameText = new JLabel("닉네임");
		nickNameText.setBounds(300, 100, 300, 100);
		nickNameText.setFont(new Font("나눔스퀘어 ExtraBold", Font.PLAIN, 20));
		nickNameText.setForeground(Color.WHITE);
		add(nickNameText);
		
		// 닉네임 변경 입력란
		JTextField nickNameTextField = new JTextField();
		nickNameTextField.setBounds(350, 400, 300, 30);
		nickNameTextField.setBorder(BorderFactory.createEmptyBorder());
		add(nickNameTextField);
		
		//이메일 텍스트
		JLabel emailText = new JLabel("닉네임");
		emailText.setBounds(300, 120, 300, 100);
		emailText.setFont(new Font("나눔스퀘어 ExtraBold", Font.PLAIN, 20));
		emailText.setForeground(Color.WHITE);
		add(emailText);
		
		// 이메일 변경 입력란
		JTextField emailTextField = new JTextField();
		emailTextField.setBounds(350, 450, 300, 30);
		emailTextField.setBorder(BorderFactory.createEmptyBorder());
		add(emailTextField);
		
		// 비밀번호 변경 입력란
		JPasswordField passwordText = new JPasswordField();
		passwordText.setBounds(350, 300, 300, 30);
		passwordText.setBorder(BorderFactory.createEmptyBorder());
		add(passwordText);
		
		// 비밀번호 변경 재입력란
		JPasswordField repasswordText = new JPasswordField();
		repasswordText.setBounds(350, 350, 300, 30);
		repasswordText.setBorder(BorderFactory.createEmptyBorder());
		add(repasswordText);
		
		//수정 완료 아이콘 이미지
		
		//수정 취소 아이콘 이미지

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
