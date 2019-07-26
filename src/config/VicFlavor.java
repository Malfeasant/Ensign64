package config;

public enum VicFlavor {
	OLDNTSC(64,262,	// cycles, lines
			-1, -1,	// hblank	TODO: not sure where to start, will wait til I start coding the Vic to play around with these
			13, 40),	// vblank
	NEWNTSC(65, 263,
			-1, -1,	
			13, 40),
	PAL(63, 312,
			-1, -1,
			300, 15);
	
	VicFlavor(int cycles, int lines,
			int hbStart, int hbEnd,
			int vbStart, int vbEnd) {
		this.cycles = cycles;
		this.lines = lines;
		this.hbStart = hbStart;
		this.hbEnd = hbEnd;
		this.vbStart = vbStart;
		this.vbEnd = vbEnd;
	}
	public final int cycles;
	public final int lines;
	public final int hbStart;
	public final int hbEnd;
	public final int vbStart;
	public final int vbEnd;
	
	// TODO: More magic numbers such as vblank begin, end, etc
	// TODO: Default oscillator & clock? or maybe that doesn't belong here...
}
