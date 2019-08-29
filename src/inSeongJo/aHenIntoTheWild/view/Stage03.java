package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Stage03 extends JPanel{
	private MainFrame mf;
	private Image background = new ImageIcon("images/stage03_image/background2.png").getImage();
	//private JLabel label = new JLabel(background);
	private Graphics ScreenGraphics;
	private Image ScreenImage;
	private int fullRate;
	private int cleanRate;
	private int tiredRate;

	
	public Stage03(MainFrame mf) {
		this.setName("Stage3");
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
		this.setBackground(new Color(0,0,0,0));
		mf.add(this);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		fullRate = 50;	//임의로 설정한 포만감
		cleanRate = 80;	//임의로 설정한 청결도
		tiredRate = 30;	//임의로 설정한 피로도
		
		
		Image riceIcon = new ImageIcon("images/stage03_image/riceIcon.png").getImage().getScaledInstance(100, 102, Image.SCALE_SMOOTH);
		JButton ricebutton = new JButton(new ImageIcon(riceIcon));
		ricebutton.setBounds(20, 620, 100, 102);
		ricebutton.setBorderPainted(false);
		ricebutton.setContentAreaFilled(false);
		add(ricebutton);
		
		Image bathIcon = new ImageIcon("images/stage03_image/bathIcon.png").getImage().getScaledInstance(100, 102, Image.SCALE_SMOOTH);
		JButton bathbutton = new JButton(new ImageIcon(bathIcon));
		bathbutton.setBounds(140, 620, 100, 102);
		bathbutton.setBorderPainted(false);
		bathbutton.setContentAreaFilled(false);
		add(bathbutton);
		
		Image playIcon = new ImageIcon("images/stage03_image/playIcon.png").getImage().getScaledInstance(100, 102, Image.SCALE_SMOOTH);
		JButton playbutton = new JButton(new ImageIcon(playIcon));
		playbutton.setBounds(260, 620, 100, 102);
		playbutton.setBorderPainted(false);
		playbutton.setContentAreaFilled(false);
		add(playbutton);
		
		Image loveIcon = new ImageIcon("images/stage03_image/loveIcon.png").getImage().getScaledInstance(100, 102, Image.SCALE_SMOOTH);
		JButton lovebutton = new JButton(new ImageIcon(loveIcon));
		lovebutton.setBorderPainted(false);
		lovebutton.setContentAreaFilled(false);
		lovebutton.setBounds(380, 620, 100, 102);
		add(lovebutton);
		
		Image bedIcon = new ImageIcon("images/stage03_image/bedIcon.png").getImage().getScaledInstance(100, 102, Image.SCALE_SMOOTH);
		JButton bedbutton = new JButton(new ImageIcon(bedIcon));
		bedbutton.setBorderPainted(false);
		bedbutton.setContentAreaFilled(false);
		bedbutton.setBounds(500, 620, 100, 102);
		add(bedbutton);
		
		Image green1 = new ImageIcon("images/stage03_image/greenE1.png").getImage();
		JLabel greenLabel = new JLabel(new ImageIcon(green1));
		greenLabel.setBounds(412, 270, 200, 279);
		add(greenLabel);
		
		Image dungji = new ImageIcon("images/stage03_image/dungji.png").getImage();
		JLabel dungjiLabel = new JLabel(new ImageIcon(dungji));
		dungjiLabel.setBounds(346, 435, 326, 193);
		add(dungjiLabel);
		
		JLabel fullRateText = new JLabel("포만감");
		fullRateText.setBounds(30, 40, 100, 20);
		fullRateText.setFont(new Font("바탕",Font.PLAIN, 20));
		fullRateText.setForeground(Color.BLUE);
		add(fullRateText);
		JLabel fullRatePercent = new JLabel(fullRate + "%");
		fullRatePercent.setBounds(220, 70, 100, 20);
		fullRatePercent.setFont(new Font("바탕",Font.PLAIN, 20));
		fullRatePercent.setForeground(Color.BLUE);
		add(fullRatePercent);
		
		JLabel cleanRateText = new JLabel("청결도");
		cleanRateText.setBounds(30, 112, 100, 20);
		cleanRateText.setFont(new Font("바탕",Font.PLAIN, 20));
		cleanRateText.setForeground(Color.BLUE);
		add(cleanRateText);
		JLabel cleanRatePercent = new JLabel(cleanRate + "%");
		cleanRatePercent.setBounds(220, 142, 100, 20);
		cleanRatePercent.setFont(new Font("바탕",Font.PLAIN, 20));
		cleanRatePercent.setForeground(Color.BLUE);
		add(cleanRatePercent);
		
		JLabel tiredRateText = new JLabel("피로도");
		tiredRateText.setBounds(30, 185, 100, 20);
		tiredRateText.setFont(new Font("바탕",Font.PLAIN, 20));
		tiredRateText.setForeground(Color.RED);
		add(tiredRateText);
		JLabel tiredRatePercent = new JLabel(tiredRate + "%");
		tiredRatePercent.setBounds(220, 214, 100, 20);
		tiredRatePercent.setFont(new Font("바탕",Font.PLAIN, 20));
		tiredRatePercent.setForeground(Color.RED);
		add(tiredRatePercent);
		
		addMouseMotionListener(new MyEvent()); //위치 확인
		
	}
	
	public void paint(Graphics g) {
		ScreenImage = createImage(1024,768);
		ScreenGraphics = ScreenImage.getGraphics();
		screenDraw(ScreenGraphics);
		g.drawImage(ScreenImage, 0, 0, null);
		
		
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		paintComponents(g);
		this.repaint();
	}
	
	
	class MyEvent extends MouseMotionAdapter{
		
		public void display(String s, MouseEvent e) {
			System.out.println(s + ": ( " + e.getX() + ", " + e.getY() + " )");
		}
		
		@Override
		public void mouseMoved(java.awt.event.MouseEvent e) {
			display("mouse Moved", e);
		}
	}
	
}









