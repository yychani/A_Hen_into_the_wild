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

	Image Missile_img; //미사일 이미지 변수

	ArrayList Missile_List = new ArrayList();

	

	Missile ms; // 미사일 클래스 접근 키

//	Missile_img = tk.getImage("images/MSImages/wind.png");

	//미사일 이미지를 불러온다.

	Image iconNagne = new ImageIcon("images/MSImages/nagne.png").getImage().getScaledInstance(200, 200, 0);//나그네 이미지
	JLabel nagne = new JLabel(new ImageIcon(iconNagne));
	private JLabel hpbar2;
	private Image IntroBG = new ImageIcon("images/MSImages/stage2_background.jpeg").getImage(); //베경 이미지
	private JLabel hpbar = new JLabel(new ImageIcon("images/MSImages/hpbar.png"));
	private JLabel wind;

 

	public Stage02(MainFrame mf) {

		setName("stage2");
		setBounds(0, 0, 1024, 768);

		int x, y; // 케릭터의 현재 좌표를 받을 변수
		int cnt; //무한 루프를 카운터 하기 위한 변수

		

		mf.add(this);
		setFocusable(true);
		setBackground(new Color (0, 0, 0, 0));
		setLayout(null);

		//애꾸 쓰레드
		ThreadAggu ag = new ThreadAggu(this,nagne,wind);
		Thread th = new Thread(ag);
		th.start();

		//시계 쓰레드
		Thread th2 = new Thread(t);
		th2.start();

		

		//미사일 스레드
		Missile ms = new Missile(this,nagne);
		Thread th3 = new Thread(ms);
//		th3.start();

		//나그네
		nagne.setBounds(100,450,300,300);
		add(nagne);

		//나그네hp
		hpbar.setBounds(100,50,300,300);
		add(hpbar);

		//나그네 키보드
		this.addKeyListener(new KeyAdapter()
		  { 
		   public void keyPressed(KeyEvent e){
		    switch(e.getKeyCode()){
		     case KeyEvent.VK_UP:System.out.println("이동했어요 위");
		     ReduceHp(hpbar);
		     ReduceHpEnemy(hpbar2);
		     nagne.setLocation(nagne.getX(), nagne.getY()-30);
		     nY = nagne.getY();
		     System.out.println(nY);
		      break;

		     case KeyEvent.VK_DOWN:
		    	 System.out.println("이동했어요 아래");
		    	 nagne.setLocation(nagne.getX(), nagne.getY()+30);
		    	 nY = nagne.getY();
		    	 System.out.println(nY);
		      break;

		     case KeyEvent.VK_LEFT:
		    	 System.out.println("이동했어요 왼");
		    	 nagne.setLocation(nagne.getX()-30, nagne.getY());
		    	 nX = nagne.getX();
		    	 System.out.println(nX);
		      break;

		     case KeyEvent.VK_RIGHT:
		    	 System.out.println("이동했어요 오");
		    	 nagne.setLocation(nagne.getX()+30, nagne.getY());
		    	 nX = nagne.getX();
		    	 System.out.println(nX);
		      break;

		     case KeyEvent.VK_SPACE :
		    	 System.out.println("스페스눌");// 스페이스키 입력 처리 추가
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
			Mytimer.setFont(new Font("바탕",Font.PLAIN,70));
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

	public void MissileProcess(){ // 미사일 처리 메소드
		if ( KeySpace ){ // 스페이스바 키 상태가 true 면
//		ms = new Missile(x, y); // 좌표 체크하여 넘기기
		Missile_List.add(ms);    // 해당 미사일 추가
		}
	}

//	public void Draw_Missile(){ // 미사일 그리는 메소드
//		for (int i = 0 ; i < Missile_List.size()  ; ++i){
//		//미사일 존재 유무를 확인한다.
//
//		ms = (Missile) (Missile_List.get(i)); 
//		// 미사일 위치값을 확인
//
//		buffg.drawImage(Missile_img, ms.pos.x + 150, ms.pos.y + 30, this); 
//		// 현재 좌표에 미사일 그리기.
//		// 이미지 크기를 감안한 미사일 발사 좌표는 수정됨.
//
//		ms.move();
//		// 그려진 미사일을 정해진 숫자만큼 이동시키기
//
//		if ( ms.pos.x > f_width ){ // 미사일이 화면 밖으로 나가면
//		Missile_List.remove(i); // 미사일 지우기
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
//		Draw_Missile(); // 그려진 미사일 가져와 실행
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

	Image iconAggu = new ImageIcon("images/MSImages/aggu_reverse.png").getImage().getScaledInstance(250, 250, 0);//애꾸눈 이미지
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
		aggu.setBounds(500,450,300,300);//애꾸 세팅
		jp.add(aggu);
		hpbar2.setBounds(550,50,300,300); //애꾸피 세팅 
		jp.add(hpbar2);
		System.out.println("이게 지나간다고?");
		for(int i = 0; i < 1000; i++) {
			aggu.setBounds(500-i,450,300,300);// 애꾸이동 
			//애꾸 충돌 시
			System.out.println("나그네 위치 : " + jl.getX());
			if(Crash(jl.getX(),jl.getY(),100,100,aggu.getX(),aggu.getY(),aggu.getHeight(),aggu.getWidth())) {
				System.out.println("i :" + i);
				agguHp-= 10;
				i=0;
				ReduceHpEnemy(hpbar2);//체력바 감소 
				if(agguHp == 0 ) {
					jp.remove(aggu);
				}
				moveBack(aggu);// 충돌 시 위치 초기화
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Missile implements Runnable{ // 미사일 위치 파악 및 이동을 위한 클래스 추가 

	Image iconWind = new ImageIcon("images/MSImages/wind4.png").getImage().getScaledInstance(100, 100, 0);
	JLabel wind = new JLabel(new ImageIcon(iconWind));
	private JPanel jp;
	private JLabel jl;
	Point pos; //미사일 좌표 변수
	Missile(JPanel jp, JLabel jl){ //미사일 좌표를 입력 받는 메소드
		this.jp = jp;
		this.jl = jl; //미사일 좌표를 체크
	}

	public void move(){ //미사일 이동을 위한 메소드
		pos.x += 10; //x 좌표에 10만큼 미사일 이동
	}

	@Override
	public void run() {
		System.out.println("이거 실행 되나요?");
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