package controller;

import view.ConsoleView;
import model.ElementaryCA;

/**
 * The controller that interfaces between the view and the model.
 * 
 * @author The Instructor
 * @author 
 */
public class ConsoleController {
	
	private ElementaryCA ca;
	private ConsoleView cv;

	/**
	 * A basic controller that interfaces between a ConsoleView and an Elementary CA object.
	 * @param ca The elementary ca
	 * @param cv The console view
	 */
	public ConsoleController(ElementaryCA ca, ConsoleView cv) {
		if (ca != null) {
			this.ca = ca;
		} else {
			throw new IllegalArgumentException("The requested CA is null.");
		}
		if (cv != null) {
			this.cv = cv;
		} else {
			throw new IllegalArgumentException("The requested view is null.");
		}
		ca.registerView(cv);
	}

	/**
	 * Run the simulation for a number of iterations.
	 * @param iterations
	 */
	public void runSimulation(int iterations){
		//run through iterations
		if(iterations > 0){
			for(int i = 0; i < iterations; i++){	
				ca.simulate();
			}
		} else {
			cv.stop();
		}
	}
}
