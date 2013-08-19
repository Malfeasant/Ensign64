package us.malfeasant.ensign64;

import us.malfeasant.ensign64.config.PixelClockRate;
import us.malfeasant.ensign64.config.PowerRate;
import us.malfeasant.ensign64.config.Stop;

public class Machine {
	private PixelClockRate pixelRate;
	private PowerRate powerRate;
	
	private int cycleCount;
	private long totalCycles;
	
	public Machine() {
		
	}
	
	/**
	 * makes the machine run some amount of time
	 * @param stop - when to stop running
	 * @return how much time elapsed
	 */
	public double runTo(Stop stop) {
		return 0;
	}
}
