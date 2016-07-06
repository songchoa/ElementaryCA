package model;

import java.util.Random;

import view.ConsoleView;

/**
 * This class provides back-end logic (model) for this Elementary Cellular Automaton
 * simulator.
 * @author xchu
 *
 */
public class ElementaryCA {

	/**
	 * the width of the simulation
	 */
	private int width;
	
	/**
	 * the rule number of the simulation
	 */
	private int rule;
	
	/**
	 * a byte array to store the state (0 or 1) of each cell
	 */
	private byte[] cell;
	
	/**
	 * the console view for each iteration
	 */
	private ConsoleView cv;

	/**
	 * constructor using default settings
	 * @param ruleNumber - a valid rule for simulation
	 */
	public ElementaryCA(int ruleNumber) {
		width = 32;
		rule = ruleNumber;
		cell = new byte[32];
		for (int i = 0; i < width; i++) {
			cell[i] = 0;
		}
		cell[15] = 1;
	}
	
	/**
	 * constructor which lets user to specify rule number and simulation width.
	 * AND the initialization is according to randomness.
	 * @param ruleNumber - a valid rule for simulation
	 * @param width - provide a width for simulation
	 */
	public ElementaryCA(int ruleNumber, int width) {
		this.width = width;
		rule = ruleNumber;
		cell = new byte[width];
		Random ran = new Random();
		for (int i = 0; i < width; i++) {
			cell[i] = (byte) ran.nextInt(2);
		}
	}

	/**
	 * constructor which lets user to provide a rule and initialize the first
	 * line  
	 * @param ruleNumber - a valid rule for simulation
	 * @param line - the initialization array for first line
	 */
	public ElementaryCA(int ruleNumber, byte[] line) {
		this.width = line.length;
		rule = ruleNumber;
		cell = new byte[width];
		for (int i = 0; i < width; i++) {
			cell[i] = line[i];
		}
	}

	/**
	 * The width getter
	 * @return width - the width for current simulation
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * The rule getter
	 * @return rule - the valid rule number for simulation
	 */
	public int getRule() {
		return rule;
	}

	/**
	 * The cell state getter
	 * @param pos - position of a cell in the cell array
	 * @return return the state (0 or 1) of that specific cell
	 */
	public byte getCell(int pos) {
		return cell[pos];
	}

	/**
	 * Register the simulated view to Console view
	 * @param cv - the view used for current simulation
	 */
	public void registerView(ConsoleView cv) {
		this.cv = cv;

	}
	/**
	 * The simulation process for Elementary Cellular Automaton. 
	 * Basic rules provided here and used for complex rules. 
	 * For example, rule 30 is consist of rule 16, 8, 4, and 2.
	 */
	public void simulate() {
		// convert rule number into binary byte array;
		byte[] ruleBinary = new byte[8];
		int ruleNum = rule;
		for (int i = 0; i < 8; i++) {
			ruleBinary[i] = (byte) (ruleNum & 0x1);
			ruleNum = ruleNum >> 1;
		}

		// for each byte in ruleBinary array, generate next line
		byte[] copy = new byte[width]; // create new copy array
		for (int i = 0; i < width; i++) {
			copy[i] = 0;
		}
		/////////////////////////////////////////////////////////////////////////////////////
		//rule 1
		if (ruleBinary[0] == 1) {
			if (cell[width - 1] == 0 && cell[0] == 0 && cell[1] == 0) {
				copy[0] = 1;
			}
			for (int j = 0; j < (width - 2); j++) {
				if (cell[j] == 0 && cell[j+1] == 0 && cell[j+2] == 0) {
					copy[j+1] = 1;
				}
			}
			if (cell[width - 2] == 0 && cell[width - 1] == 0 && cell[0] == 0) {
				copy[width-1] = 1;
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////
		//rule 2
		if (ruleBinary[1] == 1) {
			if (cell[width - 1] == 0 && cell[0] == 0 && cell[1] == 1) {
				copy[0] = 1;
			}
			for (int j = 0; j < (width - 2); j++) {
				if (cell[j] == 0 && cell[j+1] == 0 && cell[j+2] == 1) {
					copy[j+1] = 1;
				}
			}
			if (cell[width - 2] == 0 && cell[width - 1] == 0 && cell[0] == 1) {
				copy[width-1] = 1;
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////
		//rule 4
		if (ruleBinary[2] == 1) {
			if (cell[width - 1] == 0 && cell[0] == 1 && cell[1] == 0) {
				copy[0] = 1;
			}
			for (int j = 0; j < (width - 2); j++) {
				if (cell[j] == 0 && cell[j+1] == 1 && cell[j+2] == 0) {
					copy[j+1] = 1;
				}
			}
			if (cell[width - 2] == 0 && cell[width - 1] == 1 && cell[0] == 0) {
				copy[width-1] = 1;
			}
		}
		
		/////////////////////////////////////////////////////////////////////////////////////
		//rule 8
		if (ruleBinary[3] == 1) {
			if (cell[width - 1] == 0 && cell[0] == 1 && cell[1] == 1) {
				copy[0] = 1;
			}
			for (int j = 0; j < (width - 2); j++) {
				if (cell[j] == 0 && cell[j+1] == 1 && cell[j+2] == 1) {
					copy[j+1] = 1;
				}
			}
			if (cell[width - 2] == 0 && cell[width - 1] == 1 && cell[0] == 1) {
				copy[width-1] = 1;
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////
		//rule 16
		if (ruleBinary[4] == 1) {
			if (cell[width - 1] == 1 && cell[0] == 0 && cell[1] == 0) {
				copy[0] = 1;
			}
			for (int j = 0; j < (width - 2); j++) {
				if (cell[j] == 1 && cell[j+1] == 0 && cell[j+2] == 0) {
					copy[j+1] = 1;
				}
			}
			if (cell[width - 2] == 1 && cell[width - 1] == 0 && cell[0] == 0) {
				copy[width-1] = 1;
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////
		//rule 32
		if (ruleBinary[5] == 1) {
			if (cell[width - 1] == 1 && cell[0] == 0 && cell[1] == 1) {
				copy[0] = 1;
			}
			for (int j = 0; j < (width - 2); j++) {
				if (cell[j] == 1 && cell[j+1] == 0 && cell[j+2] == 1) {
					copy[j+1] = 1;
				}
			}
			if (cell[width - 2] == 1 && cell[width - 1] == 0 && cell[0] == 1) {
				copy[width-1] = 1;
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////
		//rule 64
		if (ruleBinary[6] == 1) {
			if (cell[width - 1] == 1 && cell[0] == 1 && cell[1] == 0) {
				copy[0] = 1;
			}
			for (int j = 0; j < (width - 2); j++) {
				if (cell[j] == 1 && cell[j+1] == 1 && cell[j+2] == 0) {
					copy[j+1] = 1;
				}
			}
			if (cell[width - 2] == 1 && cell[width - 1] == 1 && cell[0] == 0) {
				copy[width-1] = 1;
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////
		//rule 128
		if (ruleBinary[7] == 1) {
			if (cell[width - 1] == 1 && cell[0] == 1 && cell[1] == 1) {
				copy[0] = 1;
			}
			for (int j = 0; j < (width - 2); j++) {
				if (cell[j] == 1 && cell[j+1] == 1 && cell[j+2] == 1) {
					copy[j+1] = 1;
				}
			}
			if (cell[width - 2] == 1 && cell[width - 1] == 1 && cell[0] == 1) {
				copy[width-1] = 1;
			}
		}
		//Refresh cell[]
		for (int i = 0; i < width; i++) {
			cell[i] = copy[i];
		}
		cv.update();
	}
}
