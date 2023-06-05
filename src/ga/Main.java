package ga;

import java.util.ArrayList;
import java.util.Random;

public class Main {
	
	public static void main(String[] args) {
		
		// Create objects
		Solutions solutions = Solutions.getSolutionsObject();
		GeneticOperators geneticOperators = new GeneticOperators();
		ParentSelection parentSelection = new ParentSelection();
		ObjectiveFunction objectiveFunction = new ObjectiveFunction();
		
		// Set object references
		parentSelection.objectiveFunction = objectiveFunction;
		parentSelection.solutions = solutions;
		solutions.objectiveFunction = objectiveFunction;
		geneticOperators.solutions = solutions;
		geneticOperators.parentSelection = parentSelection;
		
		geneticOperators.setCrossoverRate(6);
		geneticOperators.setMutationRate(2);
		
		// Set inital population
		solutions.setNumberOfSolutions(20);
		solutions.setDomain(100);
		solutions.addSolutions();
		
		parentSelection.setTournamentSize(8);
		
		for (int i = 0; i < 10000; i++) {
			
			// Parent selection
			Solution[] parents = geneticOperators.parentSelection();
			
			// Crossover
			geneticOperators.crossover(parents[0], parents[1]);
			
			//Mutation
			geneticOperators.mutation();
			
			// Update population
			solutions.setNewValue(parents[0], geneticOperators.newSolution1);
			solutions.setNewValue(parents[1], geneticOperators.newSolution2);
			
		}
		
		// Find the minimised value
		Solution minimisedValue = solutions.getLowestValue();
		
		// Output to standard output stream
		System.out.format("The minimised x of the function is: %d\n", minimisedValue.value);
		
	}
	
}
