package us.malfeasant.ensign64.config;

/**
 * The CIA chips used the ac powerline frequency to drive their realtime clocks.
 */
public enum PowerRate {
	NA(60), EU(50);
	
	public final double cyclesPerSecond;
	
	PowerRate(double cps) {
		cyclesPerSecond = cps;
	}
}
