package inSeongJo.aHenIntoTheWild.model.vo;

import javax.swing.JOptionPane;

import inSeongJo.aHenIntoTheWild.view.Stage01;

public class Stage01_Thread extends Thread{
	Stage01 stage01;
	Stage01_jump jumpThread;
	Stage01_Drop dropThread;
	int cnt;
	private boolean isOver = true;
	public Stage01_Thread(Stage01 stage01) {
		this.stage01 = stage01;
	}
	@Override
	public void run() {
		while(isOver) {
			System.out.println("쓰레드 실행중");
			//GamePanel의 플레이어 좌표 변경 
			stage01.move();				
			stage01.enemyProcess();
			stage01.repaint();//Gamestage01의 화면 갱신
			stage01.setCnt(stage01.getCnt()+1);
			try { //너무 빨리 돌아서 천천히 돌도록
				sleep(50);
			} catch (InterruptedException e) {}

			jumpThread = new Stage01_jump(stage01, stage01.isJump());
			//			dropThread = new Stage01_Drop(stage01, stage01.isDrop);
			if(stage01.isJump()) {
				jumpThread.start();
			}else {
				jumpThread.setJumping(false);
			}
		}
		String[] strarr = stage01.checkRanking();
		String str = "";
		for(int i = 0; i < strarr.length; i++) {
			str += (strarr[i] + "\n");
		}
		System.out.println(str);
		if(!stage01.isGameOver()) {
			JOptionPane.showMessageDialog(null, str, "Stage 1 랭킹", JOptionPane.PLAIN_MESSAGE);
		}
	}


	public boolean isOver() {
		return isOver;
	}
	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}
	public Stage01 getStage01() {
		return stage01;
	}
	public void setStage01(Stage01 stage01) {
		this.stage01 = stage01;
	}
}
