package inSeongJo.aHenIntoTheWild.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import inSeongJo.aHenIntoTheWild.view.MainPage.MyMouseAdapter;

public class MainStage extends JPanel {
	private MainFrame mf;
	private Image background = new ImageIcon("images/YJimages/main_none.jpg").getImage();
	// private JLabel label = new JLabel(background);
	private Graphics ScreenGraphics;
	private Image ScreenImage;

	public MainStage(MainFrame mf) {
		
		this.setBounds(0, 0, 1024, 768);

		this.setLayout(null);
		mf.add(this);
		
		//���� ��ư
		Image userImage = new ImageIcon("images/YJimages/user2.png").getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		Image userPressedImage = new ImageIcon("images/YJimages/user2_pressed.png").getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		JButton userButton = new JButton(new ImageIcon(userImage));
		userButton.setBorderPainted(false);
		userButton.setContentAreaFilled(false);
		userButton.setFocusPainted(false);
		userButton.setBounds(20, 20, 70, 70);
		userButton.setPressedIcon(new ImageIcon(userPressedImage));;
		add(userButton);
		
		userButton.addMouseListener(new MyMouseAdapter());
		
		//�ٽ��� �̹���
		Image ipssak = new ImageIcon("images/YJimages/chicken.gif").getImage().getScaledInstance(180, 180, 0);
		JLabel ipssakImage = new JLabel(new ImageIcon(ipssak));
		ipssakImage.setBounds(800, 500, 200, 200);
		add(ipssakImage);
		
		//�ٽ��� �����ؽ�Ʈ
		Image bubble = new ImageIcon("images/YJimages/bubbletext.png").getImage().getScaledInstance(850, 190, 0);
		JLabel bubbleText = new JLabel(new ImageIcon(bubble));
		bubbleText.setBounds(30, 500, 900, 200);
		add(bubbleText);
		
		//STAGE1 ��ư
		Image stage1Image = new ImageIcon("images/YJimages/STAGE1.png").getImage().getScaledInstance(200, 200, 0);
		JButton stage1Button = new JButton(new ImageIcon(stage1Image));
		stage1Button.setBorderPainted(false);
		stage1Button.setContentAreaFilled(false);
		stage1Button.setFocusPainted(false);
		stage1Button.setBounds(100, 100, 300, 300);
		add(stage1Button);
		
		
		//STAGE2 ��ư
		
		//STAGE3 ��ư
		
		//STAGE4 ��ư
		
		
		
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
	
	
	class MyMouseAdapter extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("Ŭ���Ǿ����ϴ�.");
		}
	}
	

}