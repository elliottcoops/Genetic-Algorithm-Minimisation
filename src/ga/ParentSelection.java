package ga;

import java.util.Random;

public class ParentSelection {
	
	int tournamentSize;
	Solution[] parents;
	
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
	
	public void setTournamentSize(int value) {
		
		this.tournamentSize = value;
		this.parents = new Solution[this.tournamentSize];
		
	}
	
	public void printTourament(Solution[] t) {
		for (int i = 0; i < t.length; i++) {
			System.out.format("%d ", t[i].value);
		}
		
		System.out.println();
	}
	
	public Solution getParent() {
		
		Solution[] tournament = new Solution[tournamentSize];
		Solution[] solutionArr = solutions.getSolutions();
		Solution candidateSolution = null;
		int size = solutions.getSize();
		
		Random rand = new Random();
		
		for (int i = 0; i < tournamentSize; i++) {
			
			candidateSolution = solutionArr[rand.nextInt(size)];
			
			while (checkIfCandidateExists(tournament, i, candidateSolution)) {
				candidateSolution = solutionArr[rand.nextInt(size)];
			}
			
			tournament[i] = candidateSolution;
			
		}
		
		Solution parent = fitnessFunction(tournament);
	
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
	
	// Find the fittest solution in the tournament
	public Solution fitnessFunction(Solution[] tournament) {
		
		Solution currentFittest = tournament[0];
		
		for (int i = 1; i < tournamentSize; i++) {
			
			if (objectiveFunction.evaluateExpression(tournament[i].value) > objectiveFunction.evaluateExpression(currentFittest.value)) {
				currentFittest = tournament[i];
			}
		}
		
		return currentFittest;
	}
	
}






