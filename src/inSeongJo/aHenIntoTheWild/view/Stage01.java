package inSeongJo.aHenIntoTheWild.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import inSeongJo.aHenIntoTheWild.model.vo.Stage01_Thread;



public class Stage01 extends JPanel implements KeyListener{
	private MainFrame mf;
	private int width, height;//패널 사이즈 가져오기
	private int x, y, w, h;//xy : 플레이어의 중심 좌표 / wh : 이미지 절반폭;
	private int dx = 0, dy = 0;//플레이어 이미지의 이동속도, 이동방향
	private Image ipssag, stage01Background;
	private Stage01_Thread s1thread;
	
	public Stage01() {
		this.setSize(1024, 768);
		//GUI 관련 프로그램의 편의를 위해 만들어진 도구상자(Toolkit) 객체 
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		stage01Background = toolkit.getImage("images/Stage01_background.png");//배경 이미지
		ipssag = toolkit.getImage("images/ipssag.png");//플레이어 이미지 객체
		
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
			ipssag = ipssag.getScaledInstance(128, 128, Image.SCALE_SMOOTH);

			x = width/2;//플레이어의 좌표 계산
			y = height - 100;
			w = 64;
			h = 64;

		}			

		//이곳에 화가객체가 있으므로 그림 그리는 작업은 무조건 여기서			

		g.drawImage(stage01Background, 0, 0, this);//배경 그리기			
		g.drawImage(ipssag, x - w, y - h, this);//플레이어
		//여러장면 만들기 위해 일정시간마다 다시 그리기(re painting)
	}//paintComponent                                      



	public void move() { //플레이어 움직이기(좌표 변경)

		x += dx;
		y += dy;

		//플레이어 좌표가 화면 밖으로 나가지 않도록 // 나중에 벽으로 수정
		if(x < w) x = w;
		if(x > width - w) x = width - w;
		if(y < h) y = h;
		if(y > height - h) y = height - h;
	}
	// move
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//눌러진 키가 무엇인지 알아내기 
		int keyCode = e.getKeyCode();
		System.out.println(keyCode);
		switch( keyCode ) {
		case KeyEvent.VK_LEFT:
			System.out.println("왼쪽");
			this.dx = -8; 
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("오른쪽");
			this.dx = 8;
			break;
		case KeyEvent.VK_UP:
			System.out.println("위쪽");
			this.dy = -8;
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("아래쪽");
			this.dy = 8;
			break;				
		}
		//방향키 4개 구분				
	}
	@Override
	public void keyReleased(KeyEvent e) {
		//눌러진 키가 무엇인지 알아내기 
		int keyCode = e.getKeyCode();

		switch( keyCode ) {
		case KeyEvent.VK_LEFT:
			this.dx = 0; //원랜 getsetter 만들어야함
			break;
		case KeyEvent.VK_RIGHT:
			this.dx = 0;
			break;
		case KeyEvent.VK_UP:
			this.dy = 0;
			break;
		case KeyEvent.VK_DOWN:
			this.dy = 0;
			break;				
		}
		//방향키 4개 구분
	}
}
