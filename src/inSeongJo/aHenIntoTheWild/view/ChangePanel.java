package inSeongJo.aHenIntoTheWild.view;

import javax.swing.JPanel;

public class ChangePanel {
	public static void changePanel(MainFrame mf, JPanel op, JPanel np) {
		mf.remove(op);	//�õ��г� �����
		mf.add(np); 	
		mf.repaint(); //���ΰ�ħ
	}

}
