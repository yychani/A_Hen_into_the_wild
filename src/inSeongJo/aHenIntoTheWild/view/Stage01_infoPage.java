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

public class Stage01_infoPage extends JPanel{
	private MainFrame mf;
	private JPanel stage01Infopage;
	private Image infoBackGround;
	public Stage01_infoPage(MainFrame mf) {
		this.mf = mf;
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
		stage01Infopage = this;
		mf.add(this);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		infoBackGround = toolkit.getImage("images/info.png").getScaledInstance(1024, 740, Image.SCALE_SMOOTH);//배경 이미지
		JLabel stage01Info = new JLabel();
		stage01Info.setText("<html>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;- 게임 클리어 조건 -"
				+ "<br>애꾸눈을 피해 닭 무덤에서 잎싹이를 탈출시켜라!"
				+ "<br><br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;- 조작 방법 -"
				+ "<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;이동 : 방향키 ← →"
				+ "<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;점프 : Space Bar</html>");
		stage01Info.setBounds(100, 100, 800, 500);
		stage01Info.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 30));
		stage01Info.setHorizontalAlignment(JLabel.CENTER);
		
		Image stage1Start = new ImageIcon("images/ipssag/stage01Start.gif").getImage().getScaledInstance(256, 98, 0);
		JButton stage3Button = new JButton(new ImageIcon(stage1Start));
		stage3Button.setBorderPainted(false);
		stage3Button.setContentAreaFilled(false);
		stage3Button.setFocusPainted(false);
		stage3Button.setBounds(310, 500, 384, 147);
		add(stage3Button);
		stage3Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ChangePanel.changePanel(mf, stage01Infopage, new Stage01(mf));
				
			}
		});
		add(stage01Info);
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(infoBackGround, 0, 0, this);//배경 그리기
	}
	
}
