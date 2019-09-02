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
	
	Image iconNagne = new ImageIcon("images/MSImages/nagne.png").getImage().getScaledInstance(150, 150, 0);//나그네 이미지
	JLabel nagne = new JLabel(new ImageIcon(iconNagne));
	
	private Image IntroBG = new ImageIcon("images/MSImages/stage2_background.jpeg").getImage(); //베경 이미지
	private JLabel hpbar = new JLabel(new ImageIcon("images/MSImages/hpbar.png"));
	private JLabel hpbar2 = new JLabel(new ImageIcon("images/MSImages/hpbar.png"));
	final int FLYING_UNIT=10;


	public Stage02(MainFrame mf) {
//		this.mf = mf;
//		mainPage = this;
//		setUndecorated(true);
		setName("stage2");
		setBounds(0, 0, 1024, 768);
		//캐릭터 관련 시작
		
		int x, y; // 케릭터의 현재 좌표를 받을 변수
		int cnt; //무한 루프를 카운터 하기 위한 변수
		
		mf.add(this);
		setFocusable(true);
		setBackground(new Color (0, 0, 0, 0));
		setLayout(null);
		//애꾸 쓰레드
		ThreadAggu ag = new ThreadAggu(this);
		Thread th = new Thread(ag);
		th.start();
		//시계 쓰레드
		
//		Thread th2 = new Thread(t);
//		th2.start();
		//나그네
		nagne.setBounds(100,450,300,300);
		add(nagne);
		//나그네hp
		hpbar.setBounds(100,50,300,300);
		add(hpbar);
		//애꾸 hp
		hpbar2.setBounds(550,50,300,300);
		add(hpbar2);
		//나그네 키보드
		this.addKeyListener(new KeyAdapter()
		  { 
		   public void keyPressed(KeyEvent e){
		    switch(e.getKeyCode()){
		     case KeyEvent.VK_UP:System.out.println("이동했어요 위");
		     ReduceHp(hpbar);
		     nagne.setLocation(nagne.getX(), nagne.getY()-30);
		      break;
		   
		     case KeyEvent.VK_DOWN:
		    	 System.out.println("이동했어요 아래");
		    	 ReduceHp(hpbar);
		    	 nagne.setLocation(nagne.getX(), nagne.getY()+30);
		      break;
		     case KeyEvent.VK_LEFT:
		    	 System.out.println("이동했어요 왼");
		    	 ReduceHp(hpbar);
		    	 nagne.setLocation(nagne.getX()-30, nagne.getY());
		      break;
		     case KeyEvent.VK_RIGHT:
		    	 System.out.println("이동했어요 오");
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
	
//	public void run(){ // 스레드 메소드, 무한 루프
//		while(true){
//		try{
//		keyProcess();
//		repaint();
//		Thread.sleep(20);
//		cnt++;
//		}catch(Exception e){}
//		}
//		}
////		public void paint(Graphics g){ //더블버퍼링을 사용합니다.
////		buffimg = createImage(800, 600);
////		gc = buffimg.getGraphics();
////		update(g);
////		}
//		public void update(Graphics g){
//		//더블 버퍼링을 이용해 버퍼에 그려진것을 가져옵니다.
//		DrawImg();
//		g.drawImage(buffimg, 0, 0, this);
//		}
//	
//	public void DrawImg(){
//		gc.setFont(new Font("Default", Font.BOLD, 20));
//		gc.drawString(Integer.toString(cnt), 50, 50);
//		gc.drawString(Integer.toString((playerMove)?1:0),200, 50);
//		//위는 단순히 무한루프 적용여부와 케릭터 방향 체크를 위해
//		//눈으로 보면서 테스트할 용도로 쓰이는 텍스트 표출입니다.
//
//		MoveImage(img, x, y, 100, 150);
//		//케릭터를 걸어가게 만들기 위해 추가로 만든 메소드 입니다.
//		}
//		public void MoveImage(Image img, int x, int y, int width, 
//		int height){
//		//케릭터 이미지, 케릭터 위치, 케릭터 크기를 받습니다.
//		//받은 값을 이용해서 위의 이미지칩셋에서 케릭터를 잘라내
//		//표출하도록 계산하는 메소드 입니다.
//
//		gc.setClip(x  , y, width, height);
//		//현재 좌표에서 케릭터의 크기 만큼 이미지를 잘라 그립니다.
//
//		if( playerMove ){ // 케릭터의 움직임 여부를 판단합니다.
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
//		//케릭터의 방향에 따라 걸어가는 모션을 취하는 
//		//케릭터 이미지를 시간차를 이용해 순차적으로 그립니다.
//
//		}else { gc.drawImage(img, x - ( width * 1 ), 
//		y - ( height * moveStatus ), this);
//		//케릭터가 움직이지 않으면 정지한 케릭터를 그립니다.
//
//		}
//		}
//	public void keyProcess(){
//		//여기서는 단순 케릭터가 이동하는 좌표 말고도
//		//케릭터의 움직임 여부및 방향을 체크 합니다.
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
	Image iconAggu = new ImageIcon("images/MSImages/aggununangry.png").getImage().getScaledInstance(500, 300, 0);//애꾸눈 이미지
	JLabel aggu = new JLabel(new ImageIcon(iconAggu));
	
	public ThreadAggu () { } 
	
	public ThreadAggu (JPanel jp) { 
		this.jp = jp;
	}
	
	public void run() {
		
		aggu.setBounds(500,450,300,300);
		jp.add(aggu);
		System.out.println("이게 지나간다고?");
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
