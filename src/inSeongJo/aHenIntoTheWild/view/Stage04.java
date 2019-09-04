package inSeongJo.aHenIntoTheWild.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
// 프레임 생성을 위한 JFrame 상속
//키보드 이벤트 처리를 위한 KeyListener를 상속
//스레드를 돌리기 위한 Runnable 상속
public class Stage04 extends JPanel implements KeyListener, Runnable { 
	Stage04Enemy se;
	Stage04_Point sp;
	
	private MainFrame mf;
//	private JPanel stage04;
	// 캐릭터의 좌표 변수
	private int x = 100;
	private int y = 100; 
	private int w;
	private int h;
	private int life = 5;
	
	int cnt;
	int cnt2;

	//키보드 입력 처리를 위한 변수
	boolean KeyUp = false; 
	boolean KeyDown = false;
	boolean KeyLeft = false;
	boolean KeyRight = false;
	private Image chorok = new ImageIcon("images/Images/chorok.gif").getImage().getScaledInstance(150, 150, 0); 
	private Image enemy  = new ImageIcon("images/Images/bird.gif").getImage().getScaledInstance(150, 150, 0);
	private Image star = new ImageIcon("images/Images/star.png").getImage().getScaledInstance(50, 50, 0);
	

	// 스레드 생성
	Thread th; 
	Image BG = new ImageIcon("images/Images/sky3.png").getImage();
	
	ArrayList<Stage04Enemy> Enemy_List = new ArrayList<>();
	ArrayList<Stage04_Point> Point_List = new ArrayList<>();

	
	public Stage04(MainFrame mf){
		this.mf = mf;
		this.setBounds(0, 0, 1600, 768);
		this.setLayout(null);
//		stage04 = this;
		mf.add(this);

		start();

		this.setName("stage4");
		

		setVisible(true);
		this.setFocusable(true);
		this.addKeyListener(this);

	}



	public void start() {
		addKeyListener(this); //키보드 이벤트 실행
		th = new Thread(this);  // 스레드 생성
		th.start();  // 스레드 실행
	}


	@Override
	public void run() {
		try{ // 예외옵션 설정으로 에러 방지
			
			while(true){ // while 문으로 무한 루프 시키기
				KeyProcess(); // 키보드 입력처리를 하여 x,y 갱신
				System.out.println("KeyProcess");

				EnemyProcess();
				System.out.println("KeyProcess");
				pointProcess();
				System.out.println("poinProcess");

				repaint(); // 갱신된 x,y값으로 이미지 새로 그리기
				
				Thread.sleep(20); // 20 milli sec 로 스레드 돌리기 
				
				cnt++;
				cnt2++;
			}
		}catch (Exception e){}

	}

	@Override
	protected void paintComponent(Graphics g){


		g.drawImage(BG,0,0,this);
		g.drawImage(chorok, x, y, this);
//		g.drawImage(star, 500,500, this);

		drawEnemy(g);
		drawPoint(g);

		this.repaint();
	}


	private void EnemyProcess() {
		//       System.out.println("계십니까~");

		for(int i = 0; i < Enemy_List.size(); ++i) {
			se = (Enemy_List.get(i));
			//			System.out.println("하...");
			if(se.getX() <= - 60) {
				Enemy_List.remove(i);

				System.out.println("여기는?");
			} else {
				se.move();
			}
			if(collision (x - w, y - h - 15, se.getX(), se.getY(), chorok, enemy)) {
/*//				life--;
				System.out.println("life : " + life);*/
				Enemy_List.remove(i);

			}
		}
		
		//cnt가 200이 될때마다 적생성
		if(cnt % 50 == 0) {

			se = new Stage04Enemy(855 + 100, ((int) (Math.random() * 300) + 50));
			Enemy_List.add(se);
			se = new Stage04Enemy(855 + 100, ((int) (Math.random() * 100) + 350));
			Enemy_List.add(se);

		}
	}
	
	//  -10 <= x <= 880    -75 <= y <= 590
	public void pointProcess() {
		for(int i = 0; i < Point_List.size(); ++i) {
			sp = (Point_List.get(i));
			
			if(sp.getX() <= - 60) {
				Point_List.remove(i);

			} else {
				sp.move();
			}
/*			if(collision (x - w, y - h - 15, sp.getX(), sp.getY(), chorok, star)) {
				life--;
				System.out.println("life : " + life);
				Enemy_List.remove(i);
			}*/
		
		}
		if(cnt2 % 150 == 0) {

			sp = new Stage04_Point((int) (Math.random() * 890) -10, -80);
			Point_List.add(sp);
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
/*			sp = new Stage04_Point((int) (Math.random() * 890) -10, -80);
			Point_List.add(sp);*/
		}
		
		if(cnt2 % 250 == 0) {

			sp = new Stage04_Point((int) (Math.random() * 890) -10, -80);
			Point_List.add(sp);
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
/*			sp = new Stage04_Point((int) (Math.random() * 890) -10, -80);
			Point_List.add(sp);*/
		}
		
		
	}

	public void drawEnemy(Graphics g) {
		//		System.out.println("여기가문제네");
		for(int i = 0; i < Enemy_List.size(); ++i) {
			//타입형이 맞지 않아서 형변환해주어야 한다.
			se = Enemy_List.get(i);
			g.drawImage(enemy, se.getX(), se.getY(), this);
		}
	}
	
	public void drawPoint(Graphics g) {
		for(int i = 0; i < Point_List.size(); ++i) {
			sp = Point_List.get(i);
			g.drawImage(star, sp.getX(), sp.getY(), this);
		}
	}

	//초록이와 적의 거리보다 두 개의 반너비의 합이 더 크다면 그것은 이미 두 개가 닿았다는 뜻
	// chorok & nemy 의 거리 < 두개 반너비의 합
	public boolean collision(int x1, int y1, int x2, int y2, Image chorok, Image enemy) {
		boolean check = false;
		if(Math.abs((x1 + chorok.getWidth(null) / 2) - (x2 + enemy.getWidth(null) / 2)) < ( enemy.getWidth(null) / 2 + chorok.getWidth(null) / 2 - 80) 
				&& Math.abs( ( y1 + chorok.getHeight(null) / 2 )  - ( y2 + enemy.getHeight(null) / 2 ))  < ( enemy.getHeight(null)/2 + chorok.getHeight(null)/2 - 40) ){
			// 이미지 넓이, 높이값 바로 받음
			check = true;
		}else {
			check = false;
		}
		return check;
	}
	



	public void keyPressed(KeyEvent e){


		switch(e.getKeyCode()){
		case KeyEvent.VK_UP :
			System.out.println("x : " + x + " y : " + y);
			KeyUp = true;
			break;
		case KeyEvent.VK_DOWN :
			//         System.out.println("아래");
			System.out.println("x : " + x + " y : " + y);
			KeyDown = true;
			break;
		case KeyEvent.VK_LEFT :
			//         System.out.println("왼쪽");
			System.out.println("x : " + x + " y : " + y);
			KeyLeft = true;
			break;
		case KeyEvent.VK_RIGHT :
			//         System.out.println("오른쪽");
			System.out.println("x : " + x + " y : " + y);
			KeyRight = true;
			break;
		}


	}
	public void keyReleased(KeyEvent e){
		// 키보드가 눌러졌다가 때어졌을때 이벤트 처리하는 곳


		switch(e.getKeyCode()){
		case KeyEvent.VK_UP :
			KeyUp = false;
			break;
		case KeyEvent.VK_DOWN :
			KeyDown = false;
			break;
		case KeyEvent.VK_LEFT :
			KeyLeft = false;
			break;
		case KeyEvent.VK_RIGHT :
			KeyRight = false;
			break;
		}
	}

	public void keyTyped(KeyEvent e){}
	// 키보드가 타이핑 될때 이벤트 처리하는 곳

	
	//  -10 <= x <= 880    -75 <= y <= 590
	public void KeyProcess(){
		//실제로 캐릭터 움직임 실현을 위해
		//위에서 받아들인 키값을 바탕으로
		//키 입력시마다 5만큼의 이동을 시킨다.

		//      System.out.println("제발");
		if(KeyUp == true) {
			if(y <= -75) {
				y -= 0;
			}else {
				y -= 5;
			}
		}else if(KeyDown == true) {
			if(y >= 590) {
				y += 0;
			}else {
				y += 5;
			}
		}else if(KeyLeft == true) {
			if(x <= -10) {
				x -= 0;
			}else {
				x -= 5;
			}
		}else if(KeyRight == true) {
			if(x >= 880) {
				x += 0;
			}else {
				x += 5;
			}
		}

		//      move();

	}







}
