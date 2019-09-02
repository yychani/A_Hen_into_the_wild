package inSeongJo.aHenIntoTheWild.view;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public class Stage01_infoPage extends JPanel{
	private MainFrame mf;
	private JPanel stage01Info;
	public Stage01_infoPage(MainFrame mf) {
		this.mf = mf;
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
		stage01Info = this;
		mf.add(this);
	}
	
}
