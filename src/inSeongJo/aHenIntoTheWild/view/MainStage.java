package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import inSeongJo.aHenIntoTheWild.model.vo.User;

public class MainStage extends JPanel {
	private MainFrame mf;
	private JPanel MainStage;
	User user;
	private Image background = new ImageIcon("images/YJimages/main_none.png").getImage();
	// private JLabel label = new JLabel(background);
	private Graphics ScreenGraphics;
	private Image ScreenImage;
	public MainStage(MainFrame mf, User user) {
		this.mf = mf;
		this.user = user;
		MainStage = this;
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
//		mf.add(this);
		
		//���� ��ü ���� ���
		JLabel loginInfo = new JLabel("ȯ���մϴ�. "  + user.getNickName() + "��");
		loginInfo.setBounds(100, 8, 300, 100);
		loginInfo.setFont(new Font("���������� Bold", Font.PLAIN, 17));
		loginInfo.setForeground(Color.DARK_GRAY);
		add(loginInfo);
		
		//���� ��ư
		Image userImage = new ImageIcon("images/YJimages/user2.png").getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		Image userPressedImage = new ImageIcon("images/YJimages/user2_pressed.png").getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		JButton userButton = new JButton(new ImageIcon(userImage));
		userButton.setBorderPainted(false);
		userButton.setContentAreaFilled(false);
		userButton.setFocusPainted(false);
		userButton.setBounds(20, 20, 70, 70);
		userButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		userButton.setPressedIcon(new ImageIcon(userPressedImage));;
		add(userButton);
		
		userButton.addMouseListener(new MyMouseAdapter());
		
		//���� ��ư
		Image saveImage = new ImageIcon("images/YJimages/save.png").getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
		Image savePressedImage = new ImageIcon("images/YJimages/save_pressed.png").getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
		JButton saveButton = new JButton(new ImageIcon(saveImage));
		saveButton.setBorderPainted(false);
		saveButton.setContentAreaFilled(false);
		saveButton.setFocusPainted(false);
		saveButton.setBounds(930, 20, 70, 70);
		saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		saveButton.setPressedIcon(new ImageIcon(savePressedImage));;
		add(saveButton);
		
		//�ٽ��� �̹���
		Image ipssak = new ImageIcon("images/YJimages/ipssak.png").getImage().getScaledInstance(180, 250, 0);
		JLabel ipssakImage = new JLabel(new ImageIcon(ipssak));
		ipssakImage.setBounds(800, 500, 200, 200);
		add(ipssakImage);
		
		//�ٽ��� ��� �ؽ�Ʈ
		JLabel ipssakText = new JLabel("�ȳ�? �� �ٽ��̶�� ��.");
		ipssakText.setBounds(350, 510, 500, 100);
		ipssakText.setFont(new Font("���������� ExtraBold", Font.PLAIN, 20));
		ipssakText.setForeground(Color.DARK_GRAY);
		add(ipssakText);
		JLabel ipssakText2 = new JLabel("������� �ʹ� �������� �ʾ�? �� �̰��� ������ �;�.");
		ipssakText2.setBounds(270, 545, 500, 100);
		ipssakText2.setFont(new Font("���������� ExtraBold", Font.PLAIN, 20));
		ipssakText2.setForeground(Color.DARK_GRAY);
		add(ipssakText2);
		JLabel ipssakText3= new JLabel("�츮 ���� �������� ��������?");
		ipssakText3.setBounds(330, 580, 500, 100);
		ipssakText3.setFont(new Font("���������� ExtraBold", Font.PLAIN, 20));
		ipssakText3.setForeground(Color.DARK_GRAY);
		add(ipssakText3);
		
		//�ٽ��� �����ؽ�Ʈ
		Image bubble = new ImageIcon("images/YJimages/bubble.png").getImage().getScaledInstance(850, 230, 0);
		JLabel bubbleText = new JLabel(new ImageIcon(bubble));
		bubbleText.setBounds(30, 500, 900, 200);
		add(bubbleText);
		
		//STAGE1 ��ư
		Image stage1Image = new ImageIcon("images/YJimages/STAGE1.png").getImage().getScaledInstance(200, 200, 0);
		JButton stage1Button = new JButton(new ImageIcon(stage1Image));
		stage1Button.setBorderPainted(false);
		stage1Button.setContentAreaFilled(false);
		stage1Button.setFocusPainted(false);
		stage1Button.setBounds(20, 200, 300, 300);
		stage1Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		stage1Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChangePanel.changePanel(mf, MainStage, new Stage01_infoPage(mf, user));
			}
		});
		add(stage1Button);
		
		
		//STAGE2 ��ư
		Image stage2Image = new ImageIcon("images/YJimages/STAGE2.png").getImage().getScaledInstance(200, 200, 0);
		JButton stage2Button = new JButton(new ImageIcon(stage2Image));
		stage2Button.setBorderPainted(false);
		stage2Button.setContentAreaFilled(false);
		stage2Button.setFocusPainted(false);
		stage2Button.setBounds(250, 200, 300, 300);
		stage2Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(stage2Button);
		stage2Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChangePanel.changePanel(mf, MainStage, new Stage02(mf));
			}
		});
		
		//STAGE3 ��ư
		Image stage3Image = new ImageIcon("images/YJimages/STAGE3.png").getImage().getScaledInstance(200, 200, 0);
		JButton stage3Button = new JButton(new ImageIcon(stage3Image));
		stage3Button.setBorderPainted(false);
		stage3Button.setContentAreaFilled(false);
		stage3Button.setFocusPainted(false);
		stage3Button.setBounds(470, 200, 300, 300);
		stage3Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(stage3Button);
		stage3Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ChangePanel.changePanel(mf, MainStage, new Stage03(mf, 0, user));
				
			}
		});
		
		//STAGE4 ��ư
		Image stage4Image = new ImageIcon("images/YJimages/STAGE4.png").getImage().getScaledInstance(200, 200, 0);
		JButton stage4Button = new JButton(new ImageIcon(stage4Image));
		stage4Button.setBorderPainted(false);
		stage4Button.setContentAreaFilled(false);
		stage4Button.setFocusPainted(false);
		stage4Button.setBounds(710, 200, 300, 300);
		stage4Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(stage4Button);
    
		stage4Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChangePanel.changePanel(mf, MainStage, new Stage04(mf));
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
	
	class MyMouseAdapter extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			ChangePanel.changePanel(mf, MainStage, new UserInformation(mf, user));
			System.out.println("Ŭ���Ǿ����ϴ�.");
		}
	}
}