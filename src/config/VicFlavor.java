package config;

public enum VicFlavor {
	OLDNTSC(64,262), NEWNTSC(65, 263), PAL(63, 312);
	
	VicFlavor(int cycles, int lines) {
		this.cycles = cycles;
		this.lines = lines;
	}
	public final int lines;
	public final int cycles;
	// TODO: More magic numbers such as vblank begin, end, etc
	// TODO: Default oscillator & clock? or maybe that doesn't belong here...
}
