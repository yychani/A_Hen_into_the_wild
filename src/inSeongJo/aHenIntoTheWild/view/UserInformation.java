package inSeongJo.aHenIntoTheWild.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class UserInformation extends JPanel{
	
	BufferedImage img;
	JLabel larr[] = new JLabel[5];
	String info[] = {"이름", "닉네임", "이메일", "랭킹", "Stage"};
	JTextField tarr[] = new JTextField[5];
	JButton btn1 = new JButton("회원 정보 수정");
	JButton btn2 = new JButton("로그아웃");
	
	private Font f1;
	
	private MainFrame mf;
	private JPanel UserInformation;
	
	public UserInformation(MainFrame mf) {
		this.mf = mf;
		UserInformation = this;
		setLayout(null);
		setSize(1024, 768);
		//setBounds(0, 0, 1024, 768);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		
		try {
			img = ImageIO.read(new File("src/MJimages/icon3.jpg"));
						
		}catch(Exception e) {
			System.out.println(e.getMessage());        
		}
		
		int width =(int)(d.getWidth());
		int height = (int)(d.getHeight());
		
		f1 = new Font("나눔스퀘어_ac Bold", Font.PLAIN, 20);
		
		for(int i=0; i<larr.length; i++) {
			larr[i] = new JLabel(info[i]);
			larr[i].setFont(f1);
			add(larr[i]);
			switch(i) {
			case 0: larr[i].setBounds((width/3)-250, (height/3), 150, 50);break;
			case 1: larr[i].setBounds((width/3)-250, (height/3)-100, 150, 50);break;
			case 2: larr[i].setBounds((width/3)-250, (height/3)-200, 150, 50);break;
			case 3: larr[i].setBounds((width/3)+130, (height/3), 150, 50);break;
			case 4: larr[i].setBounds((width/3)+130, (height/3)-100, 150, 50);break;
			}
		}
		
		for(int i=0; i<tarr.length;i++) {
			tarr[i] = new JTextField();
			add(tarr[i]);
			switch(i) {
			case 0: tarr[i].setBounds((width/3)-140, (height/3), 150, 50);break;
			case 1: tarr[i].setBounds((width/3)-140, (height/3)-100, 150, 50);break;
			case 2: tarr[i].setBounds((width/3)-140, (height/3)-200, 150, 50);break;
			case 3: tarr[i].setBounds((width/3)+200, (height/3), 150, 50);break;
			case 4: tarr[i].setBounds((width/3)+200, (height/3)-100, 150, 50);break;
			}
		}
		
		btn1.setFont(f1);
		btn2.setFont(f1);
		add(btn1);
		add(btn2);
		btn1.setBounds(100, 100, 150, 50);
		btn2.setBounds(200, 200, 150, 50);
		
		
		//회원정보 변경으로 이동
		btn1.addActionListener(new ActionListener() {
			
			@Override
			
			public void actionPerformed(ActionEvent e) {
				ChangePanel.changePanel(mf, UserInformation, new UserInfoChange(mf));
			}
		});
		
		
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 0, 0, this);
		paintComponents(g);
		this.repaint();
	}
	
}
