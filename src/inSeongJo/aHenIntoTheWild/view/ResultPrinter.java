package inSeongJo.aHenIntoTheWild.view;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ResultPrinter {

	public void successPage(String msg) {
		switch(msg) {
		case "insertUser" :
			JFrame pu = new JFrame();
			pu.setSize(500, 500);
			Dialog complete = new Dialog(pu,"���� �Ϸ�");
			pu.setBounds(150, 250, 150, 150);
			pu.add(new JLabel("���ԿϷ��� �Ϸ�Ǿ����ϴ�."));
			
			JButton button1 = new JButton("Ȯ��");
			pu.add(button1);
			
			button1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					pu.dispose();
					
				}
			});
			
			System.out.println("ȸ������ �˾�â");
		}
	}

	public void errorPage(String string) {
		//switch(msg) {
		//case "insertUser"
		//}
	}
}
