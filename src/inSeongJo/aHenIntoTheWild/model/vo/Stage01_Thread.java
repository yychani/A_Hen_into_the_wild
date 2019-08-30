package inSeongJo.aHenIntoTheWild.model.vo;

import inSeongJo.aHenIntoTheWild.view.Stage01;

public class Stage01_Thread extends Thread{
	Stage01 stage01 = new Stage01();
	Stage01_jump jumpThread;
	Stage01_Drop dropThread;
	int cnt;
	public Stage01_Thread() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		while(true) {
			System.out.println("������ ������");
			//GamePanel�� �÷��̾� ��ǥ ���� 
			stage01.move();				
			stage01.repaint();//Gamestage01�� ȭ�� ����
			stage01.setCnt(stage01.getCnt()+1);
			try { //�ʹ� ���� ���Ƽ� õõ�� ������
				sleep(50);
			} catch (InterruptedException e) {}

			jumpThread = new Stage01_jump(stage01, stage01.isJump);
			dropThread = new Stage01_Drop(stage01, stage01.isDrop);
			if(stage01.isJump) {
				jumpThread.start();
			}else {
				jumpThread.setJumping(false);
			}
			if(stage01.isDrop) {
				dropThread.start();
			}else {
				dropThread.setDroping(false);
			}
		}
	}


	public Stage01 getStage01() {
		return stage01;
	}
	public void setStage01(Stage01 stage01) {
		this.stage01 = stage01;
	}
}