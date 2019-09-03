package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import inSeongJo.aHenIntoTheWild.controller.Stage03Manager;
import inSeongJo.aHenIntoTheWild.model.vo.User;

public class Stage03 extends JPanel{
	private MainFrame mf;
	private Image background = new ImageIcon("images/stage03_image/background2.png").getImage();
	private Graphics g;
	private Stage03 s03;
	
	User user;
	
	private int time;
	private int[] rate = new int[4];
	private int x;
	private int y;
	private int growthLevel;
	private int[][] iniRate = {{100, 100, 0, 0}, {50, 90, 20, 0}, {60, 60, 30, 0}};
	private String str = "";
	private boolean riceBl, bathBl, playBl, loveBl, bedBl;
	private boolean goOrStop = true;
	private Stage03Manager sm = new Stage03Manager();
	private Image fullImage = new ImageIcon("images/stage03_image/fullImage.png").getImage().getScaledInstance(188, 25, Image.SCALE_SMOOTH);
	private Image cleanImage = new ImageIcon("images/stage03_image/cleanImage.png").getImage().getScaledInstance(188, 25, Image.SCALE_SMOOTH);
	private Image tiredImage = new ImageIcon("images/stage03_image/tiredImage.png").getImage().getScaledInstance(188, 25, Image.SCALE_SMOOTH);
	private Image growthImage = new ImageIcon("images/stage03_image/growthRate.png").getImage().getScaledInstance(40, 243, Image.SCALE_SMOOTH);
	private Image mouse = null;

	public Stage03(MainFrame mf, int level, User user) {
		
		this.user = user;
		
		growthLevel = level;
		s03 = this;
		this.setName("Stage3");
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
		this.setBackground(new Color(0,0,0,0));
		mf.add(this);

		rate[0] = iniRate[growthLevel][0];	//임의로 설정한 포만감
		rate[1] = iniRate[growthLevel][1];	//임의로 설정한 청결도
		rate[2] = iniRate[growthLevel][2];	//임의로 설정한 피로도
		rate[3] = iniRate[growthLevel][3];	//성장도
		
		String levelStr = "현재 레벨 : " + (growthLevel+1) + " Lv";
		JLabel levelLabel = new JLabel(levelStr);
		levelLabel.setBounds(5, 5, 200, 20);
		levelLabel.setFont(new Font("바탕",Font.BOLD, 15));
		add(levelLabel);
		
		Image riceIcon = new ImageIcon("images/stage03_image/riceIcon.png").getImage().getScaledInstance(100, 102, Image.SCALE_SMOOTH);
		JButton ricebutton = new JButton(new ImageIcon(riceIcon));
		ricebutton.setBounds(20, 620, 100, 102);
		ricebutton.setBorderPainted(false);
		ricebutton.setContentAreaFilled(false);
		ricebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		ricebutton.setToolTipText("포만감 +5, 피로도+5, 성장도+2");
		add(ricebutton);
		
		ricebutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				riceBl = true;
				System.out.println("밥먹이기 버튼 눌림");	
				
				
			}
		});
		
		
		Image bathIcon = new ImageIcon("images/stage03_image/bathIcon.png").getImage().getScaledInstance(100, 102, Image.SCALE_SMOOTH);
		JButton bathbutton = new JButton(new ImageIcon(bathIcon));
		bathbutton.setBounds(140, 620, 100, 102);
		bathbutton.setBorderPainted(false);
		bathbutton.setContentAreaFilled(false);
		bathbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bathbutton.setToolTipText("청결도 +5, 피로도+5");
		add(bathbutton);
		
		bathbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bathBl = true;
				System.out.println("씻기기 버튼 눌림");
				
			}
		});
		
		Image playIcon = new ImageIcon("images/stage03_image/playIcon.png").getImage().getScaledInstance(100, 102, Image.SCALE_SMOOTH);
		JButton playbutton = new JButton(new ImageIcon(playIcon));
		playbutton.setBounds(260, 620, 100, 102);
		playbutton.setBorderPainted(false);
		playbutton.setContentAreaFilled(false);
		playbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		playbutton.setToolTipText("청결도-5, 성장도+5");
		add(playbutton);
		
		
		
		Image loveIcon = new ImageIcon("images/stage03_image/loveIcon.png").getImage().getScaledInstance(100, 102, Image.SCALE_SMOOTH);
		JButton lovebutton = new JButton(new ImageIcon(loveIcon));
		lovebutton.setBorderPainted(false);
		lovebutton.setContentAreaFilled(false);
		lovebutton.setBounds(380, 620, 100, 102);
		lovebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lovebutton.setToolTipText("성장도+5");
		add(lovebutton);
		
		
		
		Image bedIcon = new ImageIcon("images/stage03_image/bedIcon.png").getImage().getScaledInstance(100, 102, Image.SCALE_SMOOTH);
		JButton bedbutton = new JButton(new ImageIcon(bedIcon));
		bedbutton.setBorderPainted(false);
		bedbutton.setContentAreaFilled(false);
		bedbutton.setBounds(500, 620, 100, 102);
		bedbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bedbutton.setToolTipText("피로도0, 포만감-10");
		add(bedbutton);
		
		
		//초록이가 움직이는 쓰레드
		Greeny gr = new Greeny(this);
		Thread th = new Thread(gr);
		th.setDaemon(true);
		th.start();

//		Image dungji = new ImageIcon("images/stage03_image/dungji.png").getImage();
//		JLabel dungjiLabel = new JLabel(new ImageIcon(dungji));
//		dungjiLabel.setBounds(346, 435, 326, 193);
//		add(dungjiLabel);
		
		//성장지수 (포만감, 청결도, 피로도)
		JLabel fullRateText = new JLabel("포만감");
		fullRateText.setBounds(30, 40, 100, 20);
		fullRateText.setFont(new Font("바탕",Font.PLAIN, 20));
		fullRateText.setForeground(Color.BLUE);
		add(fullRateText);
		JLabel fullRatePercent = new JLabel(rate[0] + "%");
		fullRatePercent.setBounds(220, 70, 100, 20);
		fullRatePercent.setFont(new Font("바탕",Font.PLAIN, 20));
		fullRatePercent.setForeground(Color.BLUE);
		add(fullRatePercent);
		
		JLabel cleanRateText = new JLabel("청결도");
		cleanRateText.setBounds(30, 112, 100, 20);
		cleanRateText.setFont(new Font("바탕",Font.PLAIN, 20));
		cleanRateText.setForeground(Color.BLUE);
		add(cleanRateText);
		JLabel cleanRatePercent = new JLabel(rate[1] + "%");
		cleanRatePercent.setBounds(220, 142, 100, 20);
		cleanRatePercent.setFont(new Font("바탕",Font.PLAIN, 20));
		cleanRatePercent.setForeground(Color.BLUE);
		add(cleanRatePercent);
		
		
		JLabel tiredRateText = new JLabel("피로도");
		tiredRateText.setBounds(30, 185, 100, 20);
		tiredRateText.setFont(new Font("바탕",Font.PLAIN, 20));
		tiredRateText.setForeground(Color.RED);
		add(tiredRateText);
		JLabel tiredRatePercent = new JLabel(rate[2] + "%");
		tiredRatePercent.setBounds(220, 214, 100, 20);
		tiredRatePercent.setFont(new Font("바탕",Font.PLAIN, 20));
		tiredRatePercent.setForeground(Color.RED);
		add(tiredRatePercent);
		
		
		
		//addMouseMotionListener(new MyEvent()); //위치 확인

		playbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				playBl = true;
				System.out.println("놀아주기 버튼 눌림");
				//놀아주는 미니게 팝업창 만들기!
				rate = sm.playingMethod(rate);
				fullRatePercent.setText(rate[0] + "%");
				cleanRatePercent.setText(rate[1] + "%");
				tiredRatePercent.setText(rate[2] + "%");
				playBl = false;
				
				
			}
		});
		
		lovebutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loveBl = true;
				System.out.println("애정주기 버튼 눌림");
				LoadingClass lc = new LoadingClass(lovebutton, s03);
				Thread th4 = new Thread(lc);
				th4.setDaemon(true);
				th4.start();
				
				rate = sm.lovingMethod(rate);
				fullRatePercent.setText(rate[0] + "%");
				cleanRatePercent.setText(rate[1] + "%");
				tiredRatePercent.setText(rate[2] + "%");
				loveBl = false;

			}
		});
		
		bedbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bedBl = true;
				System.out.println("잠자기 버튼 눌림");
				LoadingClass lc2 = new LoadingClass(bedbutton, s03);
				Thread th5 = new Thread(lc2);
				th5.setDaemon(true);
				th5.start();
				
				rate = sm.sleepingMethod(rate);
				fullRatePercent.setText(rate[0] + "%");
				cleanRatePercent.setText(rate[1] + "%");
				tiredRatePercent.setText(rate[2] + "%");
				bedBl = false;
				
			}
		});
		
		addMouseListener(new MouseAdapter() {
			
			@Override 
			public void mouseClicked(MouseEvent e) { //아이콘이 활성화 되고, 초록이를 향해 눌러야 함!
				if(riceBl == true) { // 
					rate = sm.eatingMethod(rate, e.getX(), e.getY());
					//System.out.println("어딘가 눌러씀");	
					fullRatePercent.setText(rate[0] + "%");
					cleanRatePercent.setText(rate[1] + "%");
					tiredRatePercent.setText(rate[2] + "%");
					riceBl = false;
				}
				if(bathBl == true) { 
					rate = sm.cleaningMethod(rate, e.getX(), e.getY());
					//System.out.println("어딘가 눌러씀");	
					fullRatePercent.setText(rate[0] + "%");
					cleanRatePercent.setText(rate[1] + "%");
					tiredRatePercent.setText(rate[2] + "%");
					bathBl = false;
				}
			}
			
		});
		
		Thread th2 = new Thread(new Runnable() {

			@Override
			public void run() {
				time = 0;
				JLabel label = new JLabel("Timer : " + time);
				label.setBounds(940, 0, 100, 50);
				label.setFont(new Font("바탕",Font.PLAIN, 15));

				s03.add(label);
				
				
				while(goOrStop) {
					time++;

					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (time%2 == 0) {
						label.setText("Timer : " + time/2);

					}
					rate[0]--;
					rate[1]--;
					rate[2]++;
					g.drawImage(fullImage, 25, 69, (int)(188*(double)rate[0]/100.0), 25, null); // 포만감표시 
					fullRatePercent.setText(rate[0] + "%");
					
					g.drawImage(cleanImage, 25, 140, (int)(188*(double)rate[1]/100.0), 25, null); // 청결도표시 
					cleanRatePercent.setText(rate[1] + "%");
					
					g.drawImage(tiredImage, 25, 211, (int)(188*(double)rate[2]/100.0), 25, null); // 피로도표시 
					tiredRatePercent.setText(rate[2] + "%");
				}
				
			}
			
		});
		th2.setDaemon(true);
		th2.start();
		
		Thread th3 = new Thread(new Runnable() {

			@Override
			public void run() {
				
				while(goOrStop) {
					if(rate[0] <= 20) {
						goOrStop = false;
						
						int score = sm.scoreCalc(growthLevel, rate[3], time); //레벨, 성장도, 시간
						sm.scoreChange(score, user);
						JOptionPane.showMessageDialog(null, "초록이가 배고파 죽었습니다. \n 최종 스코어 : " +score);
						
						ChangePanel.changePanel(mf, s03, new Stage03After(mf, user, score));
						
					} else if(rate[1] <= 20) {
						goOrStop = false;
						int score = sm.scoreCalc(growthLevel, rate[3], time); //레벨, 성장도, 시간
						sm.scoreChange(score, user);
						JOptionPane.showMessageDialog(null, "초록이가 전염병에 감염되어 죽었습니다. \n최종 스코어 : " + score);
						
						ChangePanel.changePanel(mf, s03, new Stage03After(mf, user, score));

					} else if(rate[2] >=50) {
						sm.printResult(rate[3], time);
						goOrStop = false;
						int score = sm.scoreCalc(growthLevel, rate[3], time); //레벨, 성장도, 시간
						sm.scoreChange(score, user);
						JOptionPane.showMessageDialog(null, "초록이가 과로사로 죽었습니다. \n최종 스코어 : " + score);

						//ChangePanel.changePanel(mf, s03, new MainStage(mf, user));
						ChangePanel.changePanel(mf, s03, new Stage03After(mf, user, score));
					} else if(rate[3] >= 90) {
						goOrStop = false;
						if (growthLevel <3) {
							JOptionPane.showMessageDialog(null, "초록이가 " + (growthLevel+1) +"번째 성장했어요!");
							ChangePanel.changePanel(mf, s03, new Stage03(mf, ++growthLevel, user));
						} else {
							sm.printResult(rate[3], time);
							int score = sm.scoreCalc(growthLevel, rate[3], time); //레벨, 성장도, 시간
							sm.scoreChange(score, user);
							JOptionPane.showMessageDialog(null, "초록이가 드디어 어른이 되었네요! \n최종 스코어 : " +score);
							growthLevel = 0;
							
							ChangePanel.changePanel(mf, s03, new Stage03After(mf, user, score));
						}
					}
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
			
		});
		th3.setDaemon(true);
		th3.start();
		
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		this.g = g;
		g.drawImage(background, 0, 0, null); //배경을 그려줌
		paintComponents(g); //component를 그려줌
		
		g.drawImage(fullImage, 25, 69, (int)(188*(double)rate[0]/100.0), 25, null); // 포만감표시 
		g.drawImage(cleanImage, 25, 140, (int)(188*(double)rate[1]/100.0), 25, null); // 청결도표시 
		g.drawImage(tiredImage, 25, 211, (int)(188*(double)rate[2]/100.0), 25, null); // 피로도표시 
		g.drawImage(growthImage, 934, 330, 40, (int)(-243*(double)rate[3]/100.0), null); // 성장도표시 
		
		

		
		if (riceBl == true) {
			this.addMouseMotionListener(new MouseAdapter() {
				@Override
				public void mouseMoved(MouseEvent e) {
					//str = "밥먹자 초록아~";
					mouse = new ImageIcon("images/stage03_image/spoon.png").getImage();
					x = e.getX();
					y = e.getY();
					//repaint();
				}
			});
		} else if(bathBl == true) {
			this.addMouseMotionListener(new MouseAdapter() {
				@Override
				public void mouseMoved(MouseEvent e) {
					//str = "씻자 초록아~";
					mouse = new ImageIcon("images/stage03_image/showerHead.png").getImage();
					x = e.getX();
					y = e.getY();
					//repaint();
				}
			});
		}else{
			//str = "";
			mouse = null;
			//spoon = null;
			//repaint();
		}
		
		//g.drawString(str, x, y);
		g.drawImage(mouse, x-20, y-30, 100, 68, null);
		this.repaint(); //다시 그려준다는 의미?
		
		
		
	}
	
	
	class MyEvent extends MouseMotionAdapter{
		
		public void display(String s, MouseEvent e) {
			System.out.println(s + ": ( " + e.getX() + ", " + e.getY() + " )");
		}
		
		@Override
		public void mouseMoved(java.awt.event.MouseEvent e) {
			display("mouse Moved", e);
		}
		
	}
	
}


class Greeny implements Runnable{
	private JPanel jp;
	private boolean gr = false;

	Greeny(){}

	Greeny(JPanel jp){
		this.jp = jp;
	}

	@Override
	public void run() {
		Image green1 = new ImageIcon("images/stage03_image/greenE1.png").getImage();
		JLabel greenLabel = new JLabel(new ImageIcon(green1));
		greenLabel.setBounds(412, 270, 200, 279);
		jp.add(greenLabel);

		Image green2 = new ImageIcon("images/stage03_image/greenE2.png").getImage();
		JLabel greenLabel2 = new JLabel(new ImageIcon(green2));
		greenLabel2.setBounds(412, 270, 200, 279);
		
		boolean goOrStop = true;


		while(goOrStop) {
			//System.out.println("실행");
			if (gr == false) {
				jp.remove(greenLabel);
				jp.add(greenLabel2);
				gr = true;
			} else {
				jp.remove(greenLabel2);
				jp.add(greenLabel);
				gr = false;
			}
			
			

			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}


class LoadingClass implements Runnable{
	JButton jb;
	JPanel jp;
	boolean goOrStop = true;
	LoadingClass(){	}
	
	LoadingClass(JButton jb, JPanel jp){
		this.jb = jb;
		this.jp = jp;
	}
	
	@Override
	public void run() {
		
		//System.out.println("Th4가 실행됨");
		jb.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		jb.setEnabled(false);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jb.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jb.setEnabled(true);

	}
	
}






