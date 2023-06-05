package ga;

import java.util.Random;

public class GeneticOperators {

	public int newSolution1 = 0;
	public int newSolution2 = 0;
	public int crossoverRate;
	public int mutationRate;
	
	Solutions solutions;
	ParentSelection parentSelection;
	Crossover crossover;
	Mutation mutation;
	
	public Solution[] parentSelection() {
		
		Solution[] parents = parentSelection.getParents();
		
		return parents;
			
	}
	
	public void setCrossoverRate(int cr) {
		this.crossoverRate = cr;
	}
	
	public void setMutationRate(int mr) {
		this.mutationRate = mr;
	}
	
	// Apply crossover if we meet some crossover rate
	public void crossover(Solution parent1, Solution parent2) {
							
		crossover = new Crossover(crossoverRate);
		crossover.crossoverRate = this.crossoverRate;
		crossover.solutions = solutions;
		crossover.setup(parent1.value,parent1.value);
		crossover.applyCrossover();
			
	}
	
	// Apply mutation if we meet some mutation rate
	public void mutation() {
		
		mutation = new Mutation(mutationRate);
		mutation.crossover = crossover;
		mutation.mutationRate = this.mutationRate;
		
		newSolution1 = crossover.convertToInt(mutation.mutateString(crossover.newBinaryParent1));
		newSolution2 = crossover.convertToInt(mutation.mutateString(crossover.newBinaryParent2));
		
	}
	
}
