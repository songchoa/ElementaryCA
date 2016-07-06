package view;

import java.util.Scanner;
import model.ElementaryCA;
import controller.ConsoleController;

/**
 * The view for an elementary Cellular Automata Simulator
 * @author The Instructor
 * @author
 */
public class ConsoleView {

	//Dotted rectangle
	private static final char off = 0x2591;
	//Filled in rectangle
	private static final char on = 0x2588;
	private ConsoleController cc;
	private ElementaryCA ca;
	//The scanner for the entire view.
	private Scanner input = new Scanner(System.in);
	
	/**
	 * Default constructor. Initializes a Scanner for input from the console.
	 */
	public ConsoleView(){
	}
	
	/**
	 * Set up a Console View using input from the user. 
	 * @return True iff the user entered values creates a correct view.
	 */
	public boolean querySetup(){
		System.out.println("Welcome to the CA simulator! Please enter a rule number:");
		String sRule = input.nextLine();
		int rule = Integer.parseInt(sRule);
		if(!(rule >= 0 && rule <= 255)){
			//throw new IllegalArgumentException("Invalid input for rule number");
			System.err.println("Invalid Input. Program is terminating.");
			return false;
		}	
		System.out.println("Do you want to provide a width for the simulation? (y/n)");
		String provideWidth = input.nextLine();
		if(provideWidth.equals("y")){
			System.out.println("Please enter the width:");
			String inputWidth = input.nextLine();
			int w = Integer.parseInt(inputWidth);
			if(w <= 0) {
				System.err.println("Input width is invalid. Progam is terminating.");
				return false;
			}
			ca = new ElementaryCA(rule, w);
		} else {
			System.out.println("Do you want to provide a initialization for the simulation? (y/n)");
			String provideExample = input.nextLine();
			if(provideExample.equals("y")){
				System.out.println("Please provide a binary sequence for initialization (for example 001101)");
				String binarySeq = input.nextLine();
				byte[] initial = new byte[binarySeq.length()];
				for(int i = 0; i < initial.length; i++){
					if(binarySeq.charAt(i) == '0') {
						initial[i] = 0;
					} else if(binarySeq.charAt(i) == '1'){
						initial[i] = 1;
					} else {
						System.err.println("Input binary sequence is invalid. Program is terminating.");
						return false;
					}
				}
				
				ca = new ElementaryCA(rule,initial);
				
			} else {
				ca = new ElementaryCA(rule);
			}
		}
		
		cc = new ConsoleController(ca, this);
		return true;
		
	}
	
	/**
	 * Ask the user how many lines they would like to simulate
	 * @return 0 when the user enters an incorrect value, the value otherwise.
	 */
	private int queryLines(){
		System.out.println("How many iterations would you like to simulate? (Any non-valid number to quit.)");
		String sIter = input.nextLine();
		try{
			int iterations = Integer.parseInt(sIter);
			return iterations;
		} catch(Exception e){
			return 0;
		}
	}
	
	/**
	 * Repeatedly ask the user to simulate a number of lines until they enter an invalid input.
	 */
	public void simulate(){
		while(true){
			int iterations = queryLines();
			cc.runSimulation(iterations);
		}
		
	}
	
	/**
	 * Call this method to close this view. Prompts the user to press return first.
	 */
	public void stop(){
		System.out.println("Done Simulating. Press enter to exit.");
		input.nextLine();
		input.close();
		System.exit(0);
	}
	
	/**
	 * Draw a cell that is either on or off.
	 * @param isOn true for an on cell, otherwise false.
	 */
	public void drawCell(boolean isOn){
		if (isOn){
			System.out.print(on);
		} else {
			System.out.print(off);
		}
	}
	
	/**
	 * This method is called whenever the model has been changed!
	 */
	public void update(){
		int length = ca.getWidth();
		for(int l= 0; l < length; l++){
			drawCell(ca.getCell(l) == 1);
		}
		System.out.println();
	}
	
	/**
	 * Register a controller for this view.
	 * @param cc The controller to be registered.
	 */
	public void registerController(ConsoleController cc){
		this.cc = cc;
	}
	
	/**
	 * Register a model for this view.
	 * @param cc The view to be registered.
	 */
	public void registerModel(ConsoleController cc){
		this.cc = cc;
	}
	
}
