package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Stage03 extends JPanel{
	private MainFrame mf;
	private Image background = new ImageIcon("src/images/background.png").getImage();
	//private JLabel label = new JLabel(background);
	private Graphics ScreenGraphics;
	private Image ScreenImage;

	
	public Stage03(MainFrame mf) {
		this.setName("Stage3");
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
		this.setBackground(new Color(0,0,0,0));
		mf.add(this);
		
		
		Image green1 = new ImageIcon("src/images/greenE1.png").getImage();
		JLabel greenLabel = new JLabel(new ImageIcon(green1));
		greenLabel.setBounds(412, 350, 200, 279);
		add(greenLabel);
		
		Image dungji = new ImageIcon("src/images/dungji.png").getImage();
		JLabel dungjiLabel = new JLabel(new ImageIcon(dungji));
		dungjiLabel.setBounds(346, 515, 326, 193);
		add(dungjiLabel);
		
		Image riceIcon = new ImageIcon("src/images/riceIcon.png").getImage();
		JLabel riceLabel = new JLabel(new ImageIcon(riceIcon));
		riceLabel.setBounds(20, 620, 100, 102);
		add(riceLabel);
		
		Image bathIcon = new ImageIcon("src/images/riceIcon.png").getImage();
		JLabel bathLabel = new JLabel(new ImageIcon(bathIcon));
		bathLabel.setBounds(120, 620, 100, 102);
		add(bathLabel);
		
	}
	
	public void paint(Graphics g) {
		ScreenImage = createImage(1024,768);
		ScreenGraphics = ScreenImage.getGraphics();
		screenDraw(ScreenGraphics);
		g.drawImage(ScreenImage, 0, 0, null);
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		paintComponents(g);
		this.repaint();
	}
}
