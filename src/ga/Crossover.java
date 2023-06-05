package ga;

import java.util.Random;

public class Crossover {
	
	public String binaryParent1 = "";
	public String binaryParent2 = "";
	public int totalLength;
	
	public String new_parent1 = "";
	public String new_parent2 = "";
	
	public int newInt_parent1;
	public int newInt_parent2;
	
	
	public Crossover(int parent1, int parent2, int domain) {
		totalLength = domain;
		binaryParent1 = convertToBinaryString(parent1);
		binaryParent2 = convertToBinaryString(parent2);	
	}
	
	public String convertToBinaryString(int parent) {
		
		String binary = "";
		
		while (parent > 0) {
            int remainder = parent % 2;
            binary = remainder + binary;
            parent /= 2;
        }
		
		int zerosToPrepend = totalLength - binary.length();
		
		for (int i = 0; i < zerosToPrepend; i++) {
			binary = 0 + binary;
		}
        
        return binary;
        
	}
	
	public void printStrings() {
		System.out.format("String1: %s\n", binaryParent1);
		System.out.format("String2: %s\n", binaryParent2);
	}
	
	public void uniformCrossover() {
		
		String template = generateRandomisedTemplate();
		
		new_parent1 = "";
		new_parent2 = "";
		
		char temp1;
		char temp2;
		
		for (int i = 0; i < totalLength; i++) {
			
			temp1 = binaryParent1.charAt(i);
			temp2 = binaryParent2.charAt(i);
			
			if (template.charAt(i) == '0') {
				new_parent1 += temp2;
				new_parent2 += temp1;
			} else if(template.charAt(i) == '1') {
				new_parent1 += temp1;
				new_parent2 += temp2;
			}}
		
	}
	
	public void applyCrossover() {
		
		Random rand = new Random();
		
		if (!(rand.nextInt(10) > 5)){
			uniformCrossover();
		} else {
			new_parent1 = binaryParent1;
			new_parent1 = binaryParent2;	
		}
	}
	
	public int convertToInt(String binaryStr, int domain) {
		
		int value = domain;
		int total = 0;
		
		String revstr = new StringBuilder(binaryStr).reverse().toString();
		
		for (int i = 0; i < revstr.length(); i++) {
			
			if (revstr.charAt(i) == '1')
				total += Math.pow(2, i);
		}
		
		if (total > domain) {
			Random rand = new Random();
			total = rand.nextInt(32);
			
		} 
		
		return total;
			
	}
	
	public String generateRandomisedTemplate() {
		String template = "";
		
		Random rand = new Random();
		
		for (int i = 0; i < totalLength; i++) {
			template += rand.nextInt(2);
		}
		return template;
	}	
}



