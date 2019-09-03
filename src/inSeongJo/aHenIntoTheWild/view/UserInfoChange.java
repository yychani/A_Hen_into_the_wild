package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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

	public UserInfoChange(MainFrame mf, User user) {
		this.mf = mf;
		this.user = user;
		UserInfoChange = this;
		this.setBounds(0, 0, 1024, 768);

		this.setLayout(null);

		mf.add(this);
		
		//�α����� ���� ���� ��������
		UserDao userdao = new UserDao();
		allUser = userdao.readUserList();
		
		for(User u : allUser) {
			if(u.getId().equals(user.getId())) {
				if(u.getPassword().equals(user.getPassword())) {
					presentUser = u;
				}else {
				}

			}
		}
		
		//JLabel text
		// ȸ���������� �ؽ�Ʈ 
		JLabel changeInfoText = new JLabel("ȸ����������");
		changeInfoText.setBounds(35, 30, 300, 100);
		changeInfoText.setFont(new Font("���������� ExtraBold", Font.PLAIN, 50));
		changeInfoText.setForeground(Color.DARK_GRAY);
		add(changeInfoText);
		
		//�г��� �ؽ�Ʈ
		JLabel nickNameText = new JLabel("�г���");
		nickNameText.setBounds(270, 215, 300, 100);
		nickNameText.setFont(new Font("���������� ExtraBold", Font.PLAIN, 20));
		nickNameText.setForeground(Color.WHITE);
		add(nickNameText);
		
		//�̸��� �ؽ�Ʈ
		JLabel emailText = new JLabel("�̸���");
		emailText.setBounds(270, 295, 300, 100);
		emailText.setFont(new Font("���������� ExtraBold", Font.PLAIN, 20));
		emailText.setForeground(Color.WHITE);
		add(emailText);
		
		//��й�ȣ �ؽ�Ʈ
		JLabel passwordText = new JLabel("��й�ȣ");
		passwordText.setBounds(265, 375, 300, 100);
		passwordText.setFont(new Font("���������� ExtraBold", Font.PLAIN, 20));
		passwordText.setForeground(Color.WHITE);
		add(passwordText);
		
		//��й�ȣ Ȯ�� �ؽ�Ʈ
		JLabel repasswordText = new JLabel("��й�ȣ Ȯ��");
		repasswordText.setBounds(240, 455, 300, 100);
		repasswordText.setFont(new Font("���������� ExtraBold", Font.PLAIN, 20));
		repasswordText.setForeground(Color.WHITE);
		add(repasswordText);
		
		//��й�ȣ ��Ȯ�� �˸� �ؽ�Ʈ
		JLabel passwordCheckText = new JLabel("��й�ȣ ��ġ Ȯ���� �ʿ��մϴ�.");
		passwordCheckText.setBounds(360, 485, 500, 100);
		passwordCheckText.setFont(new Font("���������� Bold", Font.PLAIN, 12));
		passwordCheckText.setForeground(Color.WHITE);
		add(passwordCheckText);
		
		//JTextField
		// �г��� ���� �Է¶�
		JTextField nickNameTextField = new JTextField();
		nickNameTextField.setBounds(360, 250, 300, 30);
		nickNameTextField.setBorder(BorderFactory.createEmptyBorder());
		nickNameTextField.setText(presentUser.getNickName());
		add(nickNameTextField);
		
		// �̸��� ���� �Է¶�
		JTextField emailTextField = new JTextField();
		emailTextField.setBounds(360, 330, 300, 30);
		emailTextField.setBorder(BorderFactory.createEmptyBorder());
		emailTextField.setText(presentUser.getEmail());
		add(emailTextField);
		
		// ��й�ȣ ���� �Է¶�
		JPasswordField passwordTextField = new JPasswordField();
		passwordTextField.setBounds(360, 410, 300, 30);
		passwordTextField.setBorder(BorderFactory.createEmptyBorder());
		add(passwordTextField);
		
		// ��й�ȣ ���� ���Է¶�
		JPasswordField repasswordTextField = new JPasswordField();
		repasswordTextField.setBounds(360, 490, 300, 30);
		repasswordTextField.setBorder(BorderFactory.createEmptyBorder());
		add(repasswordTextField);
		
		//���� �Ϸ� ��ư
		JButton modiCompleteButton = new JButton("���� �Ϸ�");
		modiCompleteButton.setBounds(450, 620, 120, 40);
		modiCompleteButton.setFont(new Font("���������� Bold", Font.PLAIN, 20));
		modiCompleteButton.setBackground(Color.LIGHT_GRAY);
		modiCompleteButton.setBorderPainted(false);
		modiCompleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(modiCompleteButton);
		
		modiCompleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//ȸ�� ���� ���� �޼ҵ�
				UserManager um = new UserManager();
				//�н����� char -> String
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
				presentUser = um.UserInfoChagne(nickNameTextField.getText(), emailTextField.getText(), password, user);
			
				//�α��� ���� �������� �̵�
				ChangePanel.changePanel(mf, UserInfoChange, new UserInformation(mf, presentUser));
			}
		});
		
		//���� ��� ��ư
		JButton modiCancleButton = new JButton("���� ���");
		modiCancleButton.setBounds(650, 620, 120, 40);
		modiCancleButton.setFont(new Font("���������� Bold", Font.PLAIN, 20));
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
}
