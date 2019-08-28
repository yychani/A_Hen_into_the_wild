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
	private int width, height;//�г� ������ ��������
	private int x, y, w, h;//xy : �÷��̾��� �߽� ��ǥ / wh : �̹��� ������;
	private int dx = 0, dy = 0;//�÷��̾� �̹����� �̵��ӵ�, �̵�����
	private Image ipssag, stage01Background;
	private Stage01_Thread s1thread;
	
	public Stage01() {
		this.setSize(1024, 768);
		//GUI ���� ���α׷��� ���Ǹ� ���� ������� ��������(Toolkit) ��ü 
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		stage01Background = toolkit.getImage("images/Stage01_background.png");//��� �̹���
		ipssag = toolkit.getImage("images/ipssag.png");//�÷��̾� �̹��� ��ü
		
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
			ipssag = ipssag.getScaledInstance(128, 128, Image.SCALE_SMOOTH);

			x = width/2;//�÷��̾��� ��ǥ ���
			y = height - 100;
			w = 64;
			h = 64;

		}			

		//�̰��� ȭ����ü�� �����Ƿ� �׸� �׸��� �۾��� ������ ���⼭			

		g.drawImage(stage01Background, 0, 0, this);//��� �׸���			
		g.drawImage(ipssag, x - w, y - h, this);//�÷��̾�
		//������� ����� ���� �����ð����� �ٽ� �׸���(re painting)
	}//paintComponent                                      



	public void move() { //�÷��̾� �����̱�(��ǥ ����)

		x += dx;
		y += dy;

		//�÷��̾� ��ǥ�� ȭ�� ������ ������ �ʵ��� // ���߿� ������ ����
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
		//������ Ű�� �������� �˾Ƴ��� 
		int keyCode = e.getKeyCode();
		System.out.println(keyCode);
		switch( keyCode ) {
		case KeyEvent.VK_LEFT:
			System.out.println("����");
			this.dx = -8; 
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("������");
			this.dx = 8;
			break;
		case KeyEvent.VK_UP:
			System.out.println("����");
			this.dy = -8;
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("�Ʒ���");
			this.dy = 8;
			break;				
		}
		//����Ű 4�� ����				
	}
	@Override
	public void keyReleased(KeyEvent e) {
		//������ Ű�� �������� �˾Ƴ��� 
		int keyCode = e.getKeyCode();

		switch( keyCode ) {
		case KeyEvent.VK_LEFT:
			this.dx = 0; //���� getsetter ��������
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
		//����Ű 4�� ����
	}
}
