package inSeongJo.aHenIntoTheWild.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SavePage extends JPanel {

	JPanel panel = new JPanel();
	BufferedImage img;
	JButton bnt1 = new JButton("새로 하기");
	JButton bnt2 = new JButton("이어 하기");
	private Font f1;

	public SavePage() {

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		try {
			img = ImageIO.read(new File("src/MJimages/icon3.jpg"));
			// add(this);
			setSize(1024, 768);
			int width = (int) (d.getWidth());
			int height = (int) (d.getHeight());
			this.setLayout(null);
			System.out.println(width);
			System.out.println(height);

			bnt1.setBounds((width / 2) - 10, (height / 2) - 50, 250, 80);
			bnt2.setBounds((width / 2) - 410, (height / 2) - 50, 250, 80);

		} catch (IOException e) {
			System.out.println(e.getMessage());

		}

		f1 = new Font("나눔스퀘어_ac Bold", Font.PLAIN, 20);
		bnt1.setFont(f1);
		add(bnt1);

		bnt2.setFont(f1);
		add(bnt2);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 0, 0, this);
	}

	public static void main(String[] args) {
		new SavePage();
	}
}
