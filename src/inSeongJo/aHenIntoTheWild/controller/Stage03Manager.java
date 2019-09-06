package inSeongJo.aHenIntoTheWild.controller;

import java.util.ArrayList;
import java.util.Comparator;

import inSeongJo.aHenIntoTheWild.model.dao.RankingDao;
import inSeongJo.aHenIntoTheWild.model.vo.Ranking;
import inSeongJo.aHenIntoTheWild.model.vo.User;
import inSeongJo.aHenIntoTheWild.view.Media;

public class Stage03Manager {
	private Media media = new Media();
	
	//증가하는 메소드 
	public int plusFor (int rate, int num) {
		for (int i=0; i<num; i++) {
			if(rate >= 100) {
				break;
			} else {
				rate++;
			}
		}
		return rate;
	}
	
	//감소하는 메소드 정의 
	public int minusFor (int rate, int num) {
		for (int i=num; i>0; i--) {
			if(rate <= 0) {
				break;
			} else {
				rate--;
			}
		}
		return rate;
	}

	
	//각 버튼마다 기능하는 메소드들 정의
	
	//밥먹이기 메소드
	public int[] eatingMethod(int[] rate, int x, int y, int level) {
		int mouthX1, mouthX2, mouthY1, mouthY2;
		if (level == 0) {
			mouthX1 = 450;	mouthX2 = 580;	mouthY1 = 350;	mouthY2 = 420; //level1일때 좌표 
		} else if (level == 1){
			mouthX1 = 400;	mouthX2 = 540;	mouthY1 = 310;	mouthY2 = 380; //level2일때 좌표 
		} else {
			mouthX1 = 450;	mouthX2 = 580;	mouthY1 = 350;	mouthY2 = 420; //level3일때 (임시)좌표
		}
		if((x > mouthX1 && x < mouthX2) && (y > mouthY1 && y < mouthY2)) {
			media.sound("eatingFx");
			//포만감 증가
			rate[0] = plusFor(rate[0], 5);
			//피로도 증가
			rate[2] = plusFor(rate[2], 5);
			//성장도 증가
			rate[3] = plusFor(rate[3], 2);
		}
		return rate;
	}
	
	//씻기기 메소드
	public int[] cleaningMethod(int[] rate, int x, int y, int level) {
		int mouthX1, mouthX2, mouthY1, mouthY2;
		if (level == 0) {
			mouthX1 = 450;	mouthX2 = 580;	mouthY1 = 280;	mouthY2 = 370; //level1일때 좌표 
		} else if (level == 1){
			mouthX1 = 380;	mouthX2 = 580;	mouthY1 = 230;	mouthY2 = 310; //level2일때 좌표 
		} else {
			mouthX1 = 450;	mouthX2 = 580;	mouthY1 = 280;	mouthY2 = 370; //level3일때 (임시)좌표
		}
		if((x > mouthX1 && x < mouthX2) && (y > mouthY1 && y < mouthY2)) {
			media.sound("showerFx");
			//피로도 감소
			//rate[1] = plusFor(rate[1], 5);
			//rate[2] = plusFor(rate[2], 5);
			
			if(rate[1] >= 95) { //피로도 증가, 성장도 감소
				System.out.print("이미 깨끗해요~ ");
				rate[2] = plusFor(rate[2], 10);
				rate[3] = minusFor(rate[3], 2);
				
			} else { //청결도 증가, 피로도 증가
				rate[1] = plusFor(rate[1], 5);
				rate[2] = plusFor(rate[2], 5);
			}
		}
		
		return rate;
	}
	
	//애정주기 메소드 
	public int[] lovingMethod(int[] rate) {
		
		System.out.println("초록이가 행복해요!");
		rate[3] = plusFor(rate[3], 5); //성장도 증가 
		
		return rate;
	}
	
	public int[] playingMethod(int[] rate) {
		//미니게임에 성공했을 시에 실행하도록 함
		
		System.out.println("초록이가 즐거워해요!");
		//성장도 증가 
		rate[3] = plusFor(rate[3], 5);
		//청결도 감소
		rate[1] = minusFor(rate[1], 5);
	
		return rate;
	}
	
	public int[] sleepingMethod(int[] rate) {
		
		//피로도 초기화 
		rate[2] = 0;
		//포만감 감소
		rate[0] = minusFor(rate[0], 10);

		
		return rate;
	}
	
	public void printResult(int growth, int time) {
		
		System.out.println("초록이의 성장도 : " + growth);
		System.out.println("살아남은 시간 : " + time + "초");
	}
	
	public void scoreChange(int score, User user) {
		if(score > user.getStage3Score()) {
			user.setStage3Score(score);
		}
		
		//return user;
	}
	
	public int scoreCalc(int level, int growthTime, int time) {
		return (level*1000 + growthTime*10 + time);
	}
	
	public String rankingMethod(User user, int score) {
		RankingDao rd = new RankingDao();
		ArrayList<Ranking> list = rd.readRankingList(3);
		Ranking r = new Ranking();
		Ranking rInit = new Ranking("빈랭킹", 0);
		String str = "";
		
		if(list.size() == 0) {
			list = new ArrayList<Ranking>();
			r.setName(user.getNickName());
			r.setScore(score);
			list.add(r);
			for (int i=1; i<5; i++) {
				list.add(rInit);
			}
		}
		else {
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
		for(int i=0; i<5; i++) {
			//System.out.println((i+1) + "등 : " + list.get(i).getName() + ", " + list.get(i).getScore());
			str += (i+1) + "등 : " + list.get(i).getName() + ", " + list.get(i).getScore() + "\n";
			}
		System.out.println(str);
		int result = rd.writeRankingList(list, 3);
		System.out.println("result : " + result);
		
		return str;
	}

}









