package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import inSeongJo.aHenIntoTheWild.controller.UserManager;
import inSeongJo.aHenIntoTheWild.model.dao.UserDao;
import inSeongJo.aHenIntoTheWild.model.vo.User;


public class MainPage extends JPanel {
	private MainFrame mf;
	private JPanel mainPage;
	private Image background = new ImageIcon("images/YJimages/Main_title.png").getImage().getScaledInstance(1024, 768, 0);
	// private JLabel label = new JLabel(background);
	private Graphics ScreenGraphics;
	private Image ScreenImage;
	
	
	JTextField idTextField;
	JPasswordField passwordTextField; 

	public MainPage(MainFrame mf) {
		this.mf = mf;
		mainPage = this;
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
		mf.add(this);
		
		
		UserDao ud = new UserDao();
		ArrayList<User> list = ud.readUserList();
		System.out.println(list);

		// ���̵� : user�̹���
		Image user = new ImageIcon("images/YJimages/user.png").getImage().getScaledInstance(50, 50, 0);
		JLabel userIcon = new JLabel(new ImageIcon(user));
		userIcon.setBounds(210, 295, 200, 200);
		add(userIcon);

		// ���̵� �Է¶�
		idTextField = new JTextField();
		idTextField.setBounds(350, 380, 300, 30);
		idTextField.setBorder(BorderFactory.createEmptyBorder());
		//idText.setOpaque(false);
		add(idTextField);

		// ��ι�ȣ : lock�̹���
		Image lock = new ImageIcon("images/YJimages/lock.png").getImage().getScaledInstance(50, 50, 0);
		JLabel lockIcon = new JLabel(new ImageIcon(lock));
		lockIcon.setBounds(210, 360, 200, 200);
		add(lockIcon);

		// ��й�ȣ �Է¶�
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(350, 450, 300, 30);
		passwordTextField.setBorder(BorderFactory.createEmptyBorder());
		add(passwordTextField);
		

		
		// �α��� ��ư
		JButton loginButton = new JButton("�α���");
		loginButton.setBounds(670, 375, 120, 40);
		loginButton.setFont(new Font("���������� Bold", Font.PLAIN, 20));
		loginButton.setBackground(Color.LIGHT_GRAY);
		loginButton.setBorderPainted(false);
		loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(loginButton);
		
		
		// ȸ�� ���� ��ư
		JButton joinButton = new JButton("ȸ������");
		joinButton.setBounds(670, 445, 120, 40);
		joinButton.setFont(new Font("���������� Bold", Font.PLAIN, 20));
		joinButton.setContentAreaFilled(false);
		joinButton.setBackground(Color.LIGHT_GRAY);
		//joinButton.setBorderPainted(false);
		joinButton.setBorder(new RoundedBorder(20));
		joinButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//joinButton.setBorder(new RoundedBorder(10));
		add(joinButton);
		
		joinButton.addMouseListener(new MyMouseAdapter());
		
		// �α��� ��ư -> ���� ���������� �̵�
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// ��й�ȣ string ȭ
				String password = "";
				char[] pass = passwordTextField.getPassword();
				for (int i = 0; i < pass.length; i++) {
					password += pass[i];
				}
				
				UserManager um = new UserManager();
				
				if(um.login(idTextField.getText(), password).getId() != null) {
					System.out.println(um.login(idTextField.getText(), password));
					JOptionPane.showMessageDialog(null, "�α��� ����", "�α���",  1);
					ChangePanel.changePanel(mf, mainPage, new MainStage(mf, um.login(idTextField.getText(), password)));
				}else {
					JOptionPane.showMessageDialog(null, "�α��� ����", "�α���",  1);
					System.out.println("���������� : �α��� ����");
				}
				
			}
		});

	}
	
	
	// ȸ������ �������� �̵�
	class MyMouseAdapter extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			ChangePanel.changePanel(mf, mainPage, new UserAgreements(mf));
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
	//��ư border round
	class RoundedBorder implements Border {

	    private int radius;


	    RoundedBorder(int radius) {
	        this.radius = radius;
	    }


	    public Insets getBorderInsets(Component c) {
	        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	    }


	    public boolean isBorderOpaque() {
	        return true;
	    }


	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
	    }
	}
	
}
