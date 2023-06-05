package GeneticAlgorithm;

import java.util.Random;

public class GeneticOperators {
	
	int newSolution1 = 0;
	int newSolution2 = 0;
	
	Solutions solutions;
	ParentSelection parentSelection;
	Crossover crossover;
	Mutation mutation;
	
	public Solution[] parentSelection() {
		
		Solution[] parents = parentSelection.getParents();
		
		return parents;
			
	}
	
	public void crossover(Solution parent1, Solution parent2, int digitsToCount, int domain) {
		
		crossover = new Crossover(parent1.value,parent2.value,digitsToCount);
		crossover.applyCrossover();
		
	}
	
	public void mutation(Solution parent1, Solution parent2, int digitsToCount, int domain) {
		
		mutation = new Mutation();
		mutation.crossover = crossover;
		
		Random rand = new Random();
		
		if(!(rand.nextInt(10) > 1)){
			newSolution1 = crossover.convertToInt(mutation.mutateString(crossover.new_parent1) ,domain);
			newSolution2 = crossover.convertToInt(mutation.mutateString(crossover.new_parent2) ,domain);
		} else {
			newSolution1 = crossover.convertToInt(crossover.new_parent1 ,domain);
			newSolution2 = crossover.convertToInt(crossover.new_parent2 ,domain);
		}
		
	}
	
	public void setNewSolutions(Solution parent, int newValue) {
		
		
		
	}

}
