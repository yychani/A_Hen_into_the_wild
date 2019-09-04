package inSeongJo.aHenIntoTheWild.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
// ������ ������ ���� JFrame ���
//Ű���� �̺�Ʈ ó���� ���� KeyListener�� ���
//�����带 ������ ���� Runnable ���
public class Stage04 extends JPanel implements KeyListener, Runnable { 
	Stage04Enemy se;
	Stage04_Point sp;
	
	private MainFrame mf;
//	private JPanel stage04;
	// ĳ������ ��ǥ ����
	private int x = 100;
	private int y = 100; 
	private int w;
	private int h;
	private int life = 5;
	
	int cnt;
	int cnt2;

	//Ű���� �Է� ó���� ���� ����
	boolean KeyUp = false; 
	boolean KeyDown = false;
	boolean KeyLeft = false;
	boolean KeyRight = false;
	private Image chorok = new ImageIcon("images/Images/chorok.gif").getImage().getScaledInstance(150, 150, 0); 
	private Image enemy  = new ImageIcon("images/Images/bird.gif").getImage().getScaledInstance(150, 150, 0);
	private Image star = new ImageIcon("images/Images/star.png").getImage().getScaledInstance(50, 50, 0);
	

	// ������ ����
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
		addKeyListener(this); //Ű���� �̺�Ʈ ����
		th = new Thread(this);  // ������ ����
		th.start();  // ������ ����
	}


	@Override
	public void run() {
		try{ // ���ܿɼ� �������� ���� ����
			
			while(true){ // while ������ ���� ���� ��Ű��
				KeyProcess(); // Ű���� �Է�ó���� �Ͽ� x,y ����
				System.out.println("KeyProcess");

				EnemyProcess();
				System.out.println("KeyProcess");
				pointProcess();
				System.out.println("poinProcess");

				repaint(); // ���ŵ� x,y������ �̹��� ���� �׸���
				
				Thread.sleep(20); // 20 milli sec �� ������ ������ 
				
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
		//       System.out.println("��ʴϱ�~");

		for(int i = 0; i < Enemy_List.size(); ++i) {
			se = (Enemy_List.get(i));
			//			System.out.println("��...");
			if(se.getX() <= - 60) {
				Enemy_List.remove(i);

				System.out.println("�����?");
			} else {
				se.move();
			}
			if(collision (x - w, y - h - 15, se.getX(), se.getY(), chorok, enemy)) {
/*//				life--;
				System.out.println("life : " + life);*/
				Enemy_List.remove(i);

			}
		}
		
		//cnt�� 200�� �ɶ����� ������
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
		//		System.out.println("���Ⱑ������");
		for(int i = 0; i < Enemy_List.size(); ++i) {
			//Ÿ������ ���� �ʾƼ� ����ȯ���־�� �Ѵ�.
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

	//�ʷ��̿� ���� �Ÿ����� �� ���� �ݳʺ��� ���� �� ũ�ٸ� �װ��� �̹� �� ���� ��Ҵٴ� ��
	// chorok & nemy �� �Ÿ� < �ΰ� �ݳʺ��� ��
	public boolean collision(int x1, int y1, int x2, int y2, Image chorok, Image enemy) {
		boolean check = false;
		if(Math.abs((x1 + chorok.getWidth(null) / 2) - (x2 + enemy.getWidth(null) / 2)) < ( enemy.getWidth(null) / 2 + chorok.getWidth(null) / 2 - 80) 
				&& Math.abs( ( y1 + chorok.getHeight(null) / 2 )  - ( y2 + enemy.getHeight(null) / 2 ))  < ( enemy.getHeight(null)/2 + chorok.getHeight(null)/2 - 40) ){
			// �̹��� ����, ���̰� �ٷ� ����
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
			//         System.out.println("�Ʒ�");
			System.out.println("x : " + x + " y : " + y);
			KeyDown = true;
			break;
		case KeyEvent.VK_LEFT :
			//         System.out.println("����");
			System.out.println("x : " + x + " y : " + y);
			KeyLeft = true;
			break;
		case KeyEvent.VK_RIGHT :
			//         System.out.println("������");
			System.out.println("x : " + x + " y : " + y);
			KeyRight = true;
			break;
		}


	}
	public void keyReleased(KeyEvent e){
		// Ű���尡 �������ٰ� ���������� �̺�Ʈ ó���ϴ� ��


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
	// Ű���尡 Ÿ���� �ɶ� �̺�Ʈ ó���ϴ� ��

	
	//  -10 <= x <= 880    -75 <= y <= 590
	public void KeyProcess(){
		//������ ĳ���� ������ ������ ����
		//������ �޾Ƶ��� Ű���� ��������
		//Ű �Է½ø��� 5��ŭ�� �̵��� ��Ų��.

		//      System.out.println("����");
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
