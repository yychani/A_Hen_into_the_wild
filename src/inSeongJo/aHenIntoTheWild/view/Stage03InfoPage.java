package inSeongJo.aHenIntoTheWild.view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import inSeongJo.aHenIntoTheWild.model.vo.User;

public class Stage03InfoPage extends JPanel{
	private MainFrame mf;
	private JPanel stage03Infopage;
	private Image infoBackGround;
	private User user;
	public Stage03InfoPage(MainFrame mf, User user) {
		this.mf = mf;
		this.user = user;
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
		stage03Infopage = this;
		mf.add(this);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		infoBackGround = toolkit.getImage("images/stage03_image/GrowthGreenInfo.jpg").getScaledInstance(1024, 740, Image.SCALE_SMOOTH);//��� �̹���
		JLabel stage01Info = new JLabel();
		stage01Info.setBounds(100, 100, 800, 500);
		stage01Info.setHorizontalAlignment(JLabel.CENTER);
		
		JButton stage3Button = new JButton("�����ϱ�");
		//stage3Button.setBorderPainted(false);
		//stage3Button.setContentAreaFilled(false);
		//stage3Button.setFocusPainted(false);
		stage3Button.setBounds(310, 500, 100, 80);
		add(stage3Button);
		stage3Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ChangePanel.changePanel(mf, stage03Infopage, new Stage03(mf, 0, user));
				
			}
		});
		add(stage01Info);
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(infoBackGround, 0, 0, this);//��� �׸���
	}

}
