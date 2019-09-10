package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import inSeongJo.aHenIntoTheWild.model.vo.User;

// 프레임 생성을 위한 JFrame 상속
//키보드 이벤트 처리를 위한 KeyListener를 상속
//스레드를 돌리기 위한 Runnable 상속
public class Stage02 extends JPanel implements KeyListener, Runnable {
	Stage04Enemy se;
	Stage04_Point sp,sp2;
	User user;
	
	//bgm
//	private Media media = new Media(); 
	
	private MainFrame mf;
	private JPanel stage04;
	Stage02 Stage02 = this;
	// 캐릭터의 좌표 변수
	
	private int x = 100;
	private int y = 100;
	private int ax = 500;
	private int ay = 500;
	private int w;
	private int h;
	//   private int time;

	private int life = 5;
	private int score;
	private int score2;
	private int agguDead;
	private int time = 10; //시간조정

	private int pattern = 1; //패턴 1로 고정  (수정해야됨)

	ArrayList Missile_List = new ArrayList();
	Missile ms; // 미사일 클래스 접근 키
	Image buffImage; Graphics buffg;
	private boolean ds = false;
	private boolean stop = true;
	private boolean thS = true;
	private boolean gameOver = false;
	private boolean gameClear = false;
	Timer t = new Timer();

	int ipsakHpStatus = 100;
	int agguHpStatus = 100;
	int Hp = 300;
	int hpX = 100;
	int agguHp = 300;

	int cnt;
	int cnt2;
	int cnt3;
	//에너미 관련
	private Image enemy = new ImageIcon("images/MSImages/aggu_reverse.png").getImage().getScaledInstance(150, 150, 0);
	Enemy en; //에너미 클래스 접근 키
	Ipsak ip; //에너미 클래스 접근 키
	ArrayList Enemy_List = new ArrayList();

	int e_w = ImageWidthValue("images/MSImages/aggu_reverse.png");
	int e_h = ImageHeightValue("images/MSImages/aggu_reverse.png");
	//적 이미지의 w(넓이)값, h(높이) 값을 계산하여 받습니다.
	//해당 메소드는 아래에 이미지 크기값 계산용으로
	//추가된 메소드 입니다.
	int m_w = ImageWidthValue("images/MSImages/wind4.png");
	int m_h = ImageHeightValue("images/MSImages/wind4.png");
	
	private int punched = 0; // 펀치 맞은 횟수를 표시할 변수
	//hp
	private Image enemyHpicon = new ImageIcon("images/MSImages/hpbar_blue2.png").getImage().getScaledInstance(300, 50, 0);
	private JLabel enemyHp = new JLabel(new ImageIcon(enemyHpicon));
	private Image nagneHpicon = new ImageIcon("images/MSImages/hpbar_blue.png").getImage().getScaledInstance(300, 50, 0);
	private JLabel nagneHp = new JLabel(new ImageIcon(nagneHpicon));

	// 키보드 입력 처리를 위한 변수

	boolean KeyUp = false;
	boolean KeyDown = false;
	boolean KeyLeft = false;
	boolean KeyRight = false;
	boolean KeySpace = false;
	boolean KeyR = false;
	
	private int backX = 0;
	//   private Image overImage = new ImageIcon("images/MSImages/gameOver2.png").getImage().getScaledInstance(800, 200, 0);
	//   private Image clearImage = new ImageIcon("images/MSImages/gameClear.png").getImage().getScaledInstance(800, 250, 0);
	private Image chorok = new ImageIcon("images/MSImages/nagne.png").getImage().getScaledInstance(150, 150, 0);
	private Image chorokPunch = new ImageIcon("images/MSImages/nagne_punch2.png").getImage().getScaledInstance(150, 150, 0);
	private Image ipsak = new ImageIcon("images/MSImages/ipsakWithEgg.png").getImage().getScaledInstance(150, 150, 0);
	//게임 오버 & 클리어
	private Image clearImage = new ImageIcon("images/MSImages/stage02_gameclear.png").getImage().getScaledInstance(800, 250, 0);
	private Image overImage = new ImageIcon("images/MSImages/stage02_gameover.png").getImage().getScaledInstance(800, 200, 0);
	
	private Image retry = new ImageIcon("images/MSImages/retry_btn.png").getImage().getScaledInstance(200, 100, 0);
	private Image main = new ImageIcon("images/MSImages/main_btn.png").getImage().getScaledInstance(200, 100, 0);
	JButton retrybtn = new JButton(new ImageIcon(retry));
	JButton mainbtn = new JButton(new ImageIcon(main));

	private ArrayList<Image> life_Array;
	//   private Image me_img = new ImageIcon("images/MSImages/nagne.png").getImage().getScaledInstance(40, 40, 0);
	private Image Missile_img = new ImageIcon("images/MSImages/wind4.png").getImage().getScaledInstance(100, 100, 0);

	
	
	
	boolean isClear = false;
	// 스레드 생성
	Thread th;
	Image BG = new ImageIcon("images/MSImages/BG.png").getImage()/*.getScaledInstance(1024, 768, Image.SCALE_SMOOTH)*/;

	ArrayList<Stage04_Point> Point_List = new ArrayList<>();
	ArrayList<Stage04_Point> Point_List2 = new ArrayList<>();

	public Stage02(MainFrame mf, User user) {

		this.mf = mf;
		this.user = user;
		this.setBounds(0, 0, 1600, 768);
		this.setLayout(null);
		// stage04 = this;
		mf.add(this);
		start();

		life_Array= new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			life_Array.add(new ImageIcon("images/Images/life.png").getImage().getScaledInstance(20, 20, 0));
		}

		// -5, -50
		JLabel lifeText = new JLabel();
		lifeText.setBounds(500, 500, 100, 50);
		lifeText.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		add(lifeText);

		Image goHome = new ImageIcon("images/YJimages/home.png").getImage().getScaledInstance(60, 60,
				Image.SCALE_SMOOTH);
		Image goHomePressed = new ImageIcon("images/YJimages/home_pressed.png").getImage().
				getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		JButton homebtn = new JButton(new ImageIcon(goHome));
		homebtn.setBounds(920, 650, 60, 60);
		homebtn.setBorderPainted(false);
		homebtn.setContentAreaFilled(false);
		homebtn.setFocusPainted(false);
		homebtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		homebtn.setPressedIcon(new ImageIcon(goHomePressed));
		homebtn.setToolTipText("저장되지 않고 메인으로 돌아갑니다.");
		add(homebtn);

		//홈버튼 눌렀을 때, 메인스테이지로 돌아감
		homebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//				goOrStop = false;
				ChangePanel.changePanel(mf, Stage02, new MainStage(mf, user));
			}
		});

		enemyHp.setBounds(600, 70, 300, 100);
		add(enemyHp);

		nagneHp.setBounds(100, 70, 300, 100);
		add(nagneHp);
		setVisible(true);
		this.setFocusable(true);
		this.addKeyListener(this);
	}



	public void MissileProcess(){ 
		// 미사일 처리 메소드
		int ngX = this.x;//나그네 위치 가져오기 위한 변수
		System.out.println("ngX : " +ngX);
		
		if ( KeySpace == true){ // 스페이스바 키 상태가 true 면
			if( ( cnt % 30 ) == 0){
				ms = new Missile(x, y); // 좌표 체크하여 넘기기
				Missile_List.add(ms);    // 해당 미사일 추가
			}
		}

		for ( int i = 0 ; i < Missile_List.size() ; ++i){
			ms = (Missile) Missile_List.get(i);
			ms.move();
			if ( ms.x > ngX + 500 ){
				Missile_List.remove(i);
			}
			//편의상 그림그리기 부분에 있던 미사일 이동과
			//미사일이 화면에서 벗어났을시 명령 처리를
			//이쪽으로 옮겼습니다.
			for (int j = 0 ; j < Enemy_List.size(); ++ j){
				en = (Enemy) Enemy_List.get(j);
				System.out.println("ms.x : " + ms.x);

				if (Crash(ms.x,ms.y,en.x,en.y, 100, 100, e_w, e_h)){
					//미사일과 적 객체를 하나하나 판별하여
					//접촉했을시 미사일과 적을 화면에서 지웁니다.
					//판별엔 Crash 메소드에서 계산하는 방식을 씁니다.
					Missile_List.remove(i);
					reduceHp(enemyHp);
//					en.x += 100; // 애꾸 뒤로 밀리기
					Enemy_List.remove(j);
				}
			}
		}
	}
	
	public void PunchProcess(Graphics g){ 
		// 미사일 처리 메소드
		int ngX = this.x;//나그네 위치 가져오기 위한 변수
		int ngY = this.y;//나그네 위치 가져오기 위한 변수
		System.out.println("ngX : " +ngX);
		
		if ( KeyR == true && cnt % 50 == 0){ // 스페이스바 키 상태가 true 면
			
			g.drawImage(chorokPunch, ngX, ngY, this);
			if (Crash(ngX,ngY,en.x,en.y, 150, 150, e_w, e_h)){
				//판별엔 Crash 메소드에서 계산하는 방식을 씁니다.
//				Missile_List.remove(i);
//				reduceHp(enemyHp);
				punched++;
				System.out.println("맞은 횟수 : " + punched);
				en.x += 100; // 애꾸 뒤로 밀리기
				if(punched == 2) {
				reduceHp(enemyHp);
					Enemy_List.remove(0);
					punched = 0;
				}
//				Enemy_List.remove(j);
			}
			
		}
	}

	public void start() {
		addKeyListener(this); // 키보드 이벤트 실행
		th = new Thread(this);
		th.setDaemon(true);// 스레드 생성
		th.start(); // 스레드 실행
		//      timer.start();

		Thread th2 = new Thread(t);
		th2.start();
	}

	@Override
	public void run() {
		try { // 예외옵션 설정으로 에러 방지
//			media.sound("stage03bgm");
			while (thS) { // while 문으로 무한 루프 시키기
				KeyProcess(); // 키보드 입력처리를 하여 x,y 갱신
				System.out.println("여기는 되나요?");
				MissileProcess(); //미사일 처리 메소드 실행
				System.out.println("어디가 안되는 걸까요?");
				EnemyProcess();//에너미
				System.out.println("여기는 되겠지?");
				System.out.println("timer : " + time);
				try {
					nagneProcess();
				} catch(Exception e ) {
					e.printStackTrace();
				}
				System.out.println("여기는 돠야지??");
				repaint(); // 갱신된 x,y값으로 이미지 새로 그리기
				//				ThreadAggu ag = new ThreadAggu(this,chorok);
				//				Thread th3 = new Thread(ag);
				//				th3.start();
				Thread.sleep(20); // 20 milli sec 로 스레드 돌리기
				cnt++;
				cnt2++;
				cnt3++;
			}
		} catch (Exception e) {
		}
		System.out.println("score : " + score);
		System.out.println("score2 : " + score2);

	}

	public void nagneProcess() { // 나그네 충돌 관련 부분
		ip = new Ipsak(50, 500); 
		
		//Crash(int x1, int y1, int x2, int y2, int w1, int h1, int w2, int h2){
		//Crash(ms.x,ms.y,en.x,en.y, 100, 100, e_w, e_h)
		System.out.println("ip.x : " + ip.x);
		System.out.println("ip.y : " + ip.y);
		System.out.println("en.x : " + en.x);
		System.out.println("en.y : " + y);
		System.out.println("x : " + x);
		System.out.println("x : " + x);
		System.out.println("e_w : " + e_w);
		System.out.println("e_h : " + e_h);
		if(Crash(ip.x, ip.y, en.x, en.y, 150, 150, e_w, e_h)) {
//			for(int i = 0; i < Enemy_List.size(); i++ ) {
				  //가장 앞에 있는 index는 지워지므로 계속 0 이 됨
				if(ipsakHpStatus <= 0 ) {
					Enemy_List.remove(0);
					score = time;
					thS = false;
				} else {
					
					Enemy_List.remove(0);
					agguDead++;
					reducenaHp(nagneHp);
				}
//			}
//			moveNagneBack();
			

		}

	}
	public void EnemyProcess(){//적 행동 처리 메소드
		
		if(agguHpStatus <= 0 ) {
			
			score = time;
			thS = false;
			isClear = true;
		}
		for (int i = 0 ; i < Enemy_List.size() ; ++i ){ 
			en = (Enemy)(Enemy_List.get(i)); 
			//배열에 적이 생성되어있을 때 해당되는 적을 판별
			en.move(pattern); //해당 적을 이동시킨다.
			if(en.x < -200){ //적의 좌표가 화면 밖으로 넘어가면
				Enemy_List.remove(i); // 해당 적을 배열에서 삭제
				//				pattern = (int) (Math.random() * 3 + 1); //패턴 변수 추가
			} 
		}

		if ( cnt % 300 == 0 ){ //루프 카운트 300회 마다
			System.out.println(time +"time " );
			if(time == 85) {
				System.out.println(time +"time2 " );
				en = new Enemy(500, 300);
				System.out.println("애꾸없다");
				Enemy_List.add(en); 
			}
			cnt = 400;
			//						//﻿각 좌표로 적을 생성한 후 배열에 추가한다.
			en = new Enemy(1000,  500);
			Enemy_List.add(en);
			System.out.println("애꾸없다5");
		}
		//			if (collision(x - w, y - h - 15 + ipY, en.getX(), en.getY() + ipY, ipssagStanding[0], enemy)) {
		//				life--;
		////				media.sound("punch");
		//				x -= 20;
		//				// System.out.println(life);
		//				Enemy_List.remove(i);
		//			}		
	}
	public boolean Crash(int x1, int y1, int x2, int y2, int w1, int h1, int w2, int h2){
		//﻿충돌 판정을 위한 새로운 Crash 메소드를 만들었습니다.
		//판정을 위해 충돌할 두 사각 이미지의 좌표 및 
		//넓이와 높이값을 받아들입니다.
		//여기서 이미지의 넓이, 높이값을 계산하기 위해 밑에 보면
		//이미지 크기 계산용 메소드를 또 추가했습니다.
		boolean check = false;
		if ( Math.abs( ( x1 + w1 / 2 )  - ( x2 + w2 / 2 ))  <  ( w2 / 2 + w1 / 2 )  
				&& Math.abs( ( y1 + h1 / 2 )  - ( y2 + h2 / 2 ))  <  ( h2 / 2 + h1/ 2 ) ){
			//충돌 계산식입니다. 사각형 두개의 거리및 겹치는 여부를 확인
			//하는 방식이라고 알고 있는데, 만들다보니 생각보다 식이 
			//복잡해진것 같습니다.
			//이보다 더 간편한 방식이 있을 것도 같은데 
			//일단 이렇게 해봤습니다.
			check = true;//위 값이 true면 check에 true를 전달합니다.
		}else{ 
			check = false;
		}
		return check; //check의 값을 메소드에 리턴 시킵니다.
	}
	//모든 스윙 컴포넌트는 자신의 모양을 그리는paintComponent() 메소드를 보유
	//스윙컴포넌트가 자신의 모양을  그리는 메소드
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(BG, 0, 0, this);
		//		Draw_Enemy(); 
		g.drawImage(chorok, x, y, this);
		EndProcess(g, isClear);
		drawEnemy(g);
		drawIpsak(g);
		PunchProcess(g);
		//		g.drawImage(enemy, x, y, this);
		//      g.drawImage(star2,500,500,this);
		// g.drawImage(star, 500,500, this);
		Draw_Missile(g); // 그려진 미사일 가져와 실행
		//      drawEnemy(g);
		this.repaint();
	}
	
	public void EndProcess(Graphics g, boolean isClear) { //게임 클리어 & 오버 시 화면 띄우기
		if(!thS && isClear == true) {
			g.drawImage(clearImage, 100, 300, this);
			//외곽선을 없애준다.
			mainbtn.setBorderPainted(false);
			retrybtn.setBorderPainted(false);
			//버튼의 내용영역 채우지 않기함
			mainbtn.setContentAreaFilled(false);
			retrybtn.setContentAreaFilled(false);
			//버튼이 선택되었을때 생기는 테두리 사용안함
			mainbtn.setFocusPainted(false);
			retrybtn.setFocusPainted(false);
			
			mainbtn.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			retrybtn.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			
			mainbtn.setBounds(200, 600, 200, 100);
			add(mainbtn);
			retrybtn.setBounds(700, 600, 200, 100);
			add(retrybtn);
//			media.soundStop();
		} else if(!thS && isClear == false){
			g.drawImage(overImage, 100, 300, this);
			//외곽선을 없애준다.
			mainbtn.setBorderPainted(false);
			retrybtn.setBorderPainted(false);
			//버튼의 내용영역 채우지 않기함
			mainbtn.setContentAreaFilled(false);
			retrybtn.setContentAreaFilled(false);
			//버튼이 선택되었을때 생기는 테두리 사용안함
			mainbtn.setFocusPainted(false);
			retrybtn.setFocusPainted(false);
			
			mainbtn.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			retrybtn.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			
			mainbtn.setBounds(200, 600, 200, 100);
			add(mainbtn);
			retrybtn.setBounds(600, 600, 200, 100);
			add(retrybtn);
			
			retrybtn.addMouseListener(new MouseListener() {


				//pressed를하면 눌르고있으면 계속 생성이되서 키가 안먹는다,
				//released를 하면 눌렀다 떼면 생성이되어서 하나만 생성이 되어서 키가먹는다.
				@Override
				public void mouseReleased(MouseEvent e) {
					System.out.println("너의용도는 뭐야?");
					ChangePanel.changePanel(mf, Stage02, new Stage02(mf, user));

				}

				@Override
				public void mousePressed(MouseEvent e) {

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub

				}
			});
			mainbtn.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					System.out.println("너는 클릭이야?");
					ChangePanel.changePanel(mf, Stage02, new MainStage(mf,user));
				}

				@Override
				public void mousePressed(MouseEvent e) {

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub

				}
			});
		}
	}
	public int ImageWidthValue(String file){ 
		// 이미지 넓이 크기 값 계산용 메소드 입니다.
		// 파일을 받아들여 그 파일 값을 계산 하도록 하는 것입니다.
		int x = 0;
		try{
			File f = new File(file); // 파일을 받습니다.
			BufferedImage bi = ImageIO.read(f);
			//받을 파일을 이미지로 읽어들입니다. 
			x = bi.getWidth(); //이미지의 넓이 값을 받습니다.
		}catch(Exception e){}
		return x; //받은 넓이 값을 리턴 시킵니다.
	}

	public int ImageHeightValue(String file){ // 이미지 높이 크기 값 계산
		int y = 0;
		try{
			File f = new File(file);
			BufferedImage bi = ImageIO.read(f);
			y = bi.getHeight();
		}catch(Exception e){}
		return y;
	}


	public void Draw_Enemy(Graphics g){ // 적 이미지를 그리는 부분
		for (int i = 0 ; i < Enemy_List.size() ; ++i ){
			//		en = (Enemy)(Enemy_List.get(i));
			g.drawImage(enemy, en.x, en.y, this);
			//배열에 생성된 각 적을 판별하여 이미지 그리기
		}
	}
	class Enemy{ // 적 위치 파악 및 이동을 위한 클래스
		int x;
		int y;

		Enemy(int x, int y){ // 적좌표를 받아 객체화 시키기 위한 메소드
			this.x = x;
			this.y = y;
		}
		public void move(int pattern){ // x좌표 -3 만큼 이동 시키는 명령 메소드
			//			int random  = (int) 1/*(Math.random() *3 +1 )*/;
			if(time >=  0) {
				System.out.println("pattern : " + 1);

				System.out.println("x : " + x);
				if(x < 200) {
					y-= 10;
					x -= 10*2;
				} else if(x < 400 /*&& y <= 400*/) {
					y += 10;
					x -= 3*2;
				} else if(x < 500 && y > 400) {
					y -= 10;
					x -= 3*2;
					System.out.println("y는 이만큼 증가 했어요 : " + y);
				} /*else if(x < 300 && y <= 400) {
					y += 10;
					x -= 3;
				} else if((x >= 400 && y > 0)  || ( x <= 300 && y <= 400)) {					System.out.println("y : " +y);
					x -= 3;
				}*/ else {
					x -= 3*2;
				}
				//			y -= 10;
			} /*else if (time == 1) {
				System.out.println("pattern : " + 2);
				x -= 3;
			} */else {
				System.out.println("pattern : " + 3);
				x -= 10;
//				y += 30;
			}

		}
		public int getX() {
			return this.x;
		}
		public int getY() {
			return this.y;
		}
	}
	
	class Ipsak{ // 적 위치 파악 및 이동을 위한 클래스
		int x/* = 50*/;
		int y /*= 500*/;

		Ipsak(int x, int y){ // 적좌표를 받아 객체화 시키기 위한 메소드
			this.x = x;
			this.y = y;
		}
//		public void move(int pattern){ // x좌표 -3 만큼 이동 시키는 명령 메소드
//			//			int random  = (int) 1/*(Math.random() *3 +1 )*/;
//			if(pattern == 1) {
//				System.out.println("pattern : " + 1);
//
//				System.out.println("x : " + x);
//				//				if(x <= 200) {
//				//					x += 3;
//				//				} else {
//				//					x -= 3;
//				//				}
//				x -= 3;
//				//			y -= 10;
//			} else if (pattern == 2) {
//				System.out.println("pattern : " + 2);
//				x -= 3;
//			} else {
//				System.out.println("pattern : " + 3);
//				x -= 10;
//				y += 30;
//			}
//
//		}
		public int getX() {
			return this.x;
		}
		public int getY() {
			return this.y;
		}
	}
	
	public void reducenaHp(JLabel jl) {
		Hp -= 30;
		hpX += 30;
		ipsakHpStatus -= 10;
		jl.setBounds(hpX, 70, Hp, 100);
	}

	public void reduceHp(JLabel jl) {
		agguHp -= 30;
		agguHpStatus -= 10;
		jl.setBounds(600, 70, agguHp, 100);
	}
	
	public void moveNagneBack() {
		this.x -= 100;
	}
	
	public void moveAgguBack() {
		this.en.x =+ 100;
	}
	public void Draw_Missile(Graphics g){ // 미사일 그리는 메소드

		for (int i = 0 ; i < Missile_List.size()  ; ++i){
			ms = (Missile) (Missile_List.get(i)); 
			g.drawImage(Missile_img, ms.x, ms.y, this); 
		}
	}


	public void drawEnemy(Graphics g) {
		// System.out.println("여기가문제네");
		for (int i = 0; i < Enemy_List.size(); ++i) {
			// 타입형이 맞지 않아서 형변환해주어야 한다.
			en = (Enemy) Enemy_List.get(i);
			g.drawImage(enemy, en.getX(), en.getY(), this);
		}
	}
	
	public void drawIpsak(Graphics g) {
		// System.out.println("여기가문제네");
			g.drawImage(ipsak, ip.x, ip.y, this);
	}
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	/*public void drawEnemy(Graphics g) {
		// System.out.println("여기가문제네");
//		for (int i = 0; i < Enemy_List.size(); ++i) {
			// 타입형이 맞지 않아서 형변환해주어야 한다.
			//			en = enemy;
		for(int i = 0; i < 1000; i ++) {
//		ax= 500;
//		ay = 500;
		ax -= 5;
		System.out.println("ax : " + ax);
		System.out.println("ay : " + ay);
		g.drawImage(enemy, ax, ay, this);
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		if(i == 999) {
//			enemy.remove(i);
//			remove(g);
			ax = 500;
			ay = 500;
		}
		}

//		}
	}*/

	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	//   public void drawPoint(Graphics g) {

	//      for (int i = 0; i < Point_List.size(); ++i) {

	//         int random = (int) (Math.random() * 100) + 1;

	//         sp = Point_List.get(i);

	//

	//         g.drawImage(star, sp.getX(), sp.getY(), this);

	//      }

	//   }

	//   public void drawPoint2(Graphics g) {

	//      for (int i = 0; i < Point_List2.size(); ++i) {

	//         int random = (int) (Math.random() * 100) + 1;

	//         sp2 = Point_List2.get(i);

	//         g.drawImage(star2, sp2.getX(), sp2.getY(), this);

	//      }

	//   }

	//life_Array

	// -10 <= x <= 880 -75 <= y <= 590

	//   public void drawLife(Graphics g) {

	//      if(life == 5) {

	//         for(int i = 0; i < life_Array.size(); i++) {

	//            g.drawImage(life_Array.get(i), 830 + (i * 40), 700, this);

	//         }

	//

	//      }else if(life == 4){

	//         life_Array.set(4, emptyLife);
	//         for(int i = 0; i < life_Array.size(); i++) {
	//            g.drawImage(life_Array.get(i), 830 + (i * 40), 700, this);
	//         }
	//
	//      }else if(life == 3) {

	//         life_Array.set(4, emptyLife);

	//         life_Array.set(3, emptyLife);

	//         for(int i = 0; i < life_Array.size(); i++) {

	//            g.drawImage(life_Array.get(i), 830 + (i * 40), 700, this);

	//         }

	//      }else if(life == 2) {
	//         life_Array.set(4, emptyLife);
	//         life_Array.set(3, emptyLife);
	//         life_Array.set(2, emptyLife);
	//         for(int i = 0; i < life_Array.size(); i++) {
	//            g.drawImage(life_Array.get(i), 830 + (i * 40), 700, this);
	//         }
	//
	//      } else if(life == 1) {
	//         life_Array.set(4, emptyLife);
	//         life_Array.set(3, emptyLife);

	//         life_Array.set(2, emptyLife);

	//         life_Array.set(1, emptyLife);

	//         for(int i = 0; i < life_Array.size(); i++) {

	//            g.drawImage(life_Array.get(i), 830 + (i * 40), 700, this);

	//         }

	//         

	//      }else {

	//         life_Array.set(4, emptyLife);

	//         life_Array.set(3, emptyLife);
	//         life_Array.set(2, emptyLife);
	//         life_Array.set(1, emptyLife);
	//         life_Array.set(0, emptyLife);

	////         stop = false;

	////         gameOver = true;

	//         for(int i = 0; i < life_Array.size(); i++) {

	//         g.drawImage(life_Array.get(i), 830, 700, this);

	//         }

	//      }

	//   }

	public boolean collision(int x1, int y1, int x2, int y2, Image chorok, Image enemy) {

		boolean check = false;
		if (Math.abs((x1 + chorok.getWidth(null) / 2) - (x2 + enemy.getWidth(null) / 2)) < (enemy.getWidth(null) / 2
				+ chorok.getWidth(null) / 2 - 80)
				&& Math.abs((y1 + chorok.getHeight(null) / 2)
						- (y2 + enemy.getHeight(null) / 2)) < (enemy.getHeight(null) / 2 + chorok.getHeight(null) / 2
								- 80)) {
			// 이미지 넓이, 높이값 바로 받음
			check = true;
		} else {
			check = false;
		}
		return check;
	}
	
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("x : " + x + " y : " + y);
			KeyUp = true;
			break;
		case KeyEvent.VK_DOWN:
			// System.out.println("아래");
			System.out.println("x : " + x + " y : " + y);
			KeyDown = true;
			break;
		case KeyEvent.VK_LEFT:
			// System.out.println("왼쪽");
			System.out.println("x : " + x + " y : " + y);
			KeyLeft = true;
			break;
		case KeyEvent.VK_RIGHT:
			// System.out.println("오른쪽");
			System.out.println("x : " + x + " y : " + y);
			KeyRight = true;
			break;
		case KeyEvent.VK_SPACE : // 스페이스키 입력 처리 추가
			KeySpace = true;
//			if(time >= 90) {
//			} else {
//				KeySpace = false;
//			}
			break;
		case KeyEvent.VK_R:
			 System.out.println("R키 눌림");
			KeyR = true;
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		// 키보드가 눌러졌다가 때어졌을때 이벤트 처리하는 곳
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			KeyUp = false;
			break;
		case KeyEvent.VK_DOWN:
			KeyDown = false;
			break;
		case KeyEvent.VK_LEFT:
			KeyLeft = false;
			break;
		case KeyEvent.VK_RIGHT:
			KeyRight = false;
			break;
		case KeyEvent.VK_SPACE : // 스페이스키 입력 처리 추가
			KeySpace = false;
			break;
		case KeyEvent.VK_R : // R키 입력 처리 추가
			KeyR = false;
			break;
		}
	}

	public void keyTyped(KeyEvent e) {
	}
	// 키보드가 타이핑 될때 이벤트 처리하는 곳
	// -10 <= x <= 880 -75 <= y <= 590
	public void KeyProcess() {
		// 실제로 캐릭터 움직임 실현을 위해
		// 위에서 받아들인 키값을 바탕으로
		// 키 입력시마다 5만큼의 이동을 시킨다.
		// System.out.println("제발");
		if (KeyUp == true) {
			if (y <= -75) {
				y -= 0;
			} else {
				y -= 5;
			}
		} else if (KeyDown == true) {
			if (y >= 590) {
				y += 0;
			} else {
				y += 5;
			}
		} else if (KeyLeft == true) {
			if (x <= -10) {
				x -= 0;
			} else {
				x -= 5;
			}
		} else if (KeyRight == true) {
			if (x >= 880) {
				x += 0;
			} else {
				x += 5;                  
			}
		}
		// move();
	}
	class Missile{
		int x;
		int y; //편의상 변수 명 변경
		Missile(int x, int y){
			this.x = x; 
			this.y = y;//편의상 변수명 변경
		}
		public void move(){
			x += 10;
		}
	}
	//class ThreadAggu implements Runnable {
	//
	//	private int x = 500;
	//	private int y;
	//	int y2 = 300;
	//	private JPanel jp;
	//	private Image img;
	//	private JLabel jl2;
	//	private int agguHp = 100;
	//	private Image enemy = new ImageIcon("images/MSImages/aggu_reverse.gif").getImage().getScaledInstance(150, 150, 0);
	//	Image iconAggu = new ImageIcon("images/MSImages/aggu_reverse.png").getImage().getScaledInstance(250, 250, 0);//애꾸눈 이미지
	//	JLabel aggu = new JLabel(new ImageIcon(iconAggu));
	//	private JLabel hpbar2 = new JLabel(new ImageIcon("images/MSImages/hpbar.png"));
	//	Image iconWind = new ImageIcon("images/MSImages/wind4.png").getImage().getScaledInstance(100, 100, 0);
	//	JLabel wind = new JLabel(new ImageIcon(iconWind));
	//	Point pos; //미사일 좌표 변수
	//
	//	public ThreadAggu () { } 
	//
	//	public ThreadAggu (JPanel jp,Image img, int x, int y) { 
	//		pos = new Point(x, y); //미사일 좌표를 체크
	//		this.jp = jp;
	//		this.img = img;
	//		// this.jl2 = jl2;
	//	}
	//	ThreadAggu(int x, int y){ //미사일 좌표를 입력 받는 메소드
	//		pos = new Point(x, y); //미사일 좌표를 체크
	//	}
	//
	//	public void moveBack(Image img) {
	//		//			img.x();
	//	}
	//	public boolean Crash(int x, int y, int h, int w, int x2 , int y2, int h2 , int w2) {
	//		boolean isTrue = false;
	//		if(x  >= x2) {
	//			isTrue = true;
	//		}
	//		return isTrue;
	//	}
	//	public void run() {
	//		// wind.setBounds(500, 500, 100, 100);
	//		// jp.add(wind);
	//		aggu.setBounds(500,450,300,300);//애꾸 세팅
	//		// aggu.move(x, y);
	//		jp.add(aggu);
	//		hpbar2.setBounds(550,50,300,300); //애꾸피 세팅 
	//		jp.add(hpbar2);
	//		System.out.println("이게 지나간다고?");
	//		for(int i = 0; i < 1000; i++) {
	//			System.out.println("여기도? 지나간다고?");
	//			System.out.println("i : "  + i);
	//			aggu.setBounds(500-i*10,450,300,300);// 애꾸이동 
	//			//애꾸 충돌 시
	//			// if(Crash(jl.getX(),jl.getY(),100,100,aggu.getX(),aggu.getY(),aggu.getHeight(),aggu.getWidth())) {
	//			// System.out.println("i :" + i);
	//			try {
	//				Thread.sleep(100);
	//			} catch (InterruptedException e) {
	//
	//				e.printStackTrace();
	//
	//			}
	//
	//			// agguHp-= 10;
	//
	//			// i=0;
	//
	//			// ReduceHpEnemy(hpbar2);//체력바 감소 
	//
	//			// if(agguHp == 0 ) {
	//
	//			// jp.remove(aggu);
	//
	//			// }
	//
	//			//				moveBack(aggu);// 충돌 시 위치 초기화
	//
	//			// }
	//
	//		}
	//
	//	}
	//
	//}
	class Timer implements Runnable {
		@Override
		public void run() {
			JLabel Mytimer = new JLabel("" + time);
			Mytimer.setBounds(480, 70, 100, 100);
			Mytimer.setFont(new Font("맑은 고딕", Font.BOLD, 40));
			Mytimer.setForeground(Color.WHITE);
			add(Mytimer);
			while (thS) {
				time--;
				if(time == 0) {
					gameClear = true;
					stop = false;
					thS = false;
				}else {
				}
				
				Mytimer.setText("" + time);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//   public void gameSet(Graphics g) {
	//      if(gameOver == true) {
	//         g.drawImage(overImage, 120, 200, null);
	//         thS = false;;
	//      }else if(gameClear == true) {
	//         g.drawImage(clearImage, 120, 200, null);
	//         thS = false;
	//      }
	//   }
}