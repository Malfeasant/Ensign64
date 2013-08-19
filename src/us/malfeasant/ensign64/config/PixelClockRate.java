package us.malfeasant.ensign64.config;

/**
 * The Commodore's cpu clock (which was the pixel clock divided by 8) was derived from a color
 * subcarrier oscillator which is different depending on which TV standard it was intended for.
 * The numbers below are correct, though the representation is somewhat arbitrary.
 * (is this much precision even worthwhile?)
 */
public enum PixelClockRate {
	NTSC(9/11e-7), PAL(7.09379/9e-7);
	
	public final double cyclesPerSecond;
	
	PixelClockRate(double cps) {
		cyclesPerSecond = cps;
	}
}
