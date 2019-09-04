package inSeongJo.aHenIntoTheWild.controller;

import java.util.ArrayList;
import java.util.Comparator;

import inSeongJo.aHenIntoTheWild.model.dao.RankingDao;
import inSeongJo.aHenIntoTheWild.model.vo.Ranking;
import inSeongJo.aHenIntoTheWild.model.vo.User;

public class Stage03Manager {
	
	//�����ϴ� �޼ҵ� 
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
	
	//�����ϴ� �޼ҵ� 
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

	
	//�� ��ư���� ����ϴ� �޼ҵ�� ����
	public int[] eatingMethod(int[] rate, int x, int y) {
		if((x > 460 && x < 570) && (y > 300 && y < 400)) {
			//������ ����
			rate[0] = plusFor(rate[0], 5);
			//�Ƿε� ����
			rate[2] = plusFor(rate[2], 5);
			//���嵵 ����
			rate[3] = plusFor(rate[3], 2);
		}
		return rate;
	}
	
	public int[] cleaningMethod(int[] rate, int x, int y) {
		if((x > 460 && x < 570) && (y > 300 && y < 400)) {
			//�Ƿε� ����
			//rate[1] = plusFor(rate[1], 5);
			//rate[2] = plusFor(rate[2], 5);
			
			if(rate[1] >= 95) { //�Ƿε� ����, ���嵵 ����
				System.out.print("�̹� �����ؿ�~ ");
				rate[2] = plusFor(rate[2], 10);
				rate[3] = minusFor(rate[3], 2);
				
			} else { //û�ᵵ ����, �Ƿε� ����
				rate[1] = plusFor(rate[1], 5);
				rate[2] = plusFor(rate[2], 5);
			}
		}
		
		return rate;
	}
	
	public int[] lovingMethod(int[] rate) {
		
		System.out.println("�ʷ��̰� �ູ�ؿ�!");
		rate[3] = plusFor(rate[3], 5); //���嵵 ���� 
		
		return rate;
	}
	
	public int[] playingMethod(int[] rate) {
		//�̴ϰ��ӿ� �������� �ÿ� �����ϵ��� ��
		
		System.out.println("�ʷ��̰� ��ſ��ؿ�!");
		//���嵵 ���� 
		rate[3] = plusFor(rate[3], 5);
		//û�ᵵ ����
		rate[1] = minusFor(rate[1], 5);
	
		return rate;
	}
	
	public int[] sleepingMethod(int[] rate) {
		
		//�Ƿε� �ʱ�ȭ 
		rate[2] = 0;
		//������ ����
		rate[0] = minusFor(rate[0], 10);

		
		return rate;
	}
	
	public void printResult(int growth, int time) {
		
		System.out.println("�ʷ����� ���嵵 : " + growth);
		System.out.println("��Ƴ��� �ð� : " + time + "��");
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
	
	public void rankingMethod(User user, int score) {
		RankingDao rd = new RankingDao();
		ArrayList<Ranking> list = rd.readRankingList(3);
		Ranking r = new Ranking();
		Ranking rInit = new Ranking("��ŷ", 0);
		
		if(list == null) {
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
			System.out.println((i+1) + "�� : " + list.get(i).getName() + ", " + list.get(i).getScore());
			}
		
		int result = rd.writeRankingList(list, 3);
		System.out.println("result : " + result);
	}

}









