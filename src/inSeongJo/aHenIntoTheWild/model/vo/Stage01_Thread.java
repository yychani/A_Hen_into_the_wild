package inSeongJo.aHenIntoTheWild.model.vo;

import inSeongJo.aHenIntoTheWild.view.Stage01;

public class Stage01_Thread extends Thread{
	Stage01 stage01 = new Stage01();
	Stage01_jump jumpThread;
	int cnt;
	public Stage01_Thread() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		while(true) {
			//GamePanel의 플레이어 좌표 변경 
			stage01.move();				
			stage01.repaint();//Gamestage01의 화면 갱신
			stage01.setCnt(stage01.getCnt()+1);
			try { //너무 빨리 돌아서 천천히 돌도록
				sleep(50);
			} catch (InterruptedException e) {}
			
			jumpThread = new Stage01_jump(stage01, stage01.isJump);
			if(stage01.isJump) {
				jumpThread.start();
			}else {
				jumpThread.setJumping(false);
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
