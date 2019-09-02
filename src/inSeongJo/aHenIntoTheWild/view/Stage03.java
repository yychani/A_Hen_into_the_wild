package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import inSeongJo.aHenIntoTheWild.controller.Stage03Manager;

public class Stage03 extends JPanel{
	private MainFrame mf;
	private Image background = new ImageIcon("images/stage03_image/background2.png").getImage();
//	private JLabel label = new JLabel(background);

	private int[] rate = new int[4];
	private int x;
	private int y;
	private String str = "";
	private boolean riceBl, bathBl, playBl, loveBl, bedBl;
	private Stage03Manager sm = new Stage03Manager();
	private Image fullImage = new ImageIcon("images/stage03_image/fullImage.png").getImage().getScaledInstance(188, 25, Image.SCALE_SMOOTH);
	private Image cleanImage = new ImageIcon("images/stage03_image/cleanImage.png").getImage().getScaledInstance(188, 25, Image.SCALE_SMOOTH);
	private Image tiredImage = new ImageIcon("images/stage03_image/tiredImage.png").getImage().getScaledInstance(188, 25, Image.SCALE_SMOOTH);
	private Image growthImage = new ImageIcon("images/stage03_image/growthRate.png").getImage().getScaledInstance(40, 243, Image.SCALE_SMOOTH);
	
	public Stage03(MainFrame mf) {
		this.setName("Stage3");
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
		this.setBackground(new Color(0,0,0,0));
		mf.add(this);
		rate[0] = 50;	//���Ƿ� ������ ������
		rate[1] = 80;	//���Ƿ� ������ û�ᵵ
		rate[2] = 30;	//���Ƿ� ������ �Ƿε�
		rate[3] = 0;	//���嵵
		
		
		Image riceIcon = new ImageIcon("images/stage03_image/riceIcon.png").getImage().getScaledInstance(100, 102, Image.SCALE_SMOOTH);
		JButton ricebutton = new JButton(new ImageIcon(riceIcon));
		ricebutton.setBounds(20, 620, 100, 102);
		ricebutton.setBorderPainted(false);
		ricebutton.setContentAreaFilled(false);
		add(ricebutton);
		
		ricebutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				riceBl = true;
				System.out.println("����̱� ��ư ����");	
				
				
			}
		});
		
		
		Image bathIcon = new ImageIcon("images/stage03_image/bathIcon.png").getImage().getScaledInstance(100, 102, Image.SCALE_SMOOTH);
		JButton bathbutton = new JButton(new ImageIcon(bathIcon));
		bathbutton.setBounds(140, 620, 100, 102);
		bathbutton.setBorderPainted(false);
		bathbutton.setContentAreaFilled(false);
		add(bathbutton);
		
		bathbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bathBl = true;
				System.out.println("�ı�� ��ư ����");
				
			}
		});
		
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
		
//		Image green1 = new ImageIcon("images/stage03_image/greenE1.png").getImage();
//		JLabel greenLabel = new JLabel(new ImageIcon(green1));
//		greenLabel.setBounds(412, 270, 200, 279);
//		add(greenLabel);
	
		
		//������
		Greeny gr = new Greeny(this);
		Thread th = new Thread(gr);
		th.start();
		
		Timer tr = new Timer(this);
		Thread th2 = new Thread(tr);
		th2.start();
		
//		Image dungji = new ImageIcon("images/stage03_image/dungji.png").getImage();
//		JLabel dungjiLabel = new JLabel(new ImageIcon(dungji));
//		dungjiLabel.setBounds(346, 435, 326, 193);
//		add(dungjiLabel);
		
		//�������� (������, û�ᵵ, �Ƿε�)
		JLabel fullRateText = new JLabel("������");
		fullRateText.setBounds(30, 40, 100, 20);
		fullRateText.setFont(new Font("����",Font.PLAIN, 20));
		fullRateText.setForeground(Color.BLUE);
		add(fullRateText);
		JLabel fullRatePercent = new JLabel(rate[0] + "%");
		fullRatePercent.setBounds(220, 70, 100, 20);
		fullRatePercent.setFont(new Font("����",Font.PLAIN, 20));
		fullRatePercent.setForeground(Color.BLUE);
		add(fullRatePercent);
		
		JLabel cleanRateText = new JLabel("û�ᵵ");
		cleanRateText.setBounds(30, 112, 100, 20);
		cleanRateText.setFont(new Font("����",Font.PLAIN, 20));
		cleanRateText.setForeground(Color.BLUE);
		add(cleanRateText);
		JLabel cleanRatePercent = new JLabel(rate[1] + "%");
		cleanRatePercent.setBounds(220, 142, 100, 20);
		cleanRatePercent.setFont(new Font("����",Font.PLAIN, 20));
		cleanRatePercent.setForeground(Color.BLUE);
		add(cleanRatePercent);
		
		
		JLabel tiredRateText = new JLabel("�Ƿε�");
		tiredRateText.setBounds(30, 185, 100, 20);
		tiredRateText.setFont(new Font("����",Font.PLAIN, 20));
		tiredRateText.setForeground(Color.RED);
		add(tiredRateText);
		JLabel tiredRatePercent = new JLabel(rate[2] + "%");
		tiredRatePercent.setBounds(220, 214, 100, 20);
		tiredRatePercent.setFont(new Font("����",Font.PLAIN, 20));
		tiredRatePercent.setForeground(Color.RED);
		add(tiredRatePercent);
		
		
		
		addMouseMotionListener(new MyEvent()); //��ġ Ȯ��

		playbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				playBl = true;
				System.out.println("����ֱ� ��ư ����");
				//����ִ� �̴ϰ� �˾�â �����!
				rate = sm.playingMethod(rate);
				fullRatePercent.setText(rate[0] + "%");
				cleanRatePercent.setText(rate[1] + "%");
				tiredRatePercent.setText(rate[2] + "%");
				playBl = false;
				
				
			}
		});
		
		lovebutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loveBl = true;
				System.out.println("�����ֱ� ��ư ����");
				rate = sm.lovingMethod(rate);
				fullRatePercent.setText(rate[0] + "%");
				cleanRatePercent.setText(rate[1] + "%");
				tiredRatePercent.setText(rate[2] + "%");
				loveBl = false;
			}
		});
		
		bedbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bedBl = true;
				System.out.println("���ڱ� ��ư ����");
				rate = sm.sleepingMethod(rate);
				fullRatePercent.setText(rate[0] + "%");
				cleanRatePercent.setText(rate[1] + "%");
				tiredRatePercent.setText(rate[2] + "%");
				bedBl = false;
				
			}
		});
		
		addMouseListener(new MouseAdapter() {
			
			@Override 
			public void mouseClicked(MouseEvent e) { //�������� Ȱ��ȭ �ǰ�, �ʷ��̸� ���� ������ ��!
				if(riceBl == true) { // 
					rate = sm.eatingMethod(rate, e.getX(), e.getY());
					System.out.println("��� ������");	
					fullRatePercent.setText(rate[0] + "%");
					cleanRatePercent.setText(rate[1] + "%");
					tiredRatePercent.setText(rate[2] + "%");
					riceBl = false;
				}
				if(bathBl == true) { 
					rate = sm.cleaningMethod(rate, e.getX(), e.getY());
					System.out.println("��� ������");	
					fullRatePercent.setText(rate[0] + "%");
					cleanRatePercent.setText(rate[1] + "%");
					tiredRatePercent.setText(rate[2] + "%");
					bathBl = false;
				}
			}
			
		});
		
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null); //����� �׷���
		paintComponents(g); //component�� �׷���
		
		g.drawImage(fullImage, 25, 69, (int)(188*(double)rate[0]/100.0), 25, null); // ������ǥ�� 
		g.drawImage(cleanImage, 25, 140, (int)(188*(double)rate[1]/100.0), 25, null); // û�ᵵǥ�� 
		g.drawImage(tiredImage, 25, 211, (int)(188*(double)rate[2]/100.0), 25, null); // �Ƿε�ǥ�� 
		g.drawImage(growthImage, 934, 330, 40, (int)(-243*(double)rate[3]/100.0), null); // ���嵵ǥ�� 

		
		if (riceBl == true) {
			this.addMouseMotionListener(new MouseAdapter() {
				@Override
				public void mouseMoved(MouseEvent e) {
					str = "����� �ʷϾ�~";
					x = e.getX();
					y = e.getY();
					repaint();
				}
			});
		} else if(bathBl == true) {
			this.addMouseMotionListener(new MouseAdapter() {
				@Override
				public void mouseMoved(MouseEvent e) {
					str = "���� �ʷϾ�~";
					x = e.getX();
					y = e.getY();
					repaint();
				}
			});
		}else{
			str = "";
			repaint();
		}
		
		g.drawString(str, x, y);
		this.repaint(); //�ٽ� �׷��شٴ� �ǹ�?
		
		
		
//		ScreenImage = createImage(1024,768);
//		ScreenGraphics = ScreenImage.getGraphics();
//		screenDraw(ScreenGraphics);
//		g.drawImage(ScreenImage, 0, 0, null); // �̹��� �׸���
//		
		
	}
	
//	public void screenDraw(Graphics g) {
//		g.drawImage(background, 0, 0, null);
//		paintComponents(g);
//		this.repaint();
//	}
//	
	
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


class Greeny implements Runnable{
	private JPanel jp;
	private boolean gr = false;

	Greeny(){}

	Greeny(JPanel jp){
		this.jp = jp;
	}

	@Override
	public void run() {
		Image green1 = new ImageIcon("images/stage03_image/greenE1.png").getImage();
		JLabel greenLabel = new JLabel(new ImageIcon(green1));
		greenLabel.setBounds(412, 270, 200, 279);
		jp.add(greenLabel);

		Image green2 = new ImageIcon("images/stage03_image/greenE2.png").getImage();
		JLabel greenLabel2 = new JLabel(new ImageIcon(green2));
		greenLabel2.setBounds(412, 270, 200, 279);



		while(true) {
			//System.out.println("����");
			if (gr == false) {
				jp.remove(greenLabel);
				jp.add(greenLabel2);
				jp.repaint();
				gr = true;
			} else {
				jp.remove(greenLabel2);
				jp.add(greenLabel);
				jp.repaint();
				gr = false;
			}

			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}

class Timer implements Runnable{
	private JPanel jp;
	private boolean gr = false;

	Timer(){}

	Timer(JPanel jp){
		this.jp = jp;
	}
	
	@Override
	public void run() {
		int i = 0;
		JLabel label = new JLabel("Timer : " + i);
		label.setBounds(940, 0, 100, 50);
		label.setFont(new Font("����",Font.PLAIN, 15));

		jp.add(label);
		
		
		while(true) {
			i++;
			label.setText("Timer : " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("����");
			jp.remove(label);
			jp.add(label);
			//jp.repaint();
			
			
		}
		
	}
	
	
	
}








