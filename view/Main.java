package view;

public class Main {
	public static void main(String[] args){
		ConsoleView cv= new ConsoleView();
		//Try to set up a simulation using user input into the view.
		if(cv.querySetup()){
			cv.simulate();
		} else {
			//If the setup didn't work, stop the view.
			cv.stop();
		}
	}
}
