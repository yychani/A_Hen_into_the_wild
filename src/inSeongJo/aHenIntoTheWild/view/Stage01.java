package inSeongJo.aHenIntoTheWild.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import inSeongJo.aHenIntoTheWild.model.vo.Stage01_Thread;
import inSeongJo.aHenIntoTheWild.model.vo.Stage01_jump;



public class Stage01 extends JPanel implements KeyListener{
	private Stage01_jump jump;
	private int width, height;//�г� ������ ��������
	private int x, y, w, h;//xy : �÷��̾��� �߽� ��ǥ / wh : �̹��� ������;
	private int dx = 0, dy = 0;//�÷��̾� �̹����� �̵��ӵ�, �̵�����
	private Image stage01Background, leftWall, rightWall, ipssagJump, ipssagJumpR;
	private Image[] ipssagMoving, ipssagStanding, ipssagMovingR, ipssagStandingR;
	boolean isMoving = false, isRight = false;
	public boolean isJump = false;
	int cnt = 0;

	public Stage01() {
		//GUI ���� ���α׷��� ���Ǹ� ���� ������� ��������(Toolkit) ��ü 
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		stage01Background = toolkit.getImage("images/Stage01_background.png");//��� �̹���
		leftWall = toolkit.getImage("images/left_wall.png");
		rightWall = toolkit.getImage("images/right_wall.png");//�� �̹���

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

		}			

		//�̰��� ȭ����ü�� �����Ƿ� �׸� �׸��� �۾��� ������ ���⼭			

		g.drawImage(stage01Background, 0, 0, this);//��� �׸���
		g.drawImage(leftWall, 0, 68, this);//���� �� �׸���
		g.drawImage(rightWall, width - 300, 68, this);//���� �� �׸���
		jump(g);
		if(isRight) {
			if(isJump) {
				g.drawImage(ipssagJumpR, x - w, y - h, this);
			}
			else if(isMoving) {
				if((cnt / 5 % 2) == 0){ 
					g.drawImage(ipssagMovingR[1], x - w, y - h, this);
				}else { 
					g.drawImage(ipssagMovingR[0], x - w, y - h, this); 
				}
			}else { 
				if((cnt / 5 % 2) == 0){ 
					g.drawImage(ipssagStandingR[1], x - w, y - h, this);
					try {
						Thread.sleep(125);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else { 
					g.drawImage(ipssagStandingR[0], x - w, y - h, this); 
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
				g.drawImage(ipssagJump, x - w, y - h, this);
			}
			else if(isMoving) {
				if((cnt / 5 % 2) == 0){ 
					g.drawImage(ipssagMoving[1], x - w, y - h, this);
				}else { 
					g.drawImage(ipssagMoving[0], x - w, y - h, this); 
				}
			}else { 
				if((cnt / 5 % 2) == 0){ 
					g.drawImage(ipssagStanding[1], x - w, y - h, this);
					try {
						Thread.sleep(125);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else { 
					g.drawImage(ipssagStanding[0], x - w, y - h, this); 
					try {
						Thread.sleep(125);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}// �÷��̾� �׸���
	}//paintComponent                                      

	public void addY(int add) {
		this.y+=add;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public void move() { //�÷��̾� �����̱�(��ǥ ����)
		cnt++;
		x += dx;
		y += dy;

		//�÷��̾� ��ǥ�� ȭ�� ������ ������ �ʵ��� // ���߿� ������ ����
		if(x < w + 280) x = w + 280; // ���� �� ���� ���ϰ�
		if(x > width - w - 280) x = width - w - 280; // ������ �� ���� ���ϰ�
	}
	// move
	public void jump(Graphics g) {

	}
	public boolean isJump() {
		return isJump;
	}

	public void setJump(boolean isJump) {
		this.isJump = isJump;
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
