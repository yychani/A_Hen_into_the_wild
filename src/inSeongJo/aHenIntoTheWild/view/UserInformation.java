package inSeongJo.aHenIntoTheWild.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import inSeongJo.aHenIntoTheWild.model.dao.UserDao;
import inSeongJo.aHenIntoTheWild.model.vo.User;



public class UserInformation extends JPanel{

	Image img;
	JLabel larr[] = new JLabel[5];
	String info[] = {"아이디", "닉네임", "이메일", "랭킹", "Stage"};
	JTextField tarr[] = new JTextField[5];
	JButton btn1 = new JButton("회원 정보 수정");
	JButton btn2 = new JButton("로그아웃");
	User user;
	User presentUser;
	private ArrayList<User> allUser;
	private Font f1;

	private MainFrame mf;
	private JPanel UserInformation;

	public UserInformation(MainFrame mf, User user) {
		this.mf = mf;
		this.user = user;
		UserInformation = this;
		setLayout(null);
		setSize(1024, 768);
		Toolkit tk = Toolkit.getDefaultToolkit();
		//setBounds(0, 0, 1024, 768);
		Dimension d = tk.getScreenSize();
		UserDao userdao = new UserDao();
		allUser = userdao.readUserList();

		for(User u : allUser) {
			if(u.getId().equals(user.getId())) {
				if(u.getPassword().equals(user.getPassword())) {
					presentUser = u;
				}else {
				}

			}
		}

		try {
			img = tk.getImage("images/YJimages/Main_none.png").getScaledInstance(1024, 768, Image.SCALE_SMOOTH);
			
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
			case 0: larr[i].setBounds((width/3)-250, (height/3), 150, 50); break;
			case 1: larr[i].setBounds((width/3)-250, (height/3) - 100, 150, 50); break;
			case 2: larr[i].setBounds((width/3)-250, (height/3) - 200, 150, 50); break;
			case 3: larr[i].setBounds((width/3)+130, (height/3), 150, 50); break;
			case 4: larr[i].setBounds((width/3)+130, (height/3) - 100, 150, 50); break;
			}
		}

		for(int i=0; i<tarr.length;i++) {
			tarr[i] = new JTextField();
			add(tarr[i]);
			switch(i) {
			case 0: 
				tarr[i].setBounds((width/3)-140, (height/3), 250, 50);
				tarr[i].setText(presentUser.getId());
				tarr[i].setEditable(false);
				break;
			case 1: 
				tarr[i].setBounds((width/3)-140, (height/3)-100, 250, 50);
				tarr[i].setText(presentUser.getNickName());
				tarr[i].setEditable(false);
				break;
			case 2: 
				tarr[i].setBounds((width/3)-140, (height/3)-200, 250, 50);
				tarr[i].setText(presentUser.getEmail());
				tarr[i].setEditable(false);
				break;
			case 3: 
				tarr[i].setBounds((width/3)+200, (height/3), 250, 50);
				tarr[i].setText(presentUser.getTotalScore()+"");
				tarr[i].setEditable(false);
				break;
			case 4: 
				tarr[i].setBounds((width/3)+200, (height/3)-100, 250, 50);
				if(presentUser.getStage4Score() == 0) {
					if(presentUser.getStage3Score() == 0) {
						if(presentUser.getStage2Score() == 0) {
							if(presentUser.getStage1Score() == 0) {
								tarr[i].setText("클리어한 스테이지가 없습니다.");
								tarr[i].setEditable(false);
								break;
							}
							tarr[i].setText("1");
							tarr[i].setEditable(false);
							break;
						}
						tarr[i].setText("2");
						tarr[i].setEditable(false);
						break;
					}
					tarr[i].setText("3");
					tarr[i].setEditable(false);
					break;
				}else {
					tarr[i].setText("4");
					tarr[i].setEditable(false);
					break;
				}
				
			}
		}

		btn1.setFont(f1);
		btn2.setFont(f1);
		add(btn1);
		add(btn2);
		btn1.setBounds(300, 500, 150, 50);
		btn2.setBounds(500, 500, 150, 50);


		//회원정보 변경으로 이동
		btn1.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {
				ChangePanel.changePanel(mf, UserInformation, new UserInfoChange(mf, user));
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
