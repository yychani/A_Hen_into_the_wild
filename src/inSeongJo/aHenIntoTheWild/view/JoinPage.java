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

		// ȸ������ �ؽ�Ʈ
		JLabel joinText = new JLabel("ȸ������");
		joinText.setBounds(450, 150, 300, 100);
		joinText.setFont(new Font("���������� ExtraBold", Font.PLAIN, 35));
		joinText.setForeground(Color.WHITE);
		add(joinText);

		// ���̵� �ؽ�Ʈ
		JLabel idText = new JLabel("���̵�");
		idText.setBounds(235, 215, 300, 100);
		idText.setFont(new Font("���������� Bold", Font.PLAIN, 20));
		idText.setForeground(Color.WHITE);
		add(idText);

		// ��й�ȣ �ؽ�Ʈ
		JLabel passwordText = new JLabel("��й�ȣ");
		passwordText.setBounds(225, 285, 300, 100);
		passwordText.setFont(new Font("���������� Bold", Font.PLAIN, 20));
		passwordText.setForeground(Color.WHITE);
		add(passwordText);

		// ��й�ȣ Ȯ�� �ؽ�Ʈ
		JLabel repasswordText = new JLabel("��й�ȣ Ȯ��");
		repasswordText.setBounds(210, 355, 300, 100);
		repasswordText.setFont(new Font("���������� Bold", Font.PLAIN, 20));
		repasswordText.setForeground(Color.WHITE);
		add(repasswordText);

		// �г��� �ؽ�Ʈ
		JLabel nickNameText = new JLabel("�г���");
		nickNameText.setBounds(235, 425, 300, 100);
		nickNameText.setFont(new Font("���������� Bold", Font.PLAIN, 20));
		nickNameText.setForeground(Color.WHITE);
		add(nickNameText);

		// �̸��� �ؽ�Ʈ
		JLabel emailText = new JLabel("�̸���");
		emailText.setBounds(235, 495, 300, 100);
		emailText.setFont(new Font("���������� Bold", Font.PLAIN, 20));
		emailText.setForeground(Color.WHITE);
		add(emailText);

		// ���̵� �Է¶�
		JTextField idTextField = new JTextField();
		idTextField.setBounds(350, 250, 300, 30);
		idTextField.setBorder(BorderFactory.createEmptyBorder());
		idTextField.setBorder(BorderFactory.createBevelBorder(-1));
		add(idTextField);

		// ��й�ȣ �Է¶�
		JPasswordField passwordTextField = new JPasswordField();
		passwordTextField.setBounds(350, 320, 300, 30);
		passwordTextField.setBorder(BorderFactory.createEmptyBorder());
		add(passwordTextField);

		// ��й�ȣ ��Ȯ�� �Է¶�
		JPasswordField repasswordTextField = new JPasswordField();
		repasswordTextField.setBounds(350, 390, 300, 30);
		repasswordTextField.setBorder(BorderFactory.createEmptyBorder());
		add(repasswordTextField);

		// �г��� �Է¶�
		JTextField nickNameTextField = new JTextField();
		nickNameTextField.setBounds(350, 460, 300, 30);
		nickNameTextField.setBorder(BorderFactory.createEmptyBorder());
		add(nickNameTextField);

		// �̸��� �Է¶�
		JTextField emailTextField = new JTextField();
		emailTextField.setBounds(350, 530, 300, 30);
		emailTextField.setBorder(BorderFactory.createEmptyBorder());
		add(emailTextField);

		// �ߺ�Ȯ�� ��ư
		JButton checkButton = new JButton("�ߺ�Ȯ��");
		checkButton.setBounds(670, 245, 120, 40);
		checkButton.setFont(new Font("���������� Bold", Font.PLAIN, 20));
		checkButton.setBackground(Color.LIGHT_GRAY);
		add(checkButton);

		// �Ϸ� ��ư
		JButton completeButton = new JButton("�Ϸ�");
		completeButton.setBounds(670, 620, 120, 40);
		completeButton.setFont(new Font("���������� Bold", Font.PLAIN, 20));
		completeButton.setBackground(Color.LIGHT_GRAY);
		completeButton.setBorderPainted(false);
		add(completeButton);


		// ������� ��ư
		JButton cancleButton = new JButton("�������");
		cancleButton.setBounds(820, 620, 120, 40);
		cancleButton.setFont(new Font("���������� Bold", Font.PLAIN, 20));
		cancleButton.setBackground(Color.LIGHT_GRAY);
		cancleButton.setBorderPainted(false);
		add(cancleButton);

		// ������� -> ������������ �̵�
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
