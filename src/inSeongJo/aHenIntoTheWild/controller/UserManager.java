package inSeongJo.aHenIntoTheWild.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JOptionPane;

import inSeongJo.aHenIntoTheWild.model.dao.RankingDao;
import inSeongJo.aHenIntoTheWild.model.dao.UserDao;
import inSeongJo.aHenIntoTheWild.model.vo.Ranking;
import inSeongJo.aHenIntoTheWild.model.vo.User;
import inSeongJo.aHenIntoTheWild.view.ResultPrinter;

public class UserManager {
	private UserDao ud = new UserDao();
	private ResultPrinter rp = new ResultPrinter();
	ArrayList<User> ulist = null;

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
		} else {
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

		// 로그인 성공 : true , 실패 : false 반환

		ulist = ud.readUserList();

		User loginUser = new User();
		System.out.println(ulist);
//		if (ulist.isEmpty()) {
//			JOptionPane.showMessageDialog(null, "회원가입이 필요합니다.", "로그인", 1);
//		} else {
			for (User user : ulist) {
				if (user.getId().equals(id)) {
					if (user.getPassword().equals(pw)) {
						// 로그인 성공
						System.out.println(user);
						loginUser = user;
					} else {
						// 로그인 실패
						loginUser.setId(null);
						;
					}

				}

			}
//		}

		return loginUser;
	}

	// 회원 정보 변경 메소드
	public User UserInfoChagne(String nickName, String email, String password, User user) {

		ulist = ud.readUserList();

		int userIndex = 0;
		System.out.println(nickName + ", " + email + ", " + password);

		for (int i = 0; i < ulist.size(); i++) {
			if (ulist.get(i).getId().equals(user.getId())) {
				System.out.println(ulist.get(i));
				userIndex = i;
			} else {
				System.out.println("입력하신 회원의 정보가 없습니다.");
			}

		}

		ulist.get(userIndex).setNickName(nickName);
		ulist.get(userIndex).setEmail(email);
		ulist.get(userIndex).setPassword(password);

		for (int i = 0; i < ulist.size(); i++) {
			System.out.println(ulist.get(i));
		}

		ud.writeUserList(ulist);

		return ulist.get(userIndex);
	}
	
	// 임시비밀번호 : 기존 회원 확인 메소드
	public String passwordChange(String id) {
		
		ulist = ud.readUserList();
		
		System.out.println(id);
		
		String userEmail = "";
		
		for (int i = 0; i < ulist.size(); i++) {
			if (ulist.get(i).getId().equals(id)) {
				System.out.println("입력하신 아이디의 회원이 있으니깐 임시 비밀번호 생성해서 이메일 보내라");
				userEmail = ulist.get(i).getEmail();
			} else {
				System.out.println("입력하신 회원의 정보가 없습니다.");
				userEmail = "none";
			}

		}
		
		return userEmail;
		
	}
	
	//임시 비밀번호 생성 메소드
	public String randomPassword() {
		int index = 0;
		char[] charSet = new char[]	{
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
				'U', 'V', 'W', 'X', 'Y', 'Z'};
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < 9; i++) {
			index = (int)(charSet.length * Math.random());
			sb.append(charSet[index]);
		}
		
		return sb.toString();
	}
	
	// 비밀번호 변경 메소드
		public void passwordInfoChange(String id, String randomPass) {

			ulist = ud.readUserList();

			int userIndex = 0;
			System.out.println(id + ",  " + randomPass);

			for (int i = 0; i < ulist.size(); i++) {
				if (ulist.get(i).getId().equals(id)) {
					System.out.println(ulist.get(i));
					userIndex = i;
				} else {
					System.out.println("입력하신 회원의 정보가 없습니다.");
				}

			}

			ulist.get(userIndex).setPassword(randomPass);


			ud.writeUserList(ulist);

		}

	//랭킹 정렬 메소드
	public void rankingMethod(User user, int score, int stage) {
		int size = 0;
		RankingDao rd = new RankingDao();
		ArrayList<Ranking> list = rd.readRankingList(stage);
		Ranking r = new Ranking();
		Ranking rInit = new Ranking("빈랭킹", 0);

		if (list.size() == 0) {
			list = new ArrayList<Ranking>();
			r.setName(user.getNickName());
			r.setScore(score);
			list.add(r);
			for (int i = 0; i < 4; i++) {
				list.add(rInit);
			}
		} else {
			r.setName(user.getNickName());
			r.setScore(score);
			list.add(r);
			list.sort(new Comparator() {

				@Override
				public int compare(Object o1, Object o2) {
					Ranking cob1 = (Ranking) o1;
					Ranking cob2 = (Ranking) o2;

					int result = 0;

					if (cob1.getScore() == cob2.getScore()) {
						result = 0;
					}
					if (cob1.getScore() < cob2.getScore()) {
						result = 1;
					}
					if (cob1.getScore() > cob2.getScore()) {
						result = -1;
					}
					return result;
				}
			});
		}
		if (list.size() < 5) {
			size = list.size();
		} else {
			size = 5;
		}
		for (int i = 0; i < size; i++) {
			System.out.println((i + 1) + "등 : " + list.get(i).getName() + ", " + list.get(i).getScore());
		}

		int result = rd.writeRankingList(list, stage);
		System.out.println("result : " + result);
	}

}
