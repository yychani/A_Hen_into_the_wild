package inSeongJo.aHenIntoTheWild.controller;

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
			rate[1] = plusFor(rate[1], 5);
			rate[2] = plusFor(rate[2], 5);
			
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
		//rate[0] = minusFor(rate[0], 5); //������ ���� 
		//rate[1] = minusFor(rate[1], 5); //û�ᵵ ����
		rate[3] = plusFor(rate[3], 3); //���嵵 ���� 
		
		return rate;
	}
	
	public int[] playingMethod(int[] rate) {
		//�̴ϰ��ӿ� �������� �ÿ� �����ϵ��� ��
		
		System.out.println("�ʷ��̰� ��ſ��ؿ�!");
		//���嵵 ���� 
		rate[2] = plusFor(rate[2], 3);
		//û�ᵵ ����
		rate[1] = minusFor(rate[1], 3);
	
		return rate;
	}
	
	public int[] sleepingMethod(int[] rate) {
		
		//�Ƿε� �ʱ�ȭ 
		rate[2] = 0;
		
		//������ ����
		rate[0] = minusFor(rate[0], 10);

		
		return rate;
	}

}









