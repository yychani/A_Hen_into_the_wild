package inSeongJo.aHenIntoTheWild.model.vo;

import inSeongJo.aHenIntoTheWild.view.Stage01;

public class Stage01_Thread extends Thread{
	Stage01 stage01 = new Stage01();
	@Override
	public void run() {
		while(true) {
			//GamePanel�� �÷��̾� ��ǥ ���� 
			stage01.move();				
			stage01.repaint();//Gamestage01�� ȭ�� ����

			try { //�ʹ� ���� ���Ƽ� õõ�� ������
				sleep(20);
			} catch (InterruptedException e) {}

		}

	}
}
