package inSeongJo.aHenIntoTheWild.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UserAgreements extends JPanel {
	private MainFrame mf;
	private JPanel UserAgreements;
	private Image background = new ImageIcon("images/YJimages/main_none.png").getImage();
	// private JLabel label = new JLabel(background);
	private Graphics ScreenGraphics;
	private Image ScreenImage;

	public UserAgreements(MainFrame mf) {
		UserAgreements = this;
		this.setBounds(0, 0, 1024, 768);
		this.setLayout(null);
		mf.add(this);

		// 이용약관 텍스트
		JLabel usersAgreementsText = new JLabel("이용약관");
		usersAgreementsText.setBounds(420, 140, 300, 100);
		usersAgreementsText.setFont(new Font("나눔스퀘어 ExtraBold", Font.PLAIN, 40));
		usersAgreementsText.setForeground(Color.DARK_GRAY);
		add(usersAgreementsText);
		
		
		//마당을 나온 암탉 이용약관 동의 라디오버튼
		JRadioButton useAgree1 = new JRadioButton("A Hen into the Wild 이용약관 동의(필수)");
		useAgree1.setBounds(200, 325, 300, 200);
		useAgree1.setContentAreaFilled(false);
		useAgree1.setBorderPainted(false);
		useAgree1.setForeground(Color.white);
		add(useAgree1);
		
		//마당을 나온 암탉 이용약관 동의 텍스트
		JTextArea agreeTextArea1 = new JTextArea(10, 20);
		agreeTextArea1.setText("여러분을 환영합니다.\r\n" + 
				"\r\n" + 
				"네이버 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다.\r\n" + "\r\n" + "본 약관은 다양한 네이버 서비스의 이용과 관련하여 네이버 서비스를 제공하는 네이버 주식회사(이하 ‘네이버’)와이를 이용하는 네이버 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 네이버 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다. \r\n" + 
				"\r\n" + 
				"네이버 서비스를 이용하시거나 네이버 서비스 회원으로 가입하실 경우 여러분은 본 약관 및 관련 운영 정책을 확인하거나 동의하게 되므로, 잠시 시간을 내시어 주의 깊게 살펴봐 주시기 바랍니다. \r\n" + 
				"");
		JScrollPane scrollPane1 = new JScrollPane(agreeTextArea1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.setBounds(200, 310, 600, 100);
		add(scrollPane1);
		
		
		
		//개인정보 수집 및 이용에 대한 안내 동의 라디오버튼
		JRadioButton useAgree2 = new JRadioButton("A Hen into the Wild 이용약관 동의(필수)");
		useAgree2.setBounds(200, 470, 300, 200);
		useAgree2.setContentAreaFilled(false);
		useAgree2.setBorderPainted(false);
		useAgree2.setForeground(Color.white);
		add(useAgree2);
		
		//개인정보 수집 및 이용에 대한 안내 텍스트
		JTextArea agreeTextArea2 = new JTextArea(10, 20);
		agreeTextArea2.setText("\r\n" + 
				"정보통신망법 규정에 따라 인성조에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"1. 수집하는 개인정보\r\n" + 
				"\r\n" + 
				"이용자는 회원가입을 하지 않아도 정보 검색, 뉴스 보기 등 대부분의 네이버 서비스를 회원과 동일하게 이용할 수 있습니다. 이용자가 메일, 캘린더, 카페, 블로그 등과 같이 개인화 혹은 회원제 서비스를 이용하기 위해 회원가입을 할 경우, 네이버는 서비스 이용을 위해 필요한 최소한의 개인정보를 수집합니다.\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"회원가입 시점에 네이버가 이용자로부터 수집하는 개인정보는 아래와 같습니다.\r\n" + 
				"- 회원 가입 시에 ‘아이디, 비밀번호, 이름, 생년월일, 성별, 가입인증 휴대전화번호’를 필수항목으로 수집합니다. 만약 이용자가 입력하는 생년월일이 만14세 미만 아동일 경우에는 법정대리인 정보(법정대리인의 이름, 생년월일, 성별, 중복가입확인정보(DI), 휴대전화번호)를 추가로 수집합니다. 그리고 선택항목으로 이메일 주소를 수집합니다.\r\n" + 
				"- 단체아이디로 회원가입 시 단체아이디, 비밀번호, 단체이름, 이메일주소, 가입인증 휴대전화번호를 필수항목으로 수집합니다. 그리고 단체 대표자명을 선택항목으로 수집합니다.\r\n" + 
				"\r\n" + 
				"서비스 이용 과정에서 이용자로부터 수집하는 개인정보는 아래와 같습니다.\r\n" + 
				"\r\n" + 
				"NAVER 내의 개별 서비스 이용, 이벤트 응모 및 경품 신청 과정에서 해당 서비스의 이용자에 한해 추가 개인정보 수집이 발생할 수 있습니다. 추가로 개인정보를 수집할 경우에는 해당 개인정보 수집 시점에서 이용자에게 ‘수집하는 개인정보 항목, 개인정보의 수집 및 이용목적, 개인정보의 보관기간’에 대해 안내 드리고 동의를 받습니다.\r\n" + 
				"");
		JScrollPane scrollPane2 = new JScrollPane(agreeTextArea2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane2.setBounds(200, 450, 600, 100);
		add(scrollPane2);
		
		
		//모두 동의 버튼
		JRadioButton allAgree = new JRadioButton("이용약관, 개인정보 수집 및 이용, 위치정보 이용약관(선택),프로모션 안내, 메일 수신(선택)에 모두 동의합니다.");
		allAgree.setBounds(180, 250, 800, 50);
		allAgree.setContentAreaFilled(false);
		allAgree.setBorderPainted(false);
		allAgree.setForeground(Color.DARK_GRAY);
		add(allAgree);
		
		
		allAgree.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(allAgree.isSelected()) {
					useAgree1.setSelected(true);
					useAgree2.setSelected(true);
				}
			}
		});
		
		//취소 버튼 (메인페이지로 이동)
		JButton cancleButton = new JButton("취소");
		cancleButton.setBounds(350, 650, 100, 40);
		cancleButton.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 20));
		cancleButton.setBorderPainted(false);
		cancleButton.setBackground(Color.LIGHT_GRAY);
		cancleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(cancleButton);
		
		cancleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ChangePanel.changePanel(mf, UserAgreements, new MainPage(mf));
			}
		});
		
		//확인 버튼
		JButton confirmButton = new JButton("확인");
		confirmButton.setBounds(500, 650, 100, 40);
		confirmButton.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 20));
		confirmButton.setBorderPainted(false);
		confirmButton.setBackground(Color.LIGHT_GRAY);
		confirmButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(confirmButton);
		
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!allAgree.isSelected() || !useAgree1.isSelected() || !useAgree1.isSelected()) {
					JOptionPane.showMessageDialog(null, "약관에 모두 동의해주세요.", "약관 미동의",  1);
				}
				
				
				if(allAgree.isSelected()) {
					ChangePanel.changePanel(mf, UserAgreements, new JoinPage(mf));
				}
				
			}
		});
		

	}

	public void paint(Graphics g) {
		ScreenImage = createImage(1024, 768);
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
