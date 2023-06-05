package ga;

import java.util.Random;

public class Solutions {
	
	public int domain;
	public int digitsCount;
	public int numberOfSolutions;
	
	Solution[] solutions;
	
	ObjectiveFunction objectiveFunction;
	
	private static Solutions solutionsObject = null;
	
	private Solutions() {}
	
	static Solutions getSolutionsObject() {
		
		if (solutionsObject == null) {
			solutionsObject = new Solutions();
		}
		
		return solutionsObject;
	}
	
	public Solution[] getSolutions() {
		return solutions;
	}
	
	public int getSize() {
		return numberOfSolutions;
	}
	
	public void setDomain(int val) {
		this.domain = val;
		digitsCount = (int) (Math.floor(Math.log(this.domain) / Math.log(2))) + 1; 
	}
	
	public void setNumberOfSolutions(int value) {
		this.numberOfSolutions = value;
		solutions = new Solution[numberOfSolutions];
	}
	
	public void printSols() {
		
		for (int i = 0; i < numberOfSolutions;i++) {
			System.out.format("Solution %d: %d with value of:  %f\n", i, solutions[i].value, objectiveFunction.evaluateExpression(solutions[i].value));
		}
		System.out.println();
		
	}
	
	public Solution getLowestValue() {
		
		Solution currentLowest = solutions[0];
		
		for (int i = 1; i < numberOfSolutions; i++) {
			
			if (objectiveFunction.evaluateExpression(solutions[i].value) < objectiveFunction.evaluateExpression(currentLowest.value)) {
				currentLowest = solutions[i];
			}
		}
	
		return currentLowest;
	}
	
	// Add the solutions with a randomised 
	public void addSolutions() {
		
		Random rand = new Random();
		
		int initalValue = 0;

		for (int i = 0; i < numberOfSolutions; i++) {
			
			Solution solution = new Solution();
			
			Boolean conflictingValue = true;
			initalValue = 0;
			while (conflictingValue || initalValue == 0) {
				initalValue = rand.nextInt(1,domain);
				conflictingValue = checkConflictingValue(initalValue, i);
			}
			
			solution.setInitalValue(initalValue);
			solutions[i] = solution;
			
		}	
			
	}
	
	public void setNewValue(Solution parent, int value) {
		
		for (int i = 0; i < numberOfSolutions; i++) {
			
			Solution tempSolution = solutions[i];
			
			if (tempSolution.equals(parent)) {
				tempSolution.value = value;
			} 
			
		}
		
	}
		
	public Boolean checkConflictingValue(int initalValue, int currentLength) {
		
		Solution solution;
		
		for (int i = 0; i < currentLength-1; i++) {
			
			solution = solutions[i];
			
			if (solution.value == initalValue)
				return true;
			
		}
		
		return false;
	}
	
}
