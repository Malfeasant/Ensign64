package us.malfeasant.ensign64.config;

/**
 * Magic numbers used for simulating realtime of near 1 MHz- clock cycles per (1) second are ugly repeating
 * decimals, but storing them as x cycles per y seconds allows exact precision in finite space.
 * 
 * This represents the crystal oscillator in the machine, which was tied to the target video system-
 * since it's part of the machine it wouldn't make too much sense to simulate a mismatch, but it
 * could happen in a real machine if the crystal was replaced, for example- the CPU would run
 * slightly fast or slow, and most likely video wouldn't work properly, if at all.
 * 
 * In a real machine, the crystal generates 4x the colorburst frequency- 14.318MHz for NTSC, which is
 * then divided by 14, or 17.734475 for PAL, which is divided by 18, to reach the target ~1MHz.  A separate
 * oscillator actually drives the chips, it generates ~8MHz, which is then divided by 8 in the video chip.
 * A PLL keeps these in sync.  There's no benefit to simulating all that, so we go straight to ~1MHz. 
 * 
 * @author Malfeasant
 */
public enum Crystal {
	NTSC(11250000, 11), PAL(17734475, 18);
	Crystal (int c, int s) {
		cycles = c;
		seconds = s;
	}
	public final int cycles;
	public final int seconds;
}
