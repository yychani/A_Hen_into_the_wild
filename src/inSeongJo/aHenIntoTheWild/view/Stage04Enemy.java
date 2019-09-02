package inSeongJo.aHenIntoTheWild.view;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Stage04Enemy extends JPanel{
	int x = 960;
	int random = (int) (Math.random() * 640) -70;
	private int y = random;
	int cnt = 1;

	ArrayList<JLabel> enemyList = new ArrayList<>();

	Image enemy = new ImageIcon("images/Images/bird.gif").getImage().getScaledInstance(150, 150, 0);
	JLabel enemyC = new JLabel(new ImageIcon(enemy));


	public Stage04Enemy() {

		//		enemyC.setBounds(x,y,200,200);
//		add(enemyC);

		//		for(int i = 0; i < 100; i++) {

		//			setLayout(null);
		//			enemyC.setBounds(x,y,200,200);
		//			add(enemyC);
		//			this.setFocusable(true);

		//			enemyList.add(enemyC);

		//		}
		//		stage4.add(enemyC);
	}

	// -70 <=y <= 570,  x -100
	//	public void EnemyProcess() {
	//
	//		System.out.println("제발");
	//
	//		for(int i = 0; i <= 100; i+=10) {
	//			try {
	//				Thread.sleep(1000);
	//				if(x <= 90) {
	//					enemyC.remove(i);
	//				}else {
	//					
	//					
	//					System.out.println(x- (int)(i*9.6));
	//					System.out.println(y);
	//					enemyC.setLocation(x- (int)(i*9.6),100);
	//					System.out.println("언제쯤 움직이지?");
	//				}
	//			} catch (InterruptedException e) {
	//				// TODO Auto-generated catch block
	//				e.printStackTrace();
	//				
	//			}
	//		}
	//		System.out.println("안움직이니?");



	/*		try {
			Thread.sleep(1000);
			for(int i = 10; i <= 100; i += 10) {
				if(x <= -90) {
					enemyC.remove(i);
					break;
				}else {
					enemyC.setLocation(x- (int) (i*9.6), y);

				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		System.out.println("쫌 움직여라");*/




}


