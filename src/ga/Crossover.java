package ga;

import java.util.Random;

public class Crossover {
	
	public String binaryParent1 = "";
	public String binaryParent2 = "";
	public String newBinaryParent1 = "";
	public String newBinaryParent2 = "";
	public int newParent1Value;
	public int newParent2Value;
	public int totalLength;
	public int crossoverRate;

	Solutions solutions;
	
	public Crossover(int cr) {
		this.crossoverRate = cr;
	}
	
	// Setup some initial values
	public void setup(int parent1, int parent2) {
			
		totalLength = solutions.digitsCount;
		binaryParent1 = convertToBinaryString(parent1);
		binaryParent2 = convertToBinaryString(parent2);	
		
	}
	
	// Convert some integer to a binary string
	public String convertToBinaryString(int parent) {
			
		String binary = "";
		int remainder;
		int zerosToPrepend;
		
		while (parent > 0) {
			
            remainder = parent % 2;
            binary = remainder + binary;
            parent /= 2;
            
        }
				
		zerosToPrepend = totalLength - binary.length();
		
		for (int i = 0; i < zerosToPrepend; i++) {binary = 0 + binary;}
        
        return binary;
        
	}
	
	// Convert the binary string to an integer
	public int convertToInt(String binaryStr) {
		
		int total = 0;
		
		String revstr = new StringBuilder(binaryStr).reverse().toString();
		
		for (int i = 0; i < revstr.length(); i++) {
			
			if (revstr.charAt(i) == '1')
				total += Math.pow(2, i);
			
		}
		
		if (total > solutions.domain || total == 0) {
			
			Random rand = new Random();
			total = rand.nextInt(1,solutions.domain);
			
		} 
		
		return total;
			
	}
	
	// Print binary strings -- used / for debugging
	public void printStrings() {
		
		System.out.format("String1: %s\n", binaryParent1);
		System.out.format("String2: %s\n", binaryParent2);
		
	}
	
	// Apply crossover method -- Allows us to add in additional methods later on
	public void applyCrossover() {
		
		Random rand = new Random();
		
		if (!(rand.nextInt(10) > crossoverRate))
			
			uniformCrossover();
		
		else {
			
			newBinaryParent1 = binaryParent1;
			newBinaryParent2 = binaryParent2;
			
		}
		
	}
	
	// Crossover using uniform crossover method
	public void uniformCrossover() {
		
		String template = generateRandomisedTemplate();
		
		char temp1;
		char temp2;
		
		for (int i = 0; i < totalLength; i++) {
			
			temp1 = binaryParent1.charAt(i);
			temp2 = binaryParent2.charAt(i);
			
			if (template.charAt(i) == '0') {
				
				newBinaryParent1 += temp2;
				newBinaryParent2 += temp1;
				
			} else if(template.charAt(i) == '1') {
				
				newBinaryParent1 += temp1;
				newBinaryParent2 += temp2;
				
			}
			
		}
		
	}
	
	// Generate a random template used in uniform crossover
	public String generateRandomisedTemplate() {
		
		String template = "";
		
		Random rand = new Random();
		
		for (int i = 0; i < totalLength; i++) {
			
			template += rand.nextInt(2);
			
		}
		
		return template;
		
	}	
		
}



