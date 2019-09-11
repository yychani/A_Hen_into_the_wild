package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
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
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import inSeongJo.aHenIntoTheWild.controller.Stage03Manager;
import inSeongJo.aHenIntoTheWild.model.vo.User;

public class Stage03 extends JPanel {

	private MainFrame mf;
	private Image background = new ImageIcon("images/stage03_image/background2.png").getImage();
	private Graphics g;
	private Stage03 s03;
	private Media media = new Media();

	User user;

	private int time;
	private int lockTime = 1000;
	private int[] rate = new int[4];
	private int x;
	private int y;
	private int growthLevel;
	private int[][] iniRate = { { 100, 100, 0, 0 }, { 50, 90, 20, 0 }, { 60, 60, 30, 0 } }; // 초기세팅값 : 포만감, 청결도, 피로도,

	
	private String str = "";
	private boolean riceBl, bathBl, playBl, loveBl, bedBl;
	private boolean goOrStop = true;
	private boolean timePause = true;
	private Stage03Manager sm = new Stage03Manager();
	private Image fullImage = new ImageIcon("images/stage03_image/fullImage.png").getImage().getScaledInstance(188, 25,
			Image.SCALE_SMOOTH);
	private Image cleanImage = new ImageIcon("images/stage03_image/cleanImage.png").getImage().getScaledInstance(188,
			25, Image.SCALE_SMOOTH);
	private Image tiredImage = new ImageIcon("images/stage03_image/tiredImage.png").getImage().getScaledInstance(188,
			25, Image.SCALE_SMOOTH);
	private Image growthImage = new ImageIcon("images/stage03_image/growthRate.png").getImage().getScaledInstance(40,
			243, Image.SCALE_SMOOTH);
	private Image mouse = null;
	private Image gameOverImg = new ImageIcon("images/gameOver.png").getImage().getScaledInstance(850, 
			190, Image.SCALE_SMOOTH);
	private Image gameClearImg = new ImageIcon("images/clear.png").getImage().getScaledInstance(830, 
			250, Image.SCALE_SMOOTH);

	public Stage03(MainFrame mf, int level, User user) {

		this.user = user;

		growthLevel = level;
		s03 = this;
		this.setName("Stage3");
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
		this.setBackground(new Color(0, 0, 0, 0));
		mf.add(this);

		rate[0] = iniRate[growthLevel][0]; // 초기 포만감
		rate[1] = iniRate[growthLevel][1]; // 초기 청결도
		rate[2] = iniRate[growthLevel][2]; // 초기 피로도
		rate[3] = iniRate[growthLevel][3]; // 성장도
		
		//현재 레벨표시
		String levelStr = "현재 레벨 : " + (growthLevel + 1) + " Lv";
		JLabel levelLabel = new JLabel(levelStr);
		levelLabel.setBounds(5, 5, 200, 20);
		levelLabel.setFont(new Font("바탕", Font.BOLD, 15));
		add(levelLabel);
		
		
		
		//메인스테이지로 돌아가는 버튼
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
				goOrStop = false;
//				media.soundStop();
				ChangePanel.changePanel(mf, s03, new MainStage(mf, user, new Media()));
			}
		});
		
		//게임오버되는 상황 체크하는 쓰레드
		Thread th3 = new Thread(new Runnable() {
			private Media media2 = new Media();
			
			@Override
			public void run() {
				media2.sound("stage03bgm");

				while (goOrStop) {
					if (rate[0] <= 20) {
						goOrStop = false;
						//media2.soundStop();
						media.sound("gameover");
						int score = sm.scoreCalc(growthLevel, rate[3], time); // 레벨, 성장도, 시간
						sm.scoreChange(score, user);
						String str = sm.rankingMethod(user, score);
						JOptionPane.showMessageDialog(null, "초록이가 배고파 죽었습니다. \n당신의 점수 : " + score + "\n" + str);
						ChangePanel.changePanel(mf, s03, new MainStage(mf, user, new Media()));

					} else if (rate[1] <= 20) {
						goOrStop = false;
						//media2.soundStop();
						media.sound("gameover");
						int score = sm.scoreCalc(growthLevel, rate[3], time); // 레벨, 성장도, 시간
						sm.scoreChange(score, user);
						String str = sm.rankingMethod(user, score);
						JOptionPane.showMessageDialog(null, "초록이가 전염병에 감염되어 죽었습니다. \n당신의 점수 : " + score + "\n" + str);
						ChangePanel.changePanel(mf, s03, new MainStage(mf, user, new Media()));

					} else if (rate[2] >= 80) {
						sm.printResult(rate[3], time);
						goOrStop = false;
						//media2.soundStop();
						media.sound("gameover");
						int score = sm.scoreCalc(growthLevel, rate[3], time); // 레벨, 성장도, 시간
						sm.scoreChange(score, user);
						String str = sm.rankingMethod(user, score);
						JOptionPane.showMessageDialog(null, "초록이가 과로사로 죽었습니다. \n당신의 점수 : " + score + "\n" + str);
						ChangePanel.changePanel(mf, s03, new MainStage(mf, user, new Media()));
					} else if (rate[3] >= 100) {
						goOrStop = false;
						if (growthLevel < 2) {
							gameOverImg = null;
							
							JOptionPane.showMessageDialog(null, "초록이가 " + (growthLevel + 1) + "번째 성장했어요!");
							ChangePanel.changePanel(mf, s03, new Stage03(mf, ++growthLevel, user));							
						} else {
							gameOverImg = gameClearImg;
							//media2.soundStop();
							media.sound("clear");
							sm.printResult(rate[3], time);
							int score = sm.scoreCalc(growthLevel, rate[3], time); // 레벨, 성장도, 시간
							sm.scoreChange(score, user);
							String str = sm.rankingMethod(user, score);
							JOptionPane.showMessageDialog(null, "초록이가 드디어 어른이 되었네요! \n당신의 점수 : " + score + "\n" + str);
							growthLevel = 0;
							ChangePanel.changePanel(mf, s03, new MainStage(mf, user, new Media()));
						}
					}

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				media2.soundStop();
				
			}

		});
		th3.setDaemon(true);
		th3.start();
		
		JTextField hiden = new JTextField();
		hiden.setBounds(150, 5, 50, 20);
		hiden.setBorder(null);
		hiden.setBackground(null);
		add(hiden);
		
		hiden.requestFocus();
		hiden.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String hStr = hiden.getText();
				System.out.println(hStr);
				
				if(hStr.equals("holdset10")) {
				lockTime = 10;
			} else if(hStr.equals("holdset1000")) {
				lockTime = 1000;
			} else if(hStr.equals("timehold")) {
				timePause = false;
			} else if(hStr.equals("start")) {
				timePause = true;
			}
				
				
			}
		});
		
	
		//밥먹이기 아이콘 설정
		Image riceIcon = new ImageIcon("images/stage03_image/riceIcon.png").getImage().getScaledInstance(100, 102,
				Image.SCALE_SMOOTH);
		Image riceIconPressed = new ImageIcon("images/stage03_image/riceIcon_pressed.png").getImage().getScaledInstance(100, 102,
				Image.SCALE_SMOOTH);
		JButton ricebutton = new JButton(new ImageIcon(riceIcon));
		ricebutton.setBounds(20, 620, 100, 102);
		ricebutton.setBorderPainted(false);
		ricebutton.setContentAreaFilled(false);
		ricebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		ricebutton.setToolTipText("포만감 +5, 피로도+5, 성장도+2");
		ricebutton.setPressedIcon(new ImageIcon(riceIconPressed));
		add(ricebutton);
		//밥먹이기 눌렸을 때, 아이콘 생성되도록Bl을 true만듦(paint메소드에서 적용됨)
		ricebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				riceBl = true;
				media.sound("clickFX");
				System.out.println("밥먹이기 버튼 눌림");

			}
		});
		
		//씻기기 아이콘 
		Image bathIcon = new ImageIcon("images/stage03_image/bathIcon.png").getImage().getScaledInstance(100, 102,
				Image.SCALE_SMOOTH);
		Image bathIconPressed = new ImageIcon("images/stage03_image/bathIcon_pressed.png").getImage().getScaledInstance(100, 102,
				Image.SCALE_SMOOTH);
		JButton bathbutton = new JButton(new ImageIcon(bathIcon));
		bathbutton.setBounds(140, 620, 100, 102);
		bathbutton.setBorderPainted(false);
		bathbutton.setContentAreaFilled(false);
		bathbutton.setPressedIcon(new ImageIcon(bathIconPressed));
		bathbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bathbutton.setToolTipText("청결도 +5, 피로도+5");
		add(bathbutton);
		//밥먹이기 눌렸을 때, 아이콘 생성되도록Bl을 true만듦(paint메소드에서 적용됨)
		bathbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bathBl = true;
				media.sound("clickFX");
				System.out.println("씻기기 버튼 눌림");

			}
		});

		//놀아주기 아이콘 
		Image playIcon = new ImageIcon("images/stage03_image/playIcon.png").getImage().getScaledInstance(100, 102,
				Image.SCALE_SMOOTH);
		Image playIconPressed = new ImageIcon("images/stage03_image/playIcon_pressed.png").getImage().getScaledInstance(100, 102,
				Image.SCALE_SMOOTH);
		JButton playbutton = new JButton(new ImageIcon(playIcon));
		playbutton.setBounds(260, 620, 100, 102);
		playbutton.setBorderPainted(false);
		playbutton.setContentAreaFilled(false);
		playbutton.setPressedIcon(new ImageIcon(playIconPressed));
		playbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		playbutton.setToolTipText("청결도-5, 성장도+5");
		add(playbutton);

		//애정주기 아이콘
		Image loveIcon = new ImageIcon("images/stage03_image/loveIcon.png").getImage().getScaledInstance(100, 102,
				Image.SCALE_SMOOTH);
		Image loveIconPressed = new ImageIcon("images/stage03_image/loveIcon_pressed.png").getImage().getScaledInstance(100, 102,
				Image.SCALE_SMOOTH);
		JButton lovebutton = new JButton(new ImageIcon(loveIcon));
		lovebutton.setBorderPainted(false);
		lovebutton.setContentAreaFilled(false);
		lovebutton.setBounds(380, 620, 100, 102);
		lovebutton.setPressedIcon(new ImageIcon(loveIconPressed));
		lovebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lovebutton.setToolTipText("성장도+5");
		add(lovebutton);
		
		//잠자기 아이콘
		Image bedIcon = new ImageIcon("images/stage03_image/bedIcon.png").getImage().getScaledInstance(100, 102,
				Image.SCALE_SMOOTH);
		Image bedIconPressed = new ImageIcon("images/stage03_image/bedIcon_pressed.png").getImage().getScaledInstance(100, 102,
				Image.SCALE_SMOOTH);
		JButton bedbutton = new JButton(new ImageIcon(bedIcon));
		bedbutton.setBorderPainted(false);
		bedbutton.setContentAreaFilled(false);
		bedbutton.setBounds(500, 620, 100, 102);
		bedbutton.setPressedIcon(new ImageIcon(bedIconPressed));
		bedbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bedbutton.setToolTipText("피로도0, 포만감-10");
		add(bedbutton);

		// 초록이가 움직이는 쓰레드
		Greeny gr = new Greeny(this);
		Thread th = new Thread(gr);
		th.setDaemon(true);
		th.start();

		// Image dungji = new ImageIcon("images/stage03_image/dungji.png").getImage();
		// JLabel dungjiLabel = new JLabel(new ImageIcon(dungji));
		// dungjiLabel.setBounds(346, 435, 326, 193);
		// add(dungjiLabel);

		// 성장지수 (포만감, 청결도, 피로도)
		JLabel fullRateText = new JLabel("포만감");
		fullRateText.setBounds(30, 40, 100, 20);
		fullRateText.setFont(new Font("바탕", Font.PLAIN, 20));
		fullRateText.setForeground(Color.BLUE);
		add(fullRateText);
		JLabel fullRatePercent = new JLabel(rate[0] + "%");
		fullRatePercent.setBounds(220, 70, 100, 20);
		fullRatePercent.setFont(new Font("바탕", Font.PLAIN, 20));
		fullRatePercent.setForeground(Color.BLUE);
		add(fullRatePercent);

		JLabel cleanRateText = new JLabel("청결도");
		cleanRateText.setBounds(30, 112, 100, 20);
		cleanRateText.setFont(new Font("바탕", Font.PLAIN, 20));
		cleanRateText.setForeground(Color.BLUE);
		add(cleanRateText);
		JLabel cleanRatePercent = new JLabel(rate[1] + "%");
		cleanRatePercent.setBounds(220, 142, 100, 20);
		cleanRatePercent.setFont(new Font("바탕", Font.PLAIN, 20));
		cleanRatePercent.setForeground(Color.BLUE);
		add(cleanRatePercent);

		JLabel tiredRateText = new JLabel("피로도");
		tiredRateText.setBounds(30, 185, 100, 20);
		tiredRateText.setFont(new Font("바탕", Font.PLAIN, 20));
		tiredRateText.setForeground(Color.RED);
		add(tiredRateText);
		JLabel tiredRatePercent = new JLabel(rate[2] + "%");
		tiredRatePercent.setBounds(220, 214, 100, 20);
		tiredRatePercent.setFont(new Font("바탕", Font.PLAIN, 20));
		tiredRatePercent.setForeground(Color.RED);
		add(tiredRatePercent);
		
		JLabel growthRateText = new JLabel("성장도");
		growthRateText.setBounds(920, 50, 100, 20);
		growthRateText.setFont(new Font("바탕", Font.BOLD, 20));
		growthRateText.setForeground(Color.GREEN);
		add(growthRateText);
		JLabel growthRatePercent = new JLabel(rate[3] + "%");
		growthRatePercent.setBounds(940, 345, 120, 20);
		growthRatePercent.setFont(new Font("바탕", Font.BOLD, 20));
		growthRatePercent.setForeground(Color.GREEN);
		add(growthRatePercent);

		addMouseMotionListener(new MyEvent()); //위치 확인

		//놀아주기 버튼 눌렀을 때, 지수들(rate)변화하게 만듦
		playbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playBl = true;
				media.sound("clickFX");
				System.out.println("놀아주기 버튼 눌림");
				// 놀아주는 미니게 팝업창 만들기!
				rate = sm.playingMethod(rate);
				fullRatePercent.setText(rate[0] + "%");
				cleanRatePercent.setText(rate[1] + "%");
				tiredRatePercent.setText(rate[2] + "%");
				growthRatePercent.setText(rate[3] + "%");
				playBl = false;

			}
		});
		
		//애정주기 버튼 눌렀을 때, 지수들(rate)변화하게 만듦
		lovebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loveBl = true;
				media.sound("clickFX");
				System.out.println("애정주기 버튼 눌림");
				LoadingClass lc = new LoadingClass(lovebutton, s03, lockTime);
				Thread th4 = new Thread(lc);
				th4.setDaemon(true);
				th4.start();

				rate = sm.lovingMethod(rate);
				fullRatePercent.setText(rate[0] + "%");
				cleanRatePercent.setText(rate[1] + "%");
				tiredRatePercent.setText(rate[2] + "%");
				growthRatePercent.setText(rate[3] + "%");
				//loveBl = false;

			}
		});
		
		//잠자기 버튼 눌렀을 때, 지수들(rate)변화하게 만듦
		bedbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bedBl = true;
				media.sound("clickFX");
				System.out.println("잠자기 버튼 눌림");
				LoadingClass lc2 = new LoadingClass(bedbutton, s03, lockTime);
				Thread th5 = new Thread(lc2);
				th5.setDaemon(true);
				th5.start();

				rate = sm.sleepingMethod(rate);
				fullRatePercent.setText(rate[0] + "%");
				cleanRatePercent.setText(rate[1] + "%");
				tiredRatePercent.setText(rate[2] + "%");
				growthRatePercent.setText(rate[3] + "%");
				//bedBl = false;

			}
		});

		
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) { // 아이콘이 활성화 되고, 초록이를 향해 눌러야 함!
				if (riceBl == true) { //rice 아이콘이 활성화 되었을 때!
					rate = sm.eatingMethod(rate, e.getX(), e.getY(), growthLevel);
					// System.out.println("어딘가 눌러씀");
					fullRatePercent.setText(rate[0] + "%");
					cleanRatePercent.setText(rate[1] + "%");
					tiredRatePercent.setText(rate[2] + "%");
					growthRatePercent.setText(rate[3] + "%");
					riceBl = false;
				}
				if (bathBl == true) { //bath 아이콘이 활성화 되었을 때!
					rate = sm.cleaningMethod(rate, e.getX(), e.getY(), growthLevel);
					// System.out.println("어딘가 눌러씀");
					fullRatePercent.setText(rate[0] + "%");
					cleanRatePercent.setText(rate[1] + "%");
					tiredRatePercent.setText(rate[2] + "%");
					growthRatePercent.setText(rate[3] + "%");
					bathBl = false;
				}
			}

		});
		
		// 지수들 줄어드는 쓰레드
		Thread th2 = new Thread(new Runnable() {

			@Override
			public void run() {
				time = 0;
				JLabel label = new JLabel("Timer : " + time);
				label.setBounds(940, 0, 100, 50);
				label.setFont(new Font("바탕", Font.PLAIN, 15));

				s03.add(label);

				while (goOrStop) {
					time++;

					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (timePause) {
						if (time % 2 == 0) {
							label.setText("Timer : " + time / 2);

						}
						rate[0]--;
						rate[1]--;
						rate[2]++;
						g.drawImage(fullImage, 25, 69, (int) (188 * (double) rate[0] / 100.0), 25, null); // 포만감표시
						fullRatePercent.setText(rate[0] + "%");

						g.drawImage(cleanImage, 25, 140, (int) (188 * (double) rate[1] / 100.0), 25, null); // 청결도표시
						cleanRatePercent.setText(rate[1] + "%");

						g.drawImage(tiredImage, 25, 211, (int) (188 * (double) rate[2] / 100.0), 25, null); // 피로도표시
						tiredRatePercent.setText(rate[2] + "%");
					}
					
					
				}

			}

		});
		th2.setDaemon(true);
		th2.start();

	}

	@Override
	public void paint(Graphics g) {
		this.g = g;
		g.drawImage(background, 0, 0, null); // 배경을 그려줌
		paintComponents(g); // component를 그려줌
		
		g.drawImage(fullImage, 25, 69, (int) (188 * (double) rate[0] / 100.0), 25, null); // 포만감표시
		g.drawImage(cleanImage, 25, 140, (int) (188 * (double) rate[1] / 100.0), 25, null); // 청결도표시
		g.drawImage(tiredImage, 25, 211, (int) (188 * (double) rate[2] / 100.0), 25, null); // 피로도표시
		g.drawImage(growthImage, 934, 330, 40, (int) (-243 * (double) rate[3] / 100.0), null); // 성장도표시

		if (goOrStop == false) {
			if(rate[0] <= 20 || rate[1] <= 20 || rate[2] >= 80 || rate[3] >= 100) { //게임이 끝나는 조건
				g.drawImage(gameOverImg, 80, 80, 850, 190, null);
			}
		} 
		
		if (riceBl == true) {
			this.addMouseMotionListener(new MouseAdapter() {
				@Override
				public void mouseMoved(MouseEvent e) { // 숫가락이 마우스에 올려짐
					mouse = new ImageIcon("images/stage03_image/spoon.png").getImage();
					x = e.getX();
					y = e.getY();
				}
			});
		} else if (bathBl == true) {
			this.addMouseMotionListener(new MouseAdapter() {
				@Override
				public void mouseMoved(MouseEvent e) { // 샤워기가 마우스에 올려짐
					mouse = new ImageIcon("images/stage03_image/showerHead.png").getImage();
					x = e.getX();
					y = e.getY();
				}
			});
		} else { // default : 마우스에 아무것도 올려지지 않은 상태
			mouse = null;
		}
		
		g.drawImage(mouse, x - 20, y - 30, 100, 68, null);
		this.repaint(); // 다시 그려준다는 의미?

	}

	public int getGrowthLevel() {
		return growthLevel;
	}

	public boolean isGoOrStop() {
		return goOrStop;
	}

	public void setGoOrStop(boolean goOrStop) {
		this.goOrStop = goOrStop;
	}

	public void setGrowthLevel(int growthLevel) {
		this.growthLevel = growthLevel;
	}

	public int getLockTime() {
		return lockTime;
	}

	public void setLockTime(int lockTime) {
		this.lockTime = lockTime;
	}
	
	public boolean isLoveBl() {
		return loveBl;
	}

	public void setLoveBl(boolean loveBl) {
		this.loveBl = loveBl;
	}

	public boolean isBedBl() {
		return bedBl;
	}

	public void setBedBl(boolean bedBl) {
		this.bedBl = bedBl;
	}
}

class MyEvent extends MouseMotionAdapter {

	public void display(String s, MouseEvent e) {
		System.out.println(s + ": ( " + e.getX() + ", " + e.getY() + " )");
	}

	@Override
	public void mouseMoved(java.awt.event.MouseEvent e) {
		display("mouse Moved", e);
	}

}

class Greeny implements Runnable {
	private JPanel jp;
	private boolean gr = false;
	private Stage03 s03;
	private int time;

	Greeny() {
	}

	Greeny(Stage03 s03) {
		this.s03 = s03;
	}

	@Override
	public void run() {
		boolean goOrStop = true;
		int growthLevel = s03.getGrowthLevel();
		int gx, gy, width, height;
		Image green1;
		Image green2;


		
		if (growthLevel == 0) {
			gx = 412;
			gy = 270;
			width = 200;
			height = 279;
			green1 = new ImageIcon("images/stage03_image/greenE1.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
			green2 = new ImageIcon("images/stage03_image/greenE2.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

		} else if (growthLevel == 1) {
			gx = 380; //312;
			gy = 230; //180;
			width = 320; //400;
			height = 337; //421;
			green1 = new ImageIcon("images/stage03_image/greenELv2a.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
			green2 = new ImageIcon("images/stage03_image/greenELv2b.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		} else {
			gx = 320;
			gy = 230;
			width = 382; //477;
			height = 420; //525;
			green1 = new ImageIcon("images/stage03_image/greenELv3a.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
			green2 = new ImageIcon("images/stage03_image/greenELv3b.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		}

		JLabel greenLabel = new JLabel(new ImageIcon(green1));
		greenLabel.setBounds(gx, gy, width, height);
		s03.add(greenLabel);

		JLabel greenLabel2 = new JLabel(new ImageIcon(green2));
		greenLabel2.setBounds(gx, gy, width, height);

		while (goOrStop) {
			if (gr == false) {
				s03.remove(greenLabel);
				s03.add(greenLabel2);
				gr = true;
			} else {
				s03.remove(greenLabel2);
				s03.add(greenLabel);
				gr = false;
			}

			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			goOrStop = s03.isGoOrStop();
		}

	}

}

class LoadingClass implements Runnable {
	JButton jb;
	JPanel jp;
	boolean goOrStop = true;

	int lockTime;
	Image lovelove = new ImageIcon("images/stage03_image/love.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	Image sleeping = new ImageIcon("images/stage03_image/sleep.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

	LoadingClass() {
	}

	LoadingClass(JButton jb, JPanel jp, int lockTime) {
		this.jb = jb;
		this.jp = jp;
		this.lockTime = lockTime;
	}

	@Override
	public void run() {
		JLabel loveLabel = new JLabel(new ImageIcon(lovelove));
		JLabel sleepLabel = new JLabel(new ImageIcon(sleeping));
		
		loveLabel.setBounds(350, 200, 80, 80);
		sleepLabel.setBounds(580, 170, 100, 100);


		//마우스클릭을 할수 없도록
		jb.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		jb.setEnabled(false);
		
		if(((Stage03)jp).isLoveBl()) { //잠기는 동안 love 아이콘 보이도록
			((Stage03)jp).add(loveLabel);
			((Stage03)jp).setLoveBl(false);
		}
		
		if(((Stage03)jp).isBedBl()) { //잠기는 동안 sleeping 아이콘 보이도록
			((Stage03)jp).add(sleepLabel);
			((Stage03)jp).setBedBl(false);
		}

		try { //클릭했을때 1초동안 잠금
			Thread.sleep(lockTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		((Stage03)jp).remove(loveLabel);
		((Stage03)jp).remove(sleepLabel);
		jb.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jb.setEnabled(true);

	}

}
