package us.malfeasant.ensign64.config;

/**
 * Represents the powerline frequency in cycles per second, which drives the RTC in the CIAs.
 * NA- 60Hz, North America (and parts of South America & Japan)
 * EU- 50Hz, Europe (and most of Africa, Asia, some of South America)
 * 
 * In contrast with the Crystal type, this is not strictly part of the machine, but its environment-
 * no difference in hardware was needed to switch between, but there was a configurable bit in the
 * CIA chip that divided these ticks by either 5 or 6 to result in 10 ticks per second.  If this
 * setting was mismatched, the RTC would run either fast or slow.
 * 
 * @author Malfeasant
 */
public enum Power {
	NA(60), EU(50);
	
	Power(int c) {
		cycles = c;
	}
	public final int cycles;
}
