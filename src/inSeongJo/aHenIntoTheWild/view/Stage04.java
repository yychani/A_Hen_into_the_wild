package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import inSeongJo.aHenIntoTheWild.model.vo.User;

// 프레임 생성을 위한 JFrame 상속
//키보드 이벤트 처리를 위한 KeyListener를 상속
//스레드를 돌리기 위한 Runnable 상속
public class Stage04 extends JPanel implements KeyListener, Runnable {
	Stage04Enemy se;
	Stage04_Point sp,sp2;
	User user;

	private MainFrame mf;
	private JPanel stage04;
	Stage04 stage04page = this;
	// 캐릭터의 좌표 변수
	private int x = 100;
	private int y = 100;
	private int w;
	private int h;
	//   private int time;
	private int life = 5;
	private int score;
	private int score2;
	private int time = 20;
	
	
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
	private Image emptyLife = new ImageIcon("images/Images/emptyLife.png").getImage().getScaledInstance(20, 20, 0);
	private Image overImage = new ImageIcon("images/Images/gameOver2.png").getImage().getScaledInstance(800, 200, 0);
	private Image clearImage = new ImageIcon("images/Images/gameClear.png").getImage().getScaledInstance(800, 250, 0);
	private Image chorok = new ImageIcon("images/Images/chorok.gif").getImage().getScaledInstance(150, 150, 0);
	private Image enemy = new ImageIcon("images/Images/bird.gif").getImage().getScaledInstance(150, 150, 0);
	private Image star = new ImageIcon("images/Images/star2.png").getImage().getScaledInstance(40, 40, 0);
	private Image star2 = new ImageIcon("images/Images/star.png").getImage().getScaledInstance(40, 40, 0);
	private ArrayList<Image> life_Array;
	private Image retry = new ImageIcon("images/Images/retry.png").getImage().getScaledInstance(300, 300, 0);
	private Image goMain = new ImageIcon("images/Images/main.png").getImage().getScaledInstance(300, 300, 0);
	


	//   private Image[] starSum = {star, star2};

	// 스레드 생성
	Thread th;
	Image BG = new ImageIcon("images/Images/lastsky.png").getImage()/*.getScaledInstance(1024, 768, Image.SCALE_SMOOTH)*/;

	ArrayList<Stage04Enemy> Enemy_List = new ArrayList<>();
	ArrayList<Stage04_Point> Point_List = new ArrayList<>();
	ArrayList<Stage04_Point> Point_List2 = new ArrayList<>();


	public Stage04(MainFrame mf, User user) {
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


		setVisible(true);
		this.setFocusable(true);
		this.addKeyListener(this);
	}

	public void start() {
		addKeyListener(this); // 키보드 이벤트 실행
		th = new Thread(this);
		th.setDaemon(true);// 스레드 생성
		th.start(); // 스레드 실행
		//      timer.start();
		
		Thread th2 = new Thread(t);
		th2.start();

		//      pointProcess2();
	}

	@Override
	public void run() {

		try { // 예외옵션 설정으로 에러 방지

			while (thS) { // while 문으로 무한 루프 시키기
				KeyProcess(); // 키보드 입력처리를 하여 x,y 갱신
				//            System.out.println("KeyProcess");

				EnemyProcess();
				//            System.out.println("KeyProcess");

				pointProcess();
				//            System.out.println("poinProcess");
				//            pointProcess2();

				pointProcess2();

				repaint(); // 갱신된 x,y값으로 이미지 새로 그리기

				Thread.sleep(20); // 20 milli sec 로 스레드 돌리기
//				if(backX >= -2048) {
//					backX -= 1;
//				}
				cnt++;
				cnt2++;
				cnt3++;
			}
		} catch (Exception e) {
		}
		
		System.out.println("score : " + score);
		System.out.println("score2 : " + score2);


	}

	//모든 스윙 컴포넌트는 자신의 모양을 그리는paintComponent() 메소드를 보유
	//스윙컴포넌트가 자신의 모양을  그리는 메소드
	@Override
	protected void paintComponent(Graphics g) {

		g.drawImage(BG, 0, 0, this);
		g.drawImage(chorok, x, y, this);
		//      g.drawImage(star2,500,500,this);
		// g.drawImage(star, 500,500, this);

		drawEnemy(g);
		drawPoint(g);
		drawPoint2(g);
		drawLife(g);
		gameSet(g);
		//      drawPoint2(g);

		this.repaint();
	}

	private void EnemyProcess() {
		// System.out.println("계십니까~");

		for (int i = 0; i < Enemy_List.size(); ++i) {
			se = (Enemy_List.get(i));
			// System.out.println("하...");
			if (se.getX() <= -60) {
				Enemy_List.remove(i);

				//            System.out.println("여기는?");
			} else {
				se.move();
			}
			if (collision(x - w, y - h - 15, se.getX(), se.getY(), chorok, enemy)) {
				/*
				 * // life--; 
				 * System.out.println("life : " + life);
				 */
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


			se = new Stage04Enemy(855 + 100, ((int) (Math.random() * 630) - 75));
			Enemy_List.add(se);
		}

		if (cnt % 80 == 0) {

			se = new Stage04Enemy(855 + 100, ((int) (Math.random() * 630) - 75));
			Enemy_List.add(se);

		}
	}

	// -10 <= x <= 880 -75 <= y <= 590
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
				score++;
				System.out.println("score : " + score);
			}else {
			}


		}

		if (cnt2 % 70 == 0) {
			//         System.out.println("언제될꺼야?");

			sp = new Stage04_Point((int) (Math.random() * 890) - 10, -80);
			Point_List.add(sp);
		}
		if (cnt2 % 219 == 0) {
			//         System.out.println("지금되면안돼?");
			sp = new Stage04_Point((int) (Math.random() * 890) - 10, -80);
			Point_List.add(sp);
		}      

		if (cnt2 % 313 == 0) {
			//         System.out.println("내일은 될꺼야?");
			sp = new Stage04_Point((int) (Math.random() * 890) - 10, -80);
			Point_List.add(sp);
		}
		if (cnt2 % 130 == 0) {
			//         System.out.println("예~~~~~~~~~~~~");
			sp = new Stage04_Point((int) (Math.random() * 890) - 10, -80);
			Point_List .add(sp);

		}



		//      System.out.println("Score : " + score);

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
				score2++;
				System.out.println("score : " + score);
			}else {
			}


		}

		if (cnt2 % 90 == 0) {
			//         System.out.println("언제될꺼야?");

			sp2 = new Stage04_Point((int) (Math.random() * 890) - 10, -80);
			Point_List2.add(sp2);
		}
		if (cnt2 % 170 == 0) {
			//         System.out.println("언제될꺼야?");

			sp2 = new Stage04_Point((int) (Math.random() * 890) - 10, -80);
			Point_List2.add(sp2);
		}




		//      System.out.println("Score : " + score);

	}

	/*   public void pointProcess2() {

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
            score++;
            System.out.println("score : " + score);
         }else {
         }
      }

   if (cnt3 % 190 == 0) {
      System.out.println("이게왜안돼");
      sp2 = new Stage04_Point((int) (Math.random() * 890) - 10, -80);
      Point_List2.add(sp2);
      System.out.println("송기준");
   }

   }*/

	public void drawEnemy(Graphics g) {
		// System.out.println("여기가문제네");
		for (int i = 0; i < Enemy_List.size(); ++i) {
			// 타입형이 맞지 않아서 형변환해주어야 한다.
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

	//life_Array
	// -10 <= x <= 880 -75 <= y <= 590
	public void drawLife(Graphics g) {
		if(life == 5) {
			for(int i = 0; i < life_Array.size(); i++) {
				g.drawImage(life_Array.get(i), 830 + (i * 40), 700, this);
			}

		}else if(life == 4){
			life_Array.set(4, emptyLife);
			for(int i = 0; i < life_Array.size(); i++) {
				g.drawImage(life_Array.get(i), 830 + (i * 40), 700, this);
			}

		}else if(life == 3) {
			life_Array.set(4, emptyLife);
			life_Array.set(3, emptyLife);
			for(int i = 0; i < life_Array.size(); i++) {
				g.drawImage(life_Array.get(i), 830 + (i * 40), 700, this);
			}
		}else if(life == 2) {
			life_Array.set(4, emptyLife);
			life_Array.set(3, emptyLife);
			life_Array.set(2, emptyLife);
			for(int i = 0; i < life_Array.size(); i++) {
				g.drawImage(life_Array.get(i), 830 + (i * 40), 700, this);
			}

		} else if(life == 1) {
			life_Array.set(4, emptyLife);
			life_Array.set(3, emptyLife);
			life_Array.set(2, emptyLife);
			life_Array.set(1, emptyLife);
			for(int i = 0; i < life_Array.size(); i++) {
				g.drawImage(life_Array.get(i), 830 + (i * 40), 700, this);
			}
			
		}else {
			life_Array.set(4, emptyLife);
			life_Array.set(3, emptyLife);
			life_Array.set(2, emptyLife);
			life_Array.set(1, emptyLife);
			life_Array.set(0, emptyLife);
//			stop = false;
//			gameOver = true;
			for(int i = 0; i < life_Array.size(); i++) {
			g.drawImage(life_Array.get(i), 830, 700, this);
			}
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
//					thS = false;
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

	public void gameSet(Graphics g) {

//		Image retry = new ImageIcon("images/Images/retry.png").getImage().getScaledInstance(300, 300, 0);
//		Image goMain = new ImageIcon("images/Images/main.png").getImage().getScaledInstance(300, 300, 0);

		if(gameOver == true) {
			g.drawImage(overImage, 120, 200, null);
			g.drawImage(retry, 80, 200, null);
			g.drawImage(goMain, 200, 200, null);
			thS = false;;
		}else if(gameClear == true) {
			g.drawImage(clearImage, 120, 200, null);
			g.drawImage(retry, 80, 140, null);
			g.drawImage(goMain, 400, 300, null);
			thS = false;

		}

	}      
	
	public void gameButton(Graphics g) {
//		Image retry = new ImageIcon("images/Images/retry.png").getImage().getScaledInstance(300, 300, 0);
//		Image main = new ImageIcon("images/Images/main.png").getImage().getScaledInstance(300, 300, 0);
//				
//		JButton retryB = new JButton(new ImageIcon(retry));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}



}