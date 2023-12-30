package us.malfeasant.ensign64.simulation;

import us.malfeasant.ensign64.Console;
import us.malfeasant.ensign64.Worker;
import us.malfeasant.ensign64.config.Configuration;

/**
 * Encompasses everything about a single machine and its (immediate) environment.
 *
 * @author Malfeasant
 */
public class Simulation {
	
	public Simulation(Configuration c) {
		assert (c != null) : "Fatal error: Simulation created with null Configuration.";
		
		Worker worker = new Worker(c);
		Console console = new Console();
	}
}
