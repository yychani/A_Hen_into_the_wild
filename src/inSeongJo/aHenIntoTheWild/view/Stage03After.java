package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import inSeongJo.aHenIntoTheWild.controller.Stage03Manager;
import inSeongJo.aHenIntoTheWild.model.dao.RankingDao;
import inSeongJo.aHenIntoTheWild.model.vo.Ranking;
import inSeongJo.aHenIntoTheWild.model.vo.User;

public class Stage03After extends JPanel{
	private MainFrame mf;
	private Image background = new ImageIcon("images/stage03_image/background2.png").getImage();
	private Graphics g;
	private Stage03Manager sm = new Stage03Manager();
	private RankingDao rd = new RankingDao();

	
	public Stage03After(MainFrame mf, User user, int score) {
		this.mf = mf;
		this.setName("Stage3 Ranking");
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
		this.setBackground(new Color(0,0,0,0));
		mf.add(this);
		
		String levelStr = "당신의 스코어 : " + score;
		JLabel levelLabel = new JLabel(levelStr);
		levelLabel.setBounds(10, 10, 200, 20);
		levelLabel.setFont(new Font("바탕",Font.BOLD, 15));
		add(levelLabel);
		
		
		sm.rankingMethod(user, score);
		ArrayList<Ranking> list = rd.readRankingList(3);
		
		if(list == null) {
			System.out.println("기존 랭킹이 없습니다.");
			list = new ArrayList<Ranking>();
		} else {
			
			for(int i=0; i<5; i++) {
				System.out.println(list.get(i));
			}
		}
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ChangePanel.changePanel(mf, this, new MainStage(mf, user));
	}

}
