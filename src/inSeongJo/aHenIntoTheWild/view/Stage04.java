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
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sun.jna.platform.linux.LibC.Sysinfo;

import inSeongJo.aHenIntoTheWild.controller.UserManager;
import inSeongJo.aHenIntoTheWild.model.dao.RankingDao;
import inSeongJo.aHenIntoTheWild.model.vo.MediaThread;
import inSeongJo.aHenIntoTheWild.model.vo.Ranking;
import inSeongJo.aHenIntoTheWild.model.vo.User;

// 프레임 생성을 위한 JFrame 상속
//키보드 이벤트 처리를 위한 KeyListener를 상속
//스레드를 돌리기 위한 Runnable 상속
public class Stage04 extends JPanel implements KeyListener, Runnable {
	Stage04Enemy se;
	Stage04_Point sp,sp2;
	User user;

	//bgm
	private Media media3 = new Media();

	private MainFrame mf;
	private MainStage ms;
	private JPanel stage04;
	Stage04 stage04page = this;
	// 캐릭터의 좌표 변수
	private int x = 100;
	private int y = 100;
	private int w;
	private int h;
	private int life = 5;
	private int sCnt = 0;
	private int sCnt2 = 0;
	private int time = 30;
	private int score;

	private boolean ds = false;
	private boolean stop = true;
	private boolean thS = true;
	private boolean gameOver = false;
	private boolean gameClear = false;

	Timer t = new Timer();


	int cnt;
	int cnt2;
	int cnt3;

	// 키보드 입력 처리를 위한 변수
	boolean KeyUp = false;
	boolean KeyDown = false;
	boolean KeyLeft = false;
	boolean KeyRight = false;
	private int backX = 0;
	// private Image empty = new ImageIcon()
	private Image die = new ImageIcon("images/Images/emptyLife.png").getImage().getScaledInstance(20, 20, 0);
	private Image overImage = new ImageIcon("images/Images/gameOver2.png").getImage().getScaledInstance(800, 200, 0);
	private Image clearImage = new ImageIcon("images/Images/gameClear.png").getImage().getScaledInstance(800, 220, 0);
	private Image chorok = new ImageIcon("images/Images/chorok6.gif").getImage().getScaledInstance(150, 170, 0);
	private Image enemy = new ImageIcon("images/Images/enemy5.gif").getImage().getScaledInstance(200, 200, 0);
	private Image star = new ImageIcon("images/Images/star2.png").getImage().getScaledInstance(40, 40, 0);
	private Image star2 = new ImageIcon("images/Images/star.png").getImage().getScaledInstance(40, 40, 0);
	private Image homeButton = new ImageIcon("images/Images/homebutton.png").getImage().getScaledInstance(70, 70, 0);
	private ArrayList<Image> life_Array;

	JButton homeB = new JButton(new ImageIcon(homeButton));


	// 스레드 생성
	Thread th;
//	Thread th3 = new Thread();
	Image BG = new ImageIcon("images/Images/lastsky.png").getImage()/*.getScaledInstance(1024, 768, Image.SCALE_SMOOTH)*/;

	ArrayList<Stage04Enemy> Enemy_List = new ArrayList<>();
	ArrayList<Stage04_Point> Point_List = new ArrayList<>();
	ArrayList<Stage04_Point> Point_List2 = new ArrayList<>();

	public Stage04(MainFrame mf, User user) {
		this.mf = mf;
		this.user = user;
		this.setBounds(0, 0, 1600, 768);
		this.setLayout(null);
		stage04 = this;
		mf.add(this);
		start();

		life_Array= new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			life_Array.add(new ImageIcon("images/Images/life.png").getImage().getScaledInstance(20, 20, 0));
		}

		homeB.setBounds(950, 0, 70, 70);
		homeB.setBorderPainted(false);
		homeB.setContentAreaFilled(false);
		add(homeB);

		homeB.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				thS = false;
				media3.soundStop();
				ChangePanel.changePanel(mf, stage04, new MainStage(mf, user, new Media()));

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

		});

		JLabel lifeText = new JLabel();
		lifeText.setBounds(500, 500, 500, 50);
		lifeText.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		add(lifeText);


		setVisible(true);
		this.setFocusable(true);
		this.addKeyListener(this);
	}

	public void start() {
//		addKeyListener(this); // 키보드 이벤트 실행
		th = new Thread(this);
		th.start(); // 스레드 실행
		Thread th2 = new Thread(t);
		th2.start();
	}
	@Override
	public void run() {
		try { // 예외옵션 설정으로 에러 방지
			media3.sound("stage04bgm");
			while (thS) { // while 문으로 무한 루프 시키기
				KeyProcess(); // 키보드 입력처리를 하여 x,y 갱신
				EnemyProcess();
				pointProcess();
				pointProcess2();
				repaint(); // 갱신된 x,y값으로 이미지 새로 그리기
				Thread.sleep(20); // 20 milli sec 로 스레드 돌리기
				cnt++;
				cnt2++;
				cnt3++;
			}
		} catch (Exception e) {
		}
		if(gameClear == true) {
			System.out.println("왜안돼???");
			String[] strr = checkRanking();
			String str = "";
			for(int i = 0; i < strr.length; i++) {
				str += (strr[i] + "\n");
			}
			System.out.println(str);
			//메세지창이 어떤 Frame에서 보여지게 될 것인지 지정, 보통null을 사용
			JOptionPane.showMessageDialog(null, str, "Stage 4 랭킹", JOptionPane.PLAIN_MESSAGE);
		}
	}
	//모든 스윙 컴포넌트는 자신의 모양을 그리는paintComponent() 메소드를 보유
	//스윙컴포넌트가 자신의 모양을  그리는 메소드
	//JPanel클래스를 상속, 밑에 메소드를 오버라이딩
	@Override
	public void paintComponent(Graphics g) {

		g.drawImage(BG, 0, 0, this);
		g.drawImage(chorok, x, y, this);
		drawEnemy(g);
		drawPoint(g);
		drawPoint2(g);
		drawLife(g);
		gameButton(g);
		this.repaint();
	}

	public void EnemyProcess() {
		for (int i = 0; i < Enemy_List.size(); ++i) {
			se = (Enemy_List.get(i));
			if (se.getX() <= -60) {
				Enemy_List.remove(i);
			} else {
				se.move();
			}
			if (collision(x - w, y - h - 15, se.getX(), se.getY(), chorok, enemy)) {
				if(life == 0) {
					gameOver = true;
					System.out.println("life : " + life);
				} else {
					life--;
					System.out.println("stop : " + life);
				}
				Enemy_List.remove(i);
			}
		}
		// cnt가 이 될때마다 적생성
		if (cnt % 50 == 0) {
			se = new Stage04Enemy(950, ((int) (Math.random() * 630) - 75));
			Enemy_List.add(se);
		}
		if (cnt % 80 == 0) {
			se = new Stage04Enemy(950, ((int) (Math.random() * 630) - 75));
			Enemy_List.add(se);
		}
		if(cnt % 130 == 0) {
			se = new Stage04Enemy(950, ((int) (Math.random() * 630) - 75));
			Enemy_List.add(se);
		
		}
	}
	public void pointProcess() {
		for (int i = 0; i < Point_List.size(); ++i) {
			sp = (Point_List.get(i));
			if (sp.getX() <= -60) {
				Point_List.remove(i);
			} else {
				sp.move();
			}
			if (Math.abs((x + chorok.getWidth(null) / 2) - (sp.getX() + star.getWidth(null) / 2)) < (star.getWidth(null) / 2
					+ chorok.getWidth(null) / 2 - 40)
					&& Math.abs((y + chorok.getHeight(null) / 2)
							- (sp.getY() + star.getHeight(null) / 2)) < (star.getHeight(null) / 2 + chorok.getHeight(null) / 2
									- 40)) {
				Point_List.remove(i);
				sCnt++;
				System.out.println("sCnt : " + sCnt);
			}else {
			}
		}

		if (cnt2 % 70 == 0) {

			sp = new Stage04_Point((int) (Math.random() * 890) - 10, -80);
			Point_List.add(sp);
		}
		if (cnt2 % 219 == 0) {
			sp = new Stage04_Point((int) (Math.random() * 890) - 10, -80);
			Point_List.add(sp);
		}      

		if (cnt2 % 313 == 0) {
			sp = new Stage04_Point((int) (Math.random() * 890) - 10, -80);
			Point_List.add(sp);
		}
		if (cnt2 % 130 == 0) {
			sp = new Stage04_Point((int) (Math.random() * 890) - 10, -80);
			Point_List .add(sp);

		}
		if (cnt2 % 179 == 0) {
			sp = new Stage04_Point((int) (Math.random() * 890) - 10, -80);
			Point_List .add(sp);

		}

	}

	public void pointProcess2() {
		for (int i = 0; i < Point_List2.size(); ++i) {
			sp2 = (Point_List2.get(i));

			if (sp2.getX() <= -60) {
				Point_List2.remove(i);

			} else {
				sp2.move();
			}
			if (Math.abs((x + chorok.getWidth(null) / 2) - (sp2.getX() + star2.getWidth(null) / 2)) < (star2.getWidth(null) / 2
					+ chorok.getWidth(null) / 2 - 40)
					&& Math.abs((y + chorok.getHeight(null) / 2)
							- (sp2.getY() + star2.getHeight(null) / 2)) < (star2.getHeight(null) / 2 + chorok.getHeight(null) / 2
									- 40)) {
				Point_List2.remove(i);
				sCnt2++;
				System.out.println("sCnt2 : " + sCnt2);
			}else {
			}

		}

		if (cnt2 % 90 == 0) {
			sp2 = new Stage04_Point((int) (Math.random() * 890) - 10, -80);
			Point_List2.add(sp2);
		}
		if (cnt2 % 170 == 0) {
			sp2 = new Stage04_Point((int) (Math.random() * 890) - 10, -80);
			Point_List2.add(sp2);
		}
	}

	public void drawEnemy(Graphics g) {
		for (int i = 0; i < Enemy_List.size(); ++i) {
			se = Enemy_List.get(i);

			g.drawImage(enemy, se.getX(), se.getY(), this);
		}
	}

	public void drawPoint(Graphics g) {
		for (int i = 0; i < Point_List.size(); ++i) {
			int random = (int) (Math.random() * 100) + 1;
			sp = Point_List.get(i);

			g.drawImage(star, sp.getX(), sp.getY(), this);

		}
	}

	public void drawPoint2(Graphics g) {
		for (int i = 0; i < Point_List2.size(); ++i) {
			int random = (int) (Math.random() * 100) + 1;
			sp2 = Point_List2.get(i);

			g.drawImage(star2, sp2.getX(), sp2.getY(), this);
		}
	}

	// -10 <= x <= 880 -75 <= y <= 590
	public void drawLife(Graphics g) {
		if(life == 5) {
			for(int i = 0; i < life_Array.size(); i++) {
				g.drawImage(life_Array.get(i), 830 + (i * 40), 700, this);
			}

		}else if(life == 4){
			life_Array.set(4, die);
			for(int i = 0; i < life_Array.size(); i++) {
				g.drawImage(life_Array.get(i), 830 + (i * 40), 700, this);
			}

		}else if(life == 3) {
			life_Array.set(4, die);
			life_Array.set(3, die);
			for(int i = 0; i < life_Array.size(); i++) {
				g.drawImage(life_Array.get(i), 830 + (i * 40), 700, this);
			}
		}else if(life == 2) {
			life_Array.set(4, die);
			life_Array.set(3, die);
			life_Array.set(2, die);
			for(int i = 0; i < life_Array.size(); i++) {
				g.drawImage(life_Array.get(i), 830 + (i * 40), 700, this);
			}

		} else if(life == 1) {
			life_Array.set(4, die);
			life_Array.set(3, die);
			life_Array.set(2, die);
			life_Array.set(1, die);
			for(int i = 0; i < life_Array.size(); i++) {
				g.drawImage(life_Array.get(i), 830 + (i * 40), 700, this);
			}

		}else if(life == 0 ){
			life_Array.set(4, die);
			life_Array.set(3, die);
			life_Array.set(2, die);
			life_Array.set(1, die);
			life_Array.set(0, die);
			//         stop = false;
			gameOver = true;
			thS = false;
		}
	}
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
			System.out.println("x : " + x + " y : " + y);
			KeyDown = true;
			break;
		case KeyEvent.VK_LEFT:
			System.out.println("x : " + x + " y : " + y);
			KeyLeft = true;
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("x : " + x + " y : " + y);
			KeyRight = true;
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
		}
	}

	public void keyTyped(KeyEvent e) {
	}
	// 키보드가 타이핑 될때 이벤트 처리하는 곳

	// -10 <= x <= 880 -75 <= y <= 590
	public void KeyProcess() {
		if (KeyUp == true) {
			if (y <= -75) {
				y -= 0;
			} else {
				y -= 7;
			}
		} else if (KeyDown == true) {
			if (y >= 590) {
				y += 0;
			} else {
				y += 7;
			}
		} else if (KeyLeft == true) {
			if (x <= -10) {
				x -= 0;
			} else {
				x -= 7;
			}
		} else if (KeyRight == true) {
			if (x >= 880) {
				x += 0;
			} else {
				x += 7;
			}
		}
	}
	class Timer implements Runnable {
		@Override
		public void run() {
			JLabel Mytimer = new JLabel("" + time);
			Mytimer.setBounds(25, 610, 50, 50);
			Mytimer.setFont(new Font("맑은 고딕", Font.BOLD, 30));
			Mytimer.setForeground(Color.YELLOW);
			add(Mytimer);
			while (thS) {
				if(time == 0) {
					gameClear = true;
					stop = false;
					thS = false;
				}else {
					time--;
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
	public void gameButton(Graphics g) {

		score = (sCnt* 50) - (sCnt2 * 20);

		Image retry = new ImageIcon("images/Images/retry2.png").getImage().getScaledInstance(150, 90, 0);
		Image main = new ImageIcon("images/Images/main2.png").getImage().getScaledInstance(150, 90, 0);
		JButton retryB = new JButton(new ImageIcon(retry));
		JButton goMain = new JButton(new ImageIcon(main));
		JButton goMain2 = new JButton(new ImageIcon(main));


		//외곽선을 없애준다.
		retryB.setBorderPainted(false);
		goMain.setBorderPainted(false);
		goMain2.setBorderPainted(false);
		//버튼의 내용영역 채우지 않기함
		retryB.setContentAreaFilled(false);
		goMain.setContentAreaFilled(false);
		goMain2.setContentAreaFilled(false);
		//버튼이 선택되었을때 생기는 테두리 사용안함
		retryB.setFocusPainted(false);
		goMain.setFocusPainted(false);
		goMain2.setFocusPainted(false);

		retryB.setBounds(50, 470, 380, 280);
		goMain.setBounds(550, 470, 430, 300);
		goMain2.setBounds(550, 470, 430, 300);
		//커서바꾸기
		retryB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		goMain.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		goMain2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


		retryB.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				media3.soundStop();
				ChangePanel.changePanel(mf, stage04, new Stage04(mf, user));
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
		goMain.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				media3.soundStop();
				ChangePanel.changePanel(mf, stage04, new MainStage(mf,user, new Media()));
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
		goMain2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				media3.soundStop();
					stage04.setVisible(false);
					new VideoTest(mf, "ending", user, new Ending(mf, user, media3), media3);
					MediaThread mt = new MediaThread(ms, 41);
					user.setStage4Video(true);
					mt.start();
					mf.remove(stage04);
			}
		});
		if(gameOver == true && gameClear == false) {
			g.drawImage(overImage, 120, 200, null);
			add(retryB);
			add(goMain);
			System.out.println("star1 : " + sCnt);
			System.out.println("star2 : " + sCnt2);
			thS = false;
		}else if(gameClear == true && gameOver == false) {
			g.drawImage(clearImage, 120, 150, null);
			g.drawImage(star, 350, 400,null);
			g.drawImage(star2, 350, 450,null);
			add(retryB);
			JLabel scoreText = new JLabel(score + "");
			JLabel scoreText2 = new JLabel("점수 : ");
			JLabel cStar = new JLabel( " :   " + sCnt + " 개 ");
			JLabel cStar2 = new JLabel( " :   " + sCnt2 + " 개");

			scoreText2.setBounds(300, 500, 200, 70);
			scoreText.setBounds(550, 500, 200, 70);
			cStar.setBounds(415, 385, 200, 70);
			cStar2.setBounds(415, 435, 200, 70);

			scoreText.setFont(new Font("맑은 고딕", Font.BOLD, 40));
			scoreText2.setFont(new Font("맑은 고딕", Font.BOLD, 40));
			cStar.setFont(new Font("맑은 고딕", Font.BOLD, 40));
			cStar2.setFont(new Font("맑은 고딕", Font.BOLD, 40));
			scoreText.setForeground(Color.WHITE);
			scoreText2.setForeground(Color.WHITE);
			cStar.setForeground(Color.WHITE);
			cStar2.setForeground(Color.WHITE);
			add(scoreText);
			add(scoreText2);
			add(cStar);
			add(cStar2);
			if(score > user.getStage4Score()) {
				user.setStage4Score(score);	
			}
			add(retryB);
			add(goMain2);
		} 

	}
	public String[] checkRanking() {
		UserManager um = new UserManager();
		RankingDao rd = new RankingDao();
		
		um.rankingMethod(user, score, 4);
		ArrayList<Ranking> rankList = rd.readRankingList(4);

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
				System.out.println(rStr[i]);
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


}