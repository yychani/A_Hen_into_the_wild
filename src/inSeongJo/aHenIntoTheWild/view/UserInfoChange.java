package inSeongJo.aHenIntoTheWild.view;

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
	private Image background = new ImageIcon("images/main_none.jpg").getImage();
	// private JLabel label = new JLabel(background);
	private Graphics ScreenGraphics;
	private Image ScreenImage;

	public UserInfoChange(MainFrame mf) {
		this.setBounds(0, 0, 1024, 768);

		this.setLayout(null);

		mf.add(this);

		// ȸ���������� �ؽ�Ʈ �̹��� ��
		Image infoChange = new ImageIcon("images/ȸ����������.png").getImage().getScaledInstance(190, 60, 0);
		JLabel infoChangeIcon = new JLabel(new ImageIcon(infoChange));
		infoChangeIcon.setBounds(400, 100, 200, 200);
		add(infoChangeIcon);

		// �г��� ���� �Է¶�
		JTextField nickNameText = new JTextField();
		nickNameText.setBounds(350, 400, 300, 30);
		nickNameText.setBorder(BorderFactory.createEmptyBorder());
		add(nickNameText);
		
		// �̸��� ���� �Է¶�
		JTextField emailText = new JTextField();
		emailText.setBounds(350, 450, 300, 30);
		emailText.setBorder(BorderFactory.createEmptyBorder());
		add(emailText);
		
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
