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

		// ȸ���������� �ؽ�Ʈ 
		JLabel changeInfoText = new JLabel("ȸ����������");
		changeInfoText.setBounds(450, 150, 300, 100);
		changeInfoText.setFont(new Font("���������� ExtraBold", Font.PLAIN, 35));
		changeInfoText.setForeground(Color.WHITE);
		add(changeInfoText);
		
		//�г��� �ؽ�Ʈ
		JLabel nickNameText = new JLabel("�г���");
		nickNameText.setBounds(300, 100, 300, 100);
		nickNameText.setFont(new Font("���������� ExtraBold", Font.PLAIN, 20));
		nickNameText.setForeground(Color.WHITE);
		add(nickNameText);
		
		// �г��� ���� �Է¶�
		JTextField nickNameTextField = new JTextField();
		nickNameTextField.setBounds(350, 400, 300, 30);
		nickNameTextField.setBorder(BorderFactory.createEmptyBorder());
		add(nickNameTextField);
		
		//�̸��� �ؽ�Ʈ
		JLabel emailText = new JLabel("�г���");
		emailText.setBounds(300, 120, 300, 100);
		emailText.setFont(new Font("���������� ExtraBold", Font.PLAIN, 20));
		emailText.setForeground(Color.WHITE);
		add(emailText);
		
		// �̸��� ���� �Է¶�
		JTextField emailTextField = new JTextField();
		emailTextField.setBounds(350, 450, 300, 30);
		emailTextField.setBorder(BorderFactory.createEmptyBorder());
		add(emailTextField);
		
		// ��й�ȣ ���� �Է¶�
		JPasswordField passwordText = new JPasswordField();
		passwordText.setBounds(350, 300, 300, 30);
		passwordText.setBorder(BorderFactory.createEmptyBorder());
		add(passwordText);
		
		// ��й�ȣ ���� ���Է¶�
		JPasswordField repasswordText = new JPasswordField();
		repasswordText.setBounds(350, 350, 300, 30);
		repasswordText.setBorder(BorderFactory.createEmptyBorder());
		add(repasswordText);
		
		//���� �Ϸ� ������ �̹���
		
		//���� ��� ������ �̹���

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
