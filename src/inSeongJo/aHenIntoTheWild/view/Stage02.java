package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

//import com.kh.madang.stage2.Main;
import inSeongJo.aHenIntoTheWild.view.MainFrame;
//import com.kh.madang.stage2.ThreadAggu;
//import com.kh.madang.stage2.Timer;

public class Stage02 extends JPanel {
	
	boolean keyUp = false;
	boolean keyDown = false;
	boolean keyLeft = false;
	boolean keyRight = false;
	boolean playerMove = false;
	
	private MainFrame mf;
//	private JPanel mainPage;
	
	private Image ScreenImage;
	private Graphics ScreenGraphics;
	
//	Timer t = new Timer(this);
	
	int x = 300;
	int y = 100;
	
	Image iconNagne = new ImageIcon("images/MSImages/nagne.png").getImage().getScaledInstance(150, 150, 0);//���׳� �̹���
	JLabel nagne = new JLabel(new ImageIcon(iconNagne));
	
	private Image IntroBG = new ImageIcon("images/MSImages/stage2_background.jpeg").getImage(); //���� �̹���
	private JLabel hpbar = new JLabel(new ImageIcon("images/MSImages/hpbar.png"));
	private JLabel hpbar2 = new JLabel(new ImageIcon("images/MSImages/hpbar.png"));
	final int FLYING_UNIT=10;


	public Stage02(MainFrame mf) {
//		this.mf = mf;
//		mainPage = this;
//		setUndecorated(true);
		setName("stage2");
		setBounds(0, 0, 1024, 768);
		//ĳ���� ���� ����
		
		int x, y; // �ɸ����� ���� ��ǥ�� ���� ����
		int cnt; //���� ������ ī���� �ϱ� ���� ����
		
		mf.add(this);
		setFocusable(true);
		setBackground(new Color (0, 0, 0, 0));
		setLayout(null);
		//�ֲ� ������
		ThreadAggu ag = new ThreadAggu(this);
		Thread th = new Thread(ag);
		th.start();
		//�ð� ������
		
//		Thread th2 = new Thread(t);
//		th2.start();
		//���׳�
		nagne.setBounds(100,450,300,300);
		add(nagne);
		//���׳�hp
		hpbar.setBounds(100,50,300,300);
		add(hpbar);
		//�ֲ� hp
		hpbar2.setBounds(550,50,300,300);
		add(hpbar2);
		//���׳� Ű����
		this.addKeyListener(new KeyAdapter()
		  { 
		   public void keyPressed(KeyEvent e){
		    switch(e.getKeyCode()){
		     case KeyEvent.VK_UP:System.out.println("�̵��߾�� ��");
		     ReduceHp(hpbar);
		     nagne.setLocation(nagne.getX(), nagne.getY()-30);
		      break;
		   
		     case KeyEvent.VK_DOWN:
		    	 System.out.println("�̵��߾�� �Ʒ�");
		    	 ReduceHp(hpbar);
		    	 nagne.setLocation(nagne.getX(), nagne.getY()+30);
		      break;
		     case KeyEvent.VK_LEFT:
		    	 System.out.println("�̵��߾�� ��");
		    	 ReduceHp(hpbar);
		    	 nagne.setLocation(nagne.getX()-30, nagne.getY());
		      break;
		     case KeyEvent.VK_RIGHT:
		    	 System.out.println("�̵��߾�� ��");
		    	 ReduceHp(hpbar);
		    	 nagne.setLocation(nagne.getX()+30, nagne.getY());
		      break;
		   }
		  }
		  });
	}
//	class Timer  implements Runnable {
//		private int time = 99;
//		JLabel Mytimer = new JLabel(Integer.toString(time));
//		
//		@Override
//		public void run() {
//			setBounds(420, 150, 100, 100);
//			add(Mytimer);
//			for(int i = 0 ; i < 99; i++) {
//				time--;
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			
//		}
//		
//	}
	
	
	public void ReduceHp(JLabel j) {
//	int x = 300;
	x-= 40;
	y+=15;
		j.setBounds(y,100,x,300);
	}


	
	public void paint(Graphics g) {
		ScreenImage = createImage(1024, 768);
		ScreenGraphics = ScreenImage.getGraphics();
		screenDraw(ScreenGraphics);
		g.drawImage(ScreenImage, 0, 0, null);
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(IntroBG, 0, 0, null);
		paintComponents(g);
		this.repaint();
	}
	
//	public void run(){ // ������ �޼ҵ�, ���� ����
//		while(true){
//		try{
//		keyProcess();
//		repaint();
//		Thread.sleep(20);
//		cnt++;
//		}catch(Exception e){}
//		}
//		}
////		public void paint(Graphics g){ //������۸��� ����մϴ�.
////		buffimg = createImage(800, 600);
////		gc = buffimg.getGraphics();
////		update(g);
////		}
//		public void update(Graphics g){
//		//���� ���۸��� �̿��� ���ۿ� �׷������� �����ɴϴ�.
//		DrawImg();
//		g.drawImage(buffimg, 0, 0, this);
//		}
//	
//	public void DrawImg(){
//		gc.setFont(new Font("Default", Font.BOLD, 20));
//		gc.drawString(Integer.toString(cnt), 50, 50);
//		gc.drawString(Integer.toString((playerMove)?1:0),200, 50);
//		//���� �ܼ��� ���ѷ��� ���뿩�ο� �ɸ��� ���� üũ�� ����
//		//������ ���鼭 �׽�Ʈ�� �뵵�� ���̴� �ؽ�Ʈ ǥ���Դϴ�.
//
//		MoveImage(img, x, y, 100, 150);
//		//�ɸ��͸� �ɾ�� ����� ���� �߰��� ���� �޼ҵ� �Դϴ�.
//		}
//		public void MoveImage(Image img, int x, int y, int width, 
//		int height){
//		//�ɸ��� �̹���, �ɸ��� ��ġ, �ɸ��� ũ�⸦ �޽��ϴ�.
//		//���� ���� �̿��ؼ� ���� �̹���Ĩ�¿��� �ɸ��͸� �߶�
//		//ǥ���ϵ��� ����ϴ� �޼ҵ� �Դϴ�.
//
//		gc.setClip(x  , y, width, height);
//		//���� ��ǥ���� �ɸ����� ũ�� ��ŭ �̹����� �߶� �׸��ϴ�.
//
//		if( playerMove ){ // �ɸ����� ������ ���θ� �Ǵ��մϴ�.
//		if ( cnt / 10 % 4 == 0 ){ gc.drawImage(img,
//		x - ( width * 0 ), y - ( height * moveStatus ), this);
//
//		}else  if(cnt/10%4 == 1){ gc.drawImage(img,
//		x - ( width * 1 ), y - ( height * moveStatus ), this);
//
//		}else if(cnt/10%4 == 2){  gc.drawImage(img,
//		x - ( width * 2 ), y - ( height * moveStatus ), this);
//
//		}else  if(cnt/10%4 == 3){ gc.drawImage(img,
//		x - ( width * 1 ), y - ( height * moveStatus ), this);
//		}
//		//�ɸ����� ���⿡ ���� �ɾ�� ����� ���ϴ� 
//		//�ɸ��� �̹����� �ð����� �̿��� ���������� �׸��ϴ�.
//
//		}else { gc.drawImage(img, x - ( width * 1 ), 
//		y - ( height * moveStatus ), this);
//		//�ɸ��Ͱ� �������� ������ ������ �ɸ��͸� �׸��ϴ�.
//
//		}
//		}
//	public void keyProcess(){
//		//���⼭�� �ܼ� �ɸ��Ͱ� �̵��ϴ� ��ǥ ����
//		//�ɸ����� ������ ���ι� ������ üũ �մϴ�.
//		playerMove = false;
//
//		if ( keyUp ){
//		playerMove = true;
//		y -= 8;
//		moveStatus = 0;
//		}
//
//		if ( keyDown){
//		y += 8;
//		moveStatus = 2;
//		playerMove = true;
//		}
//
//		if ( keyLeft){
//		x -= 8;
//		moveStatus = 3;
//		playerMove = true;
//		}
//
//		if ( keyRight){
//		x += 8;
//		moveStatus = 1;
//		playerMove = true;
//		}
//		}
//	
//	public void keyPressed(KeyEvent e) {
//	
//	switch(e.getKeyCode()){
//	case KeyEvent.VK_LEFT :
//	keyLeft = true;
//	break;
//	case KeyEvent.VK_RIGHT :
//	keyRight = true;
//	break;
//	case KeyEvent.VK_UP :
//	keyUp = true;
//	break;
//	case KeyEvent.VK_DOWN :
//	keyDown = true;
//	break;
//	}
//	}
//	
//	public void keyReleased(KeyEvent e) {
//		switch(e.getKeyCode()){
//		case KeyEvent.VK_LEFT :
//		keyLeft = false;
//		break;
//		case KeyEvent.VK_RIGHT :
//		keyRight = false;
//		break;
//		case KeyEvent.VK_UP :
//		keyUp = false;
//		break;
//		case KeyEvent.VK_DOWN :
//		keyDown = false;
//		break;
//		}
//		}
//
}
class ThreadAggu implements Runnable {
	private int x = 500;
	private int y;
	private JPanel jp;
	Image iconAggu = new ImageIcon("images/MSImages/aggununangry.png").getImage().getScaledInstance(500, 300, 0);//�ֲٴ� �̹���
	JLabel aggu = new JLabel(new ImageIcon(iconAggu));
	
	public ThreadAggu () { } 
	
	public ThreadAggu (JPanel jp) { 
		this.jp = jp;
	}
	
	public void run() {
		
		aggu.setBounds(500,450,300,300);
		jp.add(aggu);
		System.out.println("�̰� �������ٰ�?");
		for(int i = 0; i < 10; i++) {
			aggu.setBounds(500-i*50,450,300,300);
			if(i ==9) {
				i=0;
				aggu.setBounds(500,450,300,300);
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
