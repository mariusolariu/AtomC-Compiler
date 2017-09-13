package La;

public class Factory {
	
	public static State getNextStateObj(Integer nextStateId) {
		
		switch (nextStateId) {
			case 0 :
				return State0.getInstance();
			
			case 1 :
				return State1.getInstance();
			
			case 2 :
				return State2.getInstance();
			
			case 3 :
				return State3.getInstance();
			
			case 4 :
				return State4.getInstance();
			
			case 5 :
				return State5.getInstance();
			
			case 6 :
				return State6.getInstance();
			
			case 7 :
				return State7.getInstance();
			
			case 8 :
				return State8.getInstance();
			
			case 9 :
				return State9.getInstance();
			
			case 10 :
				return State10.getInstance();
			
			case 11 :
				return State11.getInstance();
			
			case 12 :
				return State12.getInstance();
			
			case 13 :
				return State13.getInstance();
			
			case 14 :
				return State14.getInstance();
			
			case 15 :
				return State15.getInstance();
			
			case 16 :
				return State16.getInstance();
			
			case 17 :
				return State17.getInstance();
			
			case 18 :
				return State18.getInstance();
			
			case 19 :
				return State19.getInstance();
			
			case 20 :
				return State20.getInstance();
			
			case 21 :
				return State21.getInstance();
			
			case 22 :
				return State22.getInstance();
			
			case 23 :
				return State23.getInstance();
			
			case 24 :
				return State24.getInstance();
			
			case 25 :
				return State25.getInstance();
			
			case 26 :
				return State26.getInstance();
			
			case 27 :
				return State27.getInstance();
			
			case 28 :
				return State28.getInstance();
			
			case 29 :
				return State29.getInstance();
			
		
			
			case 31 :
				return State31.getInstance();
			
			case 32 :
				return State32.getInstance();
			
			case 33 :
				return State33.getInstance();
			
			case 34 :
				return State34.getInstance();
			
			case 35 :
				return State35.getInstance();
			
			case 36 :
				return State36.getInstance();
			
			case 37 :
				return State37.getInstance();
			
			case 38 :
				return State38.getInstance();
			
			case 39 :
				return State39.getInstance();
			
			case 40 :
				return State40.getInstance();
			
			case 41 :
				return State41.getInstance();
			
			case 42 :
				return State42.getInstance();
			
			case 43 :
				return State43.getInstance();
			
			case 44 :
				return State44.getInstance();
			
			case 45 :
				return State45.getInstance();
			
			case 46 :
				return State46.getInstance();
			
			case 47 :
				return State47.getInstance();
			
			case 48:
				return State48.getInstance();
				
			case 49 :
				return State49.getInstance();
			
			case 50 :
				return State50.getInstance();
			
			case 51 :
				return State51.getInstance();
			
			case 52 :
				return State52.getInstance();
			
			case 53:
				return State53.getInstance();
				
			case 54 :
				return State54.getInstance();
		
			
			case 55:
				return State55.getInstance();
				
			case 56 :
				return State56.getInstance();
			
			case 57 :
				return State57.getInstance();
			
			case 58:
				return State58.getInstance();
			
			default:
				return State59.getInstance();
			
		}
		
	}
}
