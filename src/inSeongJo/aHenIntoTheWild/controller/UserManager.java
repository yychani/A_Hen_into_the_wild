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

	// 새 유저 등록용 메소드
	public void insertUser(User u) {

		// 파일에 기록된 리스트 조회
		ulist = ud.readUserList();

		// 조회 내역이 있는지 확인
		if (ulist == null) {
			// 조회 내역이 없는 경우 새로운 유저 생성

			ulist = new ArrayList<User>();
		}

		// 유저리스트에 새로운 유저객체 추가
		ulist.add(u);

		// 리스트를 파일에 기록 후 결과값 정수로 리턴
		int result = ud.writeUserList(ulist);

		System.out.println(ulist);

	}

	// 아이디 중복 확인
	public boolean idCheck(String id) {
		ulist = ud.readUserList();
		System.out.println(id);
		System.out.println(ulist);
		// System.out.println(ulist.size());

		boolean check = false;
		
		if (ulist != null) {
			for (User user : ulist) {
				if (user.getId().equals(id)) {
					System.out.println("아이디 중복");
					check = true;
					break; // 중복되는 아이디, true값 반환
				}

			}
		}else {
			System.out.println("널값");
		}

		/*
		 * for (User user : ulist) {
		 * 
		 * if (ulist != null) { if (user.getId().equals(id)) {
		 * System.out.println("아이디 중복"); check = true; break; // 중복되는 아이디, true값 반환 } }
		 * else { System.out.println("널값"); } }
		 */

		return check;

	}

	// 로그인
	public User login(String id, String pw) {

		boolean check = false;
		// 로그인 성공 : true , 실패 : false 반환

		ulist = ud.readUserList();
		
		User loginUser = new User();
		
		for(User user : ulist) {
			if(user.getId().equals(id)) {
				if(user.getPassword().equals(pw)) {
					//로그인 성공
					System.out.println(user);
					loginUser = user;
					check = true;
				}else {
					//로그인 실패
					loginUser = null;
					check = false;
				}
				
			}
				
		}
		 

		return loginUser;
	}

}
