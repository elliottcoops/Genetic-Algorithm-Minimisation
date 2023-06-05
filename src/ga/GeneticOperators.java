package ga;

import java.util.Random;

public class GeneticOperators {
	// int digitsCount = (int) (Math.floor(Math.log(solutions.domain) / Math.log(2))) + 1; 
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
	
	public void crossover(Solution parent1, Solution parent2) {
		crossover = new Crossover();
		crossover.solutions = solutions;
		crossover.setup(parent1.value,parent1.value);
		crossover.applyCrossover();
		
	}
	
	public void mutation(Solution parent1, Solution parent2) {
		
		mutation = new Mutation();
		mutation.crossover = crossover;
		
		Random rand = new Random();
		
		if(!(rand.nextInt(10) > 1)){
			newSolution1 = crossover.convertToInt(mutation.mutateString(crossover.new_parent1));
			newSolution2 = crossover.convertToInt(mutation.mutateString(crossover.new_parent2));
		} else {
			newSolution1 = crossover.convertToInt(crossover.new_parent1);
			newSolution2 = crossover.convertToInt(crossover.new_parent2);
		}
		
	}
	
}
