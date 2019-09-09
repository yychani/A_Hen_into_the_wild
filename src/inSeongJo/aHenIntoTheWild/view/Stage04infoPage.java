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

public class Stage04infoPage extends JPanel{
	private MainFrame mf;
	private JPanel stage01Infopage;
	private Image infoBackGround;
	private User user;
	private Image star;
	private Image star2;
	private Image bird;
	public Stage04infoPage(MainFrame mf, User user) {
		
		this.mf = mf;
		this.user = user;
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
		stage01Infopage = this;
//		mf.add(this);

		Toolkit toolkit = Toolkit.getDefaultToolkit();

		infoBackGround = toolkit.getImage("images/info.png").getScaledInstance(1024, 740, Image.SCALE_SMOOTH);//배경 이미지
		star = toolkit.getImage("images/Images/star.png").getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		star2 = toolkit.getImage("images/Images/star2.png").getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		bird = toolkit.getImage("images/Images/bird.gif").getScaledInstance(50, 50, 0);
		JLabel stage01Info = new JLabel();
		JLabel jStar = new JLabel(new ImageIcon(star));
		JLabel jStar2 = new JLabel(new ImageIcon(star2));
		JLabel jBird = new JLabel(new ImageIcon(bird));
		JLabel starScore = new JLabel("   :    - 20");
		JLabel starScore2 = new JLabel("   :    + 50");
		JLabel birdC = new JLabel("        :    -1 life");
		
		stage01Info.setText("<html>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;- 게임 클리어 조건 -"
				+ "<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;날아오는 새들의 방해를 피해 파수꾼이 되어라!"
//				+ "<br>&nbsp "
				+ "<br><br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;- 조작 방법 -"
				+ "<br><br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;이동 : 방향키 ← ↑ → ↓"
				+ "<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;"
				+ "<br><br><br><br><br><br><br><br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;시작하려면 Space Bar를 누르시오.</html>");
		stage01Info.setBounds(10, 10, 800, 500);
		stage01Info.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		stage01Info.setHorizontalAlignment(JLabel.CENTER);
		jStar.setBounds(30, 30, 850, 530);
		jStar2.setBounds(30, 30, 850, 630);
		starScore.setBounds(30, 30, 950, 530);
		starScore2.setBounds(30, 30, 950, 630);
		starScore.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		starScore.setHorizontalAlignment(JLabel.CENTER);
		starScore2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		starScore2.setHorizontalAlignment(JLabel.CENTER);
		jBird.setBounds(30, 30, 850, 730);
		birdC.setBounds(30, 30, 950, 730);
		birdC.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		birdC.setHorizontalAlignment(JLabel.CENTER);


		
		
//		jStar.setFont(new Font("맑은 고딕", Font.BOLD, 20));
//		jStar2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		

		Image stage1Start = new ImageIcon("images/Images/startChorok.png").getImage().getScaledInstance(150, 150, 0);
		JButton stage3Button = new JButton(new ImageIcon(stage1Start));
		stage3Button.setBorderPainted(false);
		stage3Button.setContentAreaFilled(false);
		stage3Button.setFocusPainted(false);
		stage3Button.setBounds(330, 550, 384, 147);
		add(stage3Button);
		add(jStar);
		add(jStar2);
		add(starScore);
		add(starScore2);
		add(jBird);
		add(birdC);
		stage3Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ChangePanel.changePanel(mf, stage01Infopage, new Stage04(mf, user));

			}
		});
		add(stage01Info);
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(infoBackGround, 0, 0, this);//배경 그리기
	}

}


