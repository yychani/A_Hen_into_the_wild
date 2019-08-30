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
	private int width, height;//패널 사이즈 가져오기
	private int x, y, w, h;//xy : 플레이어의 중심 좌표 / wh : 이미지 절반폭;
	private int ipX, ipY; // ipXY 잎싹이 왼쪽 모서리 좌표
	private int dx = 0, dy = 0;//플레이어 이미지의 이동속도, 이동방향
	private Image stage01Background, leftWall, rightWall, ipssagJump, ipssagJumpR, stage01Footrest;
	private Image[] ipssagMoving, ipssagStanding, ipssagMovingR, ipssagStandingR;
	boolean isMoving = false, isRight = false;
	public boolean isJump = false, isDrop = false;
	int cnt = 0;

	public Stage01() {
		//GUI 관련 프로그램의 편의를 위해 만들어진 도구상자(Toolkit) 객체 
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		stage01Background = toolkit.getImage("images/Stage01_background.png");//배경 이미지
		leftWall = toolkit.getImage("images/left_wall.png");
		rightWall = toolkit.getImage("images/right_wall.png");//벽 이미지
		stage01Footrest = toolkit.getImage("images/Stage01_footrest.png"); // 발판 이미지
		
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
			stage01Footrest = stage01Footrest.getScaledInstance(150, 100, Image.SCALE_SMOOTH);
			
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

			x = width/2;//플레이어의 좌표 계산
			y = height - 100;
			w = 64;
			h = 64;
			ipX = x - w;
			ipY = y - h - 20;

		}			

		//이곳에 화가객체가 있으므로 그림 그리는 작업은 무조건 여기서			

		g.drawImage(stage01Background, 0, 0, this);//배경 그리기
		g.drawImage(leftWall, 0, 68, this);//왼쪽 벽 그리기
		g.drawImage(rightWall, width - 300, 68, this);//왼쪽 벽 그리기
		g.drawImage(stage01Footrest, width / 2, 650, this); // 발판 그리기
		
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

	public void move() { //플레이어 움직이기(좌표 변경)
		cnt++;
		x += dx;
		y += dy;
		int ddy = 0;
		ddy += 1;
		//플레이어 좌표가 화면 밖으로 나가지 않도록 // 나중에 벽으로 수정
		if(x < w + 280) x = w + 280; // 왼쪽 벽 넘지 못하게
		if(x > width - w - 280) x = width - w - 280; // 오른쪽 벽 넘지 못하게
		System.out.println(isDrop);
		System.out.println("x = " + (x - w) + " y = " + (y - h));
		if((x == -280 && y == 0) ||(x - w) >= 422 && (x - w) <=  602 && (y - h) <= 569) {
			dy = 0; // 첫번째 발판
			isDrop = false;
		}else {
			isDrop = true;
			System.out.println(isDrop);
			
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
			isJump = true;
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
