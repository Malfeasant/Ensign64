package us.malfeasant.ensign64.config;

/**
 * The Commodore's cpu clock (which was the pixel clock divided by 8) was derived from a color
 * subcarrier oscillator which is different depending on which TV standard it was intended for.
 * The numbers below are correct, though the representation is somewhat arbitrary.
 * (is this much precision even worthwhile?)
 */
public enum Oscillator {
	NTSC(11/9e7), PAL(9/7.09379e7);
	
	public final double pixelPeriod;
	
	Oscillator(double p) {
		pixelPeriod = p;
	}
}
