package inSeongJo.aHenIntoTheWild.model.vo;

import inSeongJo.aHenIntoTheWild.view.Stage01;

public class Stage01_Drop extends Thread{
	boolean droping = false;
	
	Stage01 stage01;
	
	public Stage01_Drop(Stage01 stage01, boolean droping) {
		this.stage01 = stage01;
		this.droping = droping;
	}
	
	@Override
	public void run() {
		int droppy = 0;
		while(droping) {
			
			jumpTimeArrived(droppy);
			

			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			droppy++;
//			if(stage01.getIpY() <= 569) {
//				jumpTimeEnded();
//			}
		}
		

	}
	public boolean isDroping() {
		return droping;
	}

	public void setDroping(boolean droping) {
		this.droping = droping;
	}

	public void jumpTimeArrived(int droppy) {
		stage01.addY(droppy);
	}

	public void jumpTimeEnded() {
		droping = false;
		stage01.setDrop(false);
	}
}
