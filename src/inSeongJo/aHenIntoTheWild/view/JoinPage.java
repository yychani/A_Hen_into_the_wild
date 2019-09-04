package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import inSeongJo.aHenIntoTheWild.controller.UserManager;
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
	private String password;
	private UserManager um = new UserManager();
	private String pass1 = "", pass2 = "";

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
		joinText.setForeground(Color.DARK_GRAY);
		add(joinText);

		// ���̵� �ؽ�Ʈ
		JLabel idText = new JLabel("���̵�");
		idText.setBounds(235, 215, 300, 100);
		idText.setFont(new Font("���������� Bold", Font.PLAIN, 20));
		//idText.setBorder(BorderFactory.createBevelBorder(1, Color.black, shadow));
		idText.setForeground(Color.DARK_GRAY);
		add(idText);

		// ���̵� �ߺ�Ȯ�� �޼���
		JLabel idCheckText = new JLabel("���̵� �ߺ�Ȯ���� ���ּ���.");
		idCheckText.setBounds(350, 240, 500, 100);
		idCheckText.setFont(new Font("���������� Bold", Font.PLAIN, 12));
		idCheckText.setForeground(Color.DARK_GRAY);
		add(idCheckText);

		// ��й�ȣ �ؽ�Ʈ
		JLabel passwordText = new JLabel("��й�ȣ");
		passwordText.setBounds(225, 285, 300, 100);
		passwordText.setFont(new Font("���������� Bold", Font.PLAIN, 20));
		passwordText.setForeground(Color.DARK_GRAY);
		add(passwordText);

		// ��й�ȣ Ȯ�� �ؽ�Ʈ
		JLabel repasswordText = new JLabel("��й�ȣ Ȯ��");
		repasswordText.setBounds(210, 355, 300, 100);
		repasswordText.setFont(new Font("���������� Bold", Font.PLAIN, 20));
		repasswordText.setForeground(Color.DARK_GRAY);
		add(repasswordText);

		// �г��� �ؽ�Ʈ
		JLabel nickNameText = new JLabel("�г���");
		nickNameText.setBounds(235, 425, 300, 100);
		nickNameText.setFont(new Font("���������� Bold", Font.PLAIN, 20));
		nickNameText.setForeground(Color.DARK_GRAY);
		add(nickNameText);

		// �̸��� �ؽ�Ʈ
		JLabel emailText = new JLabel("�̸���");
		emailText.setBounds(235, 495, 300, 100);
		emailText.setFont(new Font("���������� Bold", Font.PLAIN, 20));
		emailText.setForeground(Color.DARK_GRAY);
		add(emailText);

		// ���̵� �Է¶�
		/* JTextField */ idTextField = new JTextField();
		idTextField.setBounds(350, 250, 300, 30);
		idTextField.setBorder(BorderFactory.createEmptyBorder());
		idTextField.setBorder(BorderFactory.createBevelBorder(-1));
		add(idTextField);

		// ��й�ȣ �Է¶�
		/* JPasswordField */ passwordTextField = new JPasswordField();
		passwordTextField.setBounds(350, 320, 300, 30);
		passwordTextField.setBorder(BorderFactory.createEmptyBorder());
		add(passwordTextField);

		// ��й�ȣ ��Ȯ�� �Է¶�
		JPasswordField repasswordTextField = new JPasswordField();
		repasswordTextField.setBounds(350, 390, 300, 30);
		repasswordTextField.setBorder(BorderFactory.createEmptyBorder());
		add(repasswordTextField);

		// ��й�ȣ ��Ȯ�� �˸� �ؽ�Ʈ
		JLabel passwordCheckText = new JLabel("��й�ȣ ��ġ Ȯ���� �ʿ��մϴ�.");
		passwordCheckText.setBounds(350, 380, 500, 100);
		passwordCheckText.setFont(new Font("���������� Bold", Font.PLAIN, 12));
		passwordCheckText.setForeground(Color.WHITE);
		add(passwordCheckText);
		
		//��й�ȣ Ȯ�� �˶� ������
		Thread th2 = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					pass1 = "";
					pass2 = "";
					for(int i = 0; i < passwordTextField.getPassword().length; i++) {
						pass1 += passwordTextField.getPassword()[i];
					}
					for(int i = 0; i < repasswordTextField.getPassword().length; i++) {
						pass2 += repasswordTextField.getPassword()[i];
					}
					
					if(pass1.equals(pass2)) {
						passwordCheckText.setText("��й�ȣ�� ��ġ�մϴ�.");
						passwordCheckText.setForeground(Color.BLUE);
						System.out.println("��й�ȣ�� ��ġ�մϴ�.");
						
					} else {
						passwordCheckText.setText("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
						passwordCheckText.setForeground(Color.RED);
						System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					}
					
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				
			}
		});
		
		if(repasswordTextField.getPassword() != null) {
			th2.start();
		}
		

		// �г��� �Է¶�
		/* JTextField */ nickNameTextField = new JTextField();
		nickNameTextField.setBounds(350, 460, 300, 30);
		nickNameTextField.setBorder(BorderFactory.createEmptyBorder());
		add(nickNameTextField);

		// �̸��� �Է¶�
		/* JTextField */ emailTextField = new JTextField();
		emailTextField.setBounds(350, 530, 300, 30);
		emailTextField.setBorder(BorderFactory.createEmptyBorder());
		add(emailTextField);

		// �ߺ�Ȯ�� ��ư
		JButton checkButton = new JButton("���̵� �ߺ�Ȯ��");
		checkButton.setBounds(670, 245, 180, 40);
		checkButton.setFont(new Font("���������� Bold", Font.PLAIN, 20));
		checkButton.setBackground(Color.LIGHT_GRAY);
		add(checkButton);

		// ���̵� �ߺ�Ȯ�� �̺�Ʈ
		checkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//���̵� �ؽ�Ʈ �ʵ� ���Է½�
				if(idTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���.", "���̵� ���Է�",  1);
					return;
				}

				//���̵� �ߺ�Ȯ��
				if (um.idCheck(idTextField.getText())) {
					// true -> "����Ͻ� �� ���� ���̵��Դϴ�."
					// false -> "����Ͻ� �� �ִ� ���̵��Դϴ�."
					idCheckText.setText("����Ͻ� �� ���� ���̵��Դϴ�.");
					idCheckText.setForeground(Color.RED);
					idTextField.setText("");
				} else {
					idCheckText.setText("����Ͻ� �� �ִ� ���̵��Դϴ�.");
					idCheckText.setForeground(Color.BLUE);
				}

			}
		});

		// �Ϸ� ��ư
		JButton completeButton = new JButton("�Ϸ�");
		completeButton.setBounds(670, 620, 120, 40);
		completeButton.setFont(new Font("���������� Bold", Font.PLAIN, 20));
		completeButton.setBackground(Color.LIGHT_GRAY);
		completeButton.setBorderPainted(false);
		add(completeButton);
		
		// char[] �н����� -> String���� ��ȯ & ���� ��ü ���� & ���� ���������� �̵�
		completeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//�н����� char -> String
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
				
				
				
				//���̵� �ߺ� Ȯ���ߴ��� Ȯ��
				if (um.idCheck(idTextField.getText())) {
					// true -> "����Ͻ� �� ���� ���̵��Դϴ�."
					// false -> "����Ͻ� �� �ִ� ���̵��Դϴ�."
					JOptionPane.showMessageDialog(null, "���̵� �ٽ� �ѹ� Ȯ�����ּ���.", "���̵� Ȯ��",  1);
				} else {
					//��й�ȣ ��ġ Ȯ��
					if(!password.equals(repassword)){
						System.out.println("��й�ȣ ����ġ");
						JOptionPane.showMessageDialog(null, "��й�ȣ�� ����ġ�մϴ�. �ٽ� �ѹ� �Է����ּ���.", "��й�ȣ Ȯ��",  1);
						passwordCheckText.setText("�ٽ� �ѹ� �Է����ּ���.");
						passwordCheckText.setForeground(Color.RED);
						passwordTextField.setText("");
						repasswordTextField.setText("");
					}else {
						System.out.println("��й�ȣ ��ġ");
						//�г��� �Է¶� ���� Ȯ��
						if(nickNameTextField.getText().equals("")) {
							System.out.println("�г��� ���Է�");
							JOptionPane.showMessageDialog(null, "�г����� �Է����ּ���.", "�г��� ���Է�",  1);
						}else {
							if(emailTextField.getText().equals("")) {
								System.out.println("�̸��� ���Է�");
								JOptionPane.showMessageDialog(null, "�̸����� �Է����ּ���.", "�̸��� ���Է�",  1);
							}else {
								//��� ȸ������ ���� ���
								//���ο� ���� ��ü ����
								um.insertUser(inputUser());
								//���ԿϷ� �˾�â
								JOptionPane.showMessageDialog(null, "���ԿϷ�", "ȸ������",  1);
								//���� ������ �̵�
								th2.stop();
								ChangePanel.changePanel(mf, JoinPage, new MainPage(mf));
							}
						}
					}
					
					
				}
				
				//��й�ȣ ��ġ Ȯ��
				/*if(password.equals(repassword)){
					System.out.println("��й�ȣ ��ġ");
				}else {
					System.out.println("��й�ȣ ����ġ");
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ����ġ�մϴ�. �ٽ� �ѹ� �Է����ּ���.", "��й�ȣ Ȯ��",  1);
					passwordCheckText.setText("�ٽ� �ѹ� �Է����ּ���.");
					passwordCheckText.setForeground(Color.RED);
					passwordTextField.setText("");
					repasswordTextField.setText("");
				}*/

				//um.insertUser(inputUser());

				
				//JOptionPane.showMessageDialog(null, "���ԿϷ�", "ȸ������",  1);

				//ChangePanel.changePanel(mf, JoinPage, new MainStage(mf));
			}
		});

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

	// ������� -> ������������ �̵�
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
