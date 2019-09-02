package inSeongJo.aHenIntoTheWild.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import inSeongJo.aHenIntoTheWild.model.dao.UserDao;
import inSeongJo.aHenIntoTheWild.model.vo.User;
import inSeongJo.aHenIntoTheWild.view.ResultPrinter;

public class UserManager {
	private UserDao ud = new UserDao();
	private ResultPrinter rp = new ResultPrinter();
	ArrayList<User> ulist;

	// �� ���� ��Ͽ� �޼ҵ�
	public void insertUser(User u) {

		// ���Ͽ� ��ϵ� ����Ʈ ��ȸ
		ulist = ud.readUserList();

		// ��ȸ ������ �ִ��� Ȯ��
		if (ulist == null) {
			// ��ȸ ������ ���� ��� ���ο� ���� ����

			ulist = new ArrayList<User>();
		}

		// ��������Ʈ�� ���ο� ������ü �߰�
		ulist.add(u);

		// ����Ʈ�� ���Ͽ� ��� �� ����� ������ ����
		int result = ud.writeUserList(ulist);

		System.out.println(ulist);

	}

	// ���̵� �ߺ� Ȯ��
	public boolean idCheck(String id) {
		ulist = ud.readUserList();
		System.out.println(id);
		System.out.println(ulist);
		// System.out.println(ulist.size());

		boolean check = false;
		
		if (ulist != null) {
			for (User user : ulist) {
				if (user.getId().equals(id)) {
					System.out.println("���̵� �ߺ�");
					check = true;
					break; // �ߺ��Ǵ� ���̵�, true�� ��ȯ
				}

			}
		}else {
			System.out.println("�ΰ�");
		}

		/*
		 * for (User user : ulist) {
		 * 
		 * if (ulist != null) { if (user.getId().equals(id)) {
		 * System.out.println("���̵� �ߺ�"); check = true; break; // �ߺ��Ǵ� ���̵�, true�� ��ȯ } }
		 * else { System.out.println("�ΰ�"); } }
		 */

		return check;

	}

	// �α���
	public boolean login(String id, String pw) {

		boolean check = false;
		// �α��� ���� : true , ���� : false ��ȯ

		ulist = ud.readUserList();
		
		for(User user : ulist) {
			if(user.getId().equals(id)) {
				if(user.getPassword().equals(pw)) {
					//�α��� ����
					System.out.println(user);
					check = true;
				}else {
					//�α��� ����
					check = false;
				}
				
			}
				
		}

/*		for (User user : ulist) {
			if (user.getId().equals(id)) {
				if (user.getPassword().equals(pw)) {
					System.out.println("�α��� �Ϸ�");
					check = true;
					JOptionPane.showMessageDialog(null, "�α��� �Ϸ�", "�α���", 1);
					break;
				}

			} else {
				System.out.println("�������� �ʴ� ���̵�� ��й�ȣ");
				check = false;
				JOptionPane.showMessageDialog(null, "���̵�� ��й�ȣ�� �ٽ� �ѹ� Ȯ�����ּ���.", "�α���", 1);
				break;
			}

		}*/

		
		  /*for(User user : ulist) { 
			  if(user.getId().equals(id) && user.getPassword().equals(pw)) { 
				  System.out.println("�α��� �Ϸ�"); check = true;
				  JOptionPane.showMessageDialog(null, "�α��� �Ϸ�", "�α���", 1); break; 
		  }else {
		  System.out.println("�������� �ʴ� ���̵�� ��й�ȣ"); 
		  check = false;
		  JOptionPane.showMessageDialog(null, "���̵�� ��й�ȣ�� �ٽ� �ѹ� Ȯ�����ּ���.", "�α���", 1);
		  break; }
		  
		  }*/
		 

		return check;
	}

}
