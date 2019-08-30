package inSeongJo.aHenIntoTheWild.model.vo;

import inSeongJo.aHenIntoTheWild.view.Stage01;

public class Stage01_jump extends Thread{
	boolean jumping = false;
	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	Stage01 stage01;
	int jumpingy[]=new int[]{0, -15, -10, -6, -6, 0, 6, 6, 10, 15, 0}; 
	int jumpIdx = 0;

	public Stage01_jump(Stage01 stage01, boolean jumping) {
		this.stage01 = stage01;
		this.jumping = jumping;
	}

	public void run() {

		while(jumping && (jumpIdx < jumpingy.length)) {

			jumpTimeArrived(jumpIdx, jumpingy[jumpIdx]);

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			jumpIdx++;
		}
		jumpTimeEnded();

	}
	public void jumpTimeArrived(int jumpIdx, int jumpy) {
		stage01.addY(jumpy);
	}

	public void jumpTimeEnded() {
		jumping = false;
		stage01.setJump(false);
	}
}
