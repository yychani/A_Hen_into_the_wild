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

		// �̿��� �ؽ�Ʈ
		JLabel usersAgreementsText = new JLabel("�̿���");
		usersAgreementsText.setBounds(420, 140, 300, 100);
		usersAgreementsText.setFont(new Font("���������� ExtraBold", Font.PLAIN, 40));
		usersAgreementsText.setForeground(Color.DARK_GRAY);
		add(usersAgreementsText);
		
		
		//������ ���� ��ż �̿��� ���� ������ư
		JRadioButton useAgree1 = new JRadioButton("A Hen into the Wild �̿��� ����(�ʼ�)");
		useAgree1.setBounds(200, 325, 300, 200);
		useAgree1.setContentAreaFilled(false);
		useAgree1.setBorderPainted(false);
		useAgree1.setForeground(Color.white);
		add(useAgree1);
		
		//������ ���� ��ż �̿��� ���� �ؽ�Ʈ
		JTextArea agreeTextArea1 = new JTextArea(10, 20);
		agreeTextArea1.setText("�������� ȯ���մϴ�.\r\n" + 
				"\r\n" + 
				"���̹� ���� �� ��ǰ(���� �����񽺡�)�� �̿��� �ּż� �����մϴ�.\r\n" + "\r\n" + "�� ����� �پ��� ���̹� ������ �̿�� �����Ͽ� ���̹� ���񽺸� �����ϴ� ���̹� �ֽ�ȸ��(���� �����̹���)���̸� �̿��ϴ� ���̹� ���� ȸ��(���� ��ȸ����) �Ǵ� ��ȸ������ ���踦 �����ϸ�, �ƿ﷯ �������� ���̹� ���� �̿뿡 ������ �� �� �ִ� ������ ������ �����ϰ� �ֽ��ϴ�. \r\n" + 
				"\r\n" + 
				"���̹� ���񽺸� �̿��Ͻðų� ���̹� ���� ȸ������ �����Ͻ� ��� �������� �� ��� �� ���� � ��å�� Ȯ���ϰų� �����ϰ� �ǹǷ�, ��� �ð��� ���þ� ���� ��� ����� �ֽñ� �ٶ��ϴ�. \r\n" + 
				"");
		JScrollPane scrollPane1 = new JScrollPane(agreeTextArea1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.setBounds(200, 310, 600, 100);
		add(scrollPane1);
		
		
		
		//�������� ���� �� �̿뿡 ���� �ȳ� ���� ������ư
		JRadioButton useAgree2 = new JRadioButton("A Hen into the Wild �̿��� ����(�ʼ�)");
		useAgree2.setBounds(200, 470, 300, 200);
		useAgree2.setContentAreaFilled(false);
		useAgree2.setBorderPainted(false);
		useAgree2.setForeground(Color.white);
		add(useAgree2);
		
		//�������� ���� �� �̿뿡 ���� �ȳ� �ؽ�Ʈ
		JTextArea agreeTextArea2 = new JTextArea(10, 20);
		agreeTextArea2.setText("\r\n" + 
				"������Ÿ��� ������ ���� �μ����� ȸ������ ��û�Ͻô� �в� �����ϴ� ���������� �׸�, ���������� ���� �� �̿����, ���������� ���� �� �̿�Ⱓ�� �ȳ� �帮���� �ڼ��� ���� �� �����Ͽ� �ֽñ� �ٶ��ϴ�.\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"1. �����ϴ� ��������\r\n" + 
				"\r\n" + 
				"�̿��ڴ� ȸ�������� ���� �ʾƵ� ���� �˻�, ���� ���� �� ��κ��� ���̹� ���񽺸� ȸ���� �����ϰ� �̿��� �� �ֽ��ϴ�. �̿��ڰ� ����, Ķ����, ī��, ��α� ��� ���� ����ȭ Ȥ�� ȸ���� ���񽺸� �̿��ϱ� ���� ȸ�������� �� ���, ���̹��� ���� �̿��� ���� �ʿ��� �ּ����� ���������� �����մϴ�.\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"ȸ������ ������ ���̹��� �̿��ڷκ��� �����ϴ� ���������� �Ʒ��� �����ϴ�.\r\n" + 
				"- ȸ�� ���� �ÿ� �����̵�, ��й�ȣ, �̸�, �������, ����, �������� �޴���ȭ��ȣ���� �ʼ��׸����� �����մϴ�. ���� �̿��ڰ� �Է��ϴ� ��������� ��14�� �̸� �Ƶ��� ��쿡�� �����븮�� ����(�����븮���� �̸�, �������, ����, �ߺ�����Ȯ������(DI), �޴���ȭ��ȣ)�� �߰��� �����մϴ�. �׸��� �����׸����� �̸��� �ּҸ� �����մϴ�.\r\n" + 
				"- ��ü���̵�� ȸ������ �� ��ü���̵�, ��й�ȣ, ��ü�̸�, �̸����ּ�, �������� �޴���ȭ��ȣ�� �ʼ��׸����� �����մϴ�. �׸��� ��ü ��ǥ�ڸ��� �����׸����� �����մϴ�.\r\n" + 
				"\r\n" + 
				"���� �̿� �������� �̿��ڷκ��� �����ϴ� ���������� �Ʒ��� �����ϴ�.\r\n" + 
				"\r\n" + 
				"NAVER ���� ���� ���� �̿�, �̺�Ʈ ���� �� ��ǰ ��û �������� �ش� ������ �̿��ڿ� ���� �߰� �������� ������ �߻��� �� �ֽ��ϴ�. �߰��� ���������� ������ ��쿡�� �ش� �������� ���� �������� �̿��ڿ��� �������ϴ� �������� �׸�, ���������� ���� �� �̿����, ���������� �����Ⱓ���� ���� �ȳ� �帮�� ���Ǹ� �޽��ϴ�.\r\n" + 
				"");
		JScrollPane scrollPane2 = new JScrollPane(agreeTextArea2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane2.setBounds(200, 450, 600, 100);
		add(scrollPane2);
		
		
		//��� ���� ��ư
		JRadioButton allAgree = new JRadioButton("�̿���, �������� ���� �� �̿�, ��ġ���� �̿���(����),���θ�� �ȳ�, ���� ����(����)�� ��� �����մϴ�.");
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
		
		//��� ��ư (������������ �̵�)
		JButton cancleButton = new JButton("���");
		cancleButton.setBounds(350, 650, 100, 40);
		cancleButton.setFont(new Font("���������� Bold", Font.PLAIN, 20));
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
		
		//Ȯ�� ��ư
		JButton confirmButton = new JButton("Ȯ��");
		confirmButton.setBounds(500, 650, 100, 40);
		confirmButton.setFont(new Font("���������� Bold", Font.PLAIN, 20));
		confirmButton.setBorderPainted(false);
		confirmButton.setBackground(Color.LIGHT_GRAY);
		confirmButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(confirmButton);
		
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!allAgree.isSelected() || !useAgree1.isSelected() || !useAgree1.isSelected()) {
					JOptionPane.showMessageDialog(null, "����� ��� �������ּ���.", "��� �̵���",  1);
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
