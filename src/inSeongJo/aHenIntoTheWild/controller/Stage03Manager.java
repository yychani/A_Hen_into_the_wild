package inSeongJo.aHenIntoTheWild.controller;

public class Stage03Manager {
	
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
	
	//감소하는 메소드 
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
	public int[] eatingMethod(int[] rate, int x, int y) {
		if((x > 460 && x < 570) && (y > 300 && y < 400)) {
			//포만감 증가
			rate[0] = plusFor(rate[0], 5);
			//피로도 증가
			rate[2] = plusFor(rate[2], 5);
			//성장도 증가
			rate[3] = plusFor(rate[3], 2);
		}
		return rate;
	}
	
	public int[] cleaningMethod(int[] rate, int x, int y) {
		if((x > 460 && x < 570) && (y > 300 && y < 400)) {
			//피로도 감소
			rate[1] = plusFor(rate[1], 5);
			rate[2] = plusFor(rate[2], 5);
			
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
	
	public int[] lovingMethod(int[] rate) {
		
		System.out.println("초록이가 행복해요!");
		//rate[0] = minusFor(rate[0], 5); //포만감 감소 
		//rate[1] = minusFor(rate[1], 5); //청결도 감소
		rate[3] = plusFor(rate[3], 3); //성장도 증가 
		
		return rate;
	}
	
	public int[] playingMethod(int[] rate) {
		//미니게임에 성공했을 시에 실행하도록 함
		
		System.out.println("초록이가 즐거워해요!");
		//성장도 증가 
		rate[2] = plusFor(rate[2], 3);
		//청결도 감소
		rate[1] = minusFor(rate[1], 3);
	
		return rate;
	}
	
	public int[] sleepingMethod(int[] rate) {
		
		//피로도 초기화 
		rate[2] = 0;
		
		//포만감 감소
		rate[0] = minusFor(rate[0], 10);

		
		return rate;
	}

}









