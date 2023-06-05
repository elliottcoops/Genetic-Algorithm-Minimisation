package ga;

import java.util.Random;

public class Mutation {
	
	Crossover crossover;
	
	public String mutateString(String bitstring) {
		
		Random rand = new Random();
		
		for (int i = 0; i < bitstring.length(); i++) {
			if (rand.nextInt(4) == 1) {
				
				if (bitstring.charAt(i) == '1') {
					bitstring = bitstring.substring(0, i) + '0' + bitstring.substring(i + 1);
				}
				
				else {
					bitstring = bitstring.substring(0, i) + '1' + bitstring.substring(i + 1);
				}
				
			}
			
		}
				
		return bitstring;
	}

}
