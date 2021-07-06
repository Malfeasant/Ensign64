package us.malfeasant.ensign64.config;

public enum Crystal {
	NTSC(11250000, 11), PAL(17734475, 18);
	Crystal (int c, int s) {
		cycles = c;
		seconds = s;
	}
	public final int cycles;
	public final int seconds;
}
