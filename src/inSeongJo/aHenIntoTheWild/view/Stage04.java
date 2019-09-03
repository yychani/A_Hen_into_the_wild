package inSeongJo.aHenIntoTheWild.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import inSeongJo.aHenIntoTheWild.model.vo.Stage04_Thread;

//import com.kh.project.main.Main;

public class Stage04 extends JPanel implements KeyListener{
	private Stage04_Thread stage04Thread;
	private MainFrame mf;
	private JPanel Stage04;
	private int width, height; 
	private int x = 100;
	private int y = 100;  // ���ΰ��� ��ǥ
	private Image ScreenImage;
	private Graphics ScreenGraphics;
	final int FLY_U = 10;
	int cnt = 0;
	private int ddx;
	private int ddy;
	private boolean plz = true;

	private Stage04Enemy stageEnemy;
	ArrayList<JLabel> Enemy_List;
	Toolkit tk = Toolkit.getDefaultToolkit();
	// ����̹���
	Image BG = new ImageIcon("images/Images/sky2.jpg").getImage();  
	// �ʷ��� �̹���
	Image iconChorok = new ImageIcon("images/Images/chorok.gif").getImage().getScaledInstance(150, 150, 0);
	JLabel chorok = new JLabel(new ImageIcon(iconChorok));

	public Stage04(MainFrame mf) {
		this.mf = mf;

		//		Stage04 = this;

		setBounds(0, 0, 1024, 768);
		this.setName("Stage04");
		stageEnemy = new Stage04Enemy();
		//		this.add(stageEnemy);
		Enemy_List = new ArrayList<>();
		mf.add(this);
		for(int i = 0; i < 10; i++) {
			Enemy_List.add(i, stageEnemy.enemyC);
		}

		System.out.println("size : " + Enemy_List.size());

		//		Toolkit tk = Toolkit.getDefaultToolkit();
		setLayout(null);

		chorok.setBounds(x,y,200,200);
		add(chorok);


		//�̰� stage4Enemy�� �ҷ��ͼ� ����ִ� ����
		//		add(stageEnemy.enemyC);

		//		enemyC.setBounds(100,200,1000,550);
		//		add(enemyC);



		stage04Thread = new Stage04_Thread(this);
		stage04Thread.start();
		setFocusable(true);
		this.addKeyListener(new KeyAdapter()
		{ 
			public void keyPressed(KeyEvent e){
				switch(e.getKeyCode()){
				case KeyEvent.VK_UP:System.out.println("�̵��߾�� ��");

				if(chorok.getY() <= -100) {
					chorok.setLocation(chorok.getX(), chorok.getY() - 0);break;
				}else {
					chorok.setLocation(chorok.getX(), chorok.getY() - 10);
					System.out.println("X : " + chorok.getX() + ", " + "Y : " + chorok.getY());
					break;
				} case KeyEvent.VK_DOWN:
					System.out.println("�̵��߾�� �Ʒ�");
					if(chorok.getY() >= 570) {
						chorok.setLocation(chorok.getX(), chorok.getY() + 0); break;
					}else {
						chorok.setLocation(chorok.getX(), chorok.getY() + 10);
						System.out.println("X : " + chorok.getX() + ", " + "Y : " + chorok.getY());
						break;
					}
				case KeyEvent.VK_LEFT:
					System.out.println("�̵��߾�� ��");
					if(chorok.getX() <= -60) {
						chorok.setLocation(chorok.getX()-0, chorok.getY()); break;
					}else {
						chorok.setLocation(chorok.getX()-10, chorok.getY());
						System.out.println("X : " + chorok.getX() + ", " + "Y : " + chorok.getY());
						break;
					}
				case KeyEvent.VK_RIGHT:
					if(chorok.getX() >= 850) {
						chorok.setLocation(chorok.getX() + 0, chorok.getY());break;
					}else {
						chorok.setLocation(chorok.getX()+10, chorok.getY());
						//						System.out.println("X : " + chorok.getX() + ", " + "Y : " + chorok.getY());
						break;
					}
				}
			}
		});

	}
	@Override
	public void paintComponent(Graphics g) {
		ScreenImage = createImage(1024, 768);
		ScreenGraphics = ScreenImage.getGraphics();
		screenDraw(ScreenGraphics);


		//ù��° �Ű������� �Ʊ� �޾Ƶ� �̹��� ��ü, �ι�°, ����°�� x,y��ǥ��, 
		//������ �Ű������� ImageObserver��� �������̽�

		g.drawImage(ScreenImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(BG, 0, 0, null);
		//label�� �ø��ٴ� ��.

		this.repaint();
	}

	public void move() {
		chorok.setLocation(chorok.getX(), chorok.getY());
		//		System.out.println("x = " + stageEnemy.getDdx() + "  y = " + stageEnemy.getDdy());
		repaint();
	}

	public void EnemyProcess() {

		//		System.out.println("����");


		//		while(true) {
		ddx = 960;
		ddy = (int)(Math.random() *640) - 70;
		int random = new Random().nextInt(10);
		int random2 = new Random().nextInt(10);
		Enemy_List.add(new Stage04Enemy().enemyC);
		Enemy_List.add(new Stage04Enemy().enemyC);
		Enemy_List.get(random).setBounds(ddx, ddy,200,200);
		add(Enemy_List.get(random));
		Enemy_List.get(random2).setBounds(ddx, ddy,200,200);
		add(Enemy_List.get(random2));

		while(ddx >= -90) {

			try {
				Thread.sleep(5);
				//				if(ddx <= -90) {
				//					
				//				}else {
				Enemy_List.get(random).setLocation(ddx -= 1, ddy);
				Enemy_List.get(random2).setLocation(ddx -= 1, ddy);
				//					System.out.println("������ ��������?");
				//					System.out.println(ddx -= 1);
				//					System.out.println(ddy);
				//				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
		remove(Enemy_List.get(random));
		remove(Enemy_List.get(random2));
		if(random == 10) {
			plz = false;
		}
		//		}


		//		System.out.println("�ȿ����̴�?");



		/*		try {
			Thread.sleep(1000);
			for(int i = 10; i <= 100; i += 10) {
				if(x <= -90) {
					enemyC.remove(i);
					break;
				}else {
					enemyC.setLocation(x- (int) (i*9.6), y);

				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		System.out.println("�� ��������");*/

		//	}	public void EnemyProcess() {

		////		System.out.println("����");
		//
		//		for(int i = 0; i <= 100; i+=10) {
		//			try {
		//				Thread.sleep(1000);
		//				if(ddx <= -90) {
		//					enemyC.remove(i);
		//				}else {
		//					
		//					
		////					System.out.println(ddx- (int)(i*9.6));
		////					System.out.println(ddy);
		//					enemyC.setLocation(ddx- (int)(i*9.6),100);
		////					System.out.println("������ ��������?");
		//				}
		//			} catch (InterruptedException e) {
		//				// TODO Auto-generated catch block
		//				e.printStackTrace();
		//				
		//			}
		//		}
		//		System.out.println("�ȿ����̴�?");



		/*		try {
			Thread.sleep(1000);
			for(int i = 10; i <= 100; i += 10) {
				if(x <= -90) {
					enemyC.remove(i);
					break;
				}else {
					enemyC.setLocation(x- (int) (i*9.6), y);

				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		System.out.println("�� ��������");*/

	}

	//	@Override
	//	public void keyPressed(KeyEvent e) {
	//		switch(e.getKeyCode()) {
	//		case KeyEvent.VK_UP: 
	//			System.out.println("��");
	//			if(chorok.getY() <= -100) {
	//				chorok.setLocation(chorok.getX(), chorok.getY() - 0);break;
	//			}else {
	//				chorok.setLocation(chorok.getX(), chorok.getY() - 10);
	//				System.out.println("X : " + chorok.getX() + ", " + "Y : " + chorok.getY());
	//				break;
	//			}
	//
	//
	//		case KeyEvent.VK_DOWN : 
	//			if(chorok.getY() >= 570) {
	//				chorok.setLocation(chorok.getX(), chorok.getY() + 0); break;
	//			}else {
	//				chorok.setLocation(chorok.getX(), chorok.getY() + 10);
	//				System.out.println("X : " + chorok.getX() + ", " + "Y : " + chorok.getY());
	//				break;
	//			}
	//
	//		case KeyEvent.VK_LEFT:
	//			if(chorok.getX() <= -60) {
	//				chorok.setLocation(chorok.getX()-0, chorok.getY()); break;
	//			}else {
	//				chorok.setLocation(chorok.getX()-10, chorok.getY());
	//				System.out.println("X : " + chorok.getX() + ", " + "Y : " + chorok.getY());
	//				break;
	//			}
	//
	//		case KeyEvent.VK_RIGHT:
	//			if(chorok.getX() >= 850) {
	//				chorok.setLocation(chorok.getX() + 0, chorok.getY());break;
	//			}else {
	//				chorok.setLocation(chorok.getX()+10, chorok.getY());
	//				//						System.out.println("X : " + chorok.getX() + ", " + "Y : " + chorok.getY());
	//				break;
	//			}
	//		}
	//
	//	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}



	/*	public void start() {

		addKeyListener(this);

		while(true) {

			KeyProcess();
			repaint();
		}

		th = new Thread(this);
		th.start();
	}*/



}




