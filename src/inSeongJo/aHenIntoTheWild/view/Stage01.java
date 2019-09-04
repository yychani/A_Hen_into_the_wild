package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import inSeongJo.aHenIntoTheWild.controller.UserManager;
import inSeongJo.aHenIntoTheWild.model.dao.RankingDao;
import inSeongJo.aHenIntoTheWild.model.vo.Ranking;
import inSeongJo.aHenIntoTheWild.model.vo.Stage01_Enemy;
import inSeongJo.aHenIntoTheWild.model.vo.Stage01_Thread;
import inSeongJo.aHenIntoTheWild.model.vo.Stage01_jump;
import inSeongJo.aHenIntoTheWild.model.vo.User;



public class Stage01 extends JPanel implements KeyListener{
	private Stage01_Thread s1thread;
	private User user;
	Stage01 stage01page = this;
	Stage01_Enemy en;
	private int enemySpeed = 7;
	private int width, height;//패널 사이즈 가져오기
	private int life = 5;
	private int x, y, w, h;//xy : 플레이어의 중심 좌표 / wh : 이미지 절반폭;
	private int ipX, ipY; // ipXY 잎싹이 왼쪽 모서리 좌표
	private int dx = 0, dy = 0;//플레이어 이미지의 이동속도, 이동방향z
	private Image stage01Background, leftWall, rightWall, ipssagJump, ipssagJumpR, EnemyImg, EnemyImgR, emptyLife, gameOverImg, gameClearImg, scoreImg;
	private Image[] ipssagMoving, ipssagStanding, ipssagMovingR, ipssagStandingR;
	private ArrayList<Image> stage01Footrest, lifeArray;
	ArrayList<Stage01_Enemy> Enemy_List = new ArrayList<>();
	ArrayList<Stage01_Enemy> Enemy_ListR = new ArrayList<>();
	boolean isMoving = false, isRight = false;
	private boolean isJump = false, isDrop = false, gameOver = false, isClear = false;
	private MainFrame mf;
	private JPanel stage01;
	int cnt = 0;
	private int time = 20;
	private int score = 0;
	//GUI 관련 프로그램의 편의를 위해 만들어진 도구상자(Toolkit) 객체 
	Toolkit toolkit = Toolkit.getDefaultToolkit();

	public Stage01(MainFrame mf, User user) {
		gameOver = false;
		this.mf = mf;
		this.user = user;
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
		stage01 = this;
		mf.add(this);

		s1thread = new Stage01_Thread(this);
		s1thread.setDaemon(true);
		s1thread.start();


		stage01Background = toolkit.getImage("images/Stage01_background.png");//배경 이미지
		leftWall = toolkit.getImage("images/left_wall.png");
		rightWall = toolkit.getImage("images/right_wall.png");//벽 이미지
		stage01Footrest = new ArrayList<>();
		for(int i = 0; i < 7; i++) {
			stage01Footrest.add(toolkit.getImage("images/Stage01_footrest.png")); // 발판 이미지
		}
		lifeArray = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			lifeArray.add(toolkit.getImage("images/ipssag/life.png"));
		}

		emptyLife = toolkit.getImage("images/ipssag/empty_life.png");
		gameOverImg = toolkit.getImage("images/gameOver.png");
		gameClearImg = toolkit.getImage("images/clear.png");
		scoreImg = toolkit.getImage("images/score.png");

		EnemyImg = new ImageIcon("images/ipssag/stage01_Enemy.png").getImage();
		EnemyImgR = new ImageIcon("images/ipssag/stage01_Enemy_reverse.png").getImage();

		ipssagStanding = new Image[2];
		ipssagStanding[0] = toolkit.getImage("images/ipssag/ipssag.png");//플레이어 이미지 왼쪽 객체
		ipssagStanding[1] = toolkit.getImage("images/ipssag/ipssag2.png");

		ipssagMoving = new Image[2];
		ipssagMoving[0] = toolkit.getImage("images/ipssag/ipssag.png");
		ipssagMoving[1] = toolkit.getImage("images/ipssag/ipssag_Moving.png");

		ipssagStandingR = new Image[2];
		ipssagStandingR[0] = toolkit.getImage("images/ipssag/ipssag_reverse.png");//플레이어 이미지 오른쪽 객체
		ipssagStandingR[1] = toolkit.getImage("images/ipssag/ipssag2_reverse.png");

		ipssagMovingR = new Image[2];
		ipssagMovingR[0] = toolkit.getImage("images/ipssag/ipssag_reverse.png");
		ipssagMovingR[1] = toolkit.getImage("images/ipssag/ipssag_Moving_reverse.png");

		ipssagJump = toolkit.getImage("images/ipssag/ipssag_Jump.png");
		ipssagJumpR = toolkit.getImage("images/ipssag/ipssag_Jump_reverse.png");

		JLabel lifeText = new JLabel("Life : ");
		lifeText.setBounds(10, 10, 100, 50);
		lifeText.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		add(lifeText);

		JLabel countText = new JLabel("Count : ");
		countText.setBounds(880, 2, 100, 70);
		countText.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		add(countText);

		Thread timer = new Thread(new Runnable() {
			@Override
			public void run() {
				JLabel Mytimer = new JLabel();
				Mytimer.setBounds(960, 0, 70, 70);
				Mytimer.setFont(new Font("맑은 고딕", Font.BOLD, 40));
				Mytimer.setForeground(Color.BLACK);
				stage01page.add(Mytimer);
				while(true) {
					if(time == -1 || gameOver == true || isClear == true) {
						gameOver = true;
						break;
					}

					Mytimer.setText("" + time);
					time--;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		timer.setDaemon(true);
		timer.start();

		this.setFocusable(true);
		this.addKeyListener(this);

	}
	// 생성자

	@Override
	protected void paintComponent(Graphics g) {
		//화면에 보여질 작업 코딩
		if( width == 0 || height == 0) { //처음 호출시엔 느려서 안보이다 이후 보임
			width = getWidth();
			height = getHeight();

			//리사이징
			stage01Background = stage01Background.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			leftWall = leftWall.getScaledInstance(300, 700, Image.SCALE_SMOOTH);
			rightWall = rightWall.getScaledInstance(300, 700, Image.SCALE_SMOOTH);
			for(int i = 0; i < stage01Footrest.size(); i++) {
				stage01Footrest.set(i, (stage01Footrest.get(i)).getScaledInstance(150, 100, Image.SCALE_SMOOTH));
			}

			for(int i = 0; i < lifeArray.size(); i++) {
				lifeArray.set(i, (lifeArray.get(i)).getScaledInstance(60, 60, Image.SCALE_SMOOTH));
			}

			emptyLife = emptyLife.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			gameOverImg = gameOverImg.getScaledInstance(850, 190, Image.SCALE_SMOOTH);
			gameClearImg = gameClearImg.getScaledInstance(830, 250, Image.SCALE_SMOOTH);
			scoreImg = scoreImg.getScaledInstance(180, 70, Image.SCALE_SMOOTH);

			ipssagStanding[0] = ipssagStanding[0].getScaledInstance(128, 128, Image.SCALE_SMOOTH);
			ipssagStanding[1] = ipssagStanding[1].getScaledInstance(128, 128, Image.SCALE_SMOOTH);
			ipssagMoving[0] = ipssagMoving[0].getScaledInstance(128, 128, Image.SCALE_SMOOTH);
			ipssagMoving[1] = ipssagMoving[1].getScaledInstance(128, 128, Image.SCALE_SMOOTH);
			ipssagStandingR[0] = ipssagStandingR[0].getScaledInstance(128, 128, Image.SCALE_SMOOTH);
			ipssagStandingR[1] = ipssagStandingR[1].getScaledInstance(128, 128, Image.SCALE_SMOOTH);
			ipssagMovingR[0] = ipssagMovingR[0].getScaledInstance(128, 128, Image.SCALE_SMOOTH);
			ipssagMovingR[1] = ipssagMovingR[1].getScaledInstance(128, 128, Image.SCALE_SMOOTH);

			ipssagJump = ipssagJump.getScaledInstance(128, 128, Image.SCALE_SMOOTH);
			ipssagJumpR = ipssagJumpR.getScaledInstance(128, 128, Image.SCALE_SMOOTH);

			EnemyImg = EnemyImg.getScaledInstance(290, 107, Image.SCALE_SMOOTH);
			EnemyImgR = EnemyImgR.getScaledInstance(290, 107, Image.SCALE_SMOOTH);
			x = width/2;//플레이어의 좌표 계산
			y = 639;
			w = 64;
			h = 64;

		}			

		//이곳에 화가객체가 있으므로 그림 그리는 작업은 무조건 여기서			

		g.drawImage(stage01Background, 0, 0, this);//배경 그리기
		g.drawImage(leftWall, 0, 68, this);//왼쪽 벽 그리기
		g.drawImage(rightWall, width - 300, 68, this);//왼쪽 벽 그리기
		g.drawImage(stage01Footrest.get(0), width / 2 - 70, 650, this); // 발판 그리기
		g.drawImage(stage01Footrest.get(1), width / 2 - 150, 570, this); // 발판 그리기
		g.drawImage(stage01Footrest.get(2), width / 2, 490, this); // 발판 그리기
		g.drawImage(stage01Footrest.get(3), width / 2 - 180, 410, this); // 발판 그리기
		g.drawImage(stage01Footrest.get(4), width / 2 - 80, 330, this); // 발판 그리기
		g.drawImage(stage01Footrest.get(5), width / 2 - 200, 250, this); // 발판 그리기
		g.drawImage(stage01Footrest.get(6), width / 2 - 50, 170, this); // 발판 그리기
		g.drawImage(stage01Footrest.get(6), width / 2 + 40, 90, this); // 발판 그리기
		drawLife(g);
		drawIpssag(g);
		drawEnemy(g);

		gameStatusCheck(g);

	}//paintComponent                                      

	public void drawLife(Graphics g) {
		if(life == 5) {
			for(int i = 0; i < lifeArray.size(); i++) {
				g.drawImage(lifeArray.get(i), 110 + (i * 70), 10, this);
			}
		}else if(life == 4) {
			lifeArray.set(4, emptyLife);
			for(int i = 0; i < lifeArray.size(); i++) {
				g.drawImage(lifeArray.get(i), 110 + (i * 70), 10, this);
			}
		}
		else if(life == 3) {
			lifeArray.set(4, emptyLife);
			lifeArray.set(3, emptyLife);
			for(int i = 0; i < lifeArray.size(); i++) {
				g.drawImage(lifeArray.get(i), 110 + (i * 70), 10, this);
			}
		}
		else if(life == 2) {
			lifeArray.set(4, emptyLife);
			lifeArray.set(3, emptyLife);
			lifeArray.set(2, emptyLife);
			for(int i = 0; i < lifeArray.size(); i++) {
				g.drawImage(lifeArray.get(i), 110 + (i * 70), 10, this);
			}
		}
		else if(life == 1) {
			lifeArray.set(4, emptyLife);
			lifeArray.set(3, emptyLife);
			lifeArray.set(2, emptyLife);
			lifeArray.set(1, emptyLife);
			for(int i = 0; i < lifeArray.size(); i++) {
				g.drawImage(lifeArray.get(i), 110 + (i * 70), 10, this);
			}
		}else if(life <= 0){
			lifeArray.set(4, emptyLife);
			lifeArray.set(3, emptyLife);
			lifeArray.set(2, emptyLife);
			lifeArray.set(1, emptyLife);
			lifeArray.set(0, emptyLife);
			for(int i = 0; i < lifeArray.size(); i++) {
				g.drawImage(lifeArray.get(i), 110 + (i * 70), 10, this);
			}
			gameOver = true;
		}
	}

	public void gameStatusCheck(Graphics g) {
		Image retry = new ImageIcon("images/buttons/Retry_button_Normal.png").getImage().getScaledInstance(200, 140, 0);
		Image retryPressed = new ImageIcon("images/buttons/Retry_button_Pressed.png").getImage().getScaledInstance(200, 140, 0);
		JButton retryButton = new JButton(new ImageIcon(retry));
		JButton retryPButton = new JButton(new ImageIcon(retryPressed));
		retryButton.setBorderPainted(false);
		retryButton.setContentAreaFilled(false);
		retryButton.setFocusPainted(false);
		retryButton.setBounds(200, 500, 300, 300);
		
		retryButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		retryButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("마우스 눌렀다 땜, 재시작");
				ChangePanel.changePanel(mf, stage01page, new Stage01(mf, user));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("마우스 눌려지고 있음");
				retryButton.setIcon(new ImageIcon(retryPressed));
			}
		});
		Image home = new ImageIcon("images/buttons/Home_button_Normal.png").getImage().getScaledInstance(200, 140, 0);
		Image homePressed = new ImageIcon("images/buttons/Home_button_Pressed.png").getImage().getScaledInstance(200, 140, 0);
		JButton homeButton = new JButton(new ImageIcon(home));
		homeButton.setBorderPainted(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setFocusPainted(false);
		homeButton.setBounds(500, 500, 300, 300);
		
		homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		homeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("마우스 눌렀다 땜, 재시작");
				ChangePanel.changePanel(mf, stage01page, new MainPage(mf));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("마우스 눌려지고 있음");
				homeButton.setIcon(new ImageIcon(homePressed));
			}
		});
		
		//		System.out.println(time);
		if(gameOver && isClear == false) {
			g.drawImage(gameOverImg, 80, 80, this);
			
			add(retryButton);
			add(homeButton);
			
			s1thread.setOver(false);
		}else if (isClear) {
			g.drawImage(gameClearImg, 100, 40, this);
			
			score = (life * 100) + (time * 115);
			g.drawImage(scoreImg, 320, 280, this);
			JLabel scoreText = new JLabel(score + "");
			scoreText.setBounds(520, 280, 200, 70);
			scoreText.setFont(new Font("맑은 고딕", Font.BOLD, 70));
			scoreText.setForeground(Color.WHITE);
			add(scoreText);
			System.out.println(score);
			
			add(retryButton);
			add(homeButton);
			
			s1thread.setOver(false);

		}
	}

	public void drawIpssag(Graphics g) {
		if(isRight) {
			if(isJump) {
				g.drawImage(ipssagJumpR, x - w, y - h - 15, this);
			}
			else if(isMoving) {
				if((cnt / 5 % 2) == 0){ 
					g.drawImage(ipssagMovingR[1], x - w, y - h - 15, this);
				}else { 
					g.drawImage(ipssagMovingR[0], x - w, y - h - 15, this); 
				}
			}else { 
				if((cnt / 5 % 2) == 0){ 
					g.drawImage(ipssagStandingR[1], x - w, y - h - 15, this);
					try {
						Thread.sleep(125);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else { 
					g.drawImage(ipssagStandingR[0], x - w, y - h - 15, this); 
					try {
						Thread.sleep(125);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}
		else {
			if(isJump) {
				g.drawImage(ipssagJump, x - w, y - h - 15, this);
			}
			else if(isMoving) {
				if((cnt / 5 % 2) == 0){ 
					g.drawImage(ipssagMoving[1], x - w, y - h - 15, this);
				}else { 
					g.drawImage(ipssagMoving[0], x - w, y - h - 15, this); 
				}
			}else { 
				if((cnt / 5 % 2) == 0){ 
					g.drawImage(ipssagStanding[1], x - w, y - h - 15, this);
					try {
						Thread.sleep(125);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else { 
					g.drawImage(ipssagStanding[0], x - w, y - h - 15, this); 
					try {
						Thread.sleep(125);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}// 플레이어 그리기
	}
	public void addY(int add) {
		this.y+=add;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getIpX() {
		return ipX;
	}

	public void setIpX(int ipX) {
		this.ipX = ipX;
	}

	public int getIpY() {
		return ipY;
	}

	public void setIpY(int ipY) {
		this.ipY = ipY;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public void move() { //플레이어 움직이기(좌표 변경)
		cnt++;
		x += dx;
		y += dy;
		int ddy = -10;


		//플레이어 좌표가 화면 밖으로 나가지 않도록 // 나중에 벽으로 수정
		if(x < w + 280 && (y - h) > 19) x = w + 280; // 왼쪽 벽 넘지 못하게
		if(x > width - w - 280 && (y - h) > 19) x = width - w - 280; // 오른쪽 벽 넘지 못하게
		//		System.out.println(isDrop);
		System.out.println("x = " + (x - w) + " y = " + (y - h));
		if((x == 0 && y == 0) ||(x - w) >= 390 && (x - w) <=  510 && (y - h) <= 575 && (y - h) >= 570) {
			//			System.out.println("발판위에 서있다.");
			isDrop = false;
		}else if ((x - w) >= 300 && (x - w) <=  430 && (y - h) <= 495 && (y - h) >= 490){
			//			System.out.println("발판위에 서있다.");
			isDrop = false;
		}else if ((x - w) >= 450 && (x - w) <=  570 && (y - h) <= 415 && (y - h) >= 410){
			//			System.out.println("발판위에 서있다.");
			isDrop = false;
		}else if ((x - w) >= 270 && (x - w) <=  390 && (y - h) <= 335 && (y - h) >= 330){
			//			System.out.println("발판위에 서있다.");
			isDrop = false;
		}else if ((x - w) >= 370 && (x - w) <=  490 && (y - h) <= 255 && (y - h) >= 250){
			//			System.out.println("발판위에 서있다.");
			isDrop = false;
		}else if ((x - w) >= 250 && (x - w) <=  370 && (y - h) <= 175 && (y - h) >= 170){
			//			System.out.println("발판위에 서있다.");
			isDrop = false;
		}else if ((x - w) >= 400 && (x - w) <=  520 && (y - h) <= 95 && (y - h) >= 90){
			//			System.out.println("발판위에 서있다.");
			isDrop = false;
		}else if ((x - w) >= 490 && (x - w) <=  610 && (y - h) <= 15 && (y - h) >= 10){
			//			System.out.println("발판위에 서있다.");
			isDrop = false;
		}else if ((x - w) >=  650 && (x - w) <= 1000 && y - h <= - 35 && (y - h) >= -40){
			System.out.println("클리어");
			isDrop = false;
			isClear = true;
		}
		else {
			System.out.println("낙하중...");
			y -= ddy;
			isDrop = true;
		}
		if(y - h - 15 > 700) {
			gameOver = true;
		}
	}
	// move

	public void enemyProcess() {

		for (int i = 0 ; i < Enemy_List.size() ; ++i ){ 
			en = Enemy_List.get(i); 
			if(i % 2 == 0) {
				en.move(); 
			}else {
				en.move2();
			}
			if(en.getX() < -200 && i % 2 == 0){ 
				Enemy_List.remove(i); 
			}else if(en.getX() > 1500 && i % 2 != 0) {
				Enemy_List.remove(i); 
			}
			if(collision(x - w, y - h - 15, en.getX(), en.getY(), ipssagStanding[0], EnemyImg))
			{
				life--;
				//				System.out.println(life);
				Enemy_List.remove(i);
			}
		}

		if(cnt % 201 == 0 || cnt == 1) {
			en = new Stage01_Enemy(1024 + 100, ((int)(Math.random()*600) + 100), (enemySpeed + (int)(Math.random()*10)));
			Enemy_List.add(en); 
			en = new Stage01_Enemy(-100, ((int)(Math.random()*600) + 100), (enemySpeed + (int)(Math.random()*10)));
			Enemy_List.add(en);
		}
	}
	public void drawEnemy(Graphics g){ 
		for (int i = 0 ; i < Enemy_List.size() ; ++i ){
			en = Enemy_List.get(i);
			//			System.out.println("x = " + en.getX() + " y = " + en.getY());
			if(i % 2 == 0) {
				g.drawImage(EnemyImgR, en.getX(), en.getY(), this);
			}else {
				g.drawImage(EnemyImg, en.getX(), en.getY(), this);
			}
		}
	}

	public boolean collision(int x1, int y1, int x2, int y2, Image ipssag, Image enemy) {
		boolean check = false;
		if(Math.abs((x1 + ipssag.getWidth(null) / 2) - (x2 + enemy.getWidth(null) / 2)) < ( enemy.getWidth(null) / 2 + ipssag.getWidth(null) / 2 - 40) 
				&& Math.abs( ( y1 + ipssag.getHeight(null) / 2 )  - ( y2 + enemy.getHeight(null) / 2 ))  < ( enemy.getHeight(null)/2 + ipssag.getHeight(null)/2 - 40) ){
			// 이미지 넓이, 높이값 바로 받음
			check = true;
		}else {
			check = false;
		}
		return check;
	}
	public String[] checkRanking() {
		UserManager userManager = new UserManager();
		RankingDao rankingDao = new RankingDao();

		//		ArrayList<String> rankStr = new ArrayList<>();
		userManager.rankingMethod(user, score, 1);
		ArrayList<Ranking> list = rankingDao.readRankingList(1);

		int size = 0;
		if(list.size() < 5) {
			size = list.size();
		}else {
			size = 5;
		}
		String[] rankStr = new String[size];
		if(list.size() == 0) {
			System.out.println("기존 랭킹이 없습니다.");
			list = new ArrayList<Ranking>();
		} else {
			for(int i = 0; i < size; i++) {
				if(!(list.get(i).getName().equals(null))) {
					//					System.out.println(list.get(i));
					//					rankStr.add(list.get(i).getName());
					rankStr[i] = list.get(i).getName();
					System.out.println(rankStr[i]);
				}
			}
		}

		JLabel[] rankLa = new JLabel[5];
		for(int i = 0; i < rankStr.length; i++) {
			//			for(int j = 0; j < i; j++) {
			//				if(!(list.get(i).getScore() == list.get(j).getScore())) {
			rankStr[i] = new String((i + 1) + "등 : " + list.get(i).getName() + "    " + list.get(i).getScore());
			//				}
			rankLa[i] = new JLabel();
			rankLa[i].setText(rankStr[i]);
			rankLa[i].setBounds(440, 320 + 30*(i+1), 200, 100);
			rankLa[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
			rankLa[i].setForeground(Color.BLACK);
			System.out.println(rankStr[i]);
		}


		//		}
		return rankStr;

	}
	public boolean isJump() {
		return isJump;
	}

	public void setJump(boolean isJump) {
		this.isJump = isJump;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public boolean isDrop() {
		return isDrop;
	}

	public void setDrop(boolean isDrop) {
		this.isDrop = isDrop;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//눌러진 키가 무엇인지 알아내기 
		int keyCode = e.getKeyCode();

		switch( keyCode ) {
		//방향키 좌우 구분
		case KeyEvent.VK_LEFT:
			System.out.println("왼쪽");
			this.dx = -8; 
			isMoving = true;
			isRight = false;
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("오른쪽");
			this.dx = 8;
			isMoving = true;
			isRight = true;
			break;	
		case KeyEvent.VK_SPACE :
			System.out.println("점프");
			if(isDrop == false) {
				isJump = true;
			}

			break;
		}

	}
	@Override
	public void keyReleased(KeyEvent e) {
		//눌러진 키가 무엇인지 알아내기 
		int keyCode = e.getKeyCode();

		switch( keyCode ) {
		//방향키 좌우 구분
		case KeyEvent.VK_LEFT:
			this.dx = 0;
			isMoving = false;
			break;
		case KeyEvent.VK_RIGHT:
			this.dx = 0;
			isMoving = false;
			break;		
		}
	}

}

