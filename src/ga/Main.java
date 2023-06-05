package ga;

import java.util.ArrayList;
import java.util.Random;

public class Main {
	
	public static void main(String[] args) {
		
		Solutions solutions = new Solutions();
		GeneticOperators geneticOperators = new GeneticOperators();
		ParentSelection parentSelection = new ParentSelection();
		ObjectiveFunction objectiveFunction = new ObjectiveFunction();
		
		parentSelection.objectiveFunction = objectiveFunction;
		parentSelection.solutions = solutions;
		solutions.objectiveFunction = objectiveFunction;
		geneticOperators.solutions = solutions;
		geneticOperators.parentSelection = parentSelection;
		
		// Initialise the solutions array
		solutions.addSolutions();
		
		int digitsCount = (int) (Math.floor(Math.log(solutions.domain) / Math.log(2))) + 1; 
		
		for (int i = 0; i < 1000; i++) {
			
			// Parent selection using tournament selection
			Solution[] parents = geneticOperators.parentSelection();
			
			// Crossover and mutation
			geneticOperators.crossover(parents[0], parents[1]);
			geneticOperators.mutation(parents[0], parents[1], digitsCount);
			
			// Set new values
			solutions.setNewValue(parents[0], geneticOperators.newSolution1);
			solutions.setNewValue(parents[1], geneticOperators.newSolution2);
			
		}
		
		Solution minimisedValue = solutions.getLowestValue();
		
		System.out.format("The minimised of the function is: %d\n", minimisedValue.value);
		
	}
	
}
