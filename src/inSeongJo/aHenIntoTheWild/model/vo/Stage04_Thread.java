package inSeongJo.aHenIntoTheWild.model.vo;

import inSeongJo.aHenIntoTheWild.view.Stage04;
import inSeongJo.aHenIntoTheWild.view.Stage04Enemy;

//import com.kh.project.view.Stage4;
//import com.kh.project.view.stage4Enemy;

public class Stage04_Thread extends Thread{
	Stage04 stage4;
	Stage04Enemy stage4Enemy = new Stage04Enemy();

	int cnt;

	public Stage04_Thread() {}
	
	public Stage04_Thread(Stage04 stage04) {
		this.stage4 = stage04;
		System.out.println("쓰레드 시작");
	}
	
	@Override
	public void run() {
		while(true) {
			cnt++;
//			stage4.setCnt(cnt);
			//			System.out.println("쓰레드 실행중");
			stage4.move();
			System.out.println("움직임");
			stage4.EnemyProcess();
			System.out.println("적움직임");
			stage4.repaint();
			
		}
	}

	public Stage04 getStage4() {
		return stage4;
	}

	public void setStage4(Stage04 stage4) {
		this.stage4 = stage4;
	}

	public Stage04Enemy getStage4Enemy() {
		return stage4Enemy;
	}

	public void setStage4Enemy(Stage04Enemy stage4Enemy) {
		this.stage4Enemy = stage4Enemy;
	}




}
