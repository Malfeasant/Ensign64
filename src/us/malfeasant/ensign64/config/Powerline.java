package us.malfeasant.ensign64.config;

/**
 * The CIA chips used the ac powerline frequency to drive their realtime clocks.
 */
public enum Powerline {
	NA(1.0/60), EU(1.0/50);
	
	public final double period;
	
	Powerline(double p) {
		period = p;
	}
}
