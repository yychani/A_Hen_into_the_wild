package inSeongJo.aHenIntoTheWild.view;

 

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

 

public class Stage02 extends JPanel {

	

	boolean keyUp = false;
	boolean keyDown = false;
	boolean keyLeft = false;
	boolean keyRight = false;
	boolean playerMove = false;
	boolean KeySpace = false;

	private MainFrame mf;

	private Image ScreenImage;
	private Graphics ScreenGraphics;

	Timer t = new Timer();

	int x = 300;
	int y = 100;

	int x2 = 550;

	int y2 = 300;

	

	private int nX;

	private int nY;

	private int nH;

	private int nW;

	Image Missile_img; //�̻��� �̹��� ����

	ArrayList Missile_List = new ArrayList();

	

	Missile ms; // �̻��� Ŭ���� ���� Ű

//	Missile_img = tk.getImage("images/MSImages/wind.png");

	//�̻��� �̹����� �ҷ��´�.

	Image iconNagne = new ImageIcon("images/MSImages/nagne.png").getImage().getScaledInstance(200, 200, 0);//���׳� �̹���
	JLabel nagne = new JLabel(new ImageIcon(iconNagne));
	private JLabel hpbar2;
	private Image IntroBG = new ImageIcon("images/MSImages/stage2_background.jpeg").getImage(); //���� �̹���
	private JLabel hpbar = new JLabel(new ImageIcon("images/MSImages/hpbar.png"));
	private JLabel wind;

 

	public Stage02(MainFrame mf) {

		setName("stage2");
		setBounds(0, 0, 1024, 768);

		int x, y; // �ɸ����� ���� ��ǥ�� ���� ����
		int cnt; //���� ������ ī���� �ϱ� ���� ����

		

		mf.add(this);
		setFocusable(true);
		setBackground(new Color (0, 0, 0, 0));
		setLayout(null);

		//�ֲ� ������
		ThreadAggu ag = new ThreadAggu(this,nagne,wind);
		Thread th = new Thread(ag);
		th.start();

		//�ð� ������
		Thread th2 = new Thread(t);
		th2.start();

		

		//�̻��� ������
		Missile ms = new Missile(this,nagne);
		Thread th3 = new Thread(ms);
//		th3.start();

		//���׳�
		nagne.setBounds(100,450,300,300);
		add(nagne);

		//���׳�hp
		hpbar.setBounds(100,50,300,300);
		add(hpbar);

		//���׳� Ű����
		this.addKeyListener(new KeyAdapter()
		  { 
		   public void keyPressed(KeyEvent e){
		    switch(e.getKeyCode()){
		     case KeyEvent.VK_UP:System.out.println("�̵��߾�� ��");
		     ReduceHp(hpbar);
		     ReduceHpEnemy(hpbar2);
		     nagne.setLocation(nagne.getX(), nagne.getY()-30);
		     nY = nagne.getY();
		     System.out.println(nY);
		      break;

		     case KeyEvent.VK_DOWN:
		    	 System.out.println("�̵��߾�� �Ʒ�");
		    	 nagne.setLocation(nagne.getX(), nagne.getY()+30);
		    	 nY = nagne.getY();
		    	 System.out.println(nY);
		      break;

		     case KeyEvent.VK_LEFT:
		    	 System.out.println("�̵��߾�� ��");
		    	 nagne.setLocation(nagne.getX()-30, nagne.getY());
		    	 nX = nagne.getX();
		    	 System.out.println(nX);
		      break;

		     case KeyEvent.VK_RIGHT:
		    	 System.out.println("�̵��߾�� ��");
		    	 nagne.setLocation(nagne.getX()+30, nagne.getY());
		    	 nX = nagne.getX();
		    	 System.out.println(nX);
		      break;

		     case KeyEvent.VK_SPACE :
		    	 System.out.println("���佺��");// �����̽�Ű �Է� ó�� �߰�
		    	 th3.start();
		    	th3.interrupt();
		    	 break;
		   }
		  }
		  });
	}

	class Timer  implements Runnable {
		private int time = 99;

		@Override
		public void run() {
			JLabel Mytimer = new JLabel("" + time);
			Mytimer.setBounds(430, 150, 100, 100);
			Mytimer.setFont(new Font("����",Font.PLAIN,70));
			Mytimer.setForeground(Color.ORANGE);
			add(Mytimer);
			while(true) {
				time--;
				Mytimer.setText("" + time);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void MissileProcess(){ // �̻��� ó�� �޼ҵ�
		if ( KeySpace ){ // �����̽��� Ű ���°� true ��
//		ms = new Missile(x, y); // ��ǥ üũ�Ͽ� �ѱ��
		Missile_List.add(ms);    // �ش� �̻��� �߰�
		}
	}

//	public void Draw_Missile(){ // �̻��� �׸��� �޼ҵ�
//		for (int i = 0 ; i < Missile_List.size()  ; ++i){
//		//�̻��� ���� ������ Ȯ���Ѵ�.
//
//		ms = (Missile) (Missile_List.get(i)); 
//		// �̻��� ��ġ���� Ȯ��
//
//		buffg.drawImage(Missile_img, ms.pos.x + 150, ms.pos.y + 30, this); 
//		// ���� ��ǥ�� �̻��� �׸���.
//		// �̹��� ũ�⸦ ������ �̻��� �߻� ��ǥ�� ������.
//
//		ms.move();
//		// �׷��� �̻����� ������ ���ڸ�ŭ �̵���Ű��
//
//		if ( ms.pos.x > f_width ){ // �̻����� ȭ�� ������ ������
//		Missile_List.remove(i); // �̻��� �����
//		}
//		}
//		}

	public void ReduceHp(JLabel j) {

		x-= 50;

		y+=50;

		j.setBounds(y,50,x,300);

	}

	public void ReduceHpEnemy(JLabel j) {

		y2-=50;

		j.setBounds(550,50,y2,300);

	}

	public void paint(Graphics g) {

		ScreenImage = createImage(1024, 768);
		ScreenGraphics = ScreenImage.getGraphics();
		screenDraw(ScreenGraphics);
//		Draw_Missile(); // �׷��� �̻��� ������ ����
		g.drawImage(ScreenImage, 0, 0, null);
	}

	

	public void screenDraw(Graphics g) {
		g.drawImage(IntroBG, 0, 0, null);
		paintComponents(g);
		this.repaint();
	}

	public void moveBack(JLabel jl) {
		jl.setBounds(500,450,300,300);
	}

	public boolean Crash(int x, int y, int h, int w, int x2 , int y2, int h2 , int w2) {
		boolean isTrue = true;

		if(x+w >= x2) {
			isTrue = false;
		}
		return isTrue;
	}
}

class ThreadAggu implements Runnable {

	private int x = 500;
	private int y;
	int y2 = 300;
	private JPanel jp;
	private JLabel jl;
	private JLabel jl2;
	private int agguHp = 100;

	Image iconAggu = new ImageIcon("images/MSImages/aggu_reverse.png").getImage().getScaledInstance(250, 250, 0);//�ֲٴ� �̹���
	JLabel aggu = new JLabel(new ImageIcon(iconAggu));
	private JLabel hpbar2 = new JLabel(new ImageIcon("images/MSImages/hpbar.png"));
	Image iconWind = new ImageIcon("images/MSImages/wind4.png").getImage().getScaledInstance(100, 100, 0);
	JLabel wind = new JLabel(new ImageIcon(iconWind));

	public ThreadAggu () { } 

	public ThreadAggu (JPanel jp,JLabel jl,JLabel jl2) { 
		this.jp = jp;
		this.jl = jl;
		this.jl2 = jl2;
	}

	public void moveBack(JLabel jl) {
		jl.setBounds(500,450,300,300);
	}

	public boolean Crash(int x, int y, int h, int w, int x2 , int y2, int h2 , int w2) {
		boolean isTrue = false;
		if(x + w >= x2) {
			isTrue = true;
		}
		return isTrue;
	}

	public void ReduceHpEnemy(JLabel j) {
		y2-=30;
		j.setBounds(550,50,y2,300);
	}

	public void run() {
//		wind.setBounds(500, 500, 100, 100);
//		jp.add(wind);
		aggu.setBounds(500,450,300,300);//�ֲ� ����
		jp.add(aggu);
		hpbar2.setBounds(550,50,300,300); //�ֲ��� ���� 
		jp.add(hpbar2);
		System.out.println("�̰� �������ٰ�?");
		for(int i = 0; i < 1000; i++) {
			aggu.setBounds(500-i,450,300,300);// �ֲ��̵� 
			//�ֲ� �浹 ��
			System.out.println("���׳� ��ġ : " + jl.getX());
			if(Crash(jl.getX(),jl.getY(),100,100,aggu.getX(),aggu.getY(),aggu.getHeight(),aggu.getWidth())) {
				System.out.println("i :" + i);
				agguHp-= 10;
				i=0;
				ReduceHpEnemy(hpbar2);//ü�¹� ���� 
				if(agguHp == 0 ) {
					jp.remove(aggu);
				}
				moveBack(aggu);// �浹 �� ��ġ �ʱ�ȭ
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Missile implements Runnable{ // �̻��� ��ġ �ľ� �� �̵��� ���� Ŭ���� �߰� 

	Image iconWind = new ImageIcon("images/MSImages/wind4.png").getImage().getScaledInstance(100, 100, 0);
	JLabel wind = new JLabel(new ImageIcon(iconWind));
	private JPanel jp;
	private JLabel jl;
	Point pos; //�̻��� ��ǥ ����
	Missile(JPanel jp, JLabel jl){ //�̻��� ��ǥ�� �Է� �޴� �޼ҵ�
		this.jp = jp;
		this.jl = jl; //�̻��� ��ǥ�� üũ
	}

	public void move(){ //�̻��� �̵��� ���� �޼ҵ�
		pos.x += 10; //x ��ǥ�� 10��ŭ �̻��� �̵�
	}

	@Override
	public void run() {
		System.out.println("�̰� ���� �ǳ���?");
		System.out.println("x : " + jl.getX());
		System.out.println("y : " + jl.getY());
		System.out.println("w : " + jl.getWidth());
		System.out.println("h : " + jl.getHeight());
		wind.setBounds(jl.getX(), jl.getY(), jl.getWidth(), jl.getHeight());
		jp.add(wind);
		for(int i = 0; i < 1000; i++) {

			wind.setBounds(300+i, jl.getY(), jl.getWidth(), jl.getHeight());
			if(i == 200) {
				jp.remove(wind);
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}