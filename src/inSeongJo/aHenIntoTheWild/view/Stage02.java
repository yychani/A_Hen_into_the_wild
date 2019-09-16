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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import inSeongJo.aHenIntoTheWild.controller.UserManager;
import inSeongJo.aHenIntoTheWild.model.dao.RankingDao;
import inSeongJo.aHenIntoTheWild.model.vo.Ranking;
import inSeongJo.aHenIntoTheWild.model.vo.User;

// 프레임 생성을 위한 JFrame 상속
//키보드 이벤트 처리를 위한 KeyListener를 상속
//스레드를 돌리기 위한 Runnable 상속
public class Stage02 extends JPanel implements KeyListener {
	
	//bgm
	private Media media = new Media(); 
	private Media media2 = new Media(); 
	private Media media3 = new Media(); 
	
	private MainFrame mf;
	private Stage02 Stage02 = this;
	private User user;
	
	// 캐릭터의 좌표 변수
	private int x = 100;
	private int y = 500;
	
	// 키보드 입력 처리를 위한 변수
	private boolean KeyUp = false;
	private boolean KeyDown = false;
	private boolean KeyLeft = false;
	private boolean KeyRight = false;
	private boolean KeySpace = false;
	private boolean KeyR = false;

	private int score; //점수
	private int agguDead;
	private int time = 99; //시간조정

	private int pattern = (int)(Math.random() * 3 + 1); //패 턴 

	ArrayList Missile_List = new ArrayList();
	private Missile ms; // 미사일 클래스 접근 키
	Graphics buffg;
	private boolean ds = false;
	private boolean thS = true;
	private Timer t = new Timer();
	private MainThread mt = new MainThread();

	private int ipsakHpStatus = 100;
	private int agguHpStatus = 50;
	private int HpW = 300;
	private int hpX = 100;
	private int agguHp = 300;

	private int cnt;
	//에너미 관련
	private Image enemy = new ImageIcon("images/MSImages/aggu_reverse.png").getImage().getScaledInstance(150, 150, 0);
	private Enemy en; //에너미 클래스 접근 키
	private Ipsak ip; //에너미 클래스 접근 키
	ArrayList Enemy_List = new ArrayList();

	//이미지의 w(넓이)값, h(높이) 값을 계산하여 받습니다.
	//해당 메소드는 아래에 이미지 크기값 계산용으로
	//추가된 메소드 입니다.
	private int e_w = ImageWidthValue("images/MSImages/aggu_reverse.png");
	private int e_h = ImageHeightValue("images/MSImages/aggu_reverse.png");
	private int m_w = ImageWidthValue("images/MSImages/wind4.png");
	private int m_h = ImageHeightValue("images/MSImages/wind4.png");
	
	private int punched = 0; // 펀치 맞은 횟수를 표시할 변수
	
	private Image nagne = new ImageIcon("images/MSImages/nagne.png").getImage().getScaledInstance(150, 150, 0);
	private Image nagnePunch = new ImageIcon("images/MSImages/punch.png").getImage().getScaledInstance(150, 150, 0);
	private Image ipsak = new ImageIcon("images/MSImages/ipsakWithEgg.png").getImage().getScaledInstance(150, 150, 0);
	//hp
	private Image enemyHpicon = new ImageIcon("images/MSImages/hpbar_blue2.png").getImage().getScaledInstance(300, 50, 0);
	private JLabel enemyHp = new JLabel(new ImageIcon(enemyHpicon));
	private Image nagneHpicon = new ImageIcon("images/MSImages/hpbar_blue.png").getImage().getScaledInstance(300, 50, 0);
	private JLabel nagneHp = new JLabel(new ImageIcon(nagneHpicon));

	private Image Missile_img = new ImageIcon("images/MSImages/wind4.png").getImage().getScaledInstance(100, 100, 0);
	
	private int backX = 0;
	private boolean moveAggu = true;
	private int moveing = 1;
	//게임 오버 & 클리어
	private Image clearImage = new ImageIcon("images/MSImages/stage02_gameclear.png").getImage().getScaledInstance(800, 250, 0);
	private Image overImage = new ImageIcon("images/MSImages/stage02_gameover.png").getImage().getScaledInstance(800, 200, 0);
	private Image retry = new ImageIcon("images/MSImages/retry_btn.png").getImage().getScaledInstance(200, 100, 0);
	private Image main = new ImageIcon("images/MSImages/main_btn.png").getImage().getScaledInstance(200, 100, 0);
	private JButton retrybtn = new JButton(new ImageIcon(retry));
	private JButton mainbtn = new JButton(new ImageIcon(main));
	
	private boolean isClear = false;
	// 스레드 생성
	//배경 이미지
	private Image BG = new ImageIcon("images/MSImages/BG.png").getImage();


	public Stage02(MainFrame mf, User user) {

		this.mf = mf;
		this.user = user;
		this.setBounds(0, 0, 1600, 768);
		this.setLayout(null);
		mf.add(this);
		start();

		if(!thS) {
			media.soundStop();
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
				thS = false;
				media.soundStop();
				ChangePanel.changePanel(mf, Stage02, new MainStage(mf, user, new Media()));
			}
		});
		
		mainbtn.setBorderPainted(false);
		retrybtn.setBorderPainted(false);
		//버튼의 내용영역 채우지 않기함
		mainbtn.setContentAreaFilled(false);
		retrybtn.setContentAreaFilled(false);
		//버튼이 선택되었을때 생기는 테두리 사용안함
		mainbtn.setFocusPainted(false);
		retrybtn.setFocusPainted(false);
		//커서 버튼위로 올라갈시 모양 손모양
		mainbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		retrybtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//위치 지정
		mainbtn.setBounds(200, 600, 200, 100);
		retrybtn.setBounds(600, 600, 200, 100);
		
		ButtonPressed(mainbtn);
		
		retrybtn.addMouseListener(new MouseListener() {
			

			//pressed를하면 눌르고있으면 계속 생성이되서 키가 안먹는다,
			//released를 하면 눌렀다 떼면 생성이되어서 하나만 생성이 되어서 키가먹는다.
			@Override
			public void mouseReleased(MouseEvent e) {
				media.soundStop();
				ChangePanel.changePanel(mf, Stage02, new Stage02(mf, user));
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseClicked(MouseEvent e) {
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
		
		if ( KeySpace == true){ // 스페이스바 키 상태가 true 면
			if( ( cnt % 30 ) == 0){ //무한 회오리 금지 카운트
				media2.sound("wind");
				ms = new Missile(x + 100, y); // 좌표 체크하여 넘기기(나그네 x,y 좌표)
				Missile_List.add(ms);    // 해당 미사일 추가
			}
		}

		for ( int i = 0 ; i < Missile_List.size() ; ++i){
			ms = (Missile) Missile_List.get(i);
			ms.move();
			if ( ms.x > ngX + 300 ){ //사거리
				Missile_List.remove(i);
				media2.soundStop();
			}
			//편의상 그림그리기 부분에 있던 미사일 이동과
			//미사일이 화면에서 벗어났을시 명령 처리를
			//이쪽으로 옮겼습니다.
			for (int j = 0 ; j < Enemy_List.size(); ++ j){
				en = (Enemy) Enemy_List.get(j);

				if (Crash(ms.x,ms.y,en.x,en.y, 100, 100, e_w, e_h)){
					//미사일과 적 객체를 하나하나 판별하여
					//접촉했을시 미사일과 적을 화면에서 지웁니다.
					//판별엔 Crash 메소드에서 계산하는 방식을 씁니다.
					Missile_List.remove(i);
					reduceHp(enemyHp);
					Enemy_List.remove(j);
					agguDead++;
					pattern = (int) (Math.random() * 3 + 1); //패턴 변수 추가
					moveAggu = true;
					moveing = 1;
				}
			}
		}
	}
	
	public void Punch(Graphics g){ 
		// 주먹 처리 메소드
		int ngX = this.x;//나그네 위치 가져오기 위한 변수
		int ngY = this.y;//나그네 위치 가져오기 위한 변수
		
		if ( KeyR == true && cnt % 50 == 0){ // 스페이스바 키 상태가 true 면
			
			g.drawImage(nagnePunch, ngX + 100, ngY, this);
			if (Crash(ngX,ngY,en.x,en.y, 150, 150, e_w, e_h)){
				//판별엔 Crash 메소드에서 계산하는 방식을 씁니다.
				punched++;
				media.sound("punch");
				en.x += 100; // 애꾸 뒤로 밀리기
				if(punched == 2) {
				reduceHp(enemyHp);
					Enemy_List.remove(0);	
					agguDead++;
					pattern = (int) (Math.random() * 3 + 1); //패턴 변수 추가
					punched = 0;
				}
			}
			
		}
	}

	public void start() {
		addKeyListener(this); // 키보드 이벤트 실행
//		th = new Thread(this);
//		th.setDaemon(true);// 스레드 생성
//		th.start(); // 스레드 실행
		//      timer.start();
		
		Thread th3 = new Thread(mt);
		th3.start();
		Thread th2 = new Thread(t);
		th2.start();
	}

	

	public void IpsakProcess() { // 입싹이 충돌 관련 부분
		ip = new Ipsak(50, 500); 
		
		if(Crash(0, 0, en.x, en.y, 150, 768, e_w, e_h)) {
//			for(int i = 0; i < Enemy_List.size(); i++ ) {
				  //가장 앞에 있는 index는 지워지므로 계속 0 이 됨
				if(ipsakHpStatus <= 0 ) {
					if(Enemy_List.get(0).equals(null)) {
						Enemy_List.remove(0);
					}
					score = time * 5 + agguDead * 100;
					if(score > user.getStage2Score() ) {
						user.setStage2Score(score);
					}
					thS = false;
					
				} else {
					if(Enemy_List.get(0).equals(null)) {
						Enemy_List.remove(0);
					} else {
						Enemy_List.remove(0);
					}
					reducenaHp(nagneHp);
					ip.x -= 30; //맞았을 때 뒤로 밀렸다가 돌아오는 부분
					media3.sound("chicry2cut");
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ip.x += 30;
					pattern = (int) (Math.random() * 3 + 1); //패턴 변수 추가
					moveAggu = true;
					moveing = 1;
					agguDead++;
					
				}
//			}
			

		}

	}
	public void EnemyProcess(){//적 행동 처리 메소드
		
		if(agguHpStatus <= 0 ) {
			
			score = time * 5 + agguDead * 100;
			thS = false;
			media.soundStop();
			isClear = true;
			if(score > user.getStage2Score() ) { //기존 점수보다 클 시에만 저장
				user.setStage2Score(score);
			}
			String[] strr = checkRanking();
			String str = "";
			for(int i = 0; i < strr.length; i++) {
				str += (strr[i] + "\n");
			}
			//메세지창이 어떤 Frame에서 보여지게 될 것인지 지정, 보통null을 사용
			JOptionPane.showMessageDialog(null, str, "Stage 2랭킹", JOptionPane.PLAIN_MESSAGE);
		}
		for (int i = 0 ; i < Enemy_List.size() ; ++i ){ 
			en = (Enemy)(Enemy_List.get(i)); 
			//배열에 적이 생성되어있을 때 해당되는 적을 판별
			en.move(pattern); //해당 적을 이동시킨다.
			if(en.x < -200){ //적의 좌표가 화면 밖으로 넘어가면
				Enemy_List.remove(i); // 해당 적을 배열에서 삭제
				pattern = (int) (Math.random() * 3 + 1); //패턴 변수 추가
			} 
		}
		if ( cnt % 350 == 0 ){ //루프 카운트 300회 마다
			//﻿각 좌표로 적을 생성한 후 배열에 추가한다.
			if(cnt % 700 == 0) {
			en = new Enemy(1000,  100);
				Enemy_List.add(en);
			pattern = 4;
			} else {
				en = new Enemy(1000,  500);
				Enemy_List.add(en);
			}
		} 
	}
	public boolean Crash(int x1, int y1, int x2, int y2, int w1, int h1, int w2, int h2){
		//﻿충돌 판정을 위한 새로운 Crash 메소드를 만들었습니다.
		//판정을 위해 충돌할 두 사각 이미지의 좌표 및 
		//넓이와 높이값을 받아들입니다.
		//여기서 이미지의 넓이, 높이값을 계산하기 위해 밑에 보면
		//이미지 크기 계산용 메소드를 또 추가했습니다.
		boolean check = false;
		//1번째 놈 x w 중앙 값 + 2번째놈 x w 중앙 값 < 2째놈 w + 1째놈 w
		if ( Math.abs( ( x1 + w1 / 2 )  - ( x2 + w2 / 2 ))  <  ( w2 / 2 + w1 / 2 ) - 50  //x자ㅗ표
				&& Math.abs( ( y1 + h1 / 2 )  - ( y2 + h2 / 2 ))  <  ( h2 / 2 + h1/ 2 ) - 50 ){
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
		g.drawImage(nagne, x, y, this);
		drawEnding(g, isClear);
		drawEnemy(g);
		drawIpsak(g);
		Punch(g);
		Draw_Missile(g); // 그려진 미사일 가져와 실행

	}
	
	public void drawEnding(Graphics g, boolean isClear) { //게임 클리어 & 오버 시 화면 띄우기
		if(!thS && isClear == true) {
			media.soundStop();
			System.out.println("지나가지??");
			thS = false;
			g.drawImage(clearImage, 100, 300, this);
			add(mainbtn);
			add(retrybtn);
		} else if(!thS && isClear == false){
			System.out.println("지나가지??22");
			media.soundStop();
			thS = false;
			g.drawImage(overImage, 100, 300, this);
			add(mainbtn);
			add(retrybtn);
		}
	}
	
	public void ButtonPressed(JButton btn) {
		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				media.soundStop();
				ChangePanel.changePanel(mf, Stage02, new MainStage(mf,user, new Media()));
			}
		});
	}
	
	public String[] checkRanking() {

		UserManager um = new UserManager();
		RankingDao rd = new RankingDao();

		// ArrayList<String> rankStr = new ArrayList<>();
		um.rankingMethod(user, score, 2);
		ArrayList<Ranking> rankList = rd.readRankingList(2);

		int count = 0;
		if(rankList.size() > 5 ) {
			count = 5;
		}else {
			count = rankList.size();
		}

		String[] rStr = new String[count];
		if(rankList.size() == 0) {
			System.out.println("랭킹이 없습니다. 게임을 플레이하세요!");
			rankList = new ArrayList<Ranking>();

		}else {
			for(int i = 0; i < count; i++) {
					rStr[i] = rankList.get(i).getName();
			}
		}

		JLabel[] rankLabel = new JLabel[5];
		for(int i = 0; i < rStr.length; i++) {
			rStr[i] = new String((i + 1) + "등 : " + rankList.get(i).getName() + " " + rankList.get(i).getScore());
			rankLabel[i] = new JLabel();
			rankLabel[i].setText(rStr[i]);
			rankLabel[i].setBounds(440, 320 + 30 * (i + 1), 200, 100);
			rankLabel[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
			rankLabel[i].setForeground(Color.BLACK);
			System.out.println(rStr[i]);
		}

		
		return rStr;
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

//안쓰는메소드 제거 
//	public void Draw_Enemy(Graphics g){ // 적 이미지를 그리는 부분
//		for (int i = 0 ; i < Enemy_List.size() ; ++i ){
//			//		en = (Enemy)(Enemy_List.get(i));
//			g.drawImage(enemy, en.x, en.y, this);
//			//배열에 생성된 각 적을 판별하여 이미지 그리기
//		}
//	}
	class Enemy{ // 적 위치 파악 및 이동을 위한 클래스
		int x;
		int y;

		Enemy(int x, int y){ // 적좌표를 받아 객체화 시키기 위한 메소드
			this.x = x;
			this.y = y;
		}
		//무브패턴
		public void move(int pattern){ // x좌표 -3 만큼 이동 시키는 명령 메소드
			//			int random  = (int) 1/*(Math.random() *3 +1 )*/;
			if(time >=  0) {
				if(pattern == 1) { 
					if(x < 500 && !moveAggu) {
						x -= 3*2.5;
						y += 10;
						
					} else if(x < 500 ) {
						y -= 10;
						if(y < 100) {
							moveAggu = false;
						}
					} else {
						x -= 3*2;
					}
				} else if(pattern == 2) { 
					if(x < 500) {
						y-= 10;
						x -= 10*2;
					}  else {
						x -= 3*2;
					}
				} else if(pattern == 3) {
					if((x / 150) % 2 == 0) {
						y-= 10;
						x -= 5;
					} else if((x / 150) % 2 == 1) {
						y += 10;
						x -= 5;
					}
				} else if(pattern == 4) {
					x -= 10;
				} 
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
		public int getX() {
			return this.x;
		}
		public int getY() {
			return this.y;
		}
	}
	
	public void reducenaHp(JLabel jl) {
		HpW -= 30;
		hpX += 30;
		ipsakHpStatus -= 10;
		jl.setBounds(hpX, 70, HpW, 100);
	}

	public void reduceHp(JLabel jl) {
		agguHp -= 30*2;
		agguHpStatus -= 10;
		jl.setBounds(600, 70, agguHp, 100);
	}
	
	public void Draw_Missile(Graphics g){ // 미사일 그리는 메소드

		for (int i = 0 ; i < Missile_List.size()  ; ++i){
			ms = (Missile) (Missile_List.get(i)); 
			g.drawImage(Missile_img, ms.x, ms.y, this); 
		}
	}


	public void drawEnemy(Graphics g) {
		for (int i = 0; i < Enemy_List.size(); ++i) {
			// 타입형이 맞지 않아서 형변환해주어야 한다.
			en = (Enemy) Enemy_List.get(i);
			g.drawImage(enemy, en.getX(), en.getY(), this);
		}
	}
	
	public void drawIpsak(Graphics g) {
			g.drawImage(ipsak, ip.x, ip.y, this);
	}
	
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("x : " + x + " y : " + y);
			KeyUp = true;
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("x : " + x + " y : " + y);
			KeyDown = true;
			break;
		case KeyEvent.VK_LEFT:
			System.out.println("x : " + x + " y : " + y);
//			KeyLeft = true;
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("x : " + x + " y : " + y);
//			KeyRight = true;
			break;
		case KeyEvent.VK_SPACE : // 스페이스키 입력 처리 추가
			KeySpace = true;
//			media.sound("wind");//회오리 공격사운드
			
			//회오리 공격 막기 조건
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
			media2.soundStop();
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
	public void KeyProcess() {
		// 실제로 캐릭터 움직임 실현을 위해
		// 위에서 받아들인 키값을 바탕으로
		// 키 입력시마다 5만큼의 이동을 시킨다.
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
	class MainThread implements Runnable{

		@Override
		public void run() {
			media.sound("battle");
	
				while (thS) { // while 문으로 무한 루프 시키기
					if(isClear) {
						media.soundStop();
						break;
					}

					KeyProcess(); // 키보드 입력처리를 하여 x,y 갱신
					MissileProcess(); //미사일 처리 메소드 실행
					EnemyProcess();//에너미
					try {
						IpsakProcess();
						
					} catch(Exception e) {
						
					}
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // 20 milli sec 로 스레드 돌리기
					cnt++;
					repaint();
				}
				media.soundStop();
			} 
		
	}
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
				if(time == -1) {
					thS = false;
					break;
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
}