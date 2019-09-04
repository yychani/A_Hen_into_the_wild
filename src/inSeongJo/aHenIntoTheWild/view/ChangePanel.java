package inSeongJo.aHenIntoTheWild.view;

import javax.swing.JPanel;

public class ChangePanel {
	public static void changePanel(MainFrame mf, JPanel op, JPanel np) {
		mf.remove(op); // 올드패널 지우기
		mf.add(np);
		mf.repaint(); // 새로고침
	}

}
