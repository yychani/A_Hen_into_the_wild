package inSeongJo.aHenIntoTheWild.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import inSeongJo.aHenIntoTheWild.model.vo.Stage01_Thread;
import inSeongJo.aHenIntoTheWild.model.vo.Stage01_jump;



public class Stage01 extends JPanel implements KeyListener{
	private int width, height;//�г� ������ ��������
	private int x, y, w, h;//xy : �÷��̾��� �߽� ��ǥ / wh : �̹��� ������;
	private int ipX, ipY; // ipXY �ٽ��� ���� �𼭸� ��ǥ
	private int dx = 0, dy = 0;//�÷��̾� �̹����� �̵��ӵ�, �̵�����z
	private Image stage01Background, leftWall, rightWall, ipssagJump, ipssagJumpR;
	private Image[] ipssagMoving, ipssagStanding, ipssagMovingR, ipssagStandingR;
	private ArrayList<Image> stage01Footrest;
	boolean isMoving = false, isRight = false;
	private boolean isJump = false, isDrop = false;
	
	int cnt = 0;

	public Stage01() {
		//GUI ���� ���α׷��� ���Ǹ� ���� ������� ��������(Toolkit) ��ü 
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		stage01Background = toolkit.getImage("images/Stage01_background.png");//��� �̹���
		leftWall = toolkit.getImage("images/left_wall.png");
		rightWall = toolkit.getImage("images/right_wall.png");//�� �̹���
		stage01Footrest = new ArrayList<>();
		for(int i = 0; i < 7; i++) {
			stage01Footrest.add(toolkit.getImage("images/Stage01_footrest.png")); // ���� �̹���
		}

		ipssagStanding = new Image[2];
		ipssagStanding[0] = toolkit.getImage("images/ipssag/ipssag.png");//�÷��̾� �̹��� ���� ��ü
		ipssagStanding[1] = toolkit.getImage("images/ipssag/ipssag2.png");

		ipssagMoving = new Image[2];
		ipssagMoving[0] = toolkit.getImage("images/ipssag/ipssag.png");
		ipssagMoving[1] = toolkit.getImage("images/ipssag/ipssag_Moving.png");

		ipssagStandingR = new Image[2];
		ipssagStandingR[0] = toolkit.getImage("images/ipssag/ipssag_reverse.png");//�÷��̾� �̹��� ������ ��ü
		ipssagStandingR[1] = toolkit.getImage("images/ipssag/ipssag2_reverse.png");

		ipssagMovingR = new Image[2];
		ipssagMovingR[0] = toolkit.getImage("images/ipssag/ipssag_reverse.png");
		ipssagMovingR[1] = toolkit.getImage("images/ipssag/ipssag_Moving_reverse.png");

		ipssagJump = toolkit.getImage("images/ipssag/ipssag_Jump.png");
		ipssagJumpR = toolkit.getImage("images/ipssag/ipssag_Jump_reverse.png");
		this.setFocusable(true);
		this.addKeyListener(this);

	}
	// ������

	@Override
	protected void paintComponent(Graphics g) {
		//ȭ�鿡 ������ �۾� �ڵ�
		if( width == 0 || height == 0) { //ó�� ȣ��ÿ� ������ �Ⱥ��̴� ���� ����
			width = getWidth();
			height = getHeight();

			//������¡
			stage01Background = stage01Background.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			leftWall = leftWall.getScaledInstance(300, 700, Image.SCALE_SMOOTH);
			rightWall = rightWall.getScaledInstance(300, 700, Image.SCALE_SMOOTH);
			for(int i = 0; i < stage01Footrest.size(); i++) {
				stage01Footrest.set(i, (stage01Footrest.get(i)).getScaledInstance(150, 100, Image.SCALE_SMOOTH));
			}

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

			x = width/2;//�÷��̾��� ��ǥ ���
			y = height - 100;
			w = 64;
			h = 64;
			ipX = x - w;
			ipY = y - h - 20;

		}			

		//�̰��� ȭ����ü�� �����Ƿ� �׸� �׸��� �۾��� ������ ���⼭			

		g.drawImage(stage01Background, 0, 0, this);//��� �׸���
		g.drawImage(leftWall, 0, 68, this);//���� �� �׸���
		g.drawImage(rightWall, width - 300, 68, this);//���� �� �׸���
		g.drawImage(stage01Footrest.get(0), width / 2 - 70, 650, this); // ���� �׸���
		g.drawImage(stage01Footrest.get(1), width / 2 - 150, 570, this); // ���� �׸���
		g.drawImage(stage01Footrest.get(2), width / 2, 490, this); // ���� �׸���
		g.drawImage(stage01Footrest.get(3), width / 2 - 180, 410, this); // ���� �׸���
		g.drawImage(stage01Footrest.get(4), width / 2 - 80, 330, this); // ���� �׸���
		g.drawImage(stage01Footrest.get(5), width / 2 - 200, 250, this); // ���� �׸���
		g.drawImage(stage01Footrest.get(6), width / 2 - 50, 170, this); // ���� �׸���
		g.drawImage(stage01Footrest.get(6), width / 2 + 40, 90, this); // ���� �׸���

		drawIpssag(g);
	}//paintComponent                                      

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
		}// �÷��̾� �׸���
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

	public void move() { //�÷��̾� �����̱�(��ǥ ����)
		cnt++;
		x += dx;
		y += dy;
		int ddy = 0;
		ddy -= 10;

		//�÷��̾� ��ǥ�� ȭ�� ������ ������ �ʵ��� // ���߿� ������ ����
		if(x < w + 280 && (y - h) > 19) x = w + 280; // ���� �� ���� ���ϰ�
		if(x > width - w - 280 && (y - h) > 19) x = width - w - 280; // ������ �� ���� ���ϰ�
		System.out.println(isDrop);
		System.out.println("x = " + (x - w) + " y = " + (y - h));
		if((x == -280 && y == 0) ||(x - w) >= 380 && (x - w) <=  500 && (y - h) == 569) {
			System.out.println("�������� ���ִ�.");
			isDrop = false;
		}else if ((x - w) >= 300 && (x - w) <=  420 && (y - h) == 499){
			System.out.println("�������� ���ִ�.");
			isDrop = false;
		}else if ((x - w) >= 450 && (x - w) <=  570 && (y - h) == 419){
			System.out.println("�������� ���ִ�.");
			isDrop = false;
		}else if ((x - w) >= 270 && (x - w) <=  390 && (y - h) == 329){
			System.out.println("�������� ���ִ�.");
			isDrop = false;
		}else if ((x - w) >= 370 && (x - w) <=  490 && (y - h) == 249){
			System.out.println("�������� ���ִ�.");
			isDrop = false;
		}else if ((x - w) >= 250 && (x - w) <=  370 && (y - h) == 169){
			System.out.println("�������� ���ִ�.");
			isDrop = false;
		}else if ((x - w) >= 400 && (x - w) <=  520 && (y - h) == 99){
			System.out.println("�������� ���ִ�.");
			isDrop = false;
		}else if ((x - w) >= 490 && (x - w) <=  610 && (y - h) == 19){
			System.out.println("�������� ���ִ�.");
			isDrop = false;
		}else if ((x - w) >=  650 && (x - w) <= 900 && y - h == - 31){
			System.out.println("�������� ���ִ�.");
			isDrop = false;
		}
		else {
			System.out.println("������...");
			y -= ddy;
			isDrop = false;
		}
	}
	// move
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
		//������ Ű�� �������� �˾Ƴ��� 
		int keyCode = e.getKeyCode();

		switch( keyCode ) {
		//����Ű �¿� ����
		case KeyEvent.VK_LEFT:
			System.out.println("����");
			this.dx = -8; 
			isMoving = true;
			isRight = false;
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("������");
			this.dx = 8;
			isMoving = true;
			isRight = true;
			break;	
		case KeyEvent.VK_SPACE :
			System.out.println("����");
			isJump = true;
			break;
		}

	}
	@Override
	public void keyReleased(KeyEvent e) {
		//������ Ű�� �������� �˾Ƴ��� 
		int keyCode = e.getKeyCode();

		switch( keyCode ) {
		//����Ű �¿� ����
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
