package inSeongJo.aHenIntoTheWild.view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import inSeongJo.aHenIntoTheWild.model.vo.User;

public class Stage02_infoPage extends JPanel{
	private MainFrame mf;
	private JPanel stage02Infopage;
	private Image infoBackGround;
	private User user;
	public Stage02_infoPage(MainFrame mf, User user) {
		this.mf = mf;
		this.user = user;
		stage02Infopage = this;
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
		
//		mf.add(this);

		Toolkit toolkit = Toolkit.getDefaultToolkit();

		infoBackGround = toolkit.getImage("images/info.png").getScaledInstance(1024, 740, Image.SCALE_SMOOTH);//배경 이미지
		JLabel stage01Info = new JLabel();
		stage01Info.setText("<html>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;- 게임 클리어 조건 -"
				+ "<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp나그네 VS 애꾸눈"
				+ "<br><br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;- 조작 방법 -"
				+ "<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;이동 : 방향키 ← →"
				+ "<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;점프 : 회오리 바람"
				+ "<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp; R : 주먹"
				+ "<br><br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;시작하려면 Space Bar를 누르시오.</html>");
		stage01Info.setBounds(100, 100, 800, 500);
		stage01Info.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		stage01Info.setHorizontalAlignment(JLabel.CENTER);

		Image stage1Start = new ImageIcon("images/MSImages/nagne.png").getImage().getScaledInstance(350, 125, 0);
		
		Image stage2Start = new ImageIcon("images/MSImages/aggu_reverse.png").getImage().getScaledInstance(350, 125, 0);
		Image stage3Start = new ImageIcon("images/MSImages/lightening.png").getImage().getScaledInstance(350, 125, 0);
		JButton stage3Button = new JButton(new ImageIcon(stage1Start));
		JButton stage4Button = new JButton(new ImageIcon(stage2Start));
		JButton stage5Button = new JButton(new ImageIcon(stage3Start));
		stage3Button.setBorderPainted(false);
		stage3Button.setContentAreaFilled(false);
		stage3Button.setFocusPainted(false);
		stage4Button.setBorderPainted(false);
		stage4Button.setContentAreaFilled(false);
		stage4Button.setFocusPainted(false);
		stage5Button.setBorderPainted(false);
		stage5Button.setContentAreaFilled(false);
		stage5Button.setFocusPainted(false);
		stage3Button.setBounds(80, 550, 350, 125);
		stage4Button.setBounds(530, 550, 384, 147);
		stage5Button.setBounds(330, 550, 384, 147);
		add(stage3Button);
		add(stage4Button);
		add(stage5Button);
		stage3Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ChangePanel.changePanel(mf, stage02Infopage, new Stage02(mf, user));

			}
		});
		add(stage01Info);
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(infoBackGround, 0, 0, this);//배경 그리기
	}

}
