package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class MainPage extends JPanel {
	private MainFrame mf;
	private JPanel mainPage;
	private Image background = new ImageIcon("images/YJimages/Main_title.png").getImage().getScaledInstance(1024, 768, 0);
	// private JLabel label = new JLabel(background);
	private Graphics ScreenGraphics;
	private Image ScreenImage;

	public MainPage(MainFrame mf) {
		this.mf = mf;
		mainPage = this;
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
		

		// 아이디 : user이미지
		Image user = new ImageIcon("images/YJimages/user.png").getImage().getScaledInstance(50, 50, 0);
		JLabel userIcon = new JLabel(new ImageIcon(user));
		userIcon.setBounds(210, 295, 200, 200);
		add(userIcon);

		// 아이디 입력란
		JTextField idText = new JTextField();
		idText.setBounds(350, 380, 300, 30);
		idText.setBorder(BorderFactory.createEmptyBorder());
		//idText.setOpaque(false);
		add(idText);

		// 비민번호 : lock이미지
		Image lock = new ImageIcon("images/YJimages/lock.png").getImage().getScaledInstance(50, 50, 0);
		JLabel lockIcon = new JLabel(new ImageIcon(lock));
		lockIcon.setBounds(210, 360, 200, 200);
		add(lockIcon);

		// 비밀번호 입력란
		JPasswordField passwordText = new JPasswordField();
		passwordText.setBounds(350, 450, 300, 30);
		passwordText.setBorder(BorderFactory.createEmptyBorder());
		add(passwordText);
		
		// 로그인 버튼
		JButton loginButton = new JButton("로그인");
		loginButton.setBounds(670, 375, 120, 40);
		loginButton.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 20));
		loginButton.setBackground(Color.LIGHT_GRAY);
		loginButton.setBorderPainted(false);
		add(loginButton);

		// 회원 가입 버튼
		JButton joinButton = new JButton("회원가입");
		joinButton.setBounds(670, 445, 120, 40);
		joinButton.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 20));
		joinButton.setContentAreaFilled(false);
		joinButton.setBackground(Color.LIGHT_GRAY);
		//joinButton.setBorderPainted(false);
		joinButton.setBorder(new RoundedBorder(20));
		//joinButton.setBorder(new RoundedBorder(10));
		add(joinButton);
		
		// 회원가입 페이지로 이동
		joinButton.addMouseListener(new MyMouseAdapter());

		
		mf.add(this);

	}
	
	class MyMouseAdapter extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			ChangePanel.changePanel(mf, mainPage, new JoinPage(mf));
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
