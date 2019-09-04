package inSeongJo.aHenIntoTheWild.view;

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
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import inSeongJo.aHenIntoTheWild.model.dao.UserDao;
import inSeongJo.aHenIntoTheWild.model.vo.User;

public class UserInformation extends JPanel {

	Image img;
	JLabel id = new JLabel("���̵�");
	JLabel nik = new JLabel("�г���");
	JLabel email = new JLabel("�̸���");
	JLabel rank = new JLabel("��ŷ");
	JLabel stage = new JLabel("Stage");
	User user;
	User presentUser;

	JTextField tarr[] = new JTextField[5];

	JButton btn1 = new JButton("ȸ������ ����");
	JButton btn2 = new JButton("�α׾ƿ�");

	private Font f1;
	private JPanel UserInformation; // ȭ�� ��ȯ�� ���� changePanel�޼ҵ忡�� ����� �ʵ�.
	private ArrayList<User> allUser;

	public UserInformation(MainFrame mf, User user) {
		this.user = user;
		UserInformation = this;
		setLayout(null); // JFrame�� ���� ������Ʈ�� ��ġ����� �����ϴ� �޼ҵ�
		setSize(1024, 768);

		Toolkit tk = Toolkit.getDefaultToolkit();

		Dimension d = tk.getScreenSize();
		UserDao userdao = new UserDao();// userdao�� �ִ� ���� �о���̴� �޼ҵ� �ҷ����� ����
		allUser = userdao.readUserList();

		// User Ÿ���� ���� u�� allUser��� ArrayList�� �����ϸ� ���
		// �Է��� id�� �����Ϳ� ����� ���̵� ���� ������ ���
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

		f1 = new Font("����������_ac Bold", Font.PLAIN, 20);

		add(id);
		add(nik);
		add(email);
		add(rank);
		add(stage);

		id.setBounds(150, 150, 50, 50);
		nik.setBounds(150, 250, 50, 50);
		email.setBounds(150, 350, 50, 50);
		rank.setBounds(550, 150, 50, 50);
		stage.setBounds(550, 250, 50, 50);

		// TextField�� panel�� ���̰� ��ġ�� �����ϴ� ���ÿ� TextField�� ȸ�� ���� ǥ��
		for (int i = 0; i < tarr.length; i++) {
			tarr[i] = new JTextField();
			add(tarr[i]);
			switch (i) {
			case 0:
				tarr[i].setBounds(200, 150, 250, 50);
				tarr[i].setText(presentUser.getId()); // ȸ������ �� idǥ��
				tarr[i].setEditable(false); // �� �������� ���ϰ� �ϴ� �޼ҵ�
				break;

			case 1:
				tarr[i].setBounds(200, 250, 250, 50);
				tarr[i].setText(presentUser.getNickName()); // ȸ������ �� �г��� ǥ��
				tarr[i].setEditable(false);
				break;

			case 2:
				tarr[i].setBounds(200, 350, 250, 50);
				tarr[i].setText(presentUser.getEmail()); // ȸ�� ���� �� �̸��� ǥ��
				tarr[i].setEditable(false);
				break;

			case 3:
				tarr[i].setBounds(600, 150, 250, 50);
				tarr[i].setText(presentUser.getTotalScore() + "");// ȸ������ �� ��ü ���� ǥ�� (��, totalscore�� int���̹Ƿ� ���ڿ��� ��ȯ)
				tarr[i].setEditable(false);

			case 4:
				tarr[i].setBounds(600, 250, 250, 50);
				if (presentUser.getStage4Score() == 0) {
					if (presentUser.getStage3Score() == 0) {
						if (presentUser.getStage2Score() == 0) {
							if (presentUser.getStage1Score() == 0) {
								tarr[i].setText("Ŭ������ ���������� �����ϴ�.");
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

		btn1.setFont(f1);
		btn2.setFont(f1);
		add(btn1);
		add(btn2);

		btn1.setBounds(300, 500, 150, 50);
		btn2.setBounds(500, 500, 150, 50);

		// Ȩ��ư
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

		// ���� ���������� �̵�
		homeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ChangePanel.changePanel(mf, UserInformation, new MainStage(mf, user));

			}
		});
		// ȸ������ �������� �̵�
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
