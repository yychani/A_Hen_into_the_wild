package inSeongJo.aHenIntoTheWild.model.vo;

import inSeongJo.aHenIntoTheWild.view.Stage01;

public class Stage01_Thread extends Thread{
	Stage01 stage01 = new Stage01();
	@Override
	public void run() {
		while(true) {
			//GamePanel의 플레이어 좌표 변경 
			stage01.move();				
			stage01.repaint();//Gamestage01의 화면 갱신

			try { //너무 빨리 돌아서 천천히 돌도록
				sleep(20);
			} catch (InterruptedException e) {}

		}

	}
}
