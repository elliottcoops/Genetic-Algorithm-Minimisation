package GeneticAlgorithm;

import java.util.Random;

public class ParentSelection {
	
	// Using tournament selection
	
	int tournamentSize = 4;
	
	Solution[] parents = new Solution[2];
	
	ObjectiveFunction objectiveFunction;
	Solutions solutions;
	
	public Solution[] getParents() {
		
		Solution parent1 = getParent();
		Solution parent2 = getParent();
		
		while (parent1.equals(parent2)) {
			parent2 = getParent();
		}
		
		parents[0] = parent1;
		parents[1] = parent2;
		
		return parents;
		 
	}
	
	public void printTourament(Solution[] t) {
		for (int i = 0; i < t.length; i++) {
			System.out.format("%d ", t[i].value);
		}
		
		System.out.println();
	}
	
	public Solution getParent() {
		
		Solution[] tournament = new Solution[tournamentSize];
		Random rand = new Random();
		Solution candidateSolution = null;
		
		
		int size = solutions.getSize();
		
		Solution[] solutionArr = solutions.getSolutions();
		
		
		for (int i = 0; i < tournamentSize; i++) {
			
			candidateSolution = solutionArr[rand.nextInt(size)];
			
			while (checkIfCandidateExists(tournament, i, candidateSolution)) {
				candidateSolution = solutionArr[rand.nextInt(size)];
			}
			
			tournament[i] = candidateSolution;
			
		}
		
		
		Solution parent = fitnessFunction(tournament);
//		printTourament(tournament);
		
		return parent;
		
	}
	
	public Boolean checkIfCandidateExists(Solution[] tournmanet, int currentLength, Solution candidate) {
		
		if (currentLength == 0)
			return false;
		
		for (int i = 0; i < currentLength; i++) {
						
			Solution current = tournmanet[i];
			
			if (current.equals(candidate)) {
				return true;
			}
		}
		return false;
	}
	
	
	public Solution fitnessFunction(Solution[] tournament) {
		
		// Pick the best solution from the tournament
		Solution currentFittest = tournament[0];
		
		for (int i = 1; i < tournamentSize; i++) {
			
			if (objectiveFunction.evaluateExpression(tournament[i].value) > objectiveFunction.evaluateExpression(currentFittest.value)) {
				currentFittest = tournament[i];
			}
		}
		
		return currentFittest;
	}
	
}






